package com.company;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader {
    public static File FILE = new File("file.dat");
    public static MappedByteBuffer _buffer;
    public static RandomAccessFile _file;

    public static void main(String[] args) throws NewException {
        try{
        if (!FILE.exists() && !FILE.isFile() && !FILE.canRead()) {
            throw new NewException("File " + FILE + " doesn't exist");
        }
        _file = new RandomAccessFile(FILE, "rw");
        FileChannel channel = _file.getChannel();
        _buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 20);
    }catch (Throwable ex){
        throw new NewException(ex);
    }
        read();
    }

    public static void read(){
            while (true){
            _buffer.rewind();
            int checkNum = _buffer.getInt();
            if (checkNum == -1){
                break;
            }else {
                if(checkNum == 1){
                    int num1 = _buffer.getInt();
                    int num2 = _buffer.getInt();
                    int num3 = _buffer.getInt();
                    _buffer.rewind();
                    _buffer.putInt(0);
                    int sum = num1 + num2 + num3;
                    System.out.println(num1 + " + " + num2 + " + " +  num3 + " = " + sum);
                }
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }

}
