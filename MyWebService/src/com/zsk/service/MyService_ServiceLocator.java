/**
 * MyService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zsk.service;

public class MyService_ServiceLocator extends org.apache.axis.client.Service implements com.zsk.service.MyService_Service {

    public MyService_ServiceLocator() {
    }


    public MyService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MyService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MyServicePort
    private java.lang.String MyServicePort_address = "http://127.0.0.1:8080/server";

    public java.lang.String getMyServicePortAddress() {
        return MyServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MyServicePortWSDDServiceName = "MyServicePort";

    public java.lang.String getMyServicePortWSDDServiceName() {
        return MyServicePortWSDDServiceName;
    }

    public void setMyServicePortWSDDServiceName(java.lang.String name) {
        MyServicePortWSDDServiceName = name;
    }

    public com.zsk.service.MyService_PortType getMyServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MyServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMyServicePort(endpoint);
    }

    public com.zsk.service.MyService_PortType getMyServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zsk.service.MyServicePortBindingStub _stub = new com.zsk.service.MyServicePortBindingStub(portAddress, this);
            _stub.setPortName(getMyServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMyServicePortEndpointAddress(java.lang.String address) {
        MyServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zsk.service.MyService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zsk.service.MyServicePortBindingStub _stub = new com.zsk.service.MyServicePortBindingStub(new java.net.URL(MyServicePort_address), this);
                _stub.setPortName(getMyServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MyServicePort".equals(inputPortName)) {
            return getMyServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.zsk.com", "MyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.zsk.com", "MyServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MyServicePort".equals(portName)) {
            setMyServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
