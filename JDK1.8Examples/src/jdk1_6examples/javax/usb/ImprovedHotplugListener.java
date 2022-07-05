package jdk1_6examples.javax.usb;

import javax.usb.*;
import javax.usb.event.*;
import java.util.*;

public class ImprovedHotplugListener implements UsbServicesListener {

  private Map devices = new HashMap();

  public void usbDeviceAttached(UsbServicesEvent event) {
    UsbDevice device = event.getUsbDevice();
    String deviceInfo = getDeviceInfo(device); 
    devices.put(device, deviceInfo);
    System.out.println(deviceInfo + " was added to the bus.");
  }

  public void usbDeviceDetached(UsbServicesEvent event) {
    UsbDevice device = event.getUsbDevice();
    String deviceInfo = (String) devices.get(device);
    System.out.println(deviceInfo + " was removed from the bus.");
  }

  private static String getDeviceInfo(UsbDevice device) {
    try {
      String product = device.getProductString();
      String serial  = device.getSerialNumberString();
      if (product == null) return "Unknown USB device";
      if (serial != null) return product + " " + serial;
      else return product;
    }
    catch (Exception ex) {
    }
    return "Unknown USB device";
  }
}
