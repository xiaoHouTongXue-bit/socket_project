package com.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;




/*---*/
import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
/*---*/
public class MyServer extends Thread{
	public static final int port=62222;
	private ServerSocket serverSocket;
	public MyServer (){
		//创建服务端
       try {
		serverSocket = new ServerSocket(port);


	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       System.out.println("服务端启动");
	}


	@Override
	public void run() {
		try {
            while(true){
            	Socket  socket = serverSocket.accept();
                System.out.println("服务端收到一客户端连接。");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //与网络调试助手客户端（机器人）相连
                ServerSocket ss = new ServerSocket(6227); //10006
                Socket s1=ss.accept();

                //网络调试助手是添加内容包括中断内容
                for(;;){
                try {
                    //网络调试助手往回发消息
                    InputStream is = null;
                    try {
                        is = s1.getInputStream();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    byte[] b = new byte[200];
                    int len;
                    while ((len = is.read(b)) != -1) {
                        int str = Integer.parseInt(new String(b,0,len));
                        System.out.println(str);
                        sendBackToClient(socket, String.valueOf(str));
                        //如果环境突然改变 就停止显示数据变化 即0000 中断之前的内容
                        if (str==0000){
                            break;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                    try {
                        String msg;//客户端发过来的信息
                        while ((msg = bufferedReader.readLine()) != null) {
                            String first = msg;
                            String result = (first);
                            sendBackToClient(socket, result);
                            //网络调试助手（机器人）连接客户端。
                            OutputStream os = s1.getOutputStream();
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                            bw.write(first);
                            bw.flush();
                        }
                    } catch (IOException e) {
                        try {
                            if (!socket.isClosed()) {
                                socket.close();
                            }
                        } catch (IOException e1) {
                            System.out.println("关闭socket出现错误");
                        }
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	private void sendBackToClient(final Socket socket,final String msg){
		new Thread(new Runnable(){
			@Override
			public void run() {
				/*System.out.println("服务端把求值结果返回给客户端");*/
				PrintWriter printWriter=null;
				try {
					printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                printWriter.println(msg);
			}
		}).start();
	}
}


