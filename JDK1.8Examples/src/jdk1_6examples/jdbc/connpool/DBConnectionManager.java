package jdk1_6examples.jdbc.connpool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Copyright (c) 1998 by Gefion software.
 *
 * Permission to use, copy, and distribute this software for
 * NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 */
/**
 * This class is a Singleton that provides access to one or many
 * connection pools defined in a Property file. A client gets
 * access to the single instance through the static getInstance()
 * method and can then check-out and check-in connections from a pool.
 * When the client shuts down it should call the release() method
 * to close all open connections and do other clean up.
 */
public class DBConnectionManager {
  /** This class LOGGER */
  private static final Logger LOGGER =
    Logger.getLogger(DBConnectionManager.class.getName());


  private static DBConnectionManager instance;       // The single instance
  private static int clients;
  private final Vector<Driver> drivers = new Vector<Driver>();
  private final Hashtable<String, DBConnectionPool> pools =
      new Hashtable<String, DBConnectionPool>();

  /**
   * Returns the single instance, creating one if it's the
   * first time this method is called.
   *
   * @return DBConnectionManager The single instance.
   */
  static synchronized public DBConnectionManager getInstance() {
    if (instance == null) {
      instance = new DBConnectionManager();
    }
    clients++;
    return instance;
  }

  /**
   * A private constructor since this is a Singleton
   */
  private DBConnectionManager() {
    init();
  }

  /**
   * Returns a connection to the named pool.
   *
   * @param name The pool name as defined in the properties file
   * @param con The Connection
   */
  public void freeConnection(final String name, final Connection con) {
    final DBConnectionPool pool = pools.get(name);

    if (pool != null) {
      pool.freeConnection(con);
    }
  }

  /**
   * Returns an open connection. If no one is available, and the max
   * number of connections has not been reached, a new connection is
   * created.
   *
   * @param name The pool name as defined in the properties file
   * @return Connection The connection or null
   */
  public Connection getConnection(final String name) {
    final DBConnectionPool pool = pools.get(name);

    if (pool != null) {
      return pool.getConnection();
    }
    return null;
  }

  /**
   * Returns an open connection. If no one is available, and the max
   * number of connections has not been reached, a new connection is
   * created. If the max number has been reached, waits until one
   * is available or the specified time has elapsed.
   *
   * @param name The pool name as defined in the properties file
   * @param time The number of milliseconds to wait
   * @return Connection The connection or null
   */
  public Connection getConnection(String name, long time) {
    final DBConnectionPool pool = pools.get(name);

    if (pool != null) {
      return pool.getConnection(time);
    }
    return null;
  }

  /**
   * Closes all open connections and deregisters all drivers.
   */
  public synchronized void release() {
    // Wait until called by the last client
    if (--clients != 0) {
      return;
    }

    final Enumeration<DBConnectionPool> allPools = pools.elements();

    while (allPools.hasMoreElements()) {
      final DBConnectionPool pool = allPools.nextElement();
      pool.release();
    }

    final Enumeration<Driver> allDrivers = drivers.elements();

    while (allDrivers.hasMoreElements()) {
      final Driver driver = allDrivers.nextElement();

      try {
        DriverManager.deregisterDriver(driver);
        log("Deregistered JDBC driver " + driver.getClass().getName());
        
      } catch (SQLException e) {
        log(e, "Can't deregister JDBC driver: " + driver.getClass().getName());
      }
    }
  }

  /**
   * Creates instances of DBConnectionPool based on the properties.
   * A DBConnectionPool can be defined with the following properties:
   * <PRE>
   * &lt;poolname&gt;.url         The JDBC URL for the database
   * &lt;poolname&gt;.user        A database user (optional)
   * &lt;poolname&gt;.password    A database user password (if user specified)
   * &lt;poolname&gt;.maxconn     The maximal number of connections (optional)
   * </PRE>
   *
   * @param props The connection pool properties
   */
  private void createPools(final Properties props) {
    final Enumeration propNames = props.propertyNames();

    while (propNames.hasMoreElements()) {
      final String name = (String) propNames.nextElement();

      if (name.endsWith(".url")) {
        final String poolName = name.substring(0, name.lastIndexOf("."));
        final String url = props.getProperty(poolName + ".url");

        if (url == null) {
          log("No URL specified for " + poolName);
          continue;
        }

        final String user = props.getProperty(poolName + ".user");
        final String password = props.getProperty(poolName + ".password");
        final String maxconn = props.getProperty(poolName + ".maxconn", "0");
        int max;

        try {
          max = Integer.valueOf(maxconn).intValue();

        } catch (NumberFormatException e) {
          log("Invalid maxconn value " + maxconn + " for " + poolName);
          max = 0;
        }

        final DBConnectionPool pool =
            new DBConnectionPool(poolName, url, user, password, max);
        pools.put(poolName, pool);
        log("Initialized pool " + poolName);
      }
    }
  }

  /**
   * Loads properties and initializes the instance with its values.
   */
  private void init() {
    final InputStream is = getClass().getResourceAsStream(
        "/jdk1_6examples/jdbc/connpool/dbpool.properties");
    final Properties dbProps = new Properties();

    try {
      dbProps.load(is);

    } catch (Exception e) {
      System.err.println("Can't read the properties file. " +
                         "Make sure db.properties is in the CLASSPATH");
      return;
    }

//    final String logFile =
//        dbProps.getProperty("logfile", "DBConnectionManager.log");
//
//    try {
//      log = new PrintWriter(new FileWriter(logFile, true), true);
//
//    } catch (IOException e) {
//      System.err.println("Can't open the log file: " + logFile);
//      log = new PrintWriter(System.err);
//    }
    
    loadDrivers(dbProps);
    createPools(dbProps);
  }

  /**
   * Loads and registers all JDBC drivers. This is done by the
   * DBConnectionManager, as opposed to the DBConnectionPool,
   * since many pools may share the same driver.
   *
   * @param props The connection pool properties
   */
  private void loadDrivers(final Properties props) {
    final String driverClasses = props.getProperty("drivers");
    final StringTokenizer st = new StringTokenizer(driverClasses);

    while (st.hasMoreElements()) {
      final String driverClassName = st.nextToken().trim();

      try {
        final Driver driver =
            (Driver) Class.forName(driverClassName).newInstance();
        DriverManager.registerDriver(driver);
        drivers.addElement(driver);
        log("Registered JDBC driver " + driverClassName);
        
      } catch (Exception e) {
        log("Can't register JDBC driver: " +
            driverClassName + ", Exception: " + e);
      }
    }
  }

  /**
   * Writes a message to the log file.
   */
  private void log(final String msg) {
    if(LOGGER.isLoggable(Level.INFO)){
      LOGGER.log(
          Level.INFO,
          msg);
    }
  }

  /**
   * Writes a message with an Exception to the log file.
   */
  private void log(final Throwable e, final String msg) {
    if(LOGGER.isLoggable(Level.SEVERE)){
      LOGGER.log(
          Level.SEVERE,
          msg,
          e);
    }
  }

  /**
   * This inner class represents a connection pool. It creates new
   * connections on demand, up to a max number if specified.
   * It also makes sure a connection is still open before it is
   * returned to a client.
   */
  class DBConnectionPool {
    private final Vector<Connection> freeConnections = new Vector<Connection>();
    private int checkedOut;
    private int maxConn;
    private String name;
    private String password;
    private String URL;
    private String user;

    /**
     * Creates new connection pool.
     *
     * @param name The pool name
     * @param URL The JDBC URL for the database
     * @param user The database user, or null
     * @param password The database user password, or null
     * @param maxConn The maximal number of connections, or 0
     *   for no limit
     */
    public DBConnectionPool(
        final String name,
        final String URL,
        final String user,
        final String password,
        final int maxConn) {

      this.name = name;
      this.URL = URL;
      this.user = user;
      this.password = password;
      this.maxConn = maxConn;
    }

    /**
     * Checks in a connection to the pool. Notify other Threads that
     * may be waiting for a connection.
     *
     * @param con The connection to check in
     */
    public synchronized void freeConnection(final Connection con) {
      if(con != null){
        // Put the connection at the end of the Vector
        freeConnections.addElement(con);
        checkedOut--;
        notifyAll();
        
      }else{
        log("The freed connection from "+this.name+" is null so nothing is done...");
      }
    }

    /**
     * Checks out a connection from the pool. If no free connection
     * is available, a new connection is created unless the max
     * number of connections has been reached. If a free connection
     * has been closed by the database, it's removed from the pool
     * and this method is called again recursively.
     */
    public synchronized Connection getConnection() {
      Connection con = null;

      if (freeConnections.size() > 0) {
        // Pick the first Connection in the Vector
        // to get round-robin usage
        con = freeConnections.firstElement();
        freeConnections.removeElementAt(0);

        log("Re-using an already created connection from " + name);

        try {
          if (con != null && con.isClosed()) {
            log("Removed bad connection from " + name);
            // Try again recursively
            con = getConnection();
          }else{
            log("The connection obtained from " + name+" is null. Creating a new one ...");
            // Try again recursively
            con = getConnection();
          }

        } catch (SQLException e) {
          log("Removed bad connection from " + name);
          // Try again recursively
          con = getConnection();
        }

      } else if (maxConn == 0 || checkedOut < maxConn) {
        con = newConnection();
      }

      if (con != null) {
        checkedOut++;
      }

      return con;
    }

    /**
     * Checks out a connection from the pool. If no free connection
     * is available, a new connection is created unless the max
     * number of connections has been reached. If a free connection
     * has been closed by the database, it's removed from the pool
     * and this method is called again recursively.
     * <P>
     * If no connection is available and the max number has been
     * reached, this method waits the specified time for one to be
     * checked in.
     *
     * @param timeout The timeout value in milliseconds
     */
    public synchronized Connection getConnection(final long timeout) {
      final long startTime = new Date().getTime();
      Connection con;

      while ((con = getConnection()) == null) {
        try {
          wait(timeout);

        } catch (InterruptedException e) {
        }
        if ((new Date().getTime() - startTime) >= timeout) {
          // Timeout has expired
          return null;
        }
      }
      return con;
    }

    /**
     * Closes all available connections.
     */
    public synchronized void release() {
      final Enumeration<Connection> allConnections = freeConnections.elements();

      while (allConnections.hasMoreElements()) {
        final Connection con = allConnections.nextElement();

        try {
          con.close();
          log("Closed connection for pool " + name);
          
        } catch (SQLException e) {
          log(e, "Can't close connection for pool " + name);
        }
      }
      freeConnections.removeAllElements();
    }

    /**
     * Creates a new connection, using a userid and password
     * if specified.
     */
    private Connection newConnection() {
      Connection con = null;

      try {
        if (user == null) {
          con = DriverManager.getConnection(URL);
        } else {
          con = DriverManager.getConnection(URL, user, password);
        }
        
        log("Created a new connection in pool " + name);

      } catch (SQLException e) {
        log(e, "Can't create a new connection for " + URL);
        return null;
      }
      return con;
    }
  }
}