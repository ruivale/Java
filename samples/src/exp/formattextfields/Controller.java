package exp.formattextfields;


import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;


/**
 * A simple application used in testing a <code>JFormattedTextField</code>.
 * Contains a bunch of widgets for manipluating a <code>RegexFormatter</code>,
 * as well as widgets for displaying the state of the
 * <code>JFormattedTextField</code>. Any change in the widgets will
 * immediately be pushed to the <code>RegexFormatter</code> and
 * the <code>JFormattedTextField</code>.
 */
public class Controller {
  private static final RegexDescription[] examples = new RegexDescription[] {
      new RegexDescription(
          "My testig number",
          "101[01234567]\\d{2}",
          "101001"),
      new RegexDescription(
          "Last, First",
          "\\p{Upper}\\p{Lower}*, \\p{Upper}\\p{Lower}*",
          "Rubble, Barney"),
      new RegexDescription(
          "At least 10 characters",
          "\\p{Alpha}{10,}",
          "Schloomemgroven"),
      new RegexDescription(
          "Social Security Number",
          "\\d{3}-\\d{2}-\\d{4}",
          "000-00-0000"),
      new RegexDescription(
          "IP Address",
          "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}",
          "128.0.0.1"),
      new RegexDescription(
          "HTTP URL",
          "http://.*",
          "http://www.sun.com")
  };
  private JFrame frame;
  private JFormattedTextField ftf;
  private JTextField maskTF;
  private JCheckBox allowsInvalid;
  private JCheckBox overwriteMode;
  private JCheckBox commitOnValidEdit;
  private JToggleButton revert;
  private JToggleButton commit;
  private JToggleButton commitRevert;
  private JToggleButton persist;
  private JLabel valueLabel;
  private JLabel validLabel;

  public Controller() {
    createWidgets();
    layoutWidgets();

    frame.setSize(new Dimension(450, 350));
    showExample(examples[0]);
    frame.show();
  }

  private void createWidgets() {
    frame = new JFrame("Regex JFormattedTextField");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ftf = new JFormattedTextField();
    ftf.addPropertyChangeListener((PropertyChangeListener) EventHandler.
                                  create(PropertyChangeListener.class,
                                         this, "propertyChange", ""));

    ActionListener recreateAL = (ActionListener) EventHandler.create(
        ActionListener.class, this, "recreate");

    maskTF = new JTextField();
    maskTF.addActionListener(recreateAL);

    allowsInvalid = new JCheckBox("Allows Invalid");
    allowsInvalid.setSelected(true);
    allowsInvalid.addActionListener(recreateAL);

    overwriteMode = new JCheckBox("Overwrite");
    overwriteMode.addActionListener(recreateAL);

    commitOnValidEdit = new JCheckBox("Commit On Valid Edit");
    commitOnValidEdit.setSelected(true);
    commitOnValidEdit.addActionListener(recreateAL);

    ButtonGroup focusBG = new ButtonGroup();

    revert = new JRadioButton("Revert");
    revert.addActionListener(recreateAL);
    focusBG.add(revert);

    commit = new JRadioButton("Commit");
    commit.addActionListener(recreateAL);
    focusBG.add(commit);

    commitRevert = new JRadioButton("Commit Or Revert");
    commitRevert.setSelected(true);
    commitRevert.addActionListener(recreateAL);
    focusBG.add(commitRevert);

    persist = new JRadioButton("Persist");
    persist.addActionListener(recreateAL);
    focusBG.add(persist);

    valueLabel = new JLabel("null");
    validLabel = new JLabel("true");
  }

  private void layoutWidgets() {
    SpringLayout parentLayout = new SpringLayout();
    JPanel parent = new JPanel(parentLayout);

    SpringLayout panelLayout = new SpringLayout();
    JPanel panel = new JPanel(panelLayout);

    panel.setBorder(new TitledBorder("Configuration"));

    JLabel label = new JLabel("Examples:");

    JComboBox cb = new JComboBox(examples);

    panel.add(label);
    panelLayout.getConstraints(label).setX(Spring.constant(5));
    panelLayout.getConstraints(label).setY(Spring.constant(5));

    cb.addActionListener((ActionListener) EventHandler.
                         create(ActionListener.class,
                                this, "showExample", "source"));
    panel.add(cb);
    panelLayout.putConstraint("West", cb, 4, "East", label);
    panelLayout.getConstraints(cb).setWidth(null);
    panelLayout.putConstraint("East", cb, 0, "East", panel);
    panelLayout.putConstraint("South", cb, 0, "South", label);

    panel.add(maskTF);
    panelLayout.putConstraint("West", maskTF, 4, "East", label);
    panelLayout.getConstraints(maskTF).setWidth(null);
    panelLayout.putConstraint("East", maskTF, 0, "East", panel);
    panelLayout.putConstraint("North", maskTF, 4, "South", cb);

    label = new JLabel("Mask:");
    panel.add(label);
    panelLayout.getConstraints(label).setX(Spring.constant(5));
    panelLayout.putConstraint("South", label, 0, "South", maskTF);

    panel.add(allowsInvalid);
    panelLayout.putConstraint("North", allowsInvalid, 4, "South", maskTF);
    panelLayout.putConstraint("West", allowsInvalid, 0, "West", label);

    panel.add(overwriteMode);
    panelLayout.putConstraint("North", overwriteMode, 4, "South",
                              allowsInvalid);
    panelLayout.putConstraint("West", overwriteMode, 0, "West",
                              label);

    panel.add(commitOnValidEdit);
    panelLayout.putConstraint("North", commitOnValidEdit, 4, "South",
                              overwriteMode);
    panelLayout.putConstraint("West", commitOnValidEdit, 0, "West",
                              label);

    Box focusPanel = Box.createHorizontalBox();
    focusPanel.setBorder(new TitledBorder("Focus Lost Behavior"));
    focusPanel.add(revert);
    focusPanel.add(commit);
    focusPanel.add(commitRevert);
    focusPanel.add(persist);

    panel.add(focusPanel);
    Spring focusPanelWidth = panelLayout.getConstraints(focusPanel).
                             getWidth();
    panelLayout.putConstraint("West", focusPanel, 0, "West", label);
    panelLayout.getConstraints(focusPanel).setWidth(null);
    panelLayout.putConstraint("East", focusPanel, 0, "East", panel);
    panelLayout.putConstraint("North", focusPanel, 4, "South",
                              commitOnValidEdit);

    panelLayout.putConstraint("South", panel, 4, "South", focusPanel);

    parent.add(panel);
    parentLayout.getConstraints(panel).setWidth(null);
    parentLayout.putConstraint("East", panel, 0, "East", parent);

    SpringLayout ftfLayout = new SpringLayout();
    JPanel ftfPanel = new JPanel(ftfLayout);

    ftfPanel.setBorder(new TitledBorder("JFormattedTextField"));

    ftfPanel.add(ftf);
    ftfLayout.getConstraints(ftf).setWidth(null);
    ftfLayout.putConstraint("East", ftf, 0, "East", ftfPanel);
    ftfLayout.getConstraints(ftf).setX(Spring.constant(5));
    ftfLayout.getConstraints(ftf).setY(Spring.constant(5));

    label = new JLabel("Value:");
    ftfPanel.add(label);
    ftfLayout.putConstraint("North", label, 4, "South", ftf);
    ftfLayout.putConstraint("West", label, 0, "West", ftf);

    ftfPanel.add(valueLabel);
    ftfLayout.putConstraint("North", valueLabel, 4, "South", ftf);
    ftfLayout.putConstraint("West", valueLabel, 4, "East", label);

    label = new JLabel("Valid:");
    ftfPanel.add(label);
    ftfLayout.putConstraint("North", label, 4, "South", valueLabel);
    ftfLayout.putConstraint("West", label, 0, "West", ftf);

    ftfPanel.add(validLabel);
    ftfLayout.putConstraint("North", validLabel, 4, "South", valueLabel);
    ftfLayout.putConstraint("West", validLabel, 4, "East", label);
    ftfLayout.putConstraint("South", ftfPanel, 0, "South", label);

    parent.add(ftfPanel);
    parentLayout.getConstraints(ftfPanel).setWidth(null);
    parentLayout.putConstraint("East", ftfPanel, 0, "East", parent);

    parentLayout.getConstraints(ftfPanel).setY(null);
    parentLayout.getConstraints(panel).setHeight(null);
    parentLayout.putConstraint("South", ftfPanel, 0, "South", parent);
    parentLayout.putConstraint("South", panel, 0, "North", ftfPanel);

    frame.setContentPane(parent);
  }

  /**
   * Resets the fields based on the selection in the JComboBox (the source).
   */
  public void showExample(JComboBox source) {
    showExample((RegexDescription) source.getSelectedItem());
  }

  public void showExample(RegexDescription re) {
    maskTF.setText(re.getRegularExpression());
    recreate();
    ftf.setValue(re.getInitialValue());
  }

  /**
   * Resets the <code>RegexFormatter</code>.
   */
  public void recreate() {
    String text = maskTF.getText();

    if (text == null) {
      return;
    }
    RegexFormatter formatter = new RegexFormatter(maskTF.getText());

    formatter.setAllowsInvalid(allowsInvalid.isSelected());
    formatter.setOverwriteMode(overwriteMode.isSelected());
    formatter.setCommitsOnValidEdit(commitOnValidEdit.isSelected());
    if (revert.isSelected()) {
      ftf.setFocusLostBehavior(JFormattedTextField.REVERT);
    } else {
      if (commit.isSelected()) {
        ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
      } else {
        if (commitRevert.isSelected()) {
          ftf.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        } else {
          if (persist.isSelected()) {
            ftf.setFocusLostBehavior(JFormattedTextField.PERSIST);
          }
        }
      }
    }

    ftf.setFormatterFactory(new DefaultFormatterFactory(formatter));
  }

  /**
   * Invoked when a property changes on the <code>JFormattedTextField</code>,
   * resets the necessary widgets.
   */
  public void propertyChange(PropertyChangeEvent pce) {
    if ("value".equals(pce.getPropertyName())) {
      Object value = ftf.getValue();

      if (value == null) {
        valueLabel.setText("null");
      } else {
        valueLabel.setText(value.toString());
      }
    } else {
      if ("editValid".equals(pce.getPropertyName())) {
        validLabel.setText(Boolean.toString(ftf.isEditValid()));
      }
    }
  }


  public static void main(String[] args) {
    new Controller();
  }


  private static class RegexDescription {
    private String regex;
    private String description;
    private String initialValue;

    public RegexDescription(String description, String regex,
                            String value) {
      this.description = description;
      this.regex = regex;
      initialValue = value;
    }

    public String getInitialValue() {
      return initialValue;
    }

    public String getRegularExpression() {
      return regex;
    }

    public String getDescription() {
      return description;
    }

    public String toString() {
      return description;
    }
  }
}
