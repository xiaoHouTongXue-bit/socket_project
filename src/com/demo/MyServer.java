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
		//���������
       try {
		serverSocket = new ServerSocket(port);


	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       System.out.println("���������");
	}


	@Override
	public void run() {
		try {
            while(true){
            	Socket  socket = serverSocket.accept();
                System.out.println("������յ�һ�ͻ������ӡ�");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //������������ֿͻ��ˣ������ˣ�����
                ServerSocket ss = new ServerSocket(6227); //10006
                Socket s1=ss.accept();

                //�������������������ݰ����ж�����
                for(;;){
                try {
                    //��������������ط���Ϣ
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
                        //�������ͻȻ�ı� ��ֹͣ��ʾ���ݱ仯 ��0000 �ж�֮ǰ������
                        if (str==0000){
                            break;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                    try {
                        String msg;//�ͻ��˷���������Ϣ
                        while ((msg = bufferedReader.readLine()) != null) {
                            String first = msg;
                            String result = (first);
                            sendBackToClient(socket, result);
                            //����������֣������ˣ����ӿͻ��ˡ�
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
                            System.out.println("�ر�socket���ִ���");
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
				/*System.out.println("����˰���ֵ������ظ��ͻ���");*/
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


