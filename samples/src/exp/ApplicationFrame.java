
/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package exp;

import java.awt.*;



     import java.awt.event.*;



     import java.io.Serializable;



     import javax.swing.*;



     import javax.swing.border.*;



     import javax.swing.event.*;





     /**

      * A toplevel frame. The frame customizes the

      * Icon and content in the frameInit method.

      *

      * @author Mike Foley

      **/

     public class ApplicationFrame extends JFrame implements Serializable {



         /**

          * MouserAdapter to consume mouse events on glassPane.

          **/

         private MouseAdapter ourMouseAdapter;





         /**

          * ApplicationFrame, null constructor.

          **/

         public ApplicationFrame() {

             this( null );

         }





         /**

          * ApplicationFrame, constructor.

          *

          * @param title The title for the frame.

          **/

         public ApplicationFrame( String title ) {

             super( title );

         }





         /**

          * Customize the frame for our application.

          **/

         protected void frameInit() {

             //

             // Let the super create the panes.

             super.frameInit();



             ourMouseAdapter = new MouseAdapter() { } ;



             Image bullseye = new ImageIcon( "bullseye.gif" ).getImage();

             setIconImage( bullseye );



             JMenuBar menuBar = createMenu();

             setJMenuBar( menuBar );



             JToolBar toolBar = createToolBar();

             Container content = getContentPane();

             content.add( BorderLayout.NORTH, toolBar );



             createApplicationContent();



         }  // frameInit





         /**

          * Create the content for the application

          **/

         protected void createApplicationContent() {

         }  // createApplicationContent





         /**

          * Create the menu for the frame.

          *

          * @return the menu for the frame.

          **/

         protected JMenuBar createMenu() {

             JMenuBar menuBar = new JMenuBar();



             JMenu file = new JMenu( "File" );

             file.setMnemonic( KeyEvent.VK_F );



             JMenuItem item;



             item = new JMenuItem( "Exit"/*, exitIcon */);

             item.setHorizontalTextPosition( SwingConstants.RIGHT );

             item.setMnemonic( KeyEvent.VK_X );

             item.setAccelerator( KeyStroke.getKeyStroke(

                                  KeyEvent.VK_X, Event.CTRL_MASK ) );

             item.addActionListener( new ActionListener() {

                 public void actionPerformed( ActionEvent e ) {

                     System.exit( 0 );

                 }

             }  );

             file.add( item );



             menuBar.add( file );

             return( menuBar );



         }  // createMenuBar





         /**

          * Create the mouse listener for the frame.

          *

          * @return the MouseListener for the content in the frame.

          **/

         protected MouseListener createMouseListener() {

             //return new ApplicationMouseListener();
             return null;

         }



         protected JToolBar createToolBar() {

             final JToolBar toolBar = new JToolBar();

             toolBar.setFloatable( false );

            // toolBar.add( new ExitAction() );

             return( toolBar );

         }





         /**

          * Show or hide the wait cursor. The wait cursor

          * is set on the glass pane so input mouse events

          * are blocked from other components.

          * <p>

          * @param waitCursorVisible True to show the wait cursor,

          *                          False to hide the wait cursor.

          **/

         public void setWaitCursorVisible( boolean waitCursorVisible ) {

             Component glassPane = getGlassPane();

             if( waitCursorVisible ) {

                 //

                 // Show the wait cursor.

                 //

                 glassPane.addMouseListener( ourMouseAdapter );

                 glassPane.setCursor(

                           Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );

                 glassPane.setVisible( true );

             }  else {

                 //

                 // Hide the wait cursor.

                 //

                 glassPane.removeMouseListener( ourMouseAdapter );

                 glassPane.setCursor(

                       Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

                 glassPane.setVisible( false );

             }

         }



     }  // ApplicationFrame

