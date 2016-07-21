package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class P1_TCP {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		// Representa as primitivas SOCKET , BIND E CONNECT SÃO FEITOS AO MESMO
		// TEMPO
		Socket socket = new Socket("localhost", 300);

		// em relação ao processo inputstream e outputinstream
		InputStream in = socket.getInputStream();// receb dados no processo
		OutputStream out = socket.getOutputStream();// envia dados do processo
		// mecanismo para terminar o loop de funcionamento do chat de radio
		String msg, msg2, off;
		off = "desligar";
		msg = "";
		msg2 = "";
		
		System.out.println("********************** Terminal P1 *****************************\n");
		//chat roda até a pessoa enviar desligar ou receber do outro processo a mesma palavra
		while (!((msg.equals(off)) || (msg2.equals(off)))) {
			// Parte de enviar mensagem
			Scanner read = new Scanner(System.in);
			System.out.print("Eu:");
			msg = read.nextLine();
			byte[] buf = msg.getBytes();
			out.write(buf);
			
			// Parte de receber mensagem 
			byte[] buf2 = new byte[20];
			int size = in.read(buf2); // bloqueante
			msg2 = new String(buf2, 0, size);
			System.out.println("P2:" + msg2);
		}
		socket.close();

	}

}
