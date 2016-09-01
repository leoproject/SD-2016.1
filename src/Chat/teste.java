package Chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class teste {
	String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	} 
	
	public static void main(String[] args) {
		String texto = "leonardo@gmail.com";
		String procurarPor = "@";
		System.out.println(texto.toLowerCase().contains(procurarPor.toLowerCase()));
		java.util.Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.format( new Date( System.currentTimeMillis() ) );
		
		System.out.println(dStr);
	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String hora = dateFormat.format(date);
		System.out.println(hora);

	}

}
