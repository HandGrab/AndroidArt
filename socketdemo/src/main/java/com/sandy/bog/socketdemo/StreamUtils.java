package com.sandy.bog.socketdemo;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Sandy Luo on 2017/5/9.
 */

public class StreamUtils {

    public static void safeClose(Closeable closeable){
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
