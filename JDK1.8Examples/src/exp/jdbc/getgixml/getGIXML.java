package exp.jdbc.getgixml;

import java.sql.*;
import java.util.ArrayList;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) </p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
//public class GetGIXML {

  /**
    2006-03-30 (Rui Vale)
       - added some trim() calls over the String values.

    2006-03-20 (Rui Vale)
       - added the abbrname node to the station's XML nodes. This property defines the
         station's abbreviated name.

    2006-03-14 (Rui Vale)
       - added the textid property into the group/station/zone/equip nodes. It's used
         to deal with the GTC STV GI events.

    2006-03-10 (Rui Vale)
       - searching for the specific/generic config parameters (Eth$/JC<*>) optimized.
         Now the values can be searched inside the string and not just at the
         beginning. *|{Eth$ or JC<*>}|*

    2006-03-08 (Rui Vale)
        - added the symbolSTVGroupEnable to the groups xml nodes.

    2006-02-17 (Rui Vale)
        - added the enable XML node to the equips.

    2006-02-13 (Rui Vale)
        - change the specific config equip parsing. JC<javaclass>|[xpto|]. For now
          the JC< always come first but can be anywere ...

          @todo String tokenizer it ^

    2006-01-03 (Rui Vale)
        - added the "if(..)" while parsing the equips list. In some cases an
          equip may belong to a zone which isn't from the same station.

    2005-12-14 (Rui Vale)
        - added the stationtype element in a station node. This value is obtained
          from the FT_STATIONSTV.FN_TYPE field which is a FK to
          T_STATIONSTYPE.FN_ID;
        - added the state element in a station node. This value is obtained
          from the FT_STATIONSTV.FN_STATUS field which is a FK to
          T_STATIONSTATE.FN_ID;
        - added the getBoolean(ResultSet, int) method.

    2005-10-28 (Rui Vale)
        - changed t_images to t_ig_images;

    2005-10-26 (Rui Vale)
        - changed the way the groups properties are obtained (getGroups(..)).
          The t_ig_groups.fn_id_stationsymbol is being used to obtain the correct
          values for the stations XML building. The t_ig_groups.fn_id_symbol is
          used to save the groups map image path. A new var., GROUP_SYMB, has been
          added to the Group class to hold this value;

    2005-09-20 (Rui Vale)
        - added the specific config to the NVRs nodes. It allow the GI to
          initialize a class responsable for the remote rec from a NVR.
        - added the equip. comm settings to the NVRs nodes;

    2005-07-19 (Rui Vale)
        - in the getStations() method, instead of name ordering, the order is made
          according to the station's position in the t_stationsingroups;

    2005-05-04 (Luis Jeremias)
        - return certain equipment parameters according to their existence in
          t_ig_equipmenttypes and t_ig_equipmentclasses.
          The first takes prevalence over the second;

    2005-04-28 (Luis Jeremias)
        - correction on query of getEquips - change in comparison path;

    2005-04-12 (Rui Vale)
        - add ".toUpperCase()" in some String comparations;

    2005-03-08 (Rui Vale)
        - added two more variables in the Equip class. They're used to check, in
          the equip generic config, for the "ejectability" and records
          configuration mecanism in the Videorecorders. If available, it adds
          the "isEjectable" and "hasRecManag" xml properties;

    2005-02-04 (Rui Vale)
        - added the id property to the nodes. The id was an attribute. Now it's an
          attribute and a sub-node;

    2005-02-02 (Rui Vale)
        - added the getNetworkBackImage() method. This method obtains, if any, the
          t_project.fn_map path from the t_images table, related with it. If the
          result is different from "" or null the file path is added, as an
          attribute, to the XML root node (<SDM backimage="file_path">);

    2005-02-02 (Rui Vale)
        - added the x, y and color to the group list (the x and y were added to
          the T_IG_GROUPS table);

    2005-01-11 (Rui Vale)
        - the Equip class has two more vars (EQUIP_GENCONFIG and isFixed). The
          isFixed var is used to create a XML property if the equip in question
          is a camera, indicating, as the name ilustrates, if the camera is a
          fixed one or not;

    2004-01-26 (Rui Vale)
        - the equips that are not enable must be sent to the XML list with the
          state property equal to 0. Added the EQUIP_STATE var in the inner class
          Equip. Also changed the way the state property is build in the final
          XML. The creation of the Equip classes;

    2003-12-04 (Rui Vale)
        - in stations with more than one zone the equips from one zone also
          appear in the other and "vice-versa". This correction resets the last
          one. The old "if" is "back in business" but there's also a test case
          for the station equips that must appear in the zone's XML. Added the
          NULL_VALUE static final variable. This variable is used to check if a
          certain value received from the SQL query is null or not. This way the
          properties with NULL_VALUE are not inserted in the final XML. This
          variable is also used to determine which equips from a station, those
          ones that have no zone, must be inserted in a zone's XML;

    2003-11-25 (Rui Vale)
        - correct the && in the getGIXML method while comparing the equip.zone_id
          with the id of the zone in question and the equip.station_id with the
          station i id in question. It must be a ||;

   */
  class Group {
    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    public String GROUP_COLOR;

    /** .. */
    public String GROUP_TEXT_ID;

    /** .. */
    public String GROUP_NAME;

    /** .. */
    public String GROUP_MAP;

    /** .. */
    public String GROUP_SYMB_ENABLE;

    /** .. */
    public String GROUP_GEN_SYMB_ALARM;

    /** .. */
    public String GROUP_GEN_SYMB_DISABLE;

    /** .. */
    public String GROUP_GEN_SYMB_ENABLE;

    /** .. */
    public String GROUP_GEN_SYMB_OTHER;

    /** .. */
    public String GROUP_GEN_SYMB_SELECT;

    /** .. */
    public int GROUP_ID;

    /** .. */
    public int GROUP_POSX;

    /** .. */
    public int GROUP_POSY;

    /** .. */
    public int GROUP_ZONESYMBOL;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new Group object.
     *
     * @param id Insert doc ...
     * @param name Insert doc ...
     * @param zonesymbol Insert doc ...
     * @param posx Insert doc ...
     * @param posy Insert doc ...
     * @param symbol Insert doc ...
     * @param enable Insert doc ...
     * @param disable Insert doc ...
     * @param alarm Insert doc ...
     * @param select Insert doc ...
     * @param other Insert doc ...
     * @param color Insert doc ...
     */
    public Group(
        int id,
        String textid,
        String name,
        int zonesymbol,
        int posx,
        int posy,
        String map,
        String symbol,
        String enable,
        String disable,
        String alarm,
        String select,
        String other,
        String color) {

      GROUP_ID             = id;
      GROUP_TEXT_ID        = textid;
      GROUP_NAME           = name;
      GROUP_ZONESYMBOL     = zonesymbol;
      GROUP_POSX           = posx;
      GROUP_POSY           = posy;
      GROUP_MAP             = map;
      GROUP_SYMB_ENABLE    = symbol;
      GROUP_GEN_SYMB_ENABLE    = enable;
      GROUP_GEN_SYMB_DISABLE   = disable;
      GROUP_GEN_SYMB_ALARM     = alarm;
      GROUP_GEN_SYMB_SELECT    = select;
      GROUP_GEN_SYMB_OTHER     = other;
      GROUP_COLOR          = color;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
  class StationsInGroup {
    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    public int idGroup;

    /** .. */
    public int idStation;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new StationsInGroup object.
     *
     * @param group Insert doc ...
     * @param station Insert doc ...
     */
    public StationsInGroup(
      int group,
      int station) {
      idGroup     = group;
      idStation   = station;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
  class Station {
    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    String STATION_TEXT_ID;

    /** .. */
    String STATION_COLOR;

    /** .. */
    String STATION_NAME;

    /** .. */
    String STATION_ABBR_NAME;

    /** .. */
    String STATION_SYMBOLS;

    /** .. */
    String STATION_SYMB_ALARM;

    /** .. */
    String STATION_SYMB_DISABLE;

    /** .. */
    String STATION_SYMB_ENABLE;

    /** .. */
    String STATION_SYMB_OTHER;

    /** .. */
    String STATION_SYMB_SELECT;

    /** .. */
    int STATION_ENABLED;

    /** .. */
    int STATION_EQUIPS;

    /** .. */
    int STATION_GROUP;

    /** .. */
    int STATION_ID;

    /** .. */
    int STATION_STATE;

    /** .. */
    int STATION_TYPE;

    /** .. */
    int STATION_POSX;

    /** .. */
    int STATION_POSY;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new Station object.
     *
     * @param id Insert doc ...
     * @param group Insert doc ...
     * @param posx Insert doc ...
     * @param posy Insert doc ...
     * @param name Insert doc ...
     * @param symbols Insert doc ...
     * @param color Insert doc ...
     * @param equips Insert doc ...
     * @param enabled Insert doc ...
     * @param state  Insert doc ...
     * @param enable Insert doc ...
     * @param disable Insert doc ...
     * @param alarm Insert doc ...
     * @param select Insert doc ...
     * @param other Insert doc ...
     */
    public Station(
        int id,
        String textid,
        int group,
        int posx,
        int posy,
        String name,
        String abbrname,
        String symbols,
        String color,
        int equips,
        int enabled,
        int state,
        int type,
        String enable,
        String disable,
        String alarm,
        String select,
        String other) {

      STATION_ID             = id;
      STATION_TEXT_ID        = textid;
      STATION_GROUP          = group;
      STATION_POSX           = posx;
      STATION_POSY           = posy;
      STATION_NAME           = name;
      STATION_ABBR_NAME      = abbrname;
      STATION_SYMBOLS        = symbols;
      STATION_COLOR          = color;
      STATION_EQUIPS         = equips;
      STATION_ENABLED        = enabled;
      STATION_TYPE           = type;
      STATION_STATE          = state;
      STATION_SYMB_ENABLE    = enable;
      STATION_SYMB_DISABLE   = disable;
      STATION_SYMB_ALARM     = alarm;
      STATION_SYMB_SELECT    = select;
      STATION_SYMB_OTHER     = other;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
  class Zone {
    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    String ZONE_TEXT_ID;

    /** .. */
    String ZONE_COLOR;

    /** .. */
    String ZONE_NAME;

    /** .. */
    String ZONE_SYMBOL;

    /** .. */
    String ZONE_SYMB_ALARM;

    /** .. */
    String ZONE_SYMB_DISABLE;

    /** .. */
    String ZONE_SYMB_ENABLE;

    /** .. */
    String ZONE_SYMB_OTHER;

    /** .. */
    String ZONE_SYMB_SELECT;

    /** .. */
    int ZONE_ID;

    /** .. */
    int ZONE_NSYMB;

    /** .. */
    int ZONE_STATION;

    /** .. */
    int ZONE_X;

    /** .. */
    int ZONE_Y;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new Zone object.
     *
     * @param id Insert doc ...
     * @param station Insert doc ...
     * @param name Insert doc ...
     * @param symbol Insert doc ...
     * @param nsymb Insert doc ...
     * @param x Insert doc ...
     * @param y Insert doc ...
     * @param color Insert doc ...
     * @param enable Insert doc ...
     * @param disable Insert doc ...
     * @param alarm Insert doc ...
     * @param select Insert doc ...
     * @param other Insert doc ...
     */
    public Zone(
        int id,
        String textid,
        int station,
        String name,
        String symbol,
        int nsymb,
        int x,
        int y,
        String color,
        String enable,
        String disable,
        String alarm,
        String select,
        String other) {

      ZONE_ID             = id;
      ZONE_TEXT_ID        = textid;
      ZONE_STATION        = station;
      ZONE_NAME           = name;
      ZONE_SYMBOL         = symbol;
      ZONE_NSYMB          = nsymb;
      ZONE_X              = x;
      ZONE_Y              = y;
      ZONE_COLOR          = color;
      ZONE_SYMB_ENABLE    = enable;
      ZONE_SYMB_DISABLE   = disable;
      ZONE_SYMB_ALARM     = alarm;
      ZONE_SYMB_SELECT    = select;
      ZONE_SYMB_OTHER     = other;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
  class Equip {
    //~ Static fields/initializers ///////////////////////////////////////////////

    /** .. */
    private static final String PTZF = "PTZF";

    /** .. */
    private static final String CAMERA = "Camera";

    /** .. */
    private static final String VIDEORECORDER = "Videorecorder";

    /** Video cassete recorder generic config */
    protected static final String VCR_TYPE = "TYV";

    /** Digital video recorder generic config */
    protected static final String DVR_TYPE = "TYD";

    /** Network video recorder generic config */
    protected static final String NVR_TYPE = "TYN";

    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    String EQUIP_CLASS;

    /** .. */
    String EQUIP_TEXT_ID;

    /** .. */
    String EQUIP_COMMSETTINGS;

    /** .. */
    String EQUIP_GENCONFIG;

    /** .. */
    String EQUIP_NAME;

    /** .. */
    String EQUIP_SPECIFICCONFIG;

    /** .. */
    String EQUIP_STATE;

    /** .. */
    String EQUIP_ENABLE;

    /** .. */
    String EQUIP_SYMB_ALARM;

    /** .. */
    String EQUIP_SYMB_DISABLE;

    /** .. */
    String EQUIP_SYMB_ENABLE;

    /** .. */
    String EQUIP_SYMB_OTHER;

    /** .. */
    String EQUIP_SYMB_SELECT;

    /** .. */
    String EQUIP_TYPE;

    /** .. */
    String hasRecManag = null;

    /** .. */
    String isEjectable = null;

    /** .. */
    String isFixed = null;

    /** .. */
    int EQUIP_AIS;

    /** .. */
    int EQUIP_ID;

    /** .. */
    int EQUIP_POSX;

    /** .. */
    int EQUIP_POSY;

    /** .. */
    int EQUIP_SOURCE;

    /** .. */
    int EQUIP_STATION;

    /** .. */
    int EQUIP_TARGET;

    /** .. */
    int EQUIP_ZONE;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new Equip object.
     *
     * @param id Insert doc ...
     * @param zone Insert doc ...
     * @param station Insert doc ...
     * @param type Insert doc ...
     * @param sclass Insert doc ...
     * @param name Insert doc ...
     * @param posx Insert doc ...
     * @param posy Insert doc ...
     * @param target Insert doc ...
     * @param source Insert doc ...
     * @param ais Insert doc ...
     * @param enable Insert doc ...
     * @param disable Insert doc ...
     * @param alarm Insert doc ...
     * @param select Insert doc ...
     * @param other Insert doc ...
     * @param state Insert doc ...
     * @param commsettings Insert doc ...
     * @param speconfig Insert doc ...
     * @param genconfig Insert doc ...
     */
    public Equip(
        int id,
        String textid,
        int zone,
        int station,
        String type,
        String sclass,
        String name,
        int posx,
        int posy,
        int target,
        int source,
        int ais,
        String symbol_enable,
        String symbol_disable,
        String symbol_alarm,
        String symbol_select,
        String symbol_other,
        String state,
        String commsettings,
        String speconfig,
        String genconfig,
        String enable) {

      EQUIP_ID             = id;
      EQUIP_TEXT_ID        = textid;
      EQUIP_ZONE           = zone;
      EQUIP_STATION        = station;
      EQUIP_TYPE           = type;
      EQUIP_CLASS          = sclass;
      EQUIP_NAME           = name;
      EQUIP_POSX           = posx;
      EQUIP_POSY           = posy;
      EQUIP_TARGET         = target;
      EQUIP_SOURCE         = source;
      EQUIP_AIS            = ais;
      EQUIP_SYMB_ENABLE    = symbol_enable;
      EQUIP_SYMB_DISABLE   = symbol_disable;
      EQUIP_SYMB_ALARM     = symbol_alarm;
      EQUIP_SYMB_SELECT    = symbol_select;
      EQUIP_SYMB_OTHER     = symbol_other;
      EQUIP_STATE          = state;
      EQUIP_GENCONFIG      = genconfig;
      EQUIP_ENABLE         = enable;

      if((EQUIP_CLASS!=null) && EQUIP_CLASS.equals(CAMERA)) {
        isFixed = isFixedCamera(EQUIP_GENCONFIG);
      }

      if((EQUIP_CLASS!=null) && EQUIP_CLASS.equals(VIDEORECORDER)) {
        isEjectable   = isVideoRecorderEjectable(EQUIP_GENCONFIG);
        hasRecManag   = hasVideoRecorderRecConfig(EQUIP_GENCONFIG);
      }

        // SPECIFIC CONFIG
        // Related contents:
        // *|JC<java_class>|*
        if((hasRecManag!=null && hasRecManag.equals("1")) &&
           (speconfig!=null && !speconfig.equals(""))) {

          try {
            speconfig = speconfig.trim();
            speconfig = speconfig.substring(
                           speconfig.indexOf("JC<"),
                           speconfig.length());

            speconfig = speconfig.substring(
                           3,
                           speconfig.indexOf("|")-1);

            EQUIP_SPECIFICCONFIG = speconfig;
          } catch (Exception ex) {
            EQUIP_SPECIFICCONFIG = "";
          }

        } else {
          EQUIP_SPECIFICCONFIG = "";
        }

        // COMM SETTINGS
        // Related contents:
        // *|Eth$IP;PORT|*
        if((hasRecManag!=null && hasRecManag.equals("1")) &&
           (commsettings!=null && !commsettings.equals(""))) {

          try {
            commsettings = commsettings.trim();
            commsettings = commsettings.substring(
                             commsettings.indexOf("Eth$"),
                             commsettings.length());

            commsettings = commsettings.substring(
                             4,
                             commsettings.indexOf("|"));

            EQUIP_COMMSETTINGS = commsettings;
          } catch (Exception ex) {
            EQUIP_COMMSETTINGS = "";
          }

        } else {
          EQUIP_COMMSETTINGS = "";
        }
    }

    //~ Methods //////////////////////////////////////////////////////////////////

    /**
     * DOCUMENT ME!
     *
     * @param equipGenConfig String
     *
     * @return String
     */
    private static String isFixedCamera(String equipGenConfig) {
      String isFixed = null;

      if(equipGenConfig!=null) {
        equipGenConfig   = equipGenConfig.toUpperCase().trim();
        isFixed          = (equipGenConfig.indexOf(PTZF)>-1)
          ? "0"
          : "1";
      }

      return isFixed;
    }

    /**
     * Search for the VCR_TYPE string in the generic config
     *
     * @param equipGenConfig Insert doc ...
     *
     * @return String
     */
    private static String isVideoRecorderEjectable(String equipGenConfig) {
      String isEjectable = "0";

      if(equipGenConfig!=null) {
        equipGenConfig = equipGenConfig.toUpperCase().trim();

        if(equipGenConfig.indexOf(Equip.VCR_TYPE)>-1) { // A video cassete recorder ...
          isEjectable = "1";
        }
      }

      return isEjectable;
    }

    /**
     * Search for the NVR_TYPE string in the generic config
     *
     * @param equipGenConfig Insert doc ...
     *
     * @return String
     */
    private static String hasVideoRecorderRecConfig(String equipGenConfig) {
      String isRecManag = "0";

      if(equipGenConfig!=null) {
        equipGenConfig = equipGenConfig.toUpperCase().trim();

        if(equipGenConfig.indexOf(Equip.NVR_TYPE)>-1) { // A network video recorder ...
          isRecManag = "1";
        }
      }

      return isRecManag;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
  class Link {
    //~ Instance fields //////////////////////////////////////////////////////////

    /** .. */
    String LINK_PROPERTIES;

    /** .. */
    int LINK_END;

    /** .. */
    int LINK_GROUP;

    /** .. */
    int LINK_START;

    //~ Constructors /////////////////////////////////////////////////////////////

    /**
     * Creates a new Link object.
     *
     * @param start Insert doc ...
     * @param end Insert doc ...
     * @param group Insert doc ...
     * @param properties Insert doc ...
     */
    public Link(
      int    start,
      int    end,
      int    group,
      String properties) {
      LINK_START        = start;
      LINK_END          = end;
      LINK_GROUP        = group;
      LINK_PROPERTIES   = properties;
    }
  }


  /**
   * Insert doc ...
   *
   * @author $author$
   * @version $Revision: 1.14 $
   */
 public  class getGIXML {
    //~ Static fields/initializers ///////////////////////////////////////////////

    /** .. */
    static final int NULL_VALUE = -9999;

    /** .. */
    static StringBuffer xml = new StringBuffer("");

    /** .. */
    static ArrayList groups;

    /** .. */
    static ArrayList stInGroups;

    /** .. */
    static ArrayList stations;

    /** .. */
    static ArrayList zones;

    /** .. */
    static ArrayList equips;

    /** .. */
    static ArrayList links;

    /** .. */
    static ArrayList stShown;

    //~ Methods //////////////////////////////////////////////////////////////////

    /**
     * Insert doc ...
     *
     * @param clob Insert doc ...
     * @param nStation Insert doc ...
     * @param nUser Insert doc ...
     * @param tDtd Insert doc ...
     * @param tCSS Insert doc ...
     * @param nFilterShownStations Insert doc ...
     * @param nShowStation Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    public static void getGIXML(
      //oracle.sql.CLOB clob,
      int             nStation,
      int             nUser,
      String          tDtd,
      String          tCSS,
      int             nFilterShownStations,
      int             nShowStation)
        throws SQLException {

      final String strVideoRecorder = "Videorecorder";
      Connection   conn = null;
      Statement    stmt = null;
      ResultSet    rset = null;
      groups       = new ArrayList();
      stInGroups   = new ArrayList();
      stations     = new ArrayList();
      zones        = new ArrayList();
      equips       = new ArrayList();
      links        = new ArrayList();
      stShown      = new ArrayList();

      Driver d     = new oracle.jdbc.driver.OracleDriver();
      int    nSt;

      if(insideOracle()) {
        conn = DriverManager.getConnection("jdbc:oracle:kprb:");
      } else {
        conn = DriverManager.getConnection(
            "jdbc:oracle:thin:@172.18.56.9:1521:STVROL",
            "inoss_stv",
            "inoss");
      }

      stmt = conn.createStatement();

      getGroups(stmt);
      getStationsInGroups(stmt);
      getStations(
        stmt,
        nUser);
      getZones(stmt);
      getEquips(
        stmt,
        nUser);
      getLinks(stmt);

      if(tDtd==null) {
        tDtd = "file:stv/xml/stv.dtd";
      }

      if(tCSS==null) {
        tCSS = "file:stv/css/stv.css";
      }

      xml.append(
        "<?xml version = \"1.0\" encoding = \"ISO-8859-1\"?><?sdm stylesheet=\"");
      xml.append(tCSS);
      xml.append("\"?><!DOCTYPE SDM SYSTEM \"");
      xml.append(tDtd);

      // Obtaining the network background image. Must be defined in the T_PROJECTS table.
      String strNetworkImage = null;

      try {
        strNetworkImage = getNetworkBackImage(stmt);
      } catch(SQLException e) {
        strNetworkImage = null;
      }

      if((strNetworkImage!=null) && !strNetworkImage.equals("")) {
        xml.append("\"><SDM backimage=\"" + strNetworkImage + "\">");
      } else {
        xml.append("\"><SDM>");
      }

      if(nShowStation==1) {
        nSt = -1;
      } else {
        nSt = nStation; //650cd1defa
      }

      Group   group;
      Station station;
      Equip   equip;
      Link    link;
      Zone    zone;
      boolean ok;
      int     groupsSize   = groups.size();
      int     stationsSize = stations.size();
      int     zonesSize    = zones.size();
      int     equipsSize   = equips.size();
      int     linksSize    = links.size();

      for(int gr = 0; gr<groupsSize; gr++) {
        group = (Group)groups.get(gr);

        if(checkStationsInGroup(
                group.GROUP_ID,
                nStation)) {
          xml.append("<node type=\"group\" id=\"")
             .append(String.valueOf(group.GROUP_ID))
             .append("\">");

          addProperty(
            "id",
            group.GROUP_ID,
            3);
          addProperty(
            "textid",
            group.GROUP_TEXT_ID,
            3);
          addProperty(
            "name",
            group.GROUP_NAME,
            3);
          addProperty(
            "type",
            "group",
            3);
          addProperty(
            "CSSclass",
            "group_class",
            3);
          addProperty(
            "x",
            group.GROUP_POSX,
            4);
          addProperty(
            "y",
            group.GROUP_POSY,
            4);
          addProperty(
            "symbolSTV",
            group.GROUP_MAP,
            3);
          addProperty(
            "symbolSTVGroupEnable",
            group.GROUP_SYMB_ENABLE,
            3);
          addProperty(
            "symbolSTVEnable",
            group.GROUP_GEN_SYMB_ENABLE,
            3);
          addProperty(
            "symbolSTVDisable",
            group.GROUP_GEN_SYMB_DISABLE,
            3);
          addProperty(
            "symbolSTVAlarm",
            group.GROUP_GEN_SYMB_ALARM,
            3);
          addProperty(
            "symbolSTVSelect",
            group.GROUP_GEN_SYMB_SELECT,
            3);
          addProperty(
            "symbolSTVOther",
            group.GROUP_GEN_SYMB_OTHER,
            3);
          addProperty(
            "color",
            group.GROUP_COLOR,
            3);

          for(int st = 0; st<stationsSize; st++) {
            station = (Station)stations.get(st);

            if((station.STATION_GROUP==group.GROUP_ID) &&
                  (station.STATION_ID!=nSt)) {
              if((nFilterShownStations==0) ||
                    (stationShown(station.STATION_ID)==false)) {
                stShown.add(new Integer(station.STATION_ID));
                xml.append("<node type=\"station\" id=\"")
                   .append(String.valueOf(station.STATION_ID))
                   .append("\">");

                addProperty(
                  "id",
                  station.STATION_ID,
                  4);
                addProperty(
                  "textid",
                  station.STATION_TEXT_ID,
                  4);
                addProperty(
                  "type",
                  "station",
                  4);
                addProperty(
                  "name",
                  station.STATION_NAME,
                  4);
                addProperty(
                  "abbrname",
                  station.STATION_ABBR_NAME,
                  4);
                addProperty(
                  "state",
                  station.STATION_STATE,
                  4);
                addProperty(
                  "stationtype",
                  station.STATION_TYPE,
                  4);
                addProperty(
                  "x",
                  station.STATION_POSX,
                  4);
                addProperty(
                  "y",
                  station.STATION_POSY,
                  4);
                addProperty(
                  "color",
                  station.STATION_COLOR,
                  4);
                addProperty(
                  "symbolSTV",
                  station.STATION_SYMBOLS,
                  4);
                addProperty(
                  "symbolSTVEnable",
                  station.STATION_SYMB_ENABLE,
                  4);
                addProperty(
                  "symbolSTVDisable",
                  station.STATION_SYMB_DISABLE,
                  4);
                addProperty(
                  "symbolSTVAlarm",
                  station.STATION_SYMB_ALARM,
                  4);
                addProperty(
                  "symbolSTVSelect",
                  station.STATION_SYMB_SELECT,
                  4);
                addProperty(
                  "symbolSTVOther",
                  station.STATION_SYMB_OTHER,
                  4);

                if(station.STATION_ENABLED>0) {
                  addProperty(
                    "perm",
                    station.STATION_EQUIPS,
                    4);
                } else {
                  addProperty(
                    "perm",
                    "0",
                    4);
                }

                for(int zn = 0; zn<zonesSize; zn++) {
                  zone = (Zone)zones.get(zn);

                  if((zone.ZONE_STATION==station.STATION_ID) &&
                        (zone.ZONE_NSYMB==group.GROUP_ZONESYMBOL)) {
                    xml.append("<node type=\"zone\" id=\"")
                       .append(String.valueOf(zone.ZONE_ID))
                       .append("\">");
                    addProperty(
                      "type",
                      "zone",
                      5);
                    addProperty(
                      "id",
                      zone.ZONE_ID,
                      5);
                    addProperty(
                      "textid",
                      zone.ZONE_TEXT_ID,
                      5);
                    addProperty(
                      "name",
                      zone.ZONE_NAME,
                      5);
                    addProperty(
                      "zoneMap",
                      zone.ZONE_SYMBOL,
                      5);
                    addProperty(
                      "x",
                      zone.ZONE_X,
                      5);
                    addProperty(
                      "y",
                      zone.ZONE_Y,
                      5);
                    addProperty(
                      "color",
                      zone.ZONE_COLOR,
                      5);
                    addProperty(
                      "symbolSTVEnable",
                      zone.ZONE_SYMB_ENABLE,
                      5);
                    addProperty(
                      "symbolSTVDisable",
                      zone.ZONE_SYMB_DISABLE,
                      5);
                    addProperty(
                      "symbolSTVAlarm",
                      zone.ZONE_SYMB_ALARM,
                      5);
                    addProperty(
                      "symbolSTVSelect",
                      zone.ZONE_SYMB_SELECT,
                      5);
                    addProperty(
                      "symbolSTVOther",
                      zone.ZONE_SYMB_OTHER,
                      5);
                    addProperty(
                      "perm",
                      "1",
                      5);

                    for(int eq = 0; eq<equipsSize; eq++) {
                      equip = (Equip)equips.get(eq);

                      // Equips that have this two IDs inserted. Normally zone
                      // equips.
                      if((equip.EQUIP_ZONE==zone.ZONE_ID && equip.EQUIP_STATION==station.STATION_ID) ||
                         (equip.EQUIP_ZONE==NULL_VALUE && equip.EQUIP_STATION==station.STATION_ID) ||
                          equip.EQUIP_ZONE==zone.ZONE_ID) {

                        xml.append("<node type=\"")
                           .append(String.valueOf(equip.EQUIP_TYPE))
                           .append("\" id=\"")
                           .append(String.valueOf(equip.EQUIP_ID))
                           .append("\">");
                        addProperty(
                          "id",
                          equip.EQUIP_ID,
                          6);
                        addProperty(
                          "textid",
                          equip.EQUIP_TEXT_ID,
                          6);
                        addProperty(
                          "type",
                          equip.EQUIP_TYPE,
                          6);
                        addProperty(
                          "enable",
                          equip.EQUIP_ENABLE,
                          6);
                        addProperty(
                          "CSSclass",
                          equip.EQUIP_CLASS,
                          6);
                        addProperty(
                          "name",
                          equip.EQUIP_NAME,
                          6);
                        addProperty(
                          "x",
                          equip.EQUIP_POSX,
                          6);
                        addProperty(
                          "y",
                          equip.EQUIP_POSY,
                          6);
                        addProperty(
                          "target",
                          equip.EQUIP_TARGET,
                          6);
                        addProperty(
                          "source",
                          equip.EQUIP_SOURCE,
                          6);
                        addProperty(
                          "symbolSTVEnable",
                          equip.EQUIP_SYMB_ENABLE,
                          6);
                        addProperty(
                          "symbolSTVDisable",
                          equip.EQUIP_SYMB_DISABLE,
                          6);
                        addProperty(
                          "symbolSTVAlarm",
                          equip.EQUIP_SYMB_ALARM,
                          6);
                        addProperty(
                          "symbolSTVSelect",
                          equip.EQUIP_SYMB_SELECT,
                          6);
                        addProperty(
                          "symbolSTVOther",
                          equip.EQUIP_SYMB_OTHER,
                          6);

                        // <property name="javaclass">
                        addProperty(
                          "javaclass",
                          equip.EQUIP_SPECIFICCONFIG,
                          6);
                        addProperty(
                          "commsettings",
                          equip.EQUIP_COMMSETTINGS,
                          6);

                        addProperty(
                          "allowInSequence",
                          equip.EQUIP_AIS,
                          6);
                        addProperty(
                          "state",
                          equip.EQUIP_STATE,
                          6);
                        addProperty(
                          "isFixed",
                          equip.isFixed,
                          6);
                        addProperty(
                          "isEjectable",
                          equip.isEjectable,
                          6);
                        addProperty(
                          "hasRecManag",
                          equip.hasRecManag,
                          6);

                        xml.append("</node>");

                      }
                    }

                    xml.append("</node>");
                  }
                }

                xml.append("</node>");
              }
            }
          }

          for(int lk = 0; lk<linksSize; lk++) {
            link = (Link)links.get(lk);

            if((link.LINK_GROUP==group.GROUP_ID) &&
                  checkStationsInGroup(
                    group.GROUP_ID,
                    nStation)) {
              xml.append("<link islink=\"true\" from=\"")
                 .append(String.valueOf(link.LINK_START))
                 .append("\" to=\"")
                 .append(String.valueOf(link.LINK_END))
                 .append("\">");
              addProperty(
                "CSSclass",
                "link_class",
                4);
              addProperty(
                "color",
                link.LINK_PROPERTIES,
                4);
              xml.append("</link>");
            }
          }

          xml.append("</node>");
        }
      }

      xml.append("</SDM>");

      System.out.println(xml.toString());

      //clob.putString(
        //1,
        //xml.toString());

      stmt.close();
      conn.close();

      return;
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     * @param nUser Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getEquips(final Statement stmt, final int nUser)
        throws SQLException {

      int       target_et;
      int       source_et;
      int       ais_et;
      int       target;
      int       source;
      int       ais;
      ResultSet r = stmt.executeQuery(
          new StringBuffer("SELECT e.fn_id_equipment EQUIP_ID, ").append(
            "         e.ft_idtext EQUIP_TEXT_ID, ").append(
            "         ig_e.fn_id_zone EQUIP_ZONE, ").append(
            "         e.fn_id_station EQUIP_STATION, ").append(
            "         Trim(et.ft_name) EQUIP_TYPE, ").append(
            "         Trim(ec.ft_name) EQUIP_CLASS, ").append(
            "         Trim(e.ft_name) EQUIP_NAME, ").append(
            "         ig_e.fn_posx EQUIP_POSX, ").append(
            "         ig_e.fn_posy EQUIP_POSY, ").append(
            "         ig_et.fn_target EQUIP_TARGET_ET, ").append(
            "         ig_et.fn_source EQUIP_SOURCE_ET, ").append(
            "         ig_et.fn_allowedinsequence EQUIP_AIS_ET, ").append(
            "         ig_ec.fn_target EQUIP_TARGET_EC, ").append(
            "         ig_ec.fn_source EQUIP_SOURCE_EC, ").append(
            "         ig_ec.fn_allowedinsequence EQUIP_AIS_EC, ").append(
            "         ig_sy.ft_symbols EQUIP_SYMB_ENABLE, ").append(
            "         ig_sy.ft_symdisable EQUIP_SYMB_DISABLE, ").append(
            "         ig_sy.ft_symalarm EQUIP_SYMB_ALARM, ").append(
            "         ig_sy.ft_symselect EQUIP_SYMB_SELECT, ").append(
            "         ig_sy.ft_othersymbols EQUIP_SYMB_OTHER, ").append(
            "         e.fn_genericstate EQUIP_STATE, ").append(
            "         e.ft_commsettings EQUIP_COMMSETTINGS, ").append(
            "         et.ft_sxspecificconfig EQUIP_SPECIFICCONFIG, ").append(
            "         et.ft_genericconfig EQUIP_GENCONFIG, ").append(
            "         e.fn_enabled EQUIP_ENBALED ").append(
            "    FROM t_Equipments e, ").append(
            "         t_IG_Equipments ig_e, ").append(
            "         t_EquipmentClasses ec, ").append(
            "         t_EquipmentTypes et, ").append(
            "         t_IG_EquipmentTypes ig_et, ").append(
            "         t_IG_EquipmentClasses ig_ec, ").append(
            "         t_IG_SymbolsSTV ig_sy ").append(
            "   WHERE Has_Permission( ").append(String.valueOf(nUser)).append(
            "         , e.fn_id_station, ").append("         ig_e.fn_id_zone, ").append(
            "         ig_e.fn_id_equipment, ").append(
            "         ig_e.fn_id_zone) = 1 ").append("     AND ").append(
            " e.fn_id_equipment = ig_e.fn_id_equipment ").append(
            "     AND et.fn_id_equipmenttype = e.fn_id_equipmenttype (+) ").append(
            "     AND ec.fn_id_equipmentclass = et.fn_id_equipmentclass (+) ").append(
            "     AND ig_et.fn_id_equipmenttype (+) = et.fn_id_equipmenttype ").append(
            "     AND ig_ec.fn_id_equipmentclass (+) = ec.fn_id_equipmentclass ").append(
            "     AND ig_sy.fn_id_symbolstv (+) = ig_e.fn_id_symbolstv ").append(

//        "     AND e.fn_enabled = 1 ").append(
        "ORDER BY e.ft_name").toString());

      while(r.next()) {
        target_et   = getInt(r, 10);
        source_et   = getInt(r, 11);
        ais_et   = getInt(r, 12);
        target   = getInt(r, 13);
        source   = getInt(r, 14);
        ais = getInt(r, 15);

        if((target_et!=NULL_VALUE) ||
              (source_et!=NULL_VALUE) ||
              (ais_et!=NULL_VALUE)) {
          target   = target_et;
          source   = source_et;
          ais      = ais_et;
        }

        equips.add(
          new Equip(
            getInt(r, 1),
            r.getString(2),
            getInt(r, 3),
            getInt(r, 4),
            r.getString(5),
            r.getString(6),
            r.getString(7),
            getInt(r, 8),
            getInt(r, 9),
            target,
            source,
            ais,
            r.getString(16),
            r.getString(17),
            r.getString(18),
            r.getString(19),
            r.getString(20),
            r.getString(21),
            r.getString(22),
            r.getString(23),
            r.getString(24),
            r.getString(25)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getGroups(Statement stmt)
        throws SQLException {
      final StringBuffer strBufferSQL = new StringBuffer().append(
          "SELECT ig_g.fn_id_group, ").append(
          "Trim(ig_g.ft_name), ").append(
          "ig_g.fn_id_zonesymbol, ").append(
          "ig_g.fn_x, ig_g.fn_y, ").append(
          "ig_sy2.ft_symbols, ").append(
          "ig_sy2.ft_symdisable, ").append(
          "ig_sy.ft_symbols, ").append(
          "ig_sy.ft_symdisable, ").append(
          "ig_sy.ft_symalarm, ").append(
          "ig_sy.ft_symselect, ").append(
          "ig_sy.ft_othersymbols, ").append(
          "ig_g.ft_properties ").append(
          "FROM t_IG_Groups ig_g, ").append(
          "t_IG_SymbolsSTV ig_sy, ").append(
          "t_IG_SymbolsSTV ig_sy2 ").append(
          "WHERE ig_sy.fn_id_symbolstv (+) = ig_g.fn_id_stationsymbol ").append(
          "AND ig_sy2.fn_id_symbolstv (+) = ig_g.fn_id_symbol ").append(
          "ORDER BY ig_g.ft_name ");

      final ResultSet    r = stmt.executeQuery(strBufferSQL.toString());

      while(r.next()) {
        groups.add(
          new Group(
            getInt(r,1),
            new StringBuffer().append(getInt(r, 1)).toString(),
            r.getString(2),
            getInt(r,3),
            getInt(r,4),
            getInt(r,5),
            r.getString(6),
            r.getString(7),
            r.getString(8),
            r.getString(9),
            r.getString(10),
            r.getString(11),
            r.getString(12),
            r.getString(13)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param rset Insert doc ...
     * @param index Insert doc ...
     *
     * @return Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static int getInt(
      ResultSet rset,
      int       index)
        throws SQLException {
      int value = rset.getInt(index);

      if(rset.wasNull()) {
        return NULL_VALUE;
      } else {
        return value;
      }
    }
    /**
     * Insert doc ...
     *
     * @param rset Insert doc ...
     * @param index Insert doc ...
     *
     * @return Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static int getBoolean(
      ResultSet rset,
      int       index)
        throws SQLException {
      boolean value = rset.getBoolean(index);

      if(value) {
        return 1;
      } else {
        return 0;
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getLinks(Statement stmt)
        throws SQLException {
      ResultSet r = stmt.executeQuery(
          new StringBuffer("SELECT g1.fn_id_station LINK_START, ").append(
            "       g2.fn_id_station LINK_END, ").append(
            "       g1.fn_id_group LINK_GROUP, ").append(
            "       g.ft_properties LINK_PROPERTIES ").append(
            "  FROM t_ig_stationsingroups g1, ").append(
            "       t_ig_stationsingroups g2, ").append("       t_ig_groups g ").append(
            " WHERE g1.fn_id_group = g2.fn_id_group ").append(
            "   AND g1.fn_id_group = g.fn_id_group ").append(
            "   AND g2.fn_position = g1.fn_position + 1 ").append(
            "ORDER BY g1.fn_position").toString());

      while(r.next()) {
        links.add(
          new Link(
            getInt(
              r,
              1),
            getInt(
              r,
              2),
            getInt(
              r,
              3),
            r.getString(4)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @return Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static String getNetworkBackImage(Statement stmt)
        throws SQLException {
      String    strImage = null;
      ResultSet r = stmt.executeQuery(
          "SELECT Trim(ig_imgs.ft_path) " +
          "FROM t_PROJECTS ig_prj, t_IG_IMAGES ig_imgs " +
          "WHERE ig_prj.fn_map = ig_imgs.fn_id ");

      while(r.next()) {
        strImage = r.getString(1);
      }

      return strImage;
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     * @param nUser Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getStations(
      Statement stmt,
      int       nUser)
        throws SQLException {
      ResultSet r = stmt.executeQuery(
          new StringBuffer("SELECT ig_sg.fn_id_station STATION_ID, ").append(
            "       s.ft_idtext STATION_TEXT_ID, ").append(
            "       ig_sg.fn_id_group STATION_GROUP, ").append(
            "       ig_s.fn_posx STATION_POSX, ").append(
            "       ig_s.fn_posy STATION_POSY, ").append(
            "       Trim(s.ft_Name) STATION_NAME, ").append(
            "       Trim(s.ft_abbr_name) STATION_ABBR_NAME, ").append(
            "       Trim(ig_sy.ft_Symbols) STATION_SYMBOLS, ").append(
            "       Trim(ig_g1.ft_properties) STATION_COLOR, ").append(
            " Has_Equips(ig_sg.fn_id_station, ").append(String.valueOf(nUser)).append(
            ") ").append("       STATION_EQUIPS, ").append(
            "       s.fn_Enabled STATION_ENABLED, ").append(
            "       s.fn_status STATION_STATE, ").append(
            "       s.fn_type STATION_TYPE, ").append(
            "       ig_sy2.ft_symbols STATION_SYMB_ENABLE, ").append(
            "       ig_sy2.ft_symdisable STATION_SYMB_DISABLE, ").append(
            "       ig_sy2.ft_symalarm STATION_SYMB_ALARM, ").append(
            "       ig_sy2.ft_symselect STATION_SYMB_SELECT, ").append(
            "       ig_sy2.ft_othersymbols STATION_SYMB_OTHER ").append(
            "  FROM t_IG_StationsSTV ig_s, ").append(
            "       t_StationsSTV s, ").append(
            "       t_IG_StationsInGroups ig_sg, ").append(
            "       t_IG_SymbolsSTV ig_sy, ").append(
            "       t_IG_SymbolsSTV ig_sy2, ").append(
            "       t_IG_Groups ig_g1 ").append(
            " WHERE ig_sg.fn_id_group = ig_g1.fn_id_group ").append(
            "   AND ig_s.fn_id_station = ig_sg.fn_id_station ").append(
            "   AND s.fn_id_station = ig_sg.fn_id_station ").append(
            "   AND ig_sy.fn_id_symbolstv (+) = ig_s.fn_id_symbolstv ").append(
            "   AND ig_sy2.fn_id_symbolstv (+) = ig_g1.fn_id_stationsymbol ").append(

//        "ORDER BY s.ft_name" ).toString());
        "ORDER BY ig_sg.fn_position").toString());

      while(r.next()) {
        stations.add(
          new Station(
            getInt(r, 1),
            r.getString(2),
            getInt(r, 3),
            getInt(r, 4),
            getInt(r, 5),
            r.getString(6),
            r.getString(7),
            r.getString(8),
            r.getString(9),
            getInt(r, 10),
            getInt(r, 11),
            getInt(r, 12),
            getInt(r, 13),
            r.getString(14),
            r.getString(15),
            r.getString(16),
            r.getString(17),
            r.getString(18)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getStationsInGroups(Statement stmt)
        throws SQLException {
      ResultSet r = stmt.executeQuery(
          "SELECT fn_id_group, fn_id_station FROM t_IG_StationsInGroups");

      while(r.next()) {
        stInGroups.add(new StationsInGroup(
            getInt(r, 1),
            getInt(r, 2)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void getZones(Statement stmt)
        throws SQLException {
      ResultSet r = stmt.executeQuery(
          new StringBuffer("SELECT ig_z.fn_id_zone ZONE_ID, ").append(
            "      ig_z.fn_id_station ZONE_STATION, ").append(
            "      Trim(ig_z.ft_name) ZONE_NAME,  ").append(
            "      Trim(ig_sy.ft_symbols) ZONE_SYMBOL, ").append(
            "      ig_sy2.fn_id_symbolstv ZONE_NSYMB, ").append(
            "      ig_z.fn_x ZONE_X, ").append("      ig_z.fn_y ZONE_Y, ").append(
            "      ig_z.ft_Color ZONE_COLOR, ").append(
            "      ig_sy2.ft_symbols ZONE_SYMB_ENABLE, ").append(
            "      ig_sy2.ft_symdisable ZONE_SYMB_DISABLE, ").append(
            "      ig_sy2.ft_symalarm ZONE_SYMB_ALARM, ").append(
            "      ig_sy2.ft_symselect ZONE_SYMB_SELECT, ").append(
            "      ig_sy2.ft_othersymbols ZONE_SYMB_OTHER ").append(
            " FROM t_IG_Zones ig_z, ").append("      t_IG_SymbolsSTV ig_sy, ").append(
            "      t_IG_SymbolsSTV ig_sy2 ").append(
            "WHERE ig_sy.fn_id_symbolstv (+) = ig_z.fn_id_symbol").toString());

      while(r.next()) {
        zones.add(
          new Zone(
            getInt(r, 1),
            new StringBuffer().append(getInt(r, 1)).toString(),
            getInt(r, 2),
            r.getString(3),
            r.getString(4),
            getInt(r, 5),
            getInt(r, 6),
            getInt(r, 7),
            r.getString(8),
            r.getString(9),
            r.getString(10),
            r.getString(11),
            r.getString(12),
            r.getString(13)));
      }
    }

    /**
     * Insert doc ...
     *
     * @param stmt Insert doc ...
     *
     * @throws SQLException Insert doc ...
     */
    private static void _getGroups(Statement stmt)
        throws SQLException {
      ResultSet r = stmt.executeQuery(
          "SELECT ig_g.fn_id_group, Trim(ig_g.ft_name), ig_g.fn_id_zonesymbol, " +
          "  ig_g.fn_x, ig_g.fn_y, " +
          "  ig_sy.ft_symbols, ig_sy.ft_symdisable, ig_sy.ft_symalarm, " +
          "  ig_sy.ft_symselect, ig_sy.ft_othersymbols, ig_g.ft_properties " +
          "FROM t_IG_Groups ig_g, t_IG_SymbolsSTV ig_sy " +
          "WHERE ig_sy.fn_id_symbolstv (+) = ig_g.fn_id_symbol " +
          "ORDER BY ig_g.ft_name");

      while(r.next()) {
        //      groups.add( new Group( getInt( r, 1 ), r.getString( 2 ), getInt( r, 3 ), getInt( r, 4 ),getInt( r, 5 ),r.getString( 6 ),
        //                       r.getString( 7 ), r.getString( 8 ), r.getString( 9 ), r.getString( 10 ), r.getString( 11 ) ) );
      }
    }

    /**
     * Insert doc ...
     *
     * @param text Insert doc ...
     * @param indent Insert doc ...
     */
    private static void add(
      String text,
      int    indent) {
      xml.append(text);
    }

    /**
     * Insert doc ...
     *
     * @param name Insert doc ...
     * @param value Insert doc ...
     * @param indent Insert doc ...
     */
    private static void addProperty(
      String name,
      int    value,
      int    indent) {
      if(value!=NULL_VALUE) {
        addProperty(
          name,
          String.valueOf(value),
          indent,
          false);
      }
    }

    /**
     * Insert doc ...
     *
     * @param name Insert doc ...
     * @param value Insert doc ...
     * @param indent Insert doc ...
     */
    private static void addProperty(
      String name,
      String value,
      int    indent) {
      //if( value == null ) {
      //value = "";
      //}
      addProperty(
        name,
        value,
        indent,
        false);
    }

    /**
     * Insert doc ...
     *
     * @param name Insert doc ...
     * @param value Insert doc ...
     * @param indent Insert doc ...
     * @param required Insert doc ...
     */
    private static void addProperty(
      String  name,
      String  value,
      int     indent,
      boolean required) {
      if(((value!=null) && (value.length()>0)) || required) {
        if(value==null) {
          value = "";
        }

        xml.append("<property name=\"")
           .append(name)
           .append("\">")
           .append(value)
           .append("</property>");
      }
    }

    /**
     * Insert doc ...
     *
     * @param group Insert doc ...
     * @param station Insert doc ...
     *
     * @return Insert doc ...
     */
    private static boolean checkStationsInGroup(
      int group,
      int station) {
      StationsInGroup sig;
      int             groupsSize = stInGroups.size();

      for(int a = 0; a<groupsSize; a++) {
        sig = (StationsInGroup)stInGroups.get(a);

        if((sig.idGroup==group) && ((sig.idStation==station) || (station==-1))) {
          return true;
        }
      }

      return false;
    }

    /**
     * Insert doc ...
     *
     * @return Insert doc ...
     */
    private static boolean insideOracle() {
      String ver = System.getProperty("oracle.server.version");

      if(ver!=null) {
        if(ver.length()>0) {
          return true;
        }
      }

      return false;
    }

    /**
     * Insert doc ...
     *
     * @param st Insert doc ...
     *
     * @return Insert doc ...
     */
    private static boolean stationShown(int st) {
      Integer i;
      int     stSize = stShown.size();

      for(int a = 0; a<stSize; a++) {
        i = (Integer)stShown.get(a);

        if(i.intValue()==st) {
          return true;
        }
      }

      return false;
    }







    public static void main(String[] e){
     getGIXML g = new getGIXML();

     try {
       getGIXML.getGIXML(-1, 92, null, null, 0, -1);
     } catch (Exception ex) {
       ex.printStackTrace();
     }
    }

  }

