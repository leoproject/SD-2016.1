package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;


public class P2_UDP {

	public static void main(String[] args) throws Exception {
		System.out.println("Terminal do P2!!!!! \n");
		// Processo de Recieve do processo 2
		DatagramSocket socket = new DatagramSocket(4040);
		byte [] buf = new byte[300];
		DatagramPacket pack = new DatagramPacket(buf, buf.length);
		socket.receive(pack);
		String msg = new String(pack.getData());
		int portOr = pack.getPort();
		InetAddress addrOr = pack.getAddress();
		System.out.println("P1:"+msg.trim());
		
		//Processo de SEND do processo 2 
		Scanner read = new Scanner(System.in);
		System.out.print("P2:");
		String msg2 = read.nextLine();
		//String msg2 = "Ola tudo bem?";
		byte [] buf2 = msg2.getBytes();
		int portDest2 = 3030;
		InetAddress addrDest2 = InetAddress.getLocalHost();
		DatagramPacket pack2 = new DatagramPacket(buf2, buf2.length,addrDest2, portDest2);
		socket.send(pack2);
		socket.close();

	}

}
