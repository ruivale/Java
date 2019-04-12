package exp.filechoosers;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class ShowUIDefaults extends JFrame implements ActionListener {
  JFrame frame;
  JTabbedPane tabbedPane;
  JButton metal;
  JButton windows;
  JButton motif;
  SampleRenderer sampleRenderer;

  public ShowUIDefaults(String title) {
    super(title);
    frame = this;

    getContentPane().setLayout(new BorderLayout());
    tabbedPane = getTabbedPane();
    getContentPane().add(tabbedPane);

    JPanel buttons = new JPanel();
    buttons.setLayout(new GridLayout(1, 3));
    getContentPane().add(buttons, BorderLayout.SOUTH);

    metal = new JButton("Metal");
    metal.addActionListener(this);
    buttons.add(metal);

    windows = new JButton("Windows");
    windows.addActionListener(this);
    buttons.add(windows);

    motif = new JButton("Motif");
    motif.addActionListener(this);
    buttons.add(motif);
  }

  public void actionPerformed(ActionEvent e) {
    String laf = "";
    Object o = e.getSource();

    if (o == metal) {
      laf = "javax.swing.plaf.metal.MetalLookAndFeel";
    } else if (o == windows) {
      laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    } else if (o == motif) {
      laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    }

    try {
      UIManager.setLookAndFeel(laf);
    } catch (Exception e2) {
      System.out.println(e2);
    }

    getContentPane().remove(tabbedPane);
    tabbedPane = getTabbedPane();
    getContentPane().add(tabbedPane);
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }

  private JTabbedPane getTabbedPane() {
    Map components = new TreeMap();

    UIDefaults defaults = UIManager.getDefaults();

    // Build of Map of attributes for each component
    for (Enumeration enumeration = defaults.keys(); enumeration.hasMoreElements();) {
      Object key = enumeration.nextElement();
      Object value = defaults.get(key);

      Map componentMap = getComponentMap(components, key.toString());

      if (componentMap != null) {
        componentMap.put(key, value);
      }
    }

    JTabbedPane pane = new JTabbedPane(SwingConstants.BOTTOM);
    pane.setPreferredSize(new Dimension(800, 400));
    addComponentTabs(pane, components);

    return pane;
  }

  private Map getComponentMap(Map components, String key) {
    if (key.startsWith("class") | key.startsWith("javax")) {
      return null;
    }

    // Component name is found before the first "."
    String componentName;

    int pos = key.indexOf(".");

    if (pos == -1) {
      if (key.endsWith("UI")) {
        componentName = key.substring(0, key.length() - 2);
      } else {
        componentName = "System Colors";
      }
    } else {
      componentName = key.substring(0, pos);
    }

    // Get the Map for this particular component
    Object componentMap = components.get(componentName);

    if (componentMap == null) {
      componentMap = new TreeMap();
      components.put(componentName, componentMap);
    }

    return (Map) componentMap;
  }

  private void addComponentTabs(JTabbedPane pane, Map components) {
    sampleRenderer = new SampleRenderer();

    String[] colName = { "Key", "Value", "Sample" };
    Set c = components.keySet();

    for (Iterator ci = c.iterator(); ci.hasNext();) {
      String component = (String) ci.next();
      Map attributes = (Map) components.get(component);

      Object[][] rowData = new Object[attributes.size()][3];
      int i = 0;

      Set a = attributes.keySet();

      for (Iterator ai = a.iterator(); ai.hasNext(); i++) {
        String attribute = (String) ai.next();
        rowData[i][0] = attribute;

        Object o = attributes.get(attribute);

        if (o != null) {
          rowData[i][1] = o.toString();
          rowData[i][2] = "";

          if (o instanceof Font) {
            rowData[i][2] = (Font) o;
          }

          if (o instanceof Color) {
            rowData[i][2] = (Color) o;
          }

          if (o instanceof IconUIResource) {
            rowData[i][2] = (Icon) o;
          }
        } else {
          rowData[i][1] = "";
          rowData[i][2] = "";
        }
      }

      MyTableModel myModel = new MyTableModel(rowData, colName);
      JTable table = new JTable(myModel);

      table.setDefaultRenderer(sampleRenderer.getClass(), sampleRenderer);

      table.getColumnModel().getColumn(0).setPreferredWidth(250);

      table.getColumnModel().getColumn(1).setPreferredWidth(500);

      table.getColumnModel().getColumn(2).setPreferredWidth(50);

      pane.addTab(component, new JScrollPane(table));
    }
  }

  public static void main(String[] args) {
    // Set the Look and Feel
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }

    // The attributes of each component will be shown on a separate tab
    JFrame f = new ShowUIDefaults("UI Defaults");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }

  class MyTableModel extends AbstractTableModel {
    private String[] columnNames;
    private Object[][] rowData;

    public MyTableModel(Object[][] rowData, String[] columnNames) {
      this.rowData = rowData;
      this.columnNames = columnNames;

      /**/
      for (int i = 0; i < rowData.length; i++) {
        System.out.println(rowData[i][0]);
      }
      /**/

    }

    public int getColumnCount() {
      return columnNames.length;
    }

    public int getRowCount() {
      return rowData.length;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
      return rowData[row][col];
    }

    public Class getColumnClass(int c) {
      Object o;

      if (c == 2) {
        o = sampleRenderer;
      } else {
        o = getValueAt(0, c);
      }

      return o.getClass();
    }

    public void setValueAt(Object value, int row, int col) {
      rowData[row][col] = value;
      fireTableCellUpdated(row, col);
    }
  }

  class SampleRenderer extends JLabel implements TableCellRenderer {
    public SampleRenderer() {
      super();
      setHorizontalAlignment(SwingConstants.CENTER);
      setOpaque(true); //MUST do this for backgroundto show up.
    }

    public Component getTableCellRendererComponent(JTable table, Object sample,
      boolean isSelected, boolean hasFocus, int row, int column) {
      setBackground(null);
      setIcon(null);
      setText("");

      if (sample instanceof Color) {
        setBackground((Color) sample);
      }

      if (sample instanceof Font) {
        setText("Sample");
        setFont((Font) sample);
      }

      if (sample instanceof Icon) {
        setIcon((Icon) sample);
      }

      return this;
    }
  }
}



/*

This vars are all over the *_en; *_es lang files inside the javax.swing.plaf
packages.

AbstractButton.clickText
AbstractDocument.additionText
AbstractDocument.deletionText
AbstractDocument.redoText
AbstractDocument.styleChangeText
AbstractDocument.undoText
AbstractUndoableEdit.redoText
AbstractUndoableEdit.undoText
Button.background
Button.border
Button.dashedRectGapHeight
Button.dashedRectGapWidth
Button.dashedRectGapX
Button.dashedRectGapY
Button.focus
Button.focusInputMap
Button.font
Button.foreground
Button.margin
Button.textIconGap
Button.textShiftOffset
ButtonUI
CheckBox.background
CheckBox.border
CheckBox.darkShadow
CheckBox.focus
CheckBox.focusInputMap
CheckBox.font
CheckBox.foreground
CheckBox.highlight
CheckBox.icon
CheckBox.margin
CheckBox.shadow
CheckBox.textIconGap
CheckBox.textShiftOffset
CheckBoxUI
CheckBoxMenuItem.acceleratorFont
CheckBoxMenuItem.acceleratorForeground
CheckBoxMenuItem.acceleratorSelectionForeground
CheckBoxMenuItem.arrowIcon
CheckBoxMenuItem.background
CheckBoxMenuItem.border
CheckBoxMenuItem.borderPainted
CheckBoxMenuItem.checkIcon
CheckBoxMenuItem.font
CheckBoxMenuItem.foreground
CheckBoxMenuItem.margin
CheckBoxMenuItem.selectionBackground
CheckBoxMenuItem.selectionForeground
CheckBoxMenuItemUI
ColorChooser.background
ColorChooser.cancelText
ColorChooser.font
ColorChooser.foreground
ColorChooser.hsbBlueText
ColorChooser.hsbBrightnessText
ColorChooser.hsbGreenText
ColorChooser.hsbHueText
ColorChooser.hsbNameText
ColorChooser.hsbRedText
ColorChooser.hsbSaturationText
ColorChooser.okText
ColorChooser.previewText
ColorChooser.resetText
ColorChooser.rgbBlueMnemonic
ColorChooser.rgbBlueText
ColorChooser.rgbGreenMnemonic
ColorChooser.rgbGreenText
ColorChooser.rgbNameText
ColorChooser.rgbRedMnemonic
ColorChooser.rgbRedText
ColorChooser.sampleText
ColorChooser.swatchesDefaultRecentColor
ColorChooser.swatchesNameText
ColorChooser.swatchesRecentSwatchSize
ColorChooser.swatchesRecentText
ColorChooser.swatchesSwatchSize
ColorChooserUI
ComboBox.ancestorInputMap
ComboBox.background
ComboBox.border
ComboBox.disabledBackground
ComboBox.disabledForeground
ComboBox.font
ComboBox.foreground
ComboBox.selectionBackground
ComboBox.selectionForeground
ComboBox.togglePopupText
ComboBoxUI
Desktop.ancestorInputMap
Desktop.background
DesktopIcon.border
DesktopIconUI
DesktopPaneUI
EditorPane.background
EditorPane.border
EditorPane.caretBlinkRate
EditorPane.caretForeground
EditorPane.focusInputMap
EditorPane.font
EditorPane.foreground
EditorPane.inactiveForeground
EditorPane.keyBindings
EditorPane.margin
EditorPane.selectionBackground
EditorPane.selectionForeground
EditorPaneUI
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
FileView.computerIcon
FileView.directoryIcon
FileView.fileIcon
FileView.floppyDriveIcon
FileView.hardDriveIcon
FormView.resetButtonText
FormView.submitButtonText
InternalFrame.activeTitleBackground
InternalFrame.activeTitleForeground
InternalFrame.border
InternalFrame.closeButtonToolTip
InternalFrame.closeIcon
InternalFrame.icon
InternalFrame.iconButtonToolTip
InternalFrame.iconifyIcon
InternalFrame.inactiveTitleBackground
InternalFrame.inactiveTitleForeground
InternalFrame.maxButtonToolTip
InternalFrame.maximizeIcon
InternalFrame.minimizeIcon
InternalFrame.minimizeIconBackground
InternalFrame.resizeIconHighlight
InternalFrame.resizeIconShadow
InternalFrame.restoreButtonToolTip
InternalFrame.titleFont
InternalFrame.windowBindings
InternalFrameUI
InternalFrameTitlePane.closeButtonText
InternalFrameTitlePane.maximizeButtonText
InternalFrameTitlePane.minimizeButtonText
InternalFrameTitlePane.moveButtonText
InternalFrameTitlePane.restoreButtonText
InternalFrameTitlePane.sizeButtonText
Label.background
Label.disabledForeground
Label.disabledShadow
Label.font
Label.foreground
LabelUI
List.background
List.cellRenderer
List.focusCellHighlightBorder
List.focusInputMap
List.font
List.foreground
List.selectionBackground
List.selectionForeground
ListUI
Menu.acceleratorFont
Menu.acceleratorForeground
Menu.acceleratorSelectionForeground
Menu.arrowIcon
Menu.background
Menu.border
Menu.borderPainted
Menu.checkIcon
Menu.consumesTabs
Menu.font
Menu.foreground
Menu.margin
Menu.selectedWindowInputMapBindings
Menu.selectionBackground
Menu.selectionForeground
MenuUI
MenuBar.background
MenuBar.border
MenuBar.font
MenuBar.foreground
MenuBar.windowBindings
MenuBarUI
MenuItem.acceleratorDelimiter
MenuItem.acceleratorFont
MenuItem.acceleratorForeground
MenuItem.acceleratorSelectionForeground
MenuItem.arrowIcon
MenuItem.background
MenuItem.border
MenuItem.borderPainted
MenuItem.checkIcon
MenuItem.font
MenuItem.foreground
MenuItem.margin
MenuItem.selectionBackground
MenuItem.selectionForeground
MenuItemUI
OptionPane.background
OptionPane.border
OptionPane.buttonAreaBorder
OptionPane.cancelButtonText
OptionPane.errorIcon
OptionPane.font
OptionPane.foreground
OptionPane.informationIcon
OptionPane.messageAreaBorder
OptionPane.messageForeground
OptionPane.minimumSize
OptionPane.noButtonText
OptionPane.okButtonText
OptionPane.questionIcon
OptionPane.titleText
OptionPane.warningIcon
OptionPane.windowBindings
OptionPane.yesButtonText
OptionPaneUI
Panel.background
Panel.font
Panel.foreground
PanelUI
PasswordField.background
PasswordField.border
PasswordField.caretBlinkRate
PasswordField.caretForeground
PasswordField.focusInputMap
PasswordField.font
PasswordField.foreground
PasswordField.inactiveForeground
PasswordField.keyBindings
PasswordField.margin
PasswordField.selectionBackground
PasswordField.selectionForeground
PasswordFieldUI
PopupMenu.background
PopupMenu.border
PopupMenu.font
PopupMenu.foreground
PopupMenu.selectedWindowInputMapBindings
PopupMenuUI
PopupMenuSeparatorUI
ProgressBar.background
ProgressBar.border
ProgressBar.cellLength
ProgressBar.cellSpacing
ProgressBar.font
ProgressBar.foreground
ProgressBar.selectionBackground
ProgressBar.selectionForeground
ProgressBarUI
ProgressMonitor.progressText
RadioButton.background
RadioButton.border
RadioButton.darkShadow
RadioButton.focus
RadioButton.focusInputMap
RadioButton.font
RadioButton.foreground
RadioButton.highlight
RadioButton.icon
RadioButton.margin
RadioButton.shadow
RadioButton.textIconGap
RadioButton.textShiftOffset
RadioButtonUI
RadioButtonMenuItem.acceleratorFont
RadioButtonMenuItem.acceleratorForeground
RadioButtonMenuItem.acceleratorSelectionForeground
RadioButtonMenuItem.arrowIcon
RadioButtonMenuItem.background
RadioButtonMenuItem.border
RadioButtonMenuItem.borderPainted
RadioButtonMenuItem.checkIcon
RadioButtonMenuItem.font
RadioButtonMenuItem.foreground
RadioButtonMenuItem.margin
RadioButtonMenuItem.selectionBackground
RadioButtonMenuItem.selectionForeground
RadioButtonMenuItemUI
RootPane.defaultButtonWindowKeyBindings
RootPaneUI
ScrollBar.background
ScrollBar.focusInputMap
ScrollBar.foreground
ScrollBar.maximumThumbSize
ScrollBar.minimumThumbSize
ScrollBar.thumb
ScrollBar.thumbDarkShadow
ScrollBar.thumbHighlight
ScrollBar.thumbLightShadow
ScrollBar.track
ScrollBar.trackHighlight
ScrollBarUI
ScrollPane.ancestorInputMap
ScrollPane.background
ScrollPane.border
ScrollPane.font
ScrollPane.foreground
ScrollPaneUI
Separator.background
Separator.foreground
Separator.highlight
Separator.shadow
SeparatorUI
Slider.background
Slider.focus
Slider.focusInputMap
Slider.focusInsets
Slider.foreground
Slider.highlight
Slider.shadow
SliderUI
SplitPane.ancestorInputMap
SplitPane.background
SplitPane.border
SplitPane.dividerSize
SplitPane.leftButtonText
SplitPane.rightButtonText
SplitPane.shadow
SplitPaneUI
SplitPaneDivider.border
StandardDialogUI
FocusManagerClassName
activeCaption
activeCaptionBorder
activeCaptionText
control
controlDkShadow
controlHighlight
controlLtHighlight
controlShadow
controlText
desktop
inactiveCaption
inactiveCaptionBorder
inactiveCaptionText
info
infoText
menu
menuPressedItemB
menuPressedItemF
menuText
scrollbar
text
textHighlight
textHighlightText
textInactiveText
textText
window
windowBorder
windowText
TabbedPane.ancestorInputMap
TabbedPane.background
TabbedPane.contentBorderInsets
TabbedPane.darkShadow
TabbedPane.focus
TabbedPane.focusInputMap
TabbedPane.font
TabbedPane.foreground
TabbedPane.highlight
TabbedPane.lightHighlight
TabbedPane.selectedTabPadInsets
TabbedPane.shadow
TabbedPane.tabAreaInsets
TabbedPane.tabInsets
TabbedPane.tabRunOverlay
TabbedPane.textIconGap
TabbedPaneUI
Table.ancestorInputMap
Table.background
Table.focusCellBackground
Table.focusCellForeground
Table.focusCellHighlightBorder
Table.font
Table.foreground
Table.gridColor
Table.scrollPaneBorder
Table.selectionBackground
Table.selectionForeground
TableUI
TableHeader.background
TableHeader.cellBorder
TableHeader.font
TableHeader.foreground
TableHeaderUI
TextArea.background
TextArea.border
TextArea.caretBlinkRate
TextArea.caretForeground
TextArea.focusInputMap
TextArea.font
TextArea.foreground
TextArea.inactiveForeground
TextArea.keyBindings
TextArea.margin
TextArea.selectionBackground
TextArea.selectionForeground
TextAreaUI
TextField.background
TextField.border
TextField.caretBlinkRate
TextField.caretForeground
TextField.focusInputMap
TextField.font
TextField.foreground
TextField.inactiveForeground
TextField.keyBindings
TextField.margin
TextField.selectionBackground
TextField.selectionForeground
TextFieldUI
TextPane.background
TextPane.border
TextPane.caretBlinkRate
TextPane.caretForeground
TextPane.focusInputMap
TextPane.font
TextPane.foreground
TextPane.inactiveForeground
TextPane.keyBindings
TextPane.margin
TextPane.selectionBackground
TextPane.selectionForeground
TextPaneUI
TitledBorder.border
TitledBorder.font
TitledBorder.titleColor
ToggleButton.background
ToggleButton.border
ToggleButton.focus
ToggleButton.focusInputMap
ToggleButton.font
ToggleButton.foreground
ToggleButton.margin
ToggleButton.textIconGap
ToggleButton.textShiftOffset
ToggleButtonUI
ToolBar.ancestorInputMap
ToolBar.background
ToolBar.border
ToolBar.dockingBackground
ToolBar.dockingForeground
ToolBar.floatingBackground
ToolBar.floatingForeground
ToolBar.font
ToolBar.foreground
ToolBar.separatorSize
ToolBarUI
ToolBarSeparatorUI
ToolTip.background
ToolTip.border
ToolTip.font
ToolTip.foreground
ToolTipUI
Tree.ancestorInputMap
Tree.background
Tree.changeSelectionWithFocus
Tree.closedIcon
Tree.collapsedIcon
Tree.drawsFocusBorderAroundIcon
Tree.editorBorder
Tree.expandedIcon
Tree.focusInputMap
Tree.font
Tree.foreground
Tree.hash
Tree.leafIcon
Tree.leftChildIndent
Tree.openIcon
Tree.rightChildIndent
Tree.rowHeight
Tree.scrollsOnExpand
Tree.selectionBackground
Tree.selectionBorderColor
Tree.selectionForeground
Tree.textBackground
Tree.textForeground
TreeUI
Viewport.background
Viewport.font
Viewport.foreground
ViewportUI


*/

/*
 AbstractButton.clickText
 AbstractDocument.additionText
 AbstractDocument.deletionText
 AbstractDocument.redoText
 AbstractDocument.styleChangeText
 AbstractDocument.undoText
 AbstractUndoableEdit.redoText
 AbstractUndoableEdit.undoText
 Button.background
 Button.border
 Button.disabledText
 Button.focus
 Button.focusInputMap
 Button.font
 Button.foreground
 Button.margin
 Button.select
 Button.textIconGap
 Button.textShiftOffset
 ButtonUI
 CheckBox.background
 CheckBox.border
 CheckBox.disabledText
 CheckBox.focus
 CheckBox.focusInputMap
 CheckBox.font
 CheckBox.foreground
 CheckBox.icon
 CheckBox.margin
 CheckBox.textIconGap
 CheckBox.textShiftOffset
 CheckBoxMenuItem.acceleratorFont
 CheckBoxMenuItem.acceleratorForeground
 CheckBoxMenuItem.acceleratorSelectionForeground
 CheckBoxMenuItem.arrowIcon
 CheckBoxMenuItem.background
 CheckBoxMenuItem.border
 CheckBoxMenuItem.borderPainted
 CheckBoxMenuItem.checkIcon
 CheckBoxMenuItem.disabledForeground
 CheckBoxMenuItem.font
 CheckBoxMenuItem.foreground
 CheckBoxMenuItem.margin
 CheckBoxMenuItem.selectionBackground
 CheckBoxMenuItem.selectionForeground
 CheckBoxMenuItemUI
 CheckBoxUI
 Checkbox.select
 ColorChooser.background
 ColorChooser.cancelText
 ColorChooser.font
 ColorChooser.foreground
 ColorChooser.hsbBlueText
 ColorChooser.hsbBrightnessText
 ColorChooser.hsbGreenText
 ColorChooser.hsbHueText
 ColorChooser.hsbNameText
 ColorChooser.hsbRedText
 ColorChooser.hsbSaturationText
 ColorChooser.okText
 ColorChooser.previewText
 ColorChooser.resetText
 ColorChooser.rgbBlueMnemonic
 ColorChooser.rgbBlueText
 ColorChooser.rgbGreenMnemonic
 ColorChooser.rgbGreenText
 ColorChooser.rgbNameText
 ColorChooser.rgbRedMnemonic
 ColorChooser.rgbRedText
 ColorChooser.sampleText
 ColorChooser.swatchesDefaultRecentColor
 ColorChooser.swatchesNameText
 ColorChooser.swatchesRecentSwatchSize
 ColorChooser.swatchesRecentText
 ColorChooser.swatchesSwatchSize
 ColorChooserUI
 ComboBox.ancestorInputMap
 ComboBox.background
 ComboBox.disabledBackground
 ComboBox.disabledForeground
 ComboBox.font
 ComboBox.foreground
 ComboBox.listBackground
 ComboBox.listForeground
 ComboBox.selectionBackground
 ComboBox.selectionForeground
 ComboBox.togglePopupText
 ComboBoxUI
 Desktop.ancestorInputMap
 Desktop.background
 DesktopIcon.background
 DesktopIcon.border
 DesktopIcon.font
 DesktopIcon.foreground
 DesktopIconUI
 DesktopPaneUI
 EditorPane.background
 EditorPane.border
 EditorPane.caretBlinkRate
 EditorPane.caretForeground
 EditorPane.focusInputMap
 EditorPane.font
 EditorPane.foreground
 EditorPane.inactiveForeground
 EditorPane.keyBindings
 EditorPane.margin
 EditorPane.selectionBackground
 EditorPane.selectionForeground
 EditorPaneUI
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
 FileView.computerIcon
 FileView.directoryIcon
 FileView.fileIcon
 FileView.floppyDriveIcon
 FileView.hardDriveIcon
 FocusManagerClassName
 FormView.resetButtonText
 FormView.submitButtonText
 InternalFrame.activeTitleBackground
 InternalFrame.activeTitleForeground
 InternalFrame.border
 InternalFrame.closeIcon
 InternalFrame.font
 InternalFrame.icon
 InternalFrame.iconifyIcon
 InternalFrame.inactiveTitleBackground
 InternalFrame.inactiveTitleForeground
 InternalFrame.maximizeIcon
 InternalFrame.minimizeIcon
 InternalFrame.optionDialogBorder
 InternalFrame.paletteBorder
 InternalFrame.paletteCloseIcon
 InternalFrame.paletteTitleHeight
 InternalFrame.titleFont
 InternalFrame.windowBindings
 InternalFrameTitlePane.closeButtonAccessibleName
 InternalFrameTitlePane.closeButtonText
 InternalFrameTitlePane.iconifyButtonAccessibleName
 InternalFrameTitlePane.maximizeButtonAccessibleName
 InternalFrameTitlePane.maximizeButtonText
 InternalFrameTitlePane.minimizeButtonText
 InternalFrameTitlePane.moveButtonText
 InternalFrameTitlePane.restoreButtonText
 InternalFrameTitlePane.sizeButtonText
 InternalFrameUI
 Label.background
 Label.disabledForeground
 Label.disabledShadow
 Label.font
 Label.foreground
 LabelUI
 List.background
 List.cellRenderer
 List.focusCellHighlightBorder
 List.focusInputMap
 List.font
 List.foreground
 List.selectionBackground
 List.selectionForeground
 ListUI
 Menu.acceleratorFont
 Menu.acceleratorForeground
 Menu.acceleratorSelectionForeground
 Menu.arrowIcon
 Menu.background
 Menu.border
 Menu.borderPainted
 Menu.checkIcon
 Menu.consumesTabs
 Menu.disabledForeground
 Menu.font
 Menu.foreground
 Menu.margin
 Menu.selectedWindowInputMapBindings
 Menu.selectionBackground
 Menu.selectionForeground
 MenuBar.background
 MenuBar.border
 MenuBar.font
 MenuBar.foreground
 MenuBar.windowBindings
 MenuBarUI
 MenuItem.acceleratorDelimiter
 MenuItem.acceleratorFont
 MenuItem.acceleratorForeground
 MenuItem.acceleratorSelectionForeground
 MenuItem.arrowIcon
 MenuItem.background
 MenuItem.border
 MenuItem.borderPainted
 MenuItem.checkIcon
 MenuItem.disabledForeground
 MenuItem.font
 MenuItem.foreground
 MenuItem.margin
 MenuItem.selectionBackground
 MenuItem.selectionForeground
 MenuItemUI
 MenuUI
 OptionPane.background
 OptionPane.border
 OptionPane.buttonAreaBorder
 OptionPane.cancelButtonText
 OptionPane.errorIcon
 OptionPane.font
 OptionPane.foreground
 OptionPane.informationIcon
 OptionPane.messageAreaBorder
 OptionPane.messageForeground
 OptionPane.minimumSize
 OptionPane.noButtonText
 OptionPane.okButtonText
 OptionPane.questionIcon
 OptionPane.titleText
 OptionPane.warningIcon
 OptionPane.windowBindings
 OptionPane.yesButtonText
 OptionPaneUI
 Panel.background
 Panel.font
 Panel.foreground
 PanelUI
 PasswordField.background
 PasswordField.border
 PasswordField.caretBlinkRate
 PasswordField.caretForeground
 PasswordField.focusInputMap
 PasswordField.font
 PasswordField.foreground
 PasswordField.inactiveForeground
 PasswordField.keyBindings
 PasswordField.margin
 PasswordField.selectionBackground
 PasswordField.selectionForeground
 PasswordFieldUI
 PopupMenu.background
 PopupMenu.border
 PopupMenu.font
 PopupMenu.foreground
 PopupMenu.selectedWindowInputMapBindings
 PopupMenuSeparatorUI
 PopupMenuUI
 ProgressBar.background
 ProgressBar.backgroundHighlight
 ProgressBar.border
 ProgressBar.cellLength
 ProgressBar.cellSpacing
 ProgressBar.font
 ProgressBar.foreground
 ProgressBar.foregroundHighlight
 ProgressBar.selectionBackground
 ProgressBar.selectionForeground
 ProgressBarUI
 ProgressMonitor.progressText
 RadioButton.background
 RadioButton.border
 RadioButton.disabledText
 RadioButton.focus
 RadioButton.focusInputMap
 RadioButton.font
 RadioButton.foreground
 RadioButton.icon
 RadioButton.margin
 RadioButton.select
 RadioButton.textIconGap
 RadioButton.textShiftOffset
 RadioButtonMenuItem.acceleratorFont
 RadioButtonMenuItem.acceleratorForeground
 RadioButtonMenuItem.acceleratorSelectionForeground
 RadioButtonMenuItem.arrowIcon
 RadioButtonMenuItem.background
 RadioButtonMenuItem.border
 RadioButtonMenuItem.borderPainted
 RadioButtonMenuItem.checkIcon
 RadioButtonMenuItem.disabledForeground
 RadioButtonMenuItem.font
 RadioButtonMenuItem.foreground
 RadioButtonMenuItem.margin
 RadioButtonMenuItem.selectionBackground
 RadioButtonMenuItem.selectionForeground
 RadioButtonMenuItemUI
 RadioButtonUI
 RootPane.defaultButtonWindowKeyBindings
 RootPaneUI
 ScrollBar.allowsAbsolutePositioning
 ScrollBar.background
 ScrollBar.darkShadow
 ScrollBar.focusInputMap
 ScrollBar.foreground
 ScrollBar.highlight
 ScrollBar.maximumThumbSize
 ScrollBar.minimumThumbSize
 ScrollBar.shadow
 ScrollBar.thumb
 ScrollBar.thumbDarkShadow
 ScrollBar.thumbHighlight
 ScrollBar.thumbLightShadow
 ScrollBar.thumbShadow
 ScrollBar.track
 ScrollBar.trackHighlight
 ScrollBar.width
 ScrollBarUI
 ScrollPane.ancestorInputMap
 ScrollPane.background
 ScrollPane.border
 ScrollPane.font
 ScrollPane.foreground
 ScrollPaneUI
 Separator.background
 Separator.foreground
 Separator.highlight
 Separator.shadow
 SeparatorUI
 Slider.background
 Slider.focus
 Slider.focusInputMap
 Slider.focusInsets
 Slider.foreground
 Slider.highlight
 Slider.horizontalThumbIcon
 Slider.majorTickLength
 Slider.shadow
 Slider.trackWidth
 Slider.verticalThumbIcon
 SliderUI
 SplitPane.ancestorInputMap
 SplitPane.background
 SplitPane.border
 SplitPane.dividerSize
 SplitPane.highlight
 SplitPane.leftButtonText
 SplitPane.rightButtonText
 SplitPane.shadow
 SplitPaneDivider.border
 SplitPaneUI
 StandardDialogUI
 TabbedPane.ancestorInputMap
 TabbedPane.background
 TabbedPane.contentBorderInsets
 TabbedPane.darkShadow
 TabbedPane.focus
 TabbedPane.focusInputMap
 TabbedPane.font
 TabbedPane.foreground
 TabbedPane.highlight
 TabbedPane.lightHighlight
 TabbedPane.selectHighlight
 TabbedPane.selected
 TabbedPane.selectedTabPadInsets
 TabbedPane.shadow
 TabbedPane.tabAreaBackground
 TabbedPane.tabAreaInsets
 TabbedPane.tabInsets
 TabbedPane.tabRunOverlay
 TabbedPane.textIconGap
 TabbedPaneUI
 Table.ancestorInputMap
 Table.background
 Table.focusCellBackground
 Table.focusCellForeground
 Table.focusCellHighlightBorder
 Table.font
 Table.foreground
 Table.gridColor
 Table.scrollPaneBorder
 Table.selectionBackground
 Table.selectionForeground
 TableHeader.background
 TableHeader.cellBorder
 TableHeader.font
 TableHeader.foreground
 TableHeaderUI
 TableUI
 TextArea.background
 TextArea.border
 TextArea.caretBlinkRate
 TextArea.caretForeground
 TextArea.focusInputMap
 TextArea.font
 TextArea.foreground
 TextArea.inactiveForeground
 TextArea.keyBindings
 TextArea.margin
 TextArea.selectionBackground
 TextArea.selectionForeground
 TextAreaUI
 TextField.background
 TextField.border
 TextField.caretBlinkRate
 TextField.caretForeground
 TextField.focusInputMap
 TextField.font
 TextField.foreground
 TextField.inactiveForeground
 TextField.keyBindings
 TextField.margin
 TextField.selectionBackground
 TextField.selectionForeground
 TextFieldUI
 TextPane.background
 TextPane.border
 TextPane.caretBlinkRate
 TextPane.caretForeground
 TextPane.focusInputMap
 TextPane.font
 TextPane.foreground
 TextPane.inactiveForeground
 TextPane.keyBindings
 TextPane.margin
 TextPane.selectionBackground
 TextPane.selectionForeground
 TextPaneUI
 TitledBorder.border
 TitledBorder.font
 TitledBorder.titleColor
 ToggleButton.background
 ToggleButton.border
 ToggleButton.disabledBackground
 ToggleButton.disabledSelectedBackground
 ToggleButton.disabledSelectedText
 ToggleButton.disabledText
 ToggleButton.focus
 ToggleButton.focusInputMap
 ToggleButton.font
 ToggleButton.foreground
 ToggleButton.margin
 ToggleButton.select
 ToggleButton.text
 ToggleButton.textIconGap
 ToggleButton.textShiftOffset
 ToggleButtonUI
 ToolBar.ancestorInputMap
 ToolBar.background
 ToolBar.border
 ToolBar.dockingBackground
 ToolBar.dockingForeground
 ToolBar.floatingBackground
 ToolBar.floatingForeground
 ToolBar.font
 ToolBar.foreground
 ToolBar.separatorSize
 ToolBarSeparatorUI
 ToolBarUI
 ToolTip.background
 ToolTip.border
 ToolTip.font
 ToolTip.foreground
 ToolTipUI
 Tree.ancestorInputMap
 Tree.background
 Tree.changeSelectionWithFocus
 Tree.closedIcon
 Tree.collapsedIcon
 Tree.drawsFocusBorderAroundIcon
 Tree.editorBorder
 Tree.expandedIcon
 Tree.focusInputMap
 Tree.font
 Tree.foreground
 Tree.hash
 Tree.leafIcon
 Tree.leftChildIndent
 Tree.line
 Tree.openIcon
 Tree.rightChildIndent
 Tree.rowHeight
 Tree.scrollsOnExpand
 Tree.selectionBackground
 Tree.selectionBorderColor
 Tree.selectionForeground
 Tree.textBackground
 Tree.textForeground
 TreeUI
 Viewport.background
 Viewport.font
 Viewport.foreground
 ViewportUI
 activeCaption
 activeCaptionBorder
 activeCaptionText
 control
 controlDkShadow
 controlHighlight
 controlLtHighlight
 controlShadow
 controlText
 desktop
 inactiveCaption
 inactiveCaptionBorder
 inactiveCaptionText
 info
 infoText
 menu
 menuText
 scrollbar
 text
 textHighlight
 textHighlightText
 textInactiveText
 textText
 window
 windowBorder
windowText
*/
