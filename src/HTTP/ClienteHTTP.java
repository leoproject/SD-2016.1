package HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClienteHTTP {

	public static void main(String[] args) throws UnknownHostException, IOException {
	Socket s = new Socket("www.ufs.br", 80);
	/* Precisa de arquivo válido, requisitar na versão 1.0
	 * File file = new File("");
	 * FileOutputSteam fos = new FileOutputStream(file); */
	
	InputStream is = s.getInputStream();
	OutputStream os = s.getOutputStream();
	
	String msg = "HEAD / HTTP/1.1" + "\r\n" + "Host:www.ufs.br" + 
	"\r\n" + "\r\n";
	
	byte [] buf = msg.getBytes();
	os.write(buf);
	
	byte [] bufR = new byte[1024];
	int size = 0;
		while ((size = is.read(bufR)) > 0){
		String resp = new String(bufR,0,size);
		System.out.println(resp);
		
		/*while ((size = is.read(bufR)) > 0){
		 * fos.write(bufR, 0, size);
		 * } 
		 * fos.close();*/
		}
	}

}
