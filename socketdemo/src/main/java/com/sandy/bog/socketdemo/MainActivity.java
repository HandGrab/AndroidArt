package com.sandy.bog.socketdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.sandy.bog.socketdemo.tcp.TCPClient;
import com.sandy.bog.socketdemo.tcp.TCPServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    public static final int PORT = 8958;

    public static final String TAG = MainActivity.class.getSimpleName();

    private static final int MSG_RECEIVE_CONTENT = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_RECEIVE_CONTENT:
                    String content = msg.getData().getString("content");
                    mUdpReceive.setText(content);
                    break;
            }
        }
    };
    private TextView mUdpReceive;
    private EditText mSendContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testUdp();
        testTcp();
    }

    private void testTcp() {
        TCPClient tcpClient = new TCPClient();
        TCPServer tcpServer = new TCPServer();
        new Thread(() -> tcpServer.listen()).start();
        new Thread(() -> tcpClient.connect()).start();

    }

    private void testUdp() {
        mSendContent = (EditText) findViewById(R.id.udp_send_content);
        mUdpReceive = (TextView) findViewById(R.id.udp_receive);
        new Thread(() -> receive()).start();
        findViewById(R.id.udp_send).setOnClickListener((v) -> new Thread(() -> send()).start());
    }

    private void getIpAddress() {
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            InetAddress remoteAddress = InetAddress.getByName("www.baidu.com");
            Log.d(TAG, "本机的IP地址:" + localAddress.getHostAddress());
            Log.d(TAG, "百度的IP地址:" + remoteAddress.getHostAddress());
            Log.d(TAG, "判断指定的时间内地址是否可达:" + remoteAddress.isReachable(3000));
            Log.d(TAG, "获取主机名称：" + remoteAddress.getHostName());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "exception:" + e.toString());
        }

    }


    private void send() {
        String content = mSendContent.getText().toString();

        if (TextUtils.isEmpty(content)) {
            return;
        }

        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(3000);
            DatagramPacket datagramPacket = new DatagramPacket(content.getBytes("UTF-8"), content.length(), InetAddress.getByName("localhost"), PORT);
            datagramSocket.send(datagramPacket);
            datagramSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "send exception:" + e.toString());
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }


    //接收
    private void receive() {

        DatagramSocket datagramSocket = null;
        try {
            byte[] buf = new byte[1024];
            datagramSocket = new DatagramSocket(PORT);
            DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);
            Log.d(TAG, "等待数据接收:");
            datagramSocket.receive(datagramPacket);
            String content = new String(datagramPacket.getData(), 0, datagramPacket.getLength()) + " form "
                    + datagramPacket.getAddress().getHostAddress() + ":" + datagramPacket.getPort();

            Charset.forName("UTF-8").encode(content);


            Log.d(TAG, "receive data：" + content);

            Message message = new Message();
            message.what = MSG_RECEIVE_CONTENT;
            Bundle bundle = new Bundle();
            bundle.putString("content", content);
            message.setData(bundle);
            mHandler.sendMessage(message);
            datagramSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "exception:" + e.toString());
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }


}
