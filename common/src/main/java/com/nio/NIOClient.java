package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author Bryan.C <br>
 * Date 2018/7/28
 */
public class NIOClient {
    private static AtomicInteger threadNum=new AtomicInteger(0);
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                //1. 获取socketChannel
                SocketChannel sChannel = SocketChannel.open();
                //2. 创建连接
                sChannel.connect(new InetSocketAddress("127.0.0.1", 9898));
                //3. 设置通道为非阻塞
                sChannel.configureBlocking(false);

                byte[] a = new byte[1024];
                byte[] client = (Thread.currentThread().getName()+"##"+threadNum.getAndIncrement()).getBytes();
                for (int i = 0; i < client.length; i++) {
                    a[i] = client[i];
                }
                ByteBuffer buf = ByteBuffer.allocate(1024);
                buf.put(a, 0, 1024);
                buf.flip();
                if (buf.hasRemaining()) {
                    sChannel.write(buf);
                    buf.flip();
                }
                buf.clear();
                sChannel.read(buf);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(100, 200, 500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.execute(runnable);
        }
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
    }
}
