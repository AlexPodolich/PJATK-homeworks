package com.company;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Set;

public class Server {
    private static int RMI_PORT = 1099;
    public static void main(String[] args) throws Exception {
        try {
            LocateRegistry.createRegistry(RMI_PORT);
            RemoteObject remoteObject = new RemoteObject();
            System.out.println("SERVER HAS STARTED");

            //binding override methods from remoteObject
            Naming.bind("echo", remoteObject);
            Naming.bind("add", remoteObject);
        } catch (Throwable exception){
            exception.printStackTrace();
        }
    }

}
