package exp.swing.popupmenus;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



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
public class MyJPopupMenu extends JPanel{
  public MyJPopupMenu() {

    this.setLayout(new BorderLayout());

    final JButton b = new JButton("show popup menu");
    b.addMouseListener(new MouseAdapter(){
      public void mouseClicked(final MouseEvent e){
        System.out.println("clicked the button ...");

        JPopupMenu jpm = new JPopupMenu("Video");

        JMenu jm = new JMenu("Mostrar");
        //jm.setPreferredSize(new Dimension(150,100));
        jm.add(new JMenuItem("Mostrar todas"));
        jm.add(new JMenuItem("Mostrar seleccionadas"));
        jm.add(new JSeparator());

        jpm.add(jm);
        jpm.add(new JMenuItem("Esconder todas"));

        jpm.add(new JSeparator());
        jpm.add(new JPanel());
        JLabel l = new JLabel("  Tipo:");
        //l.setBackground(UIManager.getColor("List.selectionBackground"));
        jpm.add(l);

        JMenu jmm = new JMenu("Mosaico");
        //jm.setPreferredSize(new Dimension(150,100));

        JRadioButton rbVL = new JRadioButton(
            "Vertical esq.", new ImageIcon("src/exp/swing/popupmenus/VL.jpg"));
        JRadioButton rbVM = new JRadioButton(
            "Vertical meio", new ImageIcon("src/exp/swing/popupmenus/VM.jpg"));
        JRadioButton rbVR = new JRadioButton(
            "Vertical dir.", new ImageIcon("src/exp/swing/popupmenus/VR.jpg"));
        JRadioButton rbHT = new JRadioButton(
            "Horizontal topo", new ImageIcon("src/exp/swing/popupmenus/HT.jpg"));
        JRadioButton rbHM = new JRadioButton(
            "Horizontal meio", new ImageIcon("src/exp/swing/popupmenus/HM.jpg"));
        JRadioButton rbHB = new JRadioButton(
            "Horizontal baixo", new ImageIcon("src/exp/swing/popupmenus/HB.jpg"));

        jmm.add(rbVL);
        jmm.add(rbVM);
        jmm.add(rbVR);
        jmm.add(rbHT);
        jmm.add(rbHM);
        jmm.add(rbHB);

        //jpm.add(new JRadioButton(" Mosaico"));
        jpm.add(jmm);

        JRadioButton rbC =new JRadioButton(
            " Cascata",
            new ImageIcon("src/exp/swing/popupmenus/Cascade.jpg"));
        jpm.add(rbC);


        ButtonGroup bg = new ButtonGroup();
        bg.add(rbVL);
        bg.add(rbVM);
        bg.add(rbVR);
        bg.add(rbHT);
        bg.add(rbHM);
        bg.add(rbHB);
        bg.add(rbC);


        DefaultListModel model = new DefaultListModel();
        CodecsSWListCellRenderer cellRender = new CodecsSWListCellRenderer();
        JList list = new JList(model);
        list.setBackground(UIManager.getColor("MenuItem.background"));
        list.setCellRenderer(cellRender);

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBackground(UIManager.getColor("MenuItem.background"));
        jScrollPane.getViewport().setView(list);

        jm.add(jScrollPane);


        for (int i = 0; i < 10; i++) {
          //model.addElement(""+i);
          model.addElement(new JMenuItem("Câmara "+i));
        }

        jScrollPane.setPreferredSize(
            new Dimension(
                150,
                model.getSize()<5? 23*model.getSize(): 150));
        jScrollPane.setMaximumSize(new Dimension(300,400));
        jScrollPane.setMinimumSize(new Dimension(100,200));

        jpm.show(b, e.getX(), e.getY());
        //jpm.setVisible(true);

        System.out.println("AFTER");

        jpm.requestFocus();
        jpm.validate();
        jpm.repaint();

      }
    });

    this.add(b, BorderLayout.CENTER);

  }

  public static void main(String[] s) {
    MyJPopupMenu m = new MyJPopupMenu();
    JFrame f = new JFrame();
    f.getContentPane().setLayout(new BorderLayout());
    f.getContentPane().add(m, BorderLayout.CENTER);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setBounds(100,100,400,300);
    f.setVisible(true);

  }
}

class CodecsSWListCellRenderer
    extends DefaultListCellRenderer {


  public CodecsSWListCellRenderer() {
  }

  public Component getListCellRendererComponent(
      JList list,
      Object value,
      int index,
      boolean isSelected,
      boolean cellHasFocus) {

    if(value instanceof JMenuItem) {
      final JMenuItem jMenuItem = (JMenuItem)value;
      if (isSelected) {
        jMenuItem.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
      }else{
        jMenuItem.setBackground(UIManager.getColor("MenuItem.background"));
      }

      return jMenuItem;

    }else{
      return this;
    }
  }
}




