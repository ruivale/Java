package exp.java3d;


//import com.sun.j3d.utils.universe.SimpleUniverse;
//
//import java.awt.BorderLayout;
//import java.awt.Font;
//import java.awt.GraphicsConfiguration;
//
//import javax.media.j3d.Alpha;
//import javax.media.j3d.Appearance;
//import javax.media.j3d.BoundingSphere;
//import javax.media.j3d.BranchGroup;
//import javax.media.j3d.Canvas3D;
//import javax.media.j3d.DirectionalLight;
//import javax.media.j3d.Font3D;
//import javax.media.j3d.FontExtrusion;
//import javax.media.j3d.Material;
//import javax.media.j3d.RotationInterpolator;
//import javax.media.j3d.Shape3D;
//import javax.media.j3d.Text3D;
//import javax.media.j3d.Transform3D;
//import javax.media.j3d.TransformGroup;
//
import javax.swing.JFrame;
//
//import javax.vecmath.Color3f;
//import javax.vecmath.Vector3f;


/**
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c)
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Text3DWorld
    extends JFrame {
//
//  //~ Static fields/initializers ///////////////////////////////////////////////
//
//  /** .. */
//  private static final String text = "R G V";
//
//  //~ Instance fields //////////////////////////////////////////////////////////
//
//  /** .. */
//  private Transform3D rotate1 = new Transform3D();
//
//  /** .. */
//  private Transform3D rotate2 = new Transform3D();
//
//  //~ Constructors /////////////////////////////////////////////////////////////
//
//  /**
//   *
//   */
//  public Text3DWorld() {
//    super(text);
//
//    Canvas3D    canvas3D = createCanvas3D();
//    BranchGroup scene = createSceneGraph();
//    connect(canvas3D, scene);
//  }
//
//  //~ Methods //////////////////////////////////////////////////////////////////
//
//  /**
//   * Insert doc ...
//   *
//   * @param args  Insert doc ...
//   */
//  public static void main(String[] args) {
//    new Text3DWorld().setVisible(true);
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @return BranchGroup
//   */
//  public BranchGroup createSceneGraph() {
//    BranchGroup    objRoot = new BranchGroup();
//    TransformGroup mover   = moveTextBack();
//    TransformGroup spinner = createSpinner();
//    objRoot.addChild(mover);
//    mover.addChild(spinner);
//    spinner.addChild(createTextShape());
//    spinner.addChild(makeSpin(spinner));
//    setLighting(mover);
//
//    return objRoot;
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @param objMove TransformGroup
//   */
//  private void setLighting(TransformGroup objMove) {
//    DirectionalLight light = new DirectionalLight();
//    light.setInfluencingBounds(new BoundingSphere());
//    light.setDirection(new Vector3f(0.0f, 0.0f, -1.0f));
//    light.setColor(new Color3f(0.0f, 1.0f, 1.0f));
//    objMove.addChild(light);
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @param canvas3D Canvas3D
//   * @param scene BranchGroup
//   */
//  private void connect(
//    Canvas3D    canvas3D,
//    BranchGroup scene) {
//    SimpleUniverse simpleU = new SimpleUniverse(canvas3D);
//    simpleU.getViewingPlatform()
//           .setNominalViewingTransform();
//    simpleU.addBranchGraph(scene);
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @return Canvas3D
//   */
//  private Canvas3D createCanvas3D() {
//    setSize(300, 300);
//    getContentPane()
//      .setLayout(new BorderLayout());
//
//    GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
//    Canvas3D              canvas3D = new Canvas3D(config);
//    setSize(300, 300);
//    getContentPane()
//      .add(canvas3D);
//
//    return canvas3D;
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @return TransformGroup
//   */
//  private TransformGroup createSpinner() {
//    TransformGroup spinner = new TransformGroup();
//    spinner.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
//
//    return spinner;
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @return Shape3D
//   */
//  private Shape3D createTextShape() {
//    Appearance textAppear = new Appearance();
//    textAppear.setMaterial(new Material());
//
//    Font3D font3D =
//      new Font3D(new Font("Helvetica", Font.PLAIN, 1), new FontExtrusion());
//    Text3D textGeom = new Text3D(font3D, text);
//    textGeom.setAlignment(Text3D.ALIGN_CENTER);
//
//    Shape3D textShape = new Shape3D();
//    textShape.setGeometry(textGeom);
//    textShape.setAppearance(textAppear);
//
//    return textShape;
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @param spinner TransformGroup
//   *
//   * @return RotationInterpolator
//   */
//  private RotationInterpolator makeSpin(TransformGroup spinner) {
//    RotationInterpolator rotator =
//      new RotationInterpolator(new Alpha(-1, 3000), spinner);
//    rotator.setTransformAxis(rotateCube());
//
//    BoundingSphere bounds = new BoundingSphere();
//    rotator.setSchedulingBounds(bounds);
//
//    return rotator;
//  }
//
//  /**
//   * moveTextBack
//   *
//   * @return TransformGroup
//   */
//  private TransformGroup moveTextBack() {
//    Transform3D transform3D = new Transform3D();
//    transform3D.setTranslation(new Vector3f(0.0f, 0.0f, -5.0f));
//
//    return new TransformGroup(transform3D);
//  }
//
//  /**
//   * DOCUMENT ME!
//   *
//   * @return Transform3D
//   */
//  private Transform3D rotateCube() {
//    rotate1.rotX(Math.PI / 4.0d);
//    rotate2.rotY(Math.PI / 3.0d);
//    rotate1.mul(rotate2);
//
//    return rotate1;
//  }
}
