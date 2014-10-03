package org.elsysbg.ip.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private static final int SERVER_PORT = 44012;
	
	// if static - ClientHandler will not have access to EchoServer.started
	// so server can be stopped if "END" command is implemented in client handler
	
	// extends Thread - create class that can be spawned in new thread
	private /*static*/ class ClientHandler extends Thread {
		private final Socket clientSocket;
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				handleClient(clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void handleClient(final Socket clientSocket) throws IOException {
			//get I/O streams
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();
			
			final InputStreamReader inputStreamReader =
				new InputStreamReader(inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			
			final PrintWriter out = new PrintWriter(outputStream);
			
			// read from socket
			final String readLine = in.readLine();
			
			// write to socket what we have red (this is echo server)
			out.println(readLine);
			
			// always flush writer
			out.flush();
			
			// we should ALWAYS close sockets!
			clientSocket.close();
		}
		
	}
	
	private boolean started;

	public static void main(String[] args) throws IOException {
		new EchoServer().run();
	}
	
	public void run() throws IOException {
		started = true;

		// bind to port
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		while (started) {
			// accept client
			final Socket clientSocket = serverSocket.accept();

			//Thread.start() creates new thread and calls run() method
			new ClientHandler(clientSocket).start();
		}
		
		// we should ALWAYS close sockets!
		serverSocket.close();
	}


}
