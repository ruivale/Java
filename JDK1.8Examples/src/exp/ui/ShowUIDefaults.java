package exp.ui;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


/**
Color scheme
------------

activeCaption         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
activeCaptionBorder   =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
activeCaptionText     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
control               =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
controlDkShadow       =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
controlHighlight      =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
controlLtHighlight    =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
controlShadow         =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
controlText           =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
desktop               =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
inactiveCaption       =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
inactiveCaptionBorder =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
inactiveCaptionText   =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
info                  =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
infoText              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
menu                  =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
menuText              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
scrollbar             =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
text                  =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
textHighlight         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
textHighlightText     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
textInactiveText      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
textText              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
window                =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
windowBorder          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
windowText            =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]

Swing Components
----------------

Button.background        =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Button.border            =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@72380
Button.disabledText      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Button.focus             =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
Button.focusInputMap     =javax.swing.plaf.InputMapUIResource@1319c
Button.font              =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
Button.foreground        =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Button.margin            =javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14]
Button.select            =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Button.textIconGap       =4
Button.textShiftOffset   =0
ButtonUI                 =javax.swing.plaf.metal.MetalButtonUI

CheckBox.background      =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
CheckBox.border          =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@5d20f6
CheckBox.disabledText    =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
CheckBox.focus           =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
CheckBox.focusInputMap   =javax.swing.plaf.InputMapUIResource@6768e
CheckBox.font            =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
CheckBox.foreground      =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
CheckBox.icon            =javax.swing.plaf.metal.MetalIconFactory$CheckBoxIcon@122221
CheckBox.margin          =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
Checkbox.select          =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
CheckBox.textIconGap     =4
CheckBox.textShiftOffset =0

CheckBoxMenuItem.acceleratorFont
                         =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=10]
CheckBoxMenuItem.acceleratorForeground
                         =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
CheckBoxMenuItem.acceleratorSelectionForeground
                         =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
CheckBoxMenuItem.arrowIcon
                         =javax.swing.plaf.metal.MetalIconFactory$MenuItemArrowIcon@7ae3c6
CheckBoxMenuItem.background
                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
CheckBoxMenuItem.border  =javax.swing.plaf.metal.MetalBorders$MenuItemBorder@6152c5
CheckBoxMenuItem.borderPainted
                         =true
CheckBoxMenuItem.checkIcon           =javax.swing.plaf.metal.MetalIconFactory$CheckBoxMenuItemIcon@1adc30
CheckBoxMenuItem.disabledForeground  =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
CheckBoxMenuItem.font                =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
CheckBoxMenuItem.foreground          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
CheckBoxMenuItem.margin              =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
CheckBoxMenuItem.selectionBackground =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
CheckBoxMenuItem.selectionForeground =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
CheckBoxMenuItemUI                   =javax.swing.plaf.basic.BasicCheckBoxMenuItemUI
CheckBoxUI                           =javax.swing.plaf.metal.MetalCheckBoxUI

ColorChooser.background          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ColorChooser.cancelText          =Cancel
ColorChooser.font                =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
ColorChooser.foreground          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ColorChooser.hsbBlueText         =B
ColorChooser.hsbBrightnessText   =B
ColorChooser.hsbGreenText        =G
ColorChooser.hsbHueText          =H
ColorChooser.hsbNameText         =HSB
ColorChooser.hsbRedText          =R
ColorChooser.hsbSaturationText   =S
ColorChooser.okText              =OK
ColorChooser.previewText         =Preview
ColorChooser.resetText           =Reset
ColorChooser.rgbBlueMnemonic     =66
ColorChooser.rgbBlueText         =Blue
ColorChooser.rgbGreenMnemonic    =71
ColorChooser.rgbGreenText        =Green
ColorChooser.rgbNameText         =RGB
ColorChooser.rgbRedMnemonic      =82
ColorChooser.rgbRedText          =Red
ColorChooser.sampleText          =Sample Text  Sample Text
ColorChooser.swatchesDefaultRecentColor      =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ColorChooser.swatchesNameText                =Swatches
ColorChooser.swatchesRecentSwatchSize        =java.awt.Dimension[width=10,height=10]
ColorChooser.swatchesRecentText              =Recent:
ColorChooser.swatchesSwatchSize              =java.awt.Dimension[width=10,height=10]
ColorChooserUI                   =javax.swing.plaf.basic.BasicColorChooserUI

ComboBox.ancestorInputMap        =javax.swing.plaf.InputMapUIResource@21c887
ComboBox.background              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ComboBox.disabledBackground      =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ComboBox.disabledForeground      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ComboBox.font                    =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
ComboBox.foreground              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ComboBox.listBackground          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ComboBox.listForeground          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ComboBox.selectionBackground     =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
ComboBox.selectionForeground     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ComboBoxUI                       =javax.swing.plaf.metal.MetalComboBoxUI

Desktop.ancestorInputMap         =javax.swing.plaf.InputMapUIResource@422ede
Desktop.background               =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
DesktopIcon.background           =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
DesktopIcon.border               =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@389838
DesktopIcon.font                 =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
DesktopIcon.foreground           =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
DesktopIconUI                    =javax.swing.plaf.metal.MetalDesktopIconUI
DesktopPaneUI                    =javax.swing.plaf.basic.BasicDesktopPaneUI

EditorPane.background            =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
EditorPane.border                =javax.swing.plaf.basic.BasicBorders$MarginBorder@5b0afd
EditorPane.caretBlinkRate        =500
EditorPane.caretForeground       =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
EditorPane.focusInputMap         =javax.swing.plaf.InputMapUIResource@66a22b
EditorPane.font                  =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
EditorPane.foreground            =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
EditorPane.inactiveForeground    =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
EditorPane.keyBindings           =[Ljavax.swing.text.JTextComponent$KeyBinding;@26b249
EditorPane.margin                =javax.swing.plaf.InsetsUIResource[top=3,left=3,bottom=3,right=3]
EditorPane.selectionBackground   =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
EditorPane.selectionForeground   =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
EditorPaneUI                     =javax.swing.plaf.basic.BasicEditorPaneUI

FileChooser.acceptAllFileFilterText          =All Files (*.*)
FileChooser.ancestorInputMap                 =javax.swing.plaf.InputMapUIResource@49b9ca
FileChooser.cancelButtonMnemonic             =67
FileChooser.cancelButtonText                 =Cancel
FileChooser.cancelButtonToolTipText          =Abort file chooser dialog
FileChooser.detailsViewButtonAccessibleName  =Details
FileChooser.detailsViewButtonToolTipText     =Details
FileChooser.detailsViewIcon                  =javax.swing.plaf.metal.MetalIconFactory$FileChooserDetailViewIcon@1edf4
FileChooser.directoryDescriptionText         =Directory
FileChooser.fileDescriptionText              =Generic File
FileChooser.fileNameLabelMnemonic            =78
FileChooser.fileNameLabelText                =File name:
FileChooser.filesOfTypeLabelMnemonic         =84
FileChooser.filesOfTypeLabelText             =Files of type:
FileChooser.helpButtonMnemonic               =72
FileChooser.helpButtonText                   =Help
FileChooser.helpButtonToolTipText            =FileChooser help
FileChooser.homeFolderAccessibleName         =Home
FileChooser.homeFolderIcon                   =javax.swing.plaf.metal.MetalIconFactory$FileChooserHomeFolderIcon@246701
FileChooser.homeFolderToolTipText            =Home
FileChooser.listViewButtonAccessibleName     =List
FileChooser.listViewButtonToolTipText        =List
FileChooser.listViewIcon                     =javax.swing.plaf.metal.MetalIconFactory$FileChooserListViewIcon@18f375
FileChooser.lookInLabelMnemonic              =73
FileChooser.lookInLabelText                  =Look in:
FileChooser.newFolderAccessibleNam           =New Folder
FileChooser.newFolderErrorSeparator          =:
FileChooser.newFolderErrorText               =Error creating new folder
FileChooser.newFolderIcon                    =javax.swing.plaf.metal.MetalIconFactory$FileChooserNewFolderIcon@39240e
FileChooser.newFolderToolTipText             =Create New Folder
FileChooser.openButtonMnemonic               =79
FileChooser.openButtonText                   =Open
FileChooser.openButtonToolTipText            =Open selected file
FileChooser.saveButtonMnemonic               =83
FileChooser.saveButtonText                   =Save
FileChooser.saveButtonToolTipText            =Save selected file
FileChooser.updateButtonMnemonic             =85
FileChooser.updateButtonText                 =Update
FileChooser.updateButtonToolTipText          =Update directory listing
FileChooser.upFolderAccessibleName           =Up
FileChooser.upFolderIcon                     =javax.swing.plaf.metal.MetalIconFactory$FileChooserUpFolderIcon@733675
FileChooser.upFolderToolTipText              =Up One Level
FileChooserUI                                =javax.swing.plaf.metal.MetalFileChooserUI

FileView.computerIcon                        =javax.swing.plaf.metal.MetalIconFactory$TreeComputerIcon@50a5d9
FileView.directoryIcon                       =javax.swing.plaf.metal.MetalIconFactory$TreeFolderIcon@62dae9
FileView.fileIcon                            =javax.swing.plaf.metal.MetalIconFactory$TreeLeafIcon@4cce3c
FileView.floppyDriveIcon                     =javax.swing.plaf.metal.MetalIconFactory$TreeFloppyDriveIcon@15c083
FileView.hardDriveIcon                       =javax.swing.plaf.metal.MetalIconFactory$TreeHardDriveIcon@3a317a

FocusManagerClassName                        =javax.swing.DefaultFocusManager

FormView.resetButtonText                     =Reset
FormView.submitButtonText                    =Submit Query

InternalFrame.activeTitleBackground          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
InternalFrame.activeTitleForeground          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
InternalFrame.border                         =javax.swing.plaf.metal.MetalBorders$InternalFrameBorder@59dc39
InternalFrame.closeIcon                      =javax.swing.plaf.metal.MetalIconFactory$InternalFrameCloseIcon@2d9c06
InternalFrame.font                           =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
InternalFrame.icon                           =javax.swing.plaf.metal.MetalIconFactory$InternalFrameDefaultMenuIcon@105738
InternalFrame.iconifyIcon                    =javax.swing.plaf.metal.MetalIconFactory$InternalFrameMinimizeIcon@79f9d8
InternalFrame.inactiveTitleBackground        =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
InternalFrame.inactiveTitleForeground        =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
InternalFrame.maximizeIcon                   =javax.swing.plaf.metal.MetalIconFactory$InternalFrameMaximizeIcon@2b6651
InternalFrame.minimizeIcon                   =javax.swing.plaf.metal.MetalIconFactory$InternalFrameAltMaximizeIcon@504653
InternalFrame.optionDialogBorder             =javax.swing.plaf.metal.MetalBorders$OptionDialogBorder@34bb5
InternalFrame.paletteBorder                  =javax.swing.plaf.metal.MetalBorders$PaletteBorder@22c95b
InternalFrame.paletteCloseIcon               =javax.swing.plaf.metal.MetalIconFactory$PaletteCloseIcon@195d80
InternalFrame.paletteTitleHeight             =11
InternalFrame.titleFont                      =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
InternalFrame.windowBindings                 =[Ljava.lang.Object;@64f6cd
InternalFrameUI                              =javax.swing.plaf.metal.MetalInternalFrameUI

Label.background                             =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Label.disabledForeground                     =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Label.disabledShadow                         =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Label.font                                   =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
Label.foreground                             =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
LabelUI                                      =javax.swing.plaf.metal.MetalLabelUI

List.background                              =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
List.cellRenderer
    =javax.swing.DefaultListCellRenderer$UIResource[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=null,border=javax.swing.border.EmptyBorder@121f1d,
    flags=32,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,horizontalAlignment=,horizontalTextPosition=,iconTextGap=4,
    labelFor=,text=,verticalAlignment=CENTER,verticalTextPosition=CENTER]
List.focusCellHighlightBorder                =javax.swing.plaf.BorderUIResource$LineBorderUIResource@5f5897
List.focusInputMap                           =javax.swing.plaf.InputMapUIResource@814e9
List.font                                    =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
List.foreground                              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
List.selectionBackground                     =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
List.selectionForeground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ListUI                                       =javax.swing.plaf.basic.BasicListUI

Menu.acceleratorFont                         =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=10]
Menu.acceleratorForeground                   =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
Menu.acceleratorSelectionForeground          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Menu.arrowIcon                               =javax.swing.plaf.metal.MetalIconFactory$MenuArrowIcon@5c8569
Menu.background                              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Menu.border                                  =javax.swing.plaf.metal.MetalBorders$MenuItemBorder@65e2c3
Menu.borderPainted                           =true
Menu.checkIcon                               =javax.swing.plaf.metal.MetalIconFactory$MenuItemCheckIcon@32e13d
Menu.consumesTabs                            =true
Menu.disabledForeground                      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Menu.font                                    =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
Menu.foreground                              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Menu.margin                                  =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
Menu.selectedWindowInputMapBindings          =[Ljava.lang.Object;@6df84b
Menu.selectionBackground                     =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
Menu.selectionForeground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]

MenuBar.background                           =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
MenuBar.border                               =javax.swing.plaf.metal.MetalBorders$MenuBarBorder@3383e9
MenuBar.font                                 =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
MenuBar.foreground                           =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
MenuBar.windowBindings                       =[Ljava.lang.Object;@68a1f6
MenuBarUI                                    =javax.swing.plaf.basic.BasicMenuBarUI

MenuItem.acceleratorDelimiter                =-
MenuItem.acceleratorFont                     =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=10]
MenuItem.acceleratorForeground               =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
MenuItem.acceleratorSelectionForeground      =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
MenuItem.arrowIcon                           =javax.swing.plaf.metal.MetalIconFactory$MenuItemArrowIcon@7ae3c6
MenuItem.background                          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
MenuItem.border                              =javax.swing.plaf.metal.MetalBorders$MenuItemBorder@23d4cf
MenuItem.borderPainted                       =true
MenuItem.checkIcon                           =javax.swing.plaf.metal.MetalIconFactory$MenuItemCheckIcon@32e13d
MenuItem.disabledForeground                  =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
MenuItem.font                                =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
MenuItem.foreground                          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
MenuItem.margin                              =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
MenuItem.selectionBackground                 =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
MenuItem.selectionForeground                 =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
MenuItemUI                                   =javax.swing.plaf.basic.BasicMenuItemUI
MenuUI                                       =javax.swing.plaf.basic.BasicMenuUI

OptionPane.background                        =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
OptionPane.border                            =javax.swing.plaf.BorderUIResource$EmptyBorderUIResource@7d5d2a
OptionPane.buttonAreaBorder                  =javax.swing.plaf.BorderUIResource$EmptyBorderUIResource@7816d
OptionPane.cancelButtonText                  =Cancel
OptionPane.errorIcon                         =javax.swing.plaf.IconUIResource@2a5330
OptionPane.font                              =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
OptionPane.foreground                        =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
OptionPane.informationIcon                   =javax.swing.plaf.IconUIResource@2bb514
OptionPane.messageAreaBorder                 =javax.swing.plaf.BorderUIResource$EmptyBorderUIResource@4832d2
OptionPane.messageForeground                 =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
OptionPane.minimumSize                       =javax.swing.plaf.DimensionUIResource[width=262,height=90]
OptionPane.noButtonText                      =No
OptionPane.okButtonText                      =OK
OptionPane.questionIcon                      =javax.swing.plaf.IconUIResource@54a328
OptionPane.warningIcon                       =javax.swing.plaf.IconUIResource@587c94
OptionPane.windowBindings                    =[Ljava.lang.Object;@1efb05
OptionPane.yesButtonText                     =Yes
OptionPaneUI                                 =javax.swing.plaf.basic.BasicOptionPaneUI

Panel.background                             =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Panel.font                                   =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
Panel.foreground                             =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
PanelUI                                      =javax.swing.plaf.basic.BasicPanelUI

PasswordField.background                     =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
PasswordField.border                         =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@4a8cd1
PasswordField.caretBlinkRate                 =500
PasswordField.caretForeground                =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
PasswordField.focusInputMap                  =javax.swing.plaf.InputMapUIResource@2d3ba4
PasswordField.font                           =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
PasswordField.foreground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
PasswordField.inactiveForeground             =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
PasswordField.keyBindings                    =[Ljavax.swing.text.JTextComponent$KeyBinding;@7ffe01
PasswordField.margin                         =javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0]
PasswordField.selectionBackground            =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
PasswordField.selectionForeground            =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
PasswordFieldUI                              =javax.swing.plaf.basic.BasicPasswordFieldUI

PopupMenu.background                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
PopupMenu.border                             =javax.swing.plaf.metal.MetalBorders$PopupMenuBorder@7b6889
PopupMenu.font                               =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
PopupMenu.foreground                         =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
PopupMenuSeparatorUI                         =javax.swing.plaf.metal.MetalPopupMenuSeparatorUI
PopupMenuUI                                  =javax.swing.plaf.basic.BasicPopupMenuUI

ProgressBar.background                       =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ProgressBar.backgroundHighlight              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ProgressBar.border                           =javax.swing.plaf.BorderUIResource$LineBorderUIResource@7ee8b8
ProgressBar.cellLength                       =1
ProgressBar.cellSpacing                      =0
ProgressBar.font                             =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
ProgressBar.foreground                       =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
ProgressBar.foregroundHighlight              =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
ProgressBar.selectionBackground              =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
ProgressBar.selectionForeground              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ProgressBarUI                                =javax.swing.plaf.metal.MetalProgressBarUI

RadioButton.background                       =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
RadioButton.border                           =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@1503a3
RadioButton.disabledText                     =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
RadioButton.focus                            =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
RadioButton.focusInputMap                    =javax.swing.plaf.InputMapUIResource@4fb549
RadioButton.font                             =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
RadioButton.foreground                       =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
RadioButton.icon                             =javax.swing.plaf.metal.MetalIconFactory$RadioButtonIcon@26804e
RadioButton.margin                           =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
RadioButton.select                           =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
RadioButton.textIconGap                      =4
RadioButton.textShiftOffset                  =0
RadioButtonMenuItem.acceleratorFont          =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=10]
RadioButtonMenuItem.acceleratorForeground    =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
RadioButtonMenuItem.acceleratorSelectionForeground=javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
RadioButtonMenuItem.arrowIcon                =javax.swing.plaf.metal.MetalIconFactory$MenuItemArrowIcon@7ae3c6
RadioButtonMenuItem.background               =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
RadioButtonMenuItem.border                   =javax.swing.plaf.metal.MetalBorders$MenuItemBorder@361fd1
RadioButtonMenuItem.borderPainted            =true
RadioButtonMenuItem.checkIcon                =javax.swing.plaf.metal.MetalIconFactory$RadioButtonMenuItemIcon@2d77a7
RadioButtonMenuItem.disabledForeground       =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
RadioButtonMenuItem.font                     =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
RadioButtonMenuItem.foreground               =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
RadioButtonMenuItem.margin                   =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=2]
RadioButtonMenuItem.selectionBackground      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
RadioButtonMenuItem.selectionForeground      =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
RadioButtonMenuItemUI                        =javax.swing.plaf.basic.BasicRadioButtonMenuItemUI
RadioButtonUI                                =javax.swing.plaf.metal.MetalRadioButtonUI

RootPane.defaultButtonWindowKeyBindings      =[Ljava.lang.Object;@c2ff5
RootPaneUI                                   =javax.swing.plaf.basic.BasicRootPaneUI

ScrollBar.allowsAbsolutePositioning          =true
ScrollBar.background                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ScrollBar.darkShadow                         =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
ScrollBar.focusInputMap                      =javax.swing.plaf.InputMapUIResource@6025e7
ScrollBar.foreground                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ScrollBar.highlight                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
ScrollBar.maximumThumbSize                   =javax.swing.plaf.DimensionUIResource[width=4096,height=4096]
ScrollBar.minimumThumbSize                   =javax.swing.plaf.DimensionUIResource[width=8,height=8]
ScrollBar.shadow                             =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ScrollBar.thumb                              =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
ScrollBar.thumbDarkShadow                    =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
ScrollBar.thumbHighlight                     =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
ScrollBar.thumbLightShadow                   =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ScrollBar.thumbShadow                        =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
ScrollBar.track                              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ScrollBar.trackHighlight                     =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
ScrollBar.width                              =17
ScrollBarUI                                  =javax.swing.plaf.metal.MetalScrollBarUI

ScrollPane.ancestorInputMap                  =javax.swing.plaf.InputMapUIResource@2a1e44
ScrollPane.background                        =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ScrollPane.border                            =javax.swing.plaf.metal.MetalBorders$ScrollPaneBorder@617189
ScrollPane.font                              =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
ScrollPane.foreground                        =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ScrollPaneUI                                 =javax.swing.plaf.metal.MetalScrollPaneUI

Separator.background                         =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Separator.foreground                         =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
Separator.highlight                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Separator.shadow                             =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
SeparatorUI                                  =javax.swing.plaf.metal.MetalSeparatorUI

Slider.background                            =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Slider.focus                                 =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
Slider.focusInputMap                         =javax.swing.plaf.InputMapUIResource@743399
Slider.focusInsets                           =javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0]
Slider.foreground                            =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
Slider.highlight                             =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Slider.horizontalThumbIcon                   =javax.swing.plaf.metal.MetalIconFactory$HorizontalSliderThumbIcon@3a6c83
Slider.majorTickLength                       =6
Slider.shadow                                =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Slider.trackWidth                            =7
Slider.verticalThumbIcon                     =javax.swing.plaf.metal.MetalIconFactory$VerticalSliderThumbIcon@2786c3
SliderUI                                     =javax.swing.plaf.metal.MetalSliderUI

SplitPane.ancestorInputMap                   =javax.swing.plaf.InputMapUIResource@4e5b1c
SplitPane.background                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
SplitPane.border                             =javax.swing.plaf.basic.BasicBorders$SplitPaneBorder@2981ca
SplitPane.dividerSize                        =10
SplitPane.highlight                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
SplitPane.shadow                             =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
SplitPaneDivider.border                      =javax.swing.plaf.basic.BasicBorders$SplitPaneDividerBorder@341960
SplitPaneUI                                  =javax.swing.plaf.metal.MetalSplitPaneUI

StandardDialogUI                             =javax.swing.plaf.basic.BasicStandardDialogUI

TabbedPane.ancestorInputMap                  =javax.swing.plaf.InputMapUIResource@aaa1e
TabbedPane.background                        =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
TabbedPane.contentBorderInsets               =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=3,right=3]
TabbedPane.darkShadow                        =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
TabbedPane.focus                             =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
TabbedPane.focusInputMap                     =javax.swing.plaf.InputMapUIResource@5b4f6f
TabbedPane.font                              =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
TabbedPane.foreground                        =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TabbedPane.highlight                         =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
TabbedPane.lightHighlight                    =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
TabbedPane.selected                          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
TabbedPane.selectedTabPadInsets              =javax.swing.plaf.InsetsUIResource[top=2,left=2,bottom=2,right=1]
TabbedPane.selectHighlight                   =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
TabbedPane.shadow                            =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
TabbedPane.tabAreaBackground                 =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
TabbedPane.tabAreaInsets                     =javax.swing.plaf.InsetsUIResource[top=4,left=2,bottom=0,right=6]
TabbedPane.tabInsets                         =javax.swing.plaf.InsetsUIResource[top=0,left=4,bottom=1,right=4]
TabbedPane.tabRunOverlay                     =2
TabbedPane.textIconGap                       =4
TabbedPaneUI                                 =javax.swing.plaf.metal.MetalTabbedPaneUI

Table.ancestorInputMap                       =javax.swing.plaf.InputMapUIResource@60b6f5
Table.background                             =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Table.focusCellBackground                    =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Table.focusCellForeground                    =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Table.focusCellHighlightBorder               =javax.swing.plaf.BorderUIResource$LineBorderUIResource@6e1408
Table.font                                   =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
Table.foreground                             =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Table.gridColor                              =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
Table.scrollPaneBorder                       =javax.swing.plaf.metal.MetalBorders$ScrollPaneBorder@186fab
Table.selectionBackground                    =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
Table.selectionForeground                    =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]

TableHeader.background                       =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
TableHeader.cellBorder                       =javax.swing.plaf.metal.MetalBorders$TableHeaderBorder@43c749
TableHeader.font                             =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
TableHeader.foreground                       =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TableHeaderUI                                =javax.swing.plaf.basic.BasicTableHeaderUI
TableUI                                      =javax.swing.plaf.basic.BasicTableUI

TextArea.background                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
TextArea.border                              =javax.swing.plaf.basic.BasicBorders$MarginBorder@55550d
TextArea.caretBlinkRate                      =500
TextArea.caretForeground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextArea.focusInputMap                       =javax.swing.plaf.InputMapUIResource@3efab0
TextArea.font                                =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
TextArea.foreground                          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextArea.inactiveForeground                  =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
TextArea.keyBindings                         =[Ljavax.swing.text.JTextComponent$KeyBinding;@26b249
TextArea.margin                              =javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0]
TextArea.selectionBackground                 =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
TextArea.selectionForeground                 =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextAreaUI                                   =javax.swing.plaf.basic.BasicTextAreaUI

TextField.background                         =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
TextField.border                             =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@551f60
TextField.caretBlinkRate                     =500
TextField.caretForeground                    =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextField.focusInputMap                      =javax.swing.plaf.InputMapUIResource@77f540
TextField.font                               =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
TextField.foreground                         =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextField.inactiveForeground                 =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
TextField.keyBindings                        =[Ljavax.swing.text.JTextComponent$KeyBinding;@7ffe01
TextField.margin                             =javax.swing.plaf.InsetsUIResource[top=0,left=0,bottom=0,right=0]
TextField.selectionBackground                =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
TextField.selectionForeground                =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextFieldUI                                  =javax.swing.plaf.metal.MetalTextFieldUI

TextPane.background                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
TextPane.border                              =javax.swing.plaf.basic.BasicBorders$MarginBorder@762373
TextPane.caretBlinkRate                      =500
TextPane.caretForeground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextPane.focusInputMap                       =javax.swing.plaf.InputMapUIResource@44aad3
TextPane.font                                =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
TextPane.foreground                          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextPane.inactiveForeground                  =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
TextPane.keyBindings                         =[Ljavax.swing.text.JTextComponent$KeyBinding;@26b249
TextPane.margin                              =javax.swing.plaf.InsetsUIResource[top=3,left=3,bottom=3,right=3]
TextPane.selectionBackground                 =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
TextPane.selectionForeground                 =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TextPaneUI                                   =javax.swing.plaf.basic.BasicTextPaneUI

TitledBorder.border                          =javax.swing.plaf.BorderUIResource$LineBorderUIResource@482923
TitledBorder.font                            =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
TitledBorder.titleColor                      =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]

ToggleButton.background                      =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToggleButton.border                          =javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@71fba0
ToggleButton.disabledBackground              =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToggleButton.disabledSelectedBackground      =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ToggleButton.disabledSelectedText            =javax.swing.plaf.ColorUIResource[r=102,g=102,b=102]
ToggleButton.disabledText                    =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ToggleButton.focus                           =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
ToggleButton.focusInputMap                   =javax.swing.plaf.InputMapUIResource@6e4648
ToggleButton.font                            =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
ToggleButton.foreground                      =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ToggleButton.margin                          =javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14]
ToggleButton.select                          =javax.swing.plaf.ColorUIResource[r=153,g=153,b=153]
ToggleButton.text                            =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToggleButton.textIconGap                     =4
ToggleButton.textShiftOffset                 =0
ToggleButtonUI                               =javax.swing.plaf.metal.MetalToggleButtonUI

ToolBar.ancestorInputMap                     =javax.swing.plaf.InputMapUIResource@655dd
ToolBar.background                           =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToolBar.border                               =javax.swing.plaf.metal.MetalBorders$ToolBarBorder@f4fb3
ToolBar.dockingBackground                    =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToolBar.dockingForeground                    =javax.swing.plaf.ColorUIResource[r=102,g=102,b=153]
ToolBar.floatingBackground                   =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
ToolBar.floatingForeground                   =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
ToolBar.font                                 =javax.swing.plaf.FontUIResource[family=dialog.bold,name=Dialog,style=bold,size=12]
ToolBar.foreground                           =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ToolBar.separatorSize                        =javax.swing.plaf.DimensionUIResource[width=10,height=10]
ToolBarSeparatorUI                           =javax.swing.plaf.basic.BasicToolBarSeparatorUI
ToolBarUI                                    =javax.swing.plaf.metal.MetalToolBarUI

ToolTip.background                           =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
ToolTip.border                               =javax.swing.plaf.BorderUIResource$LineBorderUIResource@42ea3f
ToolTip.font                                 =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
ToolTip.foreground                           =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ToolTipUI                                    =javax.swing.plaf.metal.MetalToolTipUI

Tree.ancestorInputMap                        =javax.swing.plaf.InputMapUIResource@50bd4d
Tree.background                              =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Tree.changeSelectionWithFocus                =true
Tree.closedIcon                              =javax.swing.plaf.metal.MetalIconFactory$TreeFolderIcon@6e293a
Tree.collapsedIcon                           =javax.swing.plaf.metal.MetalIconFactory$TreeControlIcon@421495
Tree.drawsFocusBorderAroundIcon              =false
Tree.editorBorder                            =javax.swing.plaf.BorderUIResource$LineBorderUIResource@67d940
Tree.expandedIcon                            =javax.swing.plaf.metal.MetalIconFactory$TreeControlIcon@2d3205
Tree.focusInputMap                           =javax.swing.plaf.InputMapUIResource@6f5502
Tree.font                                    =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
Tree.foreground                              =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Tree.hash                                    =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
Tree.leafIcon                                =javax.swing.plaf.metal.MetalIconFactory$TreeLeafIcon@8199
Tree.leftChildIndent                         =7
Tree.line                                    =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
Tree.openIcon                                =javax.swing.plaf.metal.MetalIconFactory$TreeFolderIcon@34bed0
Tree.rightChildIndent                        =13
Tree.rowHeight                               =0
Tree.scrollsOnExpand                         =true
Tree.selectionBackground                     =javax.swing.plaf.ColorUIResource[r=204,g=204,b=255]
Tree.selectionBorderColor                    =javax.swing.plaf.ColorUIResource[r=153,g=153,b=204]
Tree.selectionForeground                     =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
Tree.textBackground                          =javax.swing.plaf.ColorUIResource[r=255,g=255,b=255]
Tree.textForeground                          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
TreeUI                                       =javax.swing.plaf.metal.MetalTreeUI

Viewport.background                          =javax.swing.plaf.ColorUIResource[r=204,g=204,b=204]
Viewport.font                                =javax.swing.plaf.FontUIResource[family=dialog,name=Dialog,style=plain,size=12]
Viewport.foreground                          =javax.swing.plaf.ColorUIResource[r=0,g=0,b=0]
ViewportUI                                   =javax.swing.plaf.basic.BasicViewportUI
/**/

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

      if((key+"").startsWith("FileChooser")){
        System.out.println(key+"->"+value);
      }

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
//    // Set the Look and Feel
//    try {
//      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//    } catch (Exception e) {
//    }

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

      //for (int i = 0; i < rowData.length; i++) {
        //System.out.println(rowData[i][0]);
      //}

      this.rowData = rowData;
      this.columnNames = columnNames;
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
