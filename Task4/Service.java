package com.goosemane;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    Integer getMessage() throws RemoteException;
    void sendMessage(int num) throws RemoteException;
    void storeCalculatedMessage(int num) throws RemoteException;
}
