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
      System.out.println("***************Listening****************");
      while (true) {
        // 4. select ready SelectionKey for I/O operation
        if (selector.selectNow() == 0) {
          continue;
        }
        // 6. 获取监听器上所有的监听事件值
        Iterator<SelectionKey> it = selector.selectedKeys().iterator();
        int i=0;
        // 7. 如果有值
        while (it.hasNext()) {
          // 8. 取到SelectionKey
          SelectionKey key = it.next();

          // 9. 根据key值判断对应的事件
          if (key.isAcceptable()) {
            // 10. 接入处理
            SocketChannel socketChannel = ssChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
          } else if (key.isReadable()) {
            // 11. 可读事件处理
            SocketChannel channel = (SocketChannel) key.channel();
            readMsg(channel,key);
          }
          // 12. 移除当前key
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

  private static void readMsg(SocketChannel channel,SelectionKey key) throws IOException {
    ByteBuffer buf = ByteBuffer.allocate(1024);
    int len = 0;
    boolean a=false;
    while ((len = channel.read(buf)) > 0) {
      a=true;
      buf.flip();
      byte[] bytes = new byte[1024];
      buf.get(bytes, 0, len);
      System.out.println(new String(bytes, 0, len));
    }
    if(a) {
      channel.close();
      key.cancel();
    }
  }

}
