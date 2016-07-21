package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


public class P1_UDP {

	public static void main(String[] args) throws Exception {
		//Socket + Bind
		//Processo de SEND do processo 1
		System.out.println("Terminal do P1!!!!! \n");
		DatagramSocket socket = new DatagramSocket(3030);
		
		System.out.print("P1:");
		Scanner read = new Scanner(System.in);
		String msg = read.nextLine();// nextline() pega toda linha inserida pelo teclado
		
		byte [] buf = msg.getBytes();
		int portDest = 4040;
		InetAddress addrDest = InetAddress.getLocalHost();
		DatagramPacket pack = new DatagramPacket(buf, buf.length,addrDest, portDest);
		socket.send(pack);
		
		
		// Processo de Recieve do processo 1
		byte [] buf2 = new byte[144];
		DatagramPacket pack2 = new DatagramPacket(buf2, buf2.length);
		socket.receive(pack2);
		String msg2 = new String(pack2.getData());
		int portOr = pack2.getPort();
		InetAddress addrOr = pack2.getAddress();
		System.out.println("P2:"+msg2.trim());// trim() é uma função de tirar o restante que não é da mensagem. 
		socket.close();

	}

}
