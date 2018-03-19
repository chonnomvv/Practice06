package com.javaex.echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(); // 서버 소켓 객체 생성
		serverSocket.bind(new InetSocketAddress("192.168.1.37", 1001)); // bind

		System.out.println("<서버 시작>");
		System.out.println("==================");
		System.out.println("[연결을 기다리고 있습니다.]");
		Socket socket = serverSocket.accept(); // accept 단계

		System.out.println("[클라이언트가 연결되었습니다.]");

		// 메세지 받기

		InputStream is = socket.getInputStream();
		Reader r = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(r);

		// 메세지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		Writer osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		String msg;
		while (true) {
			msg = br.readLine();

			if (msg == null) {
				System.out.println("클라이언트 접속 종료");
				break;
			}
			System.out.println("받은 메세지: " + msg);

			// 받은 메세지 다시 보내기
			bw.write(msg);
			bw.newLine();
			bw.flush(); // 버퍼가 다 안 차더라도 보내기
		}

		br.close();
		bw.close();

		System.out.println("==================");
		System.out.println("<서버 종료>");

		serverSocket.close();

	}

}
