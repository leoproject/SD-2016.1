package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class P2_TCP {

	public static void main(String[] args) throws IOException {
		// Aqui o lado servidor realiza no socket ativando 3 primitivas SOCKET/
		// BIND/LIST
		ServerSocket ssocket = new ServerSocket(300);
		Socket socket = ssocket.accept(); // bloqueante
		InputStream in = socket.getInputStream();// receb dados no processo
		OutputStream out = socket.getOutputStream();// envia dados do processo
		
		// mecanismo para terminar o loop de funcionamento do chat de radio
		String msg, msg2, off;
		off = "desligar";
		msg = "";
		msg2 = "";
		
		System.out.println("********************** Terminal P2 *****************************\n");
		//chat roda até a pessoa enviar desligar ou receber do outro processo a mesma palavra
		while (!((msg.equals(off)) || (msg2.equals(off)))) {
			// Parte de receber mensagem 
			byte[] buf = new byte[20];
			int size = in.read(buf); // bloqueante
		    msg = new String(buf, 0, size);
			System.out.println("P1:" + msg);

			// Parte de enviar mensagem 
			Scanner read = new Scanner(System.in);
			System.out.print("Eu:");
			msg2 = read.nextLine();

			// String msg2 = "Olá";
			byte[] buf2 = msg2.getBytes();
			out.write(buf2);
		}
		socket.close();
		System.out.print("Chat Rádio finalizado com sucesso!!!!! ");

	}

}
