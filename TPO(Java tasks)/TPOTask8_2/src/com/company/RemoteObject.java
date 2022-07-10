package com.company;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements IEcho,IAdd {

    protected RemoteObject() throws RemoteException {

    }

    @Override
    public AddResponse add(AddRequest addRequest) throws RemoteException {
        return new AddResponse(addRequest.getNum1(), addRequest.getNum2());
    }

    @Override
    public EchoResponse echo(EchoRequest echoRequest) throws RemoteException {
        return new EchoResponse(echoRequest.getMessage());
    }
}
