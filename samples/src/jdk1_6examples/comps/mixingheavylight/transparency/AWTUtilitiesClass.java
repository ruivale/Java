/**
 * <p>
 * Classname:  jdk1_6examples.comps.mixingheavylight.transparency.AWTUtilitiesClass
 * </p>
 *
 * <p>Copyright: Copyright (c) 2009 EFACEC SE
 * <br>
 * This software is the confidential and proprietary information of EFACEC SE.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into EFACEC SE.
 * </p>
 * <p>Company: EFACEC SE
 * <br>
 * Rua Engº Frederico Ulrich - Apartado 3081
 * <br>
 * 4471-907 Moreira da Maia
 * <br>
 * PORTUGAL
 * <br>
 * Tel.: +351 22 943 20 00
 * <br>
 * Fax: +351 22 943 20 50
 * <br>
 * Web: www.efacec.pt
 * <br>
 * Email: te@efacec.pt
 * </p>
 */

package jdk1_6examples.comps.mixingheavylight.transparency;


import java.awt.Component;
import java.awt.Shape;
import java.lang.reflect.Method;


public class AWTUtilitiesClass {

    private static Method mSetComponentMixing;

//    static{ try {
//        Class awtUtilitiesClass =
//              Class.forName("com.sun.awt.AWTUtilities");
//        Method mSetComponentMixing =
//              awtUtilitiesClass.getMethod(
//                            "setComponentMixingCutoutShape",
//                            Component.class, Shape.class);
//              mSetComponentMixing.invoke(null, component, shape);
//	} catch (Exception ex) {
//              ex.printStackTrace();
//	}
//    }


    public static void setMixingCutoutShape(Component c, Shape s)
    {
        if (mSetComponentMixing != null) {
            try {
                mSetComponentMixing.invoke( null, c, s );

            } catch (Exception ex) {
                ex.printStackTrace();
            }
	}
    }
}
