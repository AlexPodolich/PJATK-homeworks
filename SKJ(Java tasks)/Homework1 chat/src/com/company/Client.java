package com.company;

import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client {

    public static void main(String[] args)  throws Exception{

        InetAddress address = InetAddress.getByName("172.23.129.57");
        InetSocketAddress fullAddress = new InetSocketAddress(address, 10000);
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Scanner scanner = new Scanner(System.in);

        try(SocketChannel channel = SocketChannel.open(fullAddress)){
            inputStreamReader = new InputStreamReader(channel.socket().getInputStream());
            outputStreamWriter = new OutputStreamWriter(channel.socket().getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            System.out.println("Write your name: ");
            String clientName = scanner.nextLine();
            bufferedWriter.write(clientName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            while (true){
                System.out.println("Write your message: ");
                String message = scanner.nextLine();
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server: " + bufferedReader.readLine());

                if(message.equalsIgnoreCase("close")){
                    break;
                }
            }
        }
    }
}
