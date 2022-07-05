package exp.javax.sound;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
import javax.swing.*;

/**
 *
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
public class CoreJavaSound
    extends Object implements LineListener {

  File soundFile;
  JDialog playingDialog;
  Clip clip;

  public static void main(String[] args) {
    args = new String[1];
    args[0] = "chord.wav";
    File f;
    if (args != null && args.length > 0) {
      f = new File(args[0]);
    } else {
      JFileChooser chooser = new JFileChooser();
      chooser.showOpenDialog(null);
      f = chooser.getSelectedFile();
    }

    try {
      CoreJavaSound s = new CoreJavaSound(f);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public CoreJavaSound(File f) throws LineUnavailableException, IOException,
      UnsupportedAudioFileException {
    soundFile = f;
    // prepare a dialog to display while playing
    JOptionPane pane = new JOptionPane("Playing " + f.getName(),
                                       JOptionPane.PLAIN_MESSAGE);
    playingDialog = pane.createDialog(null, "Application Sound");
    playingDialog.pack();

    // get and play sound
    Line.Info linfo = new Line.Info(Clip.class);
    Line line = AudioSystem.getLine(linfo);
    clip = (Clip) line;
    //clip.addLineListener(this);
    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
    clip.open(ais);
    clip.loop(1);
    clip.start();

    while (true) {
      try {
        Thread.sleep(234);
      } catch (Exception ex) {
      }
      System.out.println("clip.isRunning()=" + clip.isRunning());
    }
  }

  // LineListener
  public void update(LineEvent le) {
    LineEvent.Type type = le.getType();
    if (type == LineEvent.Type.OPEN) {
      System.out.println("OPEN");
    } else {
      if (type == LineEvent.Type.CLOSE) {
        System.out.println("CLOSE");
        //System.exit(0);
      } else {
        if (type == LineEvent.Type.START) {
          System.out.println("START");
          //playingDialog.setVisible(true);
          //clip.close();
        } else {
          if (type == LineEvent.Type.STOP) {
            System.out.println("STOP");
            //playingDialog.setVisible(false);
          }
        }
      }
    }
  }
}
