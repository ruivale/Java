package exp.frames;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class Panel4DocImgs extends JPanel {

  Object[] objUsers = {"Operador 01","Operador 02","Operador 03","Administrador 01","Administrador 02" };
  Object[] objStations = {"Est. do Dragão", "Campanhã", "Heroísmo", "C. 24 de Agosto",
      "Bolhão", "Trindade", "Lapa", "Carolina Michaelis", "Casa da Música", "Francos",
      "Ramalde", "Viso"};
  Object[] objZones = {"Superfície", "Intermédio 2","Intermédio 1", "Cais"};
  Object[] objEquips = {"Câmara 1","Câmara 2","Câmara 3","Câmara 4","Câmara 5","DVR","Quad","Sensor"};
  JList jListUsers = new JList(objUsers);
  
  
  DefaultListModel<String> dlmStations = new DefaultListModel<>();
  JList<String> jListStations = new JList(dlmStations);
  
  
  
  JList jListEquips = new JList(objEquips);
  JList jListZones = new JList(objZones);

  JPanel jPanelUsers = new JPanel();
  JPanel jPanelStationsZonesEquips = new JPanel();
  JPanel jPanelStations = new JPanel();
  JPanel jPanelZones = new JPanel();
  JPanel jPanelEquips = new JPanel();
  JLabel jLabel1 = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  Border border1;
  JLabel jLabel2 = new JLabel();
  BorderLayout borderLayout2 = new BorderLayout();
  Border border2;
  JLabel jLabel4 = new JLabel();
  BorderLayout borderLayout3 = new BorderLayout();
  Border border3;
  JLabel jLabel5 = new JLabel();
  BorderLayout borderLayout4 = new BorderLayout();
  Border border4;
  GridLayout gridLayout1 = new GridLayout();
  Border border5;
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout5 = new BorderLayout();
  GridLayout gridLayout2 = new GridLayout();
  JPanel jPanel2 = new JPanel();

  public Panel4DocImgs() {
    try {
      dlmStations.addElement("Campanhã");
      dlmStations.addElement("Sra. Hora");
      dlmStations.addElement("Sete Bicas");
      dlmStations.addElement("Correios");
      dlmStations.addElement("Dinin");
      dlmStations.addElement("Trindade");
      dlmStations.addElement("Dei");
      dlmStations.addElement("Tudo");
      dlmStations.addElement("Cragão");
      dlmStations.addElement("Gaia");
      dlmStations.addElement("Coz");
      
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    border1 = BorderFactory.createEmptyBorder(5,10,5,5);
    border2 = BorderFactory.createEmptyBorder(5,5,5,5);
    border3 = BorderFactory.createEmptyBorder(5,5,5,5);
    border4 = BorderFactory.createEmptyBorder(5,5,5,5);
    border5 = BorderFactory.createEmptyBorder(5,5,5,5);
    this.setLayout(gridLayout2);
    //jPanelUsers.setBorder(border1);
    jPanelUsers.setPreferredSize(new Dimension(120, 25));
    jPanelUsers.setLayout(borderLayout1);
    //jPanelStationsZonesEquips.setBorder(border5);
    jPanelStationsZonesEquips.setLayout(gridLayout1);
    jPanelStations.setBorder(border2);
    jPanelStations.setLayout(borderLayout2);
    jPanelZones.setBorder(border3);
    jPanelZones.setLayout(borderLayout3);
    jPanelEquips.setBorder(border4);
    jPanelEquips.setLayout(borderLayout4);
    jLabel1.setText("Utilizadores");
    jLabel2.setText("Estações");
    jLabel4.setToolTipText("");
    jLabel4.setText("Equipamentos");
    jLabel5.setText("Zonas");
    jPanel1.setLayout(borderLayout5);
    gridLayout2.setColumns(1);
    gridLayout2.setRows(2);
    jPanel2.setLayout(null);
    jPanelStationsZonesEquips.add(jPanelStations, null);
    jPanelZones.add(jListEquips, BorderLayout.CENTER);
    jPanelZones.add(jLabel4, BorderLayout.NORTH);
    jPanelStationsZonesEquips.add(jPanelEquips, null);
    jPanelEquips.add(jListZones, BorderLayout.CENTER);
    jPanelEquips.add(jLabel5, BorderLayout.NORTH);
    this.add(jPanel1, null);
    this.add(jPanel2, null);
    jPanel1.add(jPanelUsers, BorderLayout.WEST);
    jPanelUsers.add(jListUsers, BorderLayout.CENTER);
    //jPanelUsers.add(jLabel1, BorderLayout.NORTH);
    jPanel1.add(jPanelStationsZonesEquips, BorderLayout.CENTER);
    jPanelStations.add(jLabel2, BorderLayout.NORTH);
    jPanelStations.add(jListStations,  BorderLayout.CENTER);
    jPanelStationsZonesEquips.add(jPanelZones, null);

    jPanelStationsZonesEquips.setBorder(new TitledBorder("Estações/Zonas/Equipamentos"));
    jPanelUsers.setBorder(new TitledBorder("Utilizadores"));




  }

  public static void main(String[] args) {
    Panel4DocImgs panel4DocImgs1 = new Panel4DocImgs();

    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(panel4DocImgs1, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(200,200,300,200);
    f.setVisible(true);

  }

}




