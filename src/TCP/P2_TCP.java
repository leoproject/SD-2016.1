package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class P2_TCP {

	public static void main(String[] args) throws IOException {
		//Aqui o lado servidor realiza no socket ativando 3 primitivas SOCKET/ BIND/LIST
		ServerSocket ssocket = new ServerSocket(300);
		Socket socket = ssocket.accept(); //bloqueante
		InputStream in = socket.getInputStream();//receb dados no processo
		OutputStream out = socket.getOutputStream();// envia dados do processo
		
		byte[] buf = new byte[20];
		int size = in.read(buf); //bloqueante
		String msg = new String (buf,0,size);
		System.out.println(msg);
		
		
		String msg2 = "Olá";
		byte [] buf2 = msg2.getBytes();
		out.write(buf2);
		
		socket.close();
		

	}

}
