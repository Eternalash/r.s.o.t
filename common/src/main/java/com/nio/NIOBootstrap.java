package com.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Author Bryan.C <br>
 * Date 2018/7/28
 */
public class NIOBootstrap {
    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        RandomAccessFile toFile = null;
        try {
            aFile = new RandomAccessFile("E:\\nio-data.txt", "rw");
            toFile = new RandomAccessFile("E:\\to-nio-data.txt", "rw");
            FileChannel inChannel = aFile.getChannel();
            FileChannel toChannel=toFile.getChannel();

            toChannel.transferFrom(inChannel,0,inChannel.size());
//            inChannel.transferTo(0,inChannel.size(),toChannel);


//create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            /*
             * read into buffer.
             */
            int bytesRead = toChannel.read(buf);
            while (bytesRead != -1) {
                /*
                 * make buffer ready for read
                 */
                buf.flip();
                int i=0;
                while (buf.hasRemaining()) {
                    /*
                     * read 1 byte at a time
                     */
                    if(buf.position()==1){
                        buf.mark();
                    }
                    System.out.print((char) buf.get());
                    if(buf.position()==10){
                        buf.reset();
                    }
//                    i++;
//                    if(i>50) break;
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = toChannel.read(buf);
            }
            aFile.close();
            toFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
