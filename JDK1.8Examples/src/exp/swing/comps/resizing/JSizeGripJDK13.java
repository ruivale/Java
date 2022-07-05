package exp.swing.comps.resizing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JSizeGripJDK13 extends JComponent {
   Robot _robot;
   Window _window;
   Point _offset = new Point();
   ComponentAdapter _maximizeWindow;

   public JSizeGripJDK13(){
      try{
         _robot = new Robot();
         _maximizeWindow = new ComponentAdapter(){
            long _prevTime;
            boolean _isLastResize;

            public void componentResized(ComponentEvent e) {
               checkMaximize(true);
            }
            public void componentMoved(ComponentEvent e) {
               checkMaximize(false);
            }
            public void checkMaximize(boolean resize){
               long currTime = System.currentTimeMillis();
               if((_isLastResize != resize) &&(_prevTime+100 > currTime)){
                  Rectangle rect = _window.getBounds();
                  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                  if((rect.x == rect.y) && (rect.x <= 0) && (rect.x > -20) &&
                      (dim.width -20 < rect.width) && (dim.width +20 > rect.width)){
                     setVisible(false);
                  }
                  else{
                     setVisible(true);
                  }
               }
               _isLastResize = resize;
               _prevTime = currTime;
            }
         };
         enableEvents(AWTEvent.MOUSE_EVENT_MASK);
         setAutoscrolls(true);
         setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
      }
      catch(AWTException ex){
         setVisible(false);
         _robot = null;
      }
   }

   protected void processMouseEvent(MouseEvent e) {
      super.processMouseEvent(e);
      switch(e.getID()){
         case MouseEvent.MOUSE_PRESSED:
            Dimension dim = _window.getSize();
            Point point = SwingUtilities.convertPoint(this,e.getPoint(),_window);
            _offset.x = dim.width  - point.x-2;
            _offset.y = dim.height - point.y-2;

            Point p = e.getPoint();
            SwingUtilities.convertPointToScreen(p,this);

            _robot.mouseRelease(InputEvent.BUTTON1_MASK);
            _robot.mouseMove(p.x+_offset.x,p.y+_offset.y);
            _robot.mousePress(InputEvent.BUTTON1_MASK);
            break;
      }
   }
   public Dimension getMinimumSize(){
      return new Dimension(16,16);
   }
   public Dimension getPreferredSize(){
      return getMinimumSize();
   }
   public void removeNotify(){
      if(_robot != null){
         _window.removeComponentListener(_maximizeWindow);
      }
   }
   public void addNotify(){
      super.addNotify();
      if(_robot != null){
         _window = (Window)SwingUtilities.getRoot(this);
         _window.addComponentListener(_maximizeWindow);
      }
   }
   public void paint(Graphics g){
      super.paint(g);
      Dimension dim = getSize();
      Insets insets = getInsets();
      dim.width -= insets.right+2;
      dim.height -= insets.bottom+2;

      for(int i = 1; i < 12; ++i){
         int mod = i % 4;
         if(mod == 3){
            g.setColor(UIManager.getColor("ScrollBar.thumbHighlight"));
         }
         else if(mod == 0){
            continue;
         }
         else{
            g.setColor(UIManager.getColor("ScrollBar.thumbShadow"));
         }

         g.drawLine(dim.width-i,dim.height,dim.width,dim.height-i);
      }
   }


   public static void main(String[] s){
     JSizeGripJDK13 j = new JSizeGripJDK13();
     j.setToolTipText("skjdskdjkjasjda");
     final JFrame f = new JFrame( "Modal Internal Frame");
     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     f.getContentPane().setLayout(new BorderLayout());
     f.getContentPane().add(j, BorderLayout.CENTER);
     f.setSize(500, 300);
     f.setVisible(true);
   }


}
