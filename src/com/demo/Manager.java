package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Manager {


    private static Manager instance;

    private MyClient myClient;
    private String message;

    private Manager() {
        init();
    }

    public static synchronized Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    private void init() {
        MyServer a1 = new MyServer();
        a1.start();

    }


    public void connectToServer() {
        if (myClient == null) {
            System.out.println("客户端向服务端发起链接");
            myClient = new MyClient();
            myClient.setMesageCallBack(new MessageCallBack() {

                @Override
                public void onMessage(String message) {
                    Manager.this.message = message;

                }
            });
            myClient.start();
        }

    }


    public String getResult() {
        return message;
    }

    //改变指令用的信息，主要改变first123...
    public void sendMessage(int first, int second) {
        if (first == 1) {
            myClient.sendData("001");
        } else if (first == 2) {
            myClient.sendData("002");
        } else if (first == 3) {
            myClient.sendData("abc");
        } else if (first == 4) {
            myClient.sendData("004");
        } else if (first == 5) {
            myClient.sendData("005");
        } else if (first == 6) {
            myClient.sendData("006");
        } else if (first == 0) {
            myClient.sendData("返回成功");
        }
    }
}
