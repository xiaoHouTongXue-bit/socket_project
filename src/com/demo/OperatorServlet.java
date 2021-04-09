package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OperatorServlet
 */
@WebServlet("/OperatorServlet")
public class OperatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher requestDispatcher;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatorServlet() {
        super();
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int first = Integer.parseInt(request.getParameter("first"));
		int second = 0;
		request.setAttribute("first", first);
		request.setAttribute("second", second);
		this.request = request;
		this.response = response;
		
		Manager.getInstance().sendMessage(first, second);
		// servlet�в��������߳��ˣ������߳̾Ͳ���session�У����������ӳ�300ms��ȥȡ����
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("answer", Manager.getInstance().getResult());
		request.getRequestDispatcher("operator.jsp").forward(request, response);
	
	}
	
	

}
