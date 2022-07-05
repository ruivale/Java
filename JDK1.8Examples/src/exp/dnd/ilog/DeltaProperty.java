
package exp.dnd.ilog;

/*
 * Copyright (C) 1996-2001 by ILOG.
 * All rights reserved.
 *
 * This source code is an addition to the ILOG JViews Reference Manual
 * delivered  with the JViews library.
 * It is supplied as an example to show you how to code with JViews.
 * Feel free to use, copy or modify it within the framework of your
 * JViews license agreement.
 */
/*
 * $Id: DeltaProperty.java,v 1.5 2001/01/11 12:57:42 jolif Exp $
 */


import java.io.*;
import ilog.views.*;
import ilog.views.io.*;

/**
 * <code>DeltaProperty</code> is a class which allows to associate
 * with an IlvGraphic the position of the mouse pointer inside it.
 * It is usefult to renember this position during DnD operations
 * @see DragAdapter
 * @see DropAdapter
 */
public class DeltaProperty extends IlvNamedProperty
{
  private IlvPoint value;

  public static String NAME = "DELTA";

  public DeltaProperty(IlvInputStream stream) throws IlvReadFileException
  {
    super(stream);
    this.value = stream.readPoint("value");
  }

  public DeltaProperty(IlvPoint delta)
  {
    super(NAME);
    value = delta;
  }

  public DeltaProperty(DeltaProperty source)
  {
    super(source);
    this.value = source.value;
  }

  public IlvNamedProperty copy()
  {
    return new DeltaProperty(this);
  }

  public boolean isPersistent()
  {
    return true;
  }

  public void write(IlvOutputStream stream) throws IOException
  {
    super.write(stream);
    stream.write("value", value);
  }

  public IlvPoint getDelta()
  {
    return value;
  }
}
