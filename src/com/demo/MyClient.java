package com.demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient extends Thread {
	private Socket socket;
	private PrintWriter printWriter;
	private MessageCallBack callBack;
	public MyClient (){
		this.callBack =callBack;
		try {
			socket = new Socket("192.168.1.8", MyServer.port); //����ʹ��192.168.0.101
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setMesageCallBack(MessageCallBack callBack){
		this.callBack = callBack;
	}
	
	@Override
	public void run(){
		BufferedReader bufferedReader =null;
        try {
        	bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;//����������������Ϣ
			while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("�ͻ����յ��������ķ��أ�" + msg);
                if(callBack!=null){
                	callBack.onMessage(msg);
                }
            }
        } catch (IOException e) {
            System.out.println("���棺�Ͽ����ӣ�");
            try {
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e1) {
                System.out.println("��ȡ�̣߳��ر�socket���ִ���");
            }
        }
        /*---�����Լ���д������*/
	}
	
	public void sendData(String msg){
		/*System.out.println("�ͻ���������������æ����:"+msg);*/
		System.out.println("��������"+msg);
		printWriter.println(msg);
	}
}

