package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class P1_TCP {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Representa as primitivas SOCKET , BIND E CONNECT SÃO FEITOS AO MESMO TEMPO
		Socket socket = new Socket("localhost", 300);
		
	   // em relação ao processo inputstream  
		InputStream in = socket.getInputStream();//receb dados no processo
		OutputStream out = socket.getOutputStream();// envia dados do processo
		
		String msg = "tudo bem";
		byte [] buf = msg.getBytes();
		out.write(buf);
		
		
		
		byte[] buf2 = new byte[20];
		int size = in.read(buf2); //bloqueante
		String msg2 = new String (buf2,0,size);
		System.out.println(msg2);
		socket.close();

	}

}
