package com.sandy.bog.socketdemo.tcp;

import android.util.Log;

import com.sandy.bog.socketdemo.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Sandy Luo on 2017/5/9.
 */

public class TCPClient {

    private static final String TAG = TCPClient.class.getSimpleName();

    private static final int PORT = 7002;

    public void connect() {
        Socket client = null;
        InputStream inputStream = null;
        try {
            client = new Socket(InetAddress.getLocalHost(), PORT);
            inputStream = client.getInputStream();
            byte[] buf = new byte[1024];
            int len = inputStream.read(buf);
            Log.d(TAG , new String(buf, 0, len));
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "exception: " + e.toString());

        }finally {
            StreamUtils.safeClose(client);
            StreamUtils.safeClose(inputStream);

        }
    }
}
