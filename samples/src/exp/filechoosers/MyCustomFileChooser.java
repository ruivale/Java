package exp.filechoosers;


import java.awt.*;

import java.util.Enumeration;

import javax.swing.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class MyCustomFileChooser {

  static{
    try {
       //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
       //SwingUtilities.updateComponentTreeUI(frame);

    }catch ( Exception e ) {
       e.printStackTrace();
    }

  }

  public MyCustomFileChooser() {
    try {

      UIManager.put("FileChooser.directoryOpenButtonText", "directoryOpenButtonText");
      UIManager.put("FileChooser.directoryOpenButtonToolTipText", "directoryOpenButtonToolTipText");
      UIManager.put("FileChooser.lookInLabelText", "lookInLabelText");
      UIManager.put("FileChooser.filesOfTypeLabelText", "filesOfTypeLabelText");
      UIManager.put("FileChooser.upFolderToolTipText", "upFolderToolTipText");
      UIManager.put("FileChooser.cancelButtonText", "cancelButtonText");
      UIManager.put("FileChooser.openButtonText", "openButtontext");
      UIManager.put("FileChooser.cancelButtonText", "cancelButtonText");
      UIManager.put("FileChooser.fileNameLabelText", "fileNameLabelText");
      UIManager.put("FileChooser.filesOfTypeLabelText", "filesOfTypeLabelText");
      UIManager.put("FileChooser.acceptAllFileFilterText", "acceptAllFileFilterText");

      UIManager.put("FileChooser.cancelButtonMnemonic", "c");
      UIManager.put("FileChooser.cancelButtonText", "cancelButtonText");
      UIManager.put("FileChooser.cancelButtonToolTipText", "cancelButtonToolTipText");

      UIManager.put("FileChooser.detailsViewButtonAccessibleName", "detailsViewButtonAccessibleName");
      UIManager.put("FileChooser.detailsViewButtonToolTipText", "detailsViewButtonToolTipText");

      UIManager.put("FileChooser.directoryDescriptionText", "directoryDescriptionText");

      UIManager.put("FileChooser.fileDescriptionText", "fileDescriptionText");

      UIManager.put("FileChooser.fileNameLabelMnemonic", "f");
      UIManager.put("FileChooser.fileNameLabelText", "fileNameLabelText");

      UIManager.put("FileChooser.filesOfTypeLabelMnemonic", "t");
      UIManager.put("FileChooser.filesOfTypeLabelText", "filesOfTypeLabelText");

      UIManager.put("FileChooser.helpButtonMnemonic", "h");
      UIManager.put("FileChooser.helpButtonText", "helpButtonText");
      UIManager.put("FileChooser.helpButtonToolTipText", "helpButtonToolTipText");

      UIManager.put("FileChooser.homeFolderAccessibleName", "homeFolderAccessibleName");
      UIManager.put("FileChooser.homeFolderToolTipText", "homeFolderToolTipText");

      UIManager.put("FileChooser.listViewButtonAccessibleName", "listViewButtonAccessibleName");
      UIManager.put("FileChooser.listViewButtonToolTipText", "listViewButtonToolTipText");

      UIManager.put("FileChooser.lookInLabelMnemonic", "l");
      UIManager.put("FileChooser.lookInLabelText", "lookInLabelText");

      UIManager.put("FileChooser.newFolderAccessibleName", "newFolderAccessibleName");
      //UIManager.put("FileChooser.newFolderErrorSeparator", "");
      UIManager.put("FileChooser.newFolderErrorText", "newFolderErrorText");
      UIManager.put("FileChooser.newFolderToolTipText", "newFolderToolTipText");

      UIManager.put("FileChooser.openButtonMnemonic", "o");
      UIManager.put("FileChooser.openButtonText", "openButtonText");
      UIManager.put("FileChooser.openButtonToolTipText", "openButtonToolTipText");
      UIManager.put("FileChooser.openDialogTitleText", "openDialogTitleText");

      UIManager.put("FileChooser.other.newFolder", "newFolder");
      UIManager.put("FileChooser.other.newFolder.subsequent", "subsequent");

      UIManager.put("FileChooser.saveButtonMnemonic", "s");
      UIManager.put("FileChooser.saveButtonText", "saveButtonText");
      UIManager.put("FileChooser.saveButtonToolTipText", "saveButtonToolTipText");
      UIManager.put("FileChooser.saveDialogTitleText", "saveDialogTitleText");

      UIManager.put("FileChooser.saveInLabelMnemonic", "e");
      UIManager.put("FileChooser.saveInLabelText", "saveInLabelText");

      UIManager.put("FileChooser.upFolderAccessibleName", "upFolderAccessibleName");
      UIManager.put("FileChooser.upFolderToolTipText", "upFolderToolTipText");

      UIManager.put("FileChooser.updateButtonMnemonic", "u");
      UIManager.put("FileChooser.updateButtonText", "updateButtonText");
      UIManager.put("FileChooser.updateButtonToolTipText", "updateButtonToolTipText");

      UIManager.put("FileChooser.win32.newFolder", "win32.newFolder");
      UIManager.put("FileChooser.win32.newFolder.subsequent", "win32.newFolder.subsequent");

      UIManager.put("FileChooser.fileAttrHeaderText", "fileAttrHeaderText");
      UIManager.put("FileChooser.fileNameHeaderText", "fileNameHeaderText");
      UIManager.put("FileChooser.fileDateHeaderText", "fileDateHeaderText");
      UIManager.put("FileChooser.fileSizeHeaderText", "fileSizeHeaderText");
      UIManager.put("FileChooser.fileTypeHeaderText", "fileTypeHeaderText");


       /////////////////////////////////////////////////////



      JFileChooser jfc = new JFileChooser();

      /**
      jfc.setDefaultLocale(new java.util.Locale("pt"));
      jfc.setLocale(new java.util.Locale("pt"));

      Component[] comps2;
      Component[] comps3;
      Component[] comps = jfc.getComponents();

      int ooo = 0;
      for (int i = 0; i < comps.length; i++) {
        System.out.println("comps[" + i + "]=" + comps[i].toString() + ".");
        comps2 = ((JPanel) comps[i]).getComponents();

        for (int j = 0; j < comps2.length; j++) {
          System.out.println("    comps2[" + j + "]=" + comps2[j].toString() +
                             ".");

          if (comps2[j] instanceof JPanel) {
            comps3 = ((JPanel) comps2[j]).getComponents();

            for (int k = 0; k < comps3.length; k++) {
              System.out.println("        comps3[" + k + "]=" +
                                 comps3[k].toString() + ".");


              //if(comps3[k] instanceof javax.swing.AbstractButton){
                //((AbstractButton)comps3[k]).setText("-"+(ooo++)+"-");
              //}
            }
          }
        }
      }
      /**/


      jfc.showOpenDialog(null);

      /**
      UIDefaults uid = UIManager.getDefaults();
      Enumeration iter = uid.keys();

      try {
        Object key;

        while (iter.hasMoreElements()) {
          key = (Object) iter.nextElement();

          if (key.toString().indexOf("FileChooser") > -1) {
            System.out.println("KEY= " + key + ".");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
      /**/

      System.out.println("jfc.file="+jfc.getSelectedFile().getName()+".");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    MyCustomFileChooser myCustomFileChooser1 = new MyCustomFileChooser();
  }
}


/*
 FileChooser.acceptAllFileFilterText
 FileChooser.ancestorInputMap
 FileChooser.cancelButtonMnemonic
 FileChooser.cancelButtonText
 FileChooser.cancelButtonToolTipText
 FileChooser.detailsViewButtonAccessibleName
 FileChooser.detailsViewButtonToolTipText
 FileChooser.detailsViewIcon
 FileChooser.directoryDescriptionText
 FileChooser.fileDescriptionText
 FileChooser.fileNameLabelMnemonic
 FileChooser.fileNameLabelText
 FileChooser.filesOfTypeLabelMnemonic
 FileChooser.filesOfTypeLabelText
 FileChooser.helpButtonMnemonic
 FileChooser.helpButtonText
 FileChooser.helpButtonToolTipText
 FileChooser.homeFolderAccessibleName
 FileChooser.homeFolderIcon
 FileChooser.homeFolderToolTipText
 FileChooser.listViewButtonAccessibleName
 FileChooser.listViewButtonToolTipText
 FileChooser.listViewIcon
 FileChooser.lookInLabelMnemonic
 FileChooser.lookInLabelText
 FileChooser.newFolderAccessibleName
 FileChooser.newFolderErrorSeparator
 FileChooser.newFolderErrorText
 FileChooser.newFolderIcon
 FileChooser.newFolderToolTipText
 FileChooser.openButtonMnemonic
 FileChooser.openButtonText
 FileChooser.openButtonToolTipText
 FileChooser.openDialogTitleText
 FileChooser.other.newFolder
 FileChooser.other.newFolder.subsequent
 FileChooser.saveButtonMnemonic
 FileChooser.saveButtonText
 FileChooser.saveButtonToolTipText
 FileChooser.saveDialogTitleText
 FileChooser.upFolderAccessibleName
 FileChooser.upFolderIcon
 FileChooser.upFolderToolTipText
 FileChooser.updateButtonMnemonic
 FileChooser.updateButtonText
 FileChooser.updateButtonToolTipText
 FileChooser.win32.newFolder
 FileChooser.win32.newFolder.subsequent
 FileChooserUI


*/


/*
 C:\jdk1.4.2\bin\javaw -classpath "D:\JBProjects\exp\classes;D:\JBProjects\GISTV\classes;D:\JBProjects\PIntV1_0\classes;D:\JBProjects\PIntV1_0\lib\ORB.jar;D:\JBProjects\PIntV1_0\lib\ORBServices.jar;C:\jdk1.4.2\jre\javaws\javaws.jar;C:\jdk1.4.2\jre\lib\charsets.jar;C:\jdk1.4.2\jre\lib\ext\crimson.jar;C:\jdk1.4.2\jre\lib\ext\dnsns.jar;C:\jdk1.4.2\jre\lib\ext\dom2.jar;C:\jdk1.4.2\jre\lib\ext\dom4j.jar;C:\jdk1.4.2\jre\lib\ext\jaxp.jar;C:\jdk1.4.2\jre\lib\ext\jcert.jar;C:\jdk1.4.2\jre\lib\ext\jmf.jar;C:\jdk1.4.2\jre\lib\ext\jnet.jar;C:\jdk1.4.2\jre\lib\ext\jsse.jar;C:\jdk1.4.2\jre\lib\ext\jviewsall.jar;C:\jdk1.4.2\jre\lib\ext\ldapsec.jar;C:\jdk1.4.2\jre\lib\ext\localedata.jar;C:\jdk1.4.2\jre\lib\ext\oracle817_jdbc.jar;C:\jdk1.4.2\jre\lib\ext\sax2.jar;C:\jdk1.4.2\jre\lib\ext\sdm.jar;C:\jdk1.4.2\jre\lib\ext\sdmgui.jar;C:\jdk1.4.2\jre\lib\ext\socks.jar;C:\jdk1.4.2\jre\lib\ext\sound.jar;C:\jdk1.4.2\jre\lib\ext\sunjce_provider.jar;C:\jdk1.4.2\jre\lib\ext\svgdom.jar;C:\jdk1.4.2\jre\lib\ext\xerces.jar;C:\jdk1.4.2\jre\lib\im\indicim.jar;C:\jdk1.4.2\jre\lib\im\thaiim.jar;C:\jdk1.4.2\jre\lib\jce.jar;C:\jdk1.4.2\jre\lib\jsse.jar;C:\jdk1.4.2\jre\lib\plugin.jar;C:\jdk1.4.2\jre\lib\rt.jar;C:\jdk1.4.2\jre\lib\sunrsasign.jar;C:\jdk1.4.2\lib\dt.jar;C:\jdk1.4.2\lib\htmlconverter.jar;C:\jdk1.4.2\lib\tools.jar"  -DdefaultCountry=PT -DdefaultLanguage=pt exp.filechoosers.MyCustomFileChooser
 comps[0]=javax.swing.JPanel[,0,0,0x0,invalid,layout=java.awt.BorderLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
    comps2[0]=javax.swing.JPanel[,0,0,0x0,invalid,layout=javax.swing.BoxLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
        comps3[0]=javax.swing.JButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1aaf0b3,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=javax.swing.plaf.metal.MetalIconFactory$FileChooserUpFolderIcon@1fc6e42,disabledIcon=,disabledSelectedIcon=,margin=java.awt.Insets[top=0,left=0,bottom=0,right=0],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=null,defaultCapable=true].
        comps3[1]=javax.swing.Box$Filler[,0,0,0x0,invalid,alignmentX=null,alignmentY=null,border=,flags=0,maximumSize=,minimumSize=,preferredSize=].
        comps3[2]=javax.swing.JButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1aaf0b3,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=javax.swing.plaf.metal.MetalIconFactory$FileChooserHomeFolderIcon@210b5b,disabledIcon=,disabledSelectedIcon=,margin=java.awt.Insets[top=0,left=0,bottom=0,right=0],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=,defaultCapable=true].
        comps3[3]=javax.swing.Box$Filler[,0,0,0x0,invalid,alignmentX=null,alignmentY=null,border=,flags=0,maximumSize=,minimumSize=,preferredSize=].
        comps3[4]=javax.swing.JButton[,0,0,0x0,invalid,disabled,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1aaf0b3,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=javax.swing.plaf.metal.MetalIconFactory$FileChooserNewFolderIcon@1f06dc3,disabledIcon=,disabledSelectedIcon=,margin=java.awt.Insets[top=0,left=0,bottom=0,right=0],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=null,defaultCapable=true].
        comps3[5]=javax.swing.Box$Filler[,0,0,0x0,invalid,alignmentX=null,alignmentY=null,border=,flags=0,maximumSize=,minimumSize=,preferredSize=].
        comps3[6]=javax.swing.JToggleButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1d62270,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=javax.swing.plaf.metal.MetalIconFactory$FileChooserListViewIcon@1b64e6a,disabledIcon=,disabledSelectedIcon=,margin=java.awt.Insets[top=0,left=0,bottom=0,right=0],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=].
        comps3[7]=javax.swing.JToggleButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1d62270,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=javax.swing.plaf.metal.MetalIconFactory$FileChooserDetailViewIcon@16f0be8,disabledIcon=,disabledSelectedIcon=,margin=java.awt.Insets[top=0,left=0,bottom=0,right=0],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=].
    comps2[1]=javax.swing.JLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=null,border=,flags=384,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=C:\Documents and Settings\rvale\My Documents],text=Look In:,verticalAlignment=CENTER,verticalTextPosition=CENTER].
    comps2[2]=javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=0.0,alignmentY=0.0,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=C:\Documents and Settings\rvale\My Documents].
 comps[1]=javax.swing.JPanel[,0,0,0x0,invalid,layout=java.awt.BorderLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
    comps2[0]=javax.swing.JPanel[,0,0,0x0,invalid,layout=java.awt.BorderLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=java.awt.Dimension[width=405,height=135]].
        comps3[0]=javax.swing.JScrollPane[,0,0,0x0,invalid,layout=javax.swing.ScrollPaneLayout$UIResource,alignmentX=null,alignmentY=null,border=javax.swing.plaf.metal.MetalBorders$ScrollPaneBorder@176cad3,flags=328,maximumSize=,minimumSize=,preferredSize=,columnHeader=,horizontalScrollBar=javax.swing.JScrollPane$ScrollBar[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalScrollBarUI,alignmentX=null,alignmentY=null,border=,flags=4194600,maximumSize=,minimumSize=,preferredSize=,blockIncrement=10,orientation=HORIZONTAL,unitIncrement=1],horizontalScrollBarPolicy=HORIZONTAL_SCROLLBAR_AS_NEEDED,lowerLeft=,lowerRight=,rowHeader=,upperLeft=,upperRight=,verticalScrollBar=javax.swing.JScrollPane$ScrollBar[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalScrollBarUI,alignmentX=null,alignmentY=null,border=,flags=4194600,maximumSize=,minimumSize=,preferredSize=,blockIncrement=10,orientation=VERTICAL,unitIncrement=1],verticalScrollBarPolicy=VERTICAL_SCROLLBAR_AS_NEEDED,viewport=javax.swing.JViewport[,0,0,0x0,invalid,layout=javax.swing.ViewportLayout,alignmentX=null,alignmentY=null,border=,flags=8,maximumSize=,minimumSize=,preferredSize=,isViewSizeSet=false,lastPaintPosition=,scrollUnderway=false],viewportBorder=].
    comps2[1]=javax.swing.JPanel[,0,0,0x0,invalid,layout=java.awt.BorderLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
 comps[2]=javax.swing.JPanel[,0,0,0x0,invalid,layout=javax.swing.BoxLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
    comps2[0]=javax.swing.JPanel[,0,0,0x0,invalid,layout=javax.swing.BoxLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
        comps3[0]=javax.swing.plaf.metal.MetalFileChooserUI$AlignedLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=null,border=,flags=384,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=javax.swing.plaf.metal.MetalFileChooserUI$2[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=null,alignmentY=null,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1fe1feb,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=javax.swing.plaf.ColorUIResource[r=0,g=0,b=0],disabledTextColor=javax.swing.plaf.ColorUIResource[r=153,g=153,b=153],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=javax.swing.plaf.ColorUIResource[r=0,g=0,b=0],selectionColor=javax.swing.plaf.ColorUIResource[r=204,g=204,b=255],columns=35,columnWidth=0,command=,horizontalAlignment=LEADING],text=File Name:,verticalAlignment=CENTER,verticalTextPosition=CENTER].
        comps3[1]=javax.swing.plaf.metal.MetalFileChooserUI$2[,0,0,0x0,invalid,layout=javax.swing.plaf.basic.BasicTextUI$UpdateHandler,alignmentX=null,alignmentY=null,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1fe1feb,flags=296,maximumSize=,minimumSize=,preferredSize=,caretColor=javax.swing.plaf.ColorUIResource[r=0,g=0,b=0],disabledTextColor=javax.swing.plaf.ColorUIResource[r=153,g=153,b=153],editable=true,margin=javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0],selectedTextColor=javax.swing.plaf.ColorUIResource[r=0,g=0,b=0],selectionColor=javax.swing.plaf.ColorUIResource[r=204,g=204,b=255],columns=35,columnWidth=0,command=,horizontalAlignment=LEADING].
    comps2[1]=javax.swing.Box$Filler[,0,0,0x0,invalid,alignmentX=null,alignmentY=null,border=,flags=0,maximumSize=,minimumSize=,preferredSize=].
    comps2[2]=javax.swing.JPanel[,0,0,0x0,invalid,layout=javax.swing.BoxLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
        comps3[0]=javax.swing.plaf.metal.MetalFileChooserUI$AlignedLabel[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=null,border=,flags=384,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=LEADING,horizontalTextPosition=TRAILING,iconTextGap=4,labelFor=javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=null,alignmentY=null,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=javax.swing.plaf.basic.BasicFileChooserUI$AcceptAllFileFilter@1eb2c1b],text=Files of Type:,verticalAlignment=CENTER,verticalTextPosition=CENTER].
        comps3[1]=javax.swing.JComboBox[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalComboBoxUI$MetalComboBoxLayoutManager,alignmentX=null,alignmentY=null,border=,flags=328,maximumSize=,minimumSize=,preferredSize=,isEditable=false,lightWeightPopupEnabled=true,maximumRowCount=8,selectedItemReminder=javax.swing.plaf.basic.BasicFileChooserUI$AcceptAllFileFilter@1eb2c1b].
    comps2[3]=javax.swing.JPanel[,0,0,0x0,invalid,layout=javax.swing.plaf.metal.MetalFileChooserUI$ButtonAreaLayout,alignmentX=null,alignmentY=null,border=,flags=9,maximumSize=,minimumSize=,preferredSize=].
        comps3[0]=javax.swing.JButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1aaf0b3,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Open,defaultCapable=true].
        comps3[1]=javax.swing.JButton[,0,0,0x0,invalid,layout=javax.swing.OverlayLayout,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1aaf0b3,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=false,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Cancel,defaultCapable=true].

 KEY= class javax.swing.plaf.metal.MetalFileChooserUI.
 KEY= javax.swing.plaf.metal.MetalFileChooserUI.

 KEY= FileChooser.fileNameLabelMnemonic.
 KEY= FileChooser.upFolderIcon.
 KEY= FileChooser.lookInLabelMnemonic.
 KEY= FileChooser.listViewIcon.
 KEY= FileChooser.directoryOpenButtonMnemonic.
 KEY= FileChooser.openButtonMnemonic.
 KEY= FileChooser.updateButtonMnemonic.
 KEY= FileChooser.cancelButtonMnemonic.
 KEY= FileChooser.newFolderIcon.
 KEY= FileChooser.detailsViewIcon.
 KEY= FileChooser.homeFolderIcon.
 KEY= FileChooserUI.
 KEY= FileChooser.filesOfTypeLabelMnemonic.
 KEY= FileChooser.ancestorInputMap.
 KEY= FileChooser.helpButtonMnemonic.
 KEY= FileChooser.saveButtonMnemonic.

 */
