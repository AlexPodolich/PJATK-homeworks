package com.company;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Writer {
    public static File FILE = new File("file.dat");
    public static MappedByteBuffer _buffer;
    public static RandomAccessFile _file;
    public static void main(String[] args) throws NewException{
        try{
            FILE.delete();
            _file = new RandomAccessFile(FILE, "rw");
            FileChannel channel = _file.getChannel();
            _buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 20);
        }catch (Throwable ex){
            throw new NewException(ex);
        }

        write(8);
    }

    public static void write(int numOfReps){
        while (numOfReps > 0){
            _buffer.rewind();
            int checkNum = _buffer.getInt();
            if(checkNum == 0){
                int value1 = (int)(Math.random() * 99) + 1;
                int value2 = (int)(Math.random() * 99) + 1;
                int value3 = (int)(Math.random() * 99) + 1;
                _buffer.rewind();
                _buffer.putInt(1);
                _buffer.putInt(value1);
                _buffer.putInt(value2);
                _buffer.putInt(value3);
                numOfReps--;
            }
            try{
                Thread.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        _buffer.rewind();
        _buffer.putInt(-1);
    }
}


