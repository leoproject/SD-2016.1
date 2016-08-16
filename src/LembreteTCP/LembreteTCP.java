package LembreteTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LembreteTCP extends Thread {
	private Socket socket;
	
	public LembreteTCP(Socket socket){
		this.socket = socket;
	}//final construtor
	
	public void run(){
		try{
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			String nome = dis.readUTF();
			int intervalo = dis.readInt();
			int total = dis.readInt();
			
			for(int i = 1; i <= total; i++){
				dos.writeUTF("Tomar " + nome + " " + i);
				Thread.sleep(intervalo);
			}
		} catch (InterruptedException | IOException e){
			e.printStackTrace();
		}
	}//final do método run

}
