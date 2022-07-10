package com.company;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {
    private static SocketChannel channel;
    private static String server = "localhost";
    private static int port = 1500;
    private static ByteBuffer buffer;

    private Client() {
        try {
            channel = SocketChannel.open(new InetSocketAddress(server, port));
            System.out.print("Connecting ...");
            buffer = ByteBuffer.allocate(256);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("\nConnected");
    }

    public static void main(String[] args) {
        new Client();
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your request: ");
            System.out.println("Type \'ECHO <message>\'");
            System.out.println("OR");
            System.out.println("Type \'MATH <number> <sign> <number>\'");
            String msg = scanner.nextLine();
            if(isValidRequest(msg) == 1){
                System.out.println(sendMessage(msg));
            }else {
                System.out.println("WRONG REQUEST");
                continue;
            }
        }
    }

    public static int isValidRequest(String msg) {
        String[] tmp = msg.split(" ");
        if (tmp[0].equalsIgnoreCase("ECHO") || tmp[0].equalsIgnoreCase("MATH")) {
            return 1;
        }else {
            return 0;
        }
    }

    public static String sendMessage(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;
        try {
            channel.write(buffer);
            buffer = ByteBuffer.allocate(256);
            channel.read(buffer);
            response = new String(buffer.array()).trim();
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }
}
