/**
 * MyService_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zsk.service;

public interface MyService_Service extends javax.xml.rpc.Service {
    public java.lang.String getMyServicePortAddress();

    public com.zsk.service.MyService_PortType getMyServicePort() throws javax.xml.rpc.ServiceException;

    public com.zsk.service.MyService_PortType getMyServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
