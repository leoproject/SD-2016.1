package LembreteTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteLembrete {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Socket s = new Socket("localhost", 44111);

		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();

		DataInputStream dis = new DataInputStream(is);
		DataOutputStream dos = new DataOutputStream(os);

		dos.writeUTF("Dorflex");
		dos.writeInt(4);
		dos.writeInt(10);

		for (int i = 0; i < 10; i++) {
			System.out.println(dis.readUTF());
		}

	}

}
