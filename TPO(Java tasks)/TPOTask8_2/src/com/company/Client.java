package com.company;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            IEcho echo = (IEcho) Naming.lookup("echo");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter echo message: ");
            String echoString = scanner.nextLine();
            EchoRequest echoRequest = new EchoRequest(echoString);
            EchoResponse echoResponse = echo.echo(echoRequest);
            System.out.println(echoResponse);

            IAdd add = (IAdd) Naming.lookup("add");
            System.out.println("Enter num1: ");
            int num1 = scanner.nextInt();
            System.out.println("Enter num2: ");
            int num2 = scanner.nextInt();
            AddRequest addRequest = new AddRequest(num1, num2);
            AddResponse addResponse = add.add(addRequest);
            System.out.println(addResponse);
        }catch (Throwable exception){
            exception.printStackTrace();
        }
    }
}
