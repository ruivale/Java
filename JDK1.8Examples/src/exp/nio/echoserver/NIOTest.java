package exp.nio.echoserver;


/*******************************************************************************************
    NIOTest.java
        A Swing based tester for NIO based sockets
        author PKWooster, Oct 2003 GPL license
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// client class that acts as a simple terminal
public class NIOTest
    extends JFrame implements NMReceiver {
  // swing GUI components
  JTextField userText = new JTextField(40); // input text
  JTextArea log = new JTextArea(24, 40); // logging window
  JTextField statusText = new JTextField(40); // status
  JPanel outPanel = new JPanel();
  JScrollPane logScroll = new JScrollPane(log);

  JMenuBar menuBar = new JMenuBar();
  JMenuItem startItem = new JMenuItem("Start");
  JMenuItem hostItem = new JMenuItem("Host");
  JMenuItem discItem = new JMenuItem("Request disconnect");
  JMenuItem sendsItem = new JMenuItem("send count");
  JMenuItem readItem = new JMenuItem("Read off");
  JMenuItem debugItem = new JMenuItem("Debug level");
  JMenuItem aboutItem = new JMenuItem("About");
  JMenuItem exitItem = new JMenuItem("Exit");
  JMenu fileMenu = new JMenu("File");
  JMenu helpMenu = new JMenu("Help");
  public static int debugLevel = 1;

  Container cp;

  // teminal stuff
  NIOController controller;
  NIOSocket remote; // NIO support
  String address = "127.0.0.1"; // default host is self
  int port = 5050;
  int sends = 1;
  boolean readOn = true;
  int state = NIOControllable.CLOSED;
  //----------------------------------------------------------------
  // only constructor is default
  NIOTest() {
    controller = new NIOController(); // runs the NIO select
    controller.start(true); // start NIO select as a separate thread

    // build a simple GUI
    buildMenu();
    cp = getContentPane();
    log.setEditable(false);
    outPanel.add(new JLabel("Send: "));
    outPanel.add(userText);

    // enter on userText causes transmit
    userText.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        userTyped(evt);
      }
    });
    cp.setLayout(new BorderLayout());
    cp.add(outPanel, BorderLayout.NORTH);
    cp.add(logScroll, BorderLayout.CENTER);
    cp.add(statusText, BorderLayout.SOUTH);
    setStatus("Closed");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        mnuExit();
      }
    });

    // put some documentaion in log window
    toLog("!! use start menu to start");
    toLog("!! host is " + address + ":" + port);
    pack();
  }

  // user pressed enter in the user text field, we try to send the text
  void userTyped(ActionEvent evt) {
    String txt = evt.getActionCommand();
    userText.setText(""); // don't send it twice
    toLog(txt, true);
    System.out.println("From user:" + txt);

    // put the text on the remote transmit queue
    if (state == NIOControllable.OPENED) {
      for (int i = 0; i < sends; i++) {
        remote.send(txt);
      }
    }
  }

  // methods to put text in logging window, toLog(text,true) if it came from user
  void toLog(String txt) {
    toLog(txt, false);
  }

  void toLog(String txt, boolean user) {
    log.append((user ? "> " : "< ") + txt + "\n");
    log.setCaretPosition(log.getDocument().getLength()); // force last line visible
  }


  // build the standard menu bar
  void buildMenu() {
    JMenuItem item;

    // file menu
    startItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuStart();
      }
    });
    fileMenu.add(startItem);

    hostItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuHost();
      }
    });
    fileMenu.add(hostItem);

    discItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuDisc();
      }
    });
    fileMenu.add(discItem);

    sendsItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuSends();
      }
    });
    fileMenu.add(sendsItem);

    readItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuRead();
      }
    });
    fileMenu.add(readItem);

    debugItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuDebug();
      }
    });
    fileMenu.add(debugItem);

    exitItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuExit();
      }
    });
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);

    helpMenu.add(aboutItem);
    aboutItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        mnuAbout();
      }
    });

    menuBar.add(helpMenu);

    setJMenuBar(menuBar);
  }


  // start and stop menu
  void mnuStart() {
    switch (state) {
    case NIOControllable.CLOSED:
      remote = new NIOSocket(this, address, port, controller);
      if (remote.start()) {
        setSockState(NIOControllable.OPENING);
      } else {
        remote = null;
      }
      break;
    case NIOControllable.OPENED:
    case NIOControllable.OPENING:
    case NIOControllable.CLOSING:
      if (remote != null) {
        remote.close(); // shut it down
      }
      break;
    }
  }

  // prompt user for host in form address:port
  // default is 127.0.0.1:5050
  void mnuHost() {
    String txt = JOptionPane.showInputDialog("Enter host address and port");
    if (txt == null) {
      return;
    }

    int n = txt.indexOf(':');
    if (n == 0) {
      address = "127.0.0.1";
      port = toInt(txt.substring(1), 5050);
    } else {
      if (n < 0) {
        address = txt;
        port = 5050;
      } else {
        address = txt.substring(0, n);
        port = toInt(txt.substring(n + 1), 5050);
      }
    }
    toLog("!! host set to " + address + ":" + port);
  }

  void mnuDisc() {
    if (state == NIOControllable.OPENED) {
      remote.requestClose(); // request disconnect
      setSockState(NIOControllable.CLOSING);
    }
  }

  void mnuSends() {
    String txt = JOptionPane.showInputDialog(
        "Enter number of times to send data");
    if (txt == null) {
      return;
    }
    sends = toInt(txt, 1);
    if (sends < 1) {
      sends = 1;
    }
  }

  void mnuRead() {
    readOn = !readOn;
    readItem.setText("Turn read " + (readOn ? "off" : "on"));
  }

  void mnuDebug() {
    String txt = JOptionPane.showInputDialog("Enter debug level");
    if (txt == null) {
      return;
    }
    debugLevel = toInt(txt, 0);
  }

  void mnuAbout() {
    new AboutDialog(this).show();
  }

  // exit menu
  void mnuExit() {
    if (remote != null) {
      remote.close(); // close socket
    }
    System.exit(0);
  }

  private void setSockState(int s) {
    if (state != s) {
      state = s;
      switch (state) {
      case NIOControllable.OPENED:
        startItem.setText("Close");
        setStatus("Connected to " + address);
        break;
      case NIOControllable.CLOSED:
        startItem.setText("Start");
        setStatus("Disconnected");
        break;
      case NIOControllable.OPENING:
        setStatus("Connecting to " + address);
        startItem.setText("Abort");
        break;

      case NIOControllable.CLOSING:
        setStatus("Disconnecting from " + address);
        startItem.setText("Abort");
        break;
      }
    }
  }

  void setStatus(String st) {
    statusText.setText(st);
  }

  // called when the run method in NMessage is executed in the AWT event dispatch thread
  // looks a bit like an action event
  public void runNMessage(NMessage nm) {
    switch (nm.type) {
    case NMessage.OPEN:
      setSockState(NIOControllable.OPENED);
      break;

    case NMessage.CLOSE:
      setSockState(NIOControllable.CLOSED);
      break;

    case NMessage.DATA:
      toLog((String) nm.data); // just send the remote data to the log
      break;
    }
  }

  public void fromRemote(String text) {
    postNMessage(text, NMessage.DATA);
  }

  public void fromRemote(boolean open) {
    postNMessage(null, open ? NMessage.OPEN : NMessage.CLOSE);
  }

  // uses invokeLater to put this message on the system event queue so Swing will run it
  public void postNMessage(String dat, int type) {
    NMessage nm = new NMessage(this, dat, type);
    SwingUtilities.invokeLater(nm); // the message implements Runnable, so we can use invokeLater
  }

  //-------------------------------------------------------------------------------------
  // utility functions

  // start up
  static public void main(String[] args) {
    try {
      new NIOTest().show();
    } catch (OutOfMemoryError e) {
      e.printStackTrace();
    }
  }

  // get an integer or the default value in er if not convertable
  public static int toInt(String s, int er) {
    int i;

    try {
      i = new Integer(s).intValue();
    } catch (NumberFormatException exc) {
      i = er;
    }
    return i;
  }

  public static void dout(int level, String text) {
    if (level >= debugLevel) {
      System.out.println(level + ": " + text);
    }
  }

  //============================================================================================
  // inner classes

  //----------------------------------------------------------------------------
  // about dialog
  class AboutDialog
      extends JDialog {
    Container contentPane;
    JTextField text = new JTextField("Simple character client");

    AboutDialog(Frame f) {
      super(f, "About Client", true);
      contentPane = getContentPane();
      contentPane.add(text);
      pack();
    }
  }
}
