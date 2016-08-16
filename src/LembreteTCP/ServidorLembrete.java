package LembreteTCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorLembrete {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(44111);
		while(true){
			Socket socket = ss.accept();
			LembreteTCP lemb = new LembreteTCP(socket);
			lemb.start();
		}
	}

}
