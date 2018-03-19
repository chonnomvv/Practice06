package com.javaex.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();
		Scanner sc = new Scanner(System.in);

		System.out.println("<클라이언트 시작>");
		System.out.println("============");

		System.out.println("[서버에 연결을 요청합니다.]");

		socket.connect(new InetSocketAddress("192.168.1.28", 1001));
		System.out.println("[서버에 연결 되었습니다.");
				
		//메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		Writer osw = new OutputStreamWriter(os,"UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("q를 입력하면 프로그램 종료");
		//메세지 보내기(키보드 입력)
		while(true) {
			
			String str = sc.nextLine();
			bw.write(str);		
			bw.newLine();
			bw.flush();
			String reMsg = br.readLine();
			System.out.println("Server: [" + reMsg + "]");
			
			if("/q".equals(str)) {
				break;
			}
		}
		
		
		br.close();
		bw.close();
		
		

		System.out.println("=============");
		System.out.println("<클라이언트 종료>");

		socket.close();
		sc.close();
	}

}
