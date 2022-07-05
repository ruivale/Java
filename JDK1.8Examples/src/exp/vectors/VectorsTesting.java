package exp.vectors;


/**
 * Title: Description: Copyright:    Copyright (c) Company:
 */
import java.util.*;


/**
 *  Insert doc ...
 *
 * @author $author$
 * @version $Revision$
 */
public class VectorsTesting {
  //~ Constructors /////////////////////////////////////////////////////////////

  /**
   * Creates a new VectorsTesting object.
   */
  public VectorsTesting() {
    Vector     vectorAgentsVersions = new Vector();
    String[][] agentsVersion =
      {
        { "2.0", "S. Hora", "229000" },
        { "3.0", "Viso", "226000" },
        { "3.0", "Trindade", "220000" },
        { "3.0", "Barranha", "236000" },
        { "2.0", "V. Gama", "229000" },
        { "1.0", "Sete Bicas", "227000" },
        { "1.0", "Estádio", "230000" }
      };

    String[]    args                 = { "", "" };
    String      arg                  = "";
    int         nbrOfDifferentAgents = agentsVersion.length;
    HashMap     hashMap              = new HashMap(nbrOfDifferentAgents);
    StationInfo stationInfo          = null;
    String      versionAndName       = "";

    for(int i = 0; i<nbrOfDifferentAgents; i++) {
      versionAndName   = agentsVersion[i][0] + "##" + agentsVersion[i][1];

      stationInfo =
        new StationInfo(agentsVersion[i][0], agentsVersion[i][2],
          agentsVersion[i][1]);

      hashMap.put(versionAndName, stationInfo);

      vectorAgentsVersions.addElement(versionAndName);
    }

    // Ordering this vector elements.
    java.util.Collections.sort(vectorAgentsVersions);

    String key;
    String lastKey = "";

    for(int i = 0; i<vectorAgentsVersions.size(); i++) {
      key = (String)vectorAgentsVersions.get(i);

      if(!lastKey.equals(key.substring(
                0,
                key.indexOf("##")))) {
        //creates a new treeNode.
      }

      stationInfo = (StationInfo)hashMap.get(key);

      System.out.println("vector[" + i + "]: " + key + ". ID: " +
        stationInfo.stationID + ", name: " + stationInfo.stationName + ".");

      lastKey = key.substring(
          0,
          key.indexOf("##"));
    }
  }

  //~ Methods //////////////////////////////////////////////////////////////////

  /**
   * Insert doc ...
   *
   * @param args  Insert doc ...
   */
  public static void main(String[] args) {
    VectorsTesting vectorsTesting1 = new VectorsTesting();
  }

  //~ Inner Classes ////////////////////////////////////////////////////////////

  class StationInfo {
    public String agentVersion;
    public String stationID;
    public String stationName;

    public StationInfo(
      String agentVersion,
      String stationID,
      String stationName) {
      this.agentVersion   = agentVersion;
      this.stationID      = stationID;
      this.stationName    = stationName;
    }
  }
}
