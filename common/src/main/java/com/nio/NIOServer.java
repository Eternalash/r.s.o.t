package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Author Bryan.C <br>
 * Date 2018/7/28
 */
public class NIOServer {
  public static void main(String[] args) {
    ServerSocketChannel ssChannel = null;
    try {
      // 1. 获取服务端通道
      ssChannel = ServerSocketChannel.open();
      ssChannel.bind(new InetSocketAddress(9898));
      // 2. 设置为非阻塞模式
      ssChannel.configureBlocking(false);

      // 3. 打开一个监听器
      Selector selector = Selector.open();
      // 4. 向监听器注册接收事件
      ssChannel.register(selector, SelectionKey.OP_ACCEPT);
      while (selector.select() > 0) {
        // 5. 获取监听器上所有的监听事件值
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();

        // 6. 如果有值
        while (it.hasNext()) {
          // 7. 取到SelectionKey
          SelectionKey key = it.next();

          // 8. 根据key值判断对应的事件
          if (key.isAcceptable()) {
            // 9. 接入处理
            SocketChannel socketChannel = ssChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
          } else if (key.isReadable()) {
            // 10. 可读事件处理
            SocketChannel channel = (SocketChannel) key.channel();
            readMsg(channel);
          } else if (key.isConnectable()) {
            System.out.println("connect");
          } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            channel.write(ByteBuffer.allocate(1024));
            System.out.println("write");
          }
          // 11. 移除当前key
          it.remove();
        }
      }
    } catch (Exception e) {
    } finally {
      try {
        if (ssChannel != null) {
          ssChannel.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void readMsg(SocketChannel channel) throws IOException {
    ByteBuffer buf = ByteBuffer.allocate(1024);
    int len = 0;
    while ((len = channel.read(buf)) > 0) {
      buf.flip();
      byte[] bytes = new byte[1024];
      buf.get(bytes, 0, len);
      System.out.println(new String(bytes, 0, len));
    }
  }

}
