package com.sandy.bog.socketdemo.tcp;

import android.util.Log;

import com.sandy.bog.socketdemo.StreamUtils;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sandy Luo on 2017/5/9.
 */

public class TCPServer {

    public static final String TAG = TCPServer.class.getSimpleName();

    private static final int PORT = 7002;

    public void listen() {
        ServerSocket serverSocket = null;
        Socket client = null;
        OutputStream outputStream = null;
        try {
            serverSocket = new ServerSocket(PORT);
            client = serverSocket.accept(); //接收数据，调用后阻塞，直到收到客户端数据
            outputStream = client.getOutputStream();
            Log.d(TAG, "开始与客户端交互数据");
            outputStream.write("Hello World".getBytes());
            Thread.sleep(3000);    //模拟执行其他功能占用的时间
            Log.d(TAG, "结束与客户端交互数据");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "exception:" + e.toString());
        } finally {
            StreamUtils.safeClose(client);
            StreamUtils.safeClose(outputStream);
        }
    }


}
