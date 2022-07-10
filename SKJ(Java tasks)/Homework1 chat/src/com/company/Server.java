package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws Exception{
        InetAddress address = InetAddress.getByName("172.23.129.57");
        InetSocketAddress fullAddress = new InetSocketAddress(address, 10000);
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        String clientName = "Client";

        try(ServerSocketChannel channel = ServerSocketChannel.open()){
            channel.bind(fullAddress);
            System.out.println("Server launched!");
            try (SocketChannel client = channel.accept()){
                inputStreamReader = new InputStreamReader(client.socket().getInputStream());
                outputStreamWriter = new OutputStreamWriter(client.socket().getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                int i = 0;
                while (true){
                    String messageFromClient = bufferedReader.readLine();
                    if(i==0){
                        clientName = messageFromClient;
                        System.out.println(clientName + " joined the chat!");
                    }else{
                        System.out.println(clientName + ": " + messageFromClient);

                        bufferedWriter.write("Message received from " + clientName);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                        if(messageFromClient.equalsIgnoreCase("close")){
                            System.out.println("Server closed!");
                            break;
                        }
                    }
                    i++;
                }
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            }
        }
    }
}