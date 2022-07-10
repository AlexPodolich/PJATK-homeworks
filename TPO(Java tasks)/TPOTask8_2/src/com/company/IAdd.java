package com.company;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAdd extends Remote {
    AddResponse add(AddRequest addRequest) throws RemoteException;
}
