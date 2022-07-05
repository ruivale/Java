
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

     import javax.swing.*;





     /**

      * An application that displays a frame.

      * It demonstrates the glassPane in the JFrame class.

      *

      * @author Mike Foley

      **/

     public class GlassPaneTest extends Object {



         private static JComponent createContent() {



             JPanel content = new JPanel();

             JButton addButton = new JButton( "Add Glass Pane" );

             content.add( addButton );

             addButton.addActionListener( new ActionListener() {

                 public void actionPerformed( ActionEvent e ) {

     Component root = SwingUtilities.getRoot( ( Component )

          e.getSource() );

                     if( root instanceof JFrame ) {

                         JFrame frame = ( JFrame )root;



                         Component glassPane = frame.getGlassPane();

                         glassPane.addMouseListener( new MouseAdapter() { }  );

     glassPane.setCursor( Cursor.getPredefinedCursor

         ( Cursor.WAIT_CURSOR ) );

                         glassPane.setVisible( true );

                     }

                 }

             }  );



             return( content );

         }



         /**

          * Application entry point.

          * Create the frame, and display it.

          *

          * @param args Command line parameter. Not used.

          **/

         public static void main( String args[] ) {



             JFrame frame = new JFrame( "Glass Pane Test" );



             frame.getContentPane().add( createContent(),

                                         BorderLayout.CENTER );



             frame.pack();



             frame.setVisible( true );



         }  // main



     }  // GlassPaneTest

