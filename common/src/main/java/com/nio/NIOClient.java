package com.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author Bryan.C <br>
 * Date 2018/7/28
 */
public class NIOClient {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            RandomAccessFile aFile = null;
            try {
                aFile = new RandomAccessFile("E:\\nio-data.txt", "rw");
                FileChannel inChannel = aFile.getChannel();


                //1. 获取socketChannel
                SocketChannel sChannel = SocketChannel.open();
                //2. 创建连接
                sChannel.connect(new InetSocketAddress("127.0.0.1", 9898));
                //3. 设置通道为非阻塞
                sChannel.configureBlocking(false);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssSSS");
                String dateTime = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);

                byte[] a = new byte[1024];
                byte[] client = ("Client" + Thread.currentThread().getName() + "-" + dateTime).getBytes();
                for (int i = 0; i < client.length; i++) {
                    a[i] = client[i];
                }
                ByteBuffer buf = ByteBuffer.allocate(1024);
                buf.put(a, 0, 1024);
                int i = 0;
                while (true) {
                    buf.flip();
                    while (buf.hasRemaining()) {
                        sChannel.write(buf);
                        buf.flip();
                        i++;
                        if (i > 20) break;
                    }
                    buf.clear();
                    if (i > 20) break;
                }

                sChannel.read(buf);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 200, 500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute(runnable);
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
    }
}
