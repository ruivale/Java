/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package java22.recordswithinlineclasses;

/**
 * <p>
 * Description:
 * 
 * Records with Inline Classes (JEP 447 – Preview)
 * 
 * While records were introduced in Java 16, Java 22 brings a preview of records with inline classes. 
 * This allows defining a compact class directly within the record definition, useful for associated 
 * helper functionality.
 * 
 * </p>
 *
 * @author rUI vALE - {rui dot vale at efacec dot com}
 *
 * @since 240430
 */
public record PointOld(int x, int y) {

  public double distanceToOrigin() {
    PointOld origin = new PointOld(0, 0);
    // calculate distance
    
    return (double)10;
  }
}
