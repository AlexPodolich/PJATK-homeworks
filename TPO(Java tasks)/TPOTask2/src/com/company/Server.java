package com.company;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private static String host = "localhost";
    private static int port = 1500;

    public Server(){}

    public static void main(String[] args) throws Exception {
        //Creating a server socket channel and binding it to a specific network address (host+port)
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(host, port));
        //Setting non-blocking mode for server socket channel.
        serverChannel.configureBlocking(false);
        //Intantiating a selector
        Selector selector = Selector.open();
        //Registering a server socket channel with a selector.
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if(key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array()).trim());
                    String response = checkRequest(buffer);
                    buffer.flip();
                    socketChannel.write(ByteBuffer.wrap(response.getBytes()));
                    buffer.clear();
                }
                iter.remove();
                buffer = ByteBuffer.allocate(256);
            }
        }

    }

    public static String checkRequest(ByteBuffer buffer) {
        String msg = new String(buffer.array()).trim();
        String requestName = msg.split(" ")[0];
        if(requestName.equalsIgnoreCase("MATH")) {
            int answer = calculate(msg);
            return ("ANSWER FROM SERVER: " + answer).trim();
        }else if(requestName.equalsIgnoreCase("ECHO")) {
            String[] arr = msg.split(" ", 2);
            return ("ECHO FROM SERVER: " + arr[1]).trim();
        }
        return "WRONG REQUEST";
    }

    public static int calculate(String str){
        String sign;
        int number1;
        int number2;
        int answer = 0;
        String[] arr = str.split(" ");
        sign = arr[2];
        number1 = Integer.parseInt(arr[1]);
        number2 = Integer.parseInt(arr[3]);
        switch (sign) {
            case "+" -> answer = number1 + number2;
            case "-" -> answer = number1 - number2;
            case "*" -> answer = number1 * number2;
            case "/" -> answer = number1 / number2;
        }

        return answer;
    }
}
