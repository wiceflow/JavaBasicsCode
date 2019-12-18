package com.wiceflow.Io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author BF
 * @date 2019/8/20 17:02
 * <p>
 * 利用 nio 做一个通信服务端  单线程处理多个客户端
 */
public class NioService {

    public static void main(String[] args) throws IOException {
        // 选择器
        Selector selector = Selector.open();
        // 服务端的 Channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8888));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 设置缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        RequestHandler requestHandler = new RequestHandler();

        while (true) {
            int select = selector.select();
            if (select == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    // 打印连接地址
                    System.out.println("Connection from " + socketChannel.getRemoteAddress());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.read(byteBuffer);
                    String request = new String(byteBuffer.array()).trim();
                    byteBuffer.clear();
                    System.out.println(String.format("From %s : %s ", socketChannel.getRemoteAddress(), request));
                    String response = requestHandler.respeonseMessage(request);
                    socketChannel.write(ByteBuffer.wrap(response.getBytes()));
                }
                // 这个 remove 很重要，每次结束一轮循环把当前请求移出选择器
                iterator.remove();
            }
        }
    }
}
