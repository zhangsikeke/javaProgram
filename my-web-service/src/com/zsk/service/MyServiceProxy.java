package com.zsk.service;

public class MyServiceProxy implements com.zsk.service.MyService_PortType {
  private String _endpoint = null;
  private com.zsk.service.MyService_PortType myService_PortType = null;
  
  public MyServiceProxy() {
    _initMyServiceProxy();
  }
  
  public MyServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initMyServiceProxy();
  }
  
  private void _initMyServiceProxy() {
    try {
      myService_PortType = (new com.zsk.service.MyService_ServiceLocator()).getMyServicePort();
      if (myService_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)myService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)myService_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (myService_PortType != null)
      ((javax.xml.rpc.Stub)myService_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.zsk.service.MyService_PortType getMyService_PortType() {
    if (myService_PortType == null)
      _initMyServiceProxy();
    return myService_PortType;
  }
  
  public java.lang.String showInfo(java.lang.String name) throws java.rmi.RemoteException{
    if (myService_PortType == null)
      _initMyServiceProxy();
    return myService_PortType.showInfo(name);
  }
  
  
}