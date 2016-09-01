package Chat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.rabbitmq.client.*;

public class ChaTeado {
	private final static String QUEUE_NAME = "@rafael";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("black-boar.rmq.cloudamqp.com"); // parametros para
															// conexão com
															// rabbitmq
															// (black-boar.rmq.cloudamqp.com)
		factory.setUsername("ytfkfcaf");
		factory.setPassword("XA-QF47k64tMyZNFcqXEOQAMjBpfKYl2");
		factory.setVirtualHost("ytfkfcaf");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        Date date = new Date();
	        String hora = dateFormat.format(date);
		String msg = " ";
		String off = "desligar";
		int liberar = 0;
		String contato = "@leonardo";
		
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" ");
				System.out.println( message );
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		
		// loop até colocar a palavra off
		while (!((msg.equals(off)) || (msg.equals(off)))) {
			Scanner s = new Scanner(System.in);
			System.out.print(hora+"  " +"Eu:");
			msg = s.nextLine();
		    
			int verificar = msg.indexOf('@');
			
			if ((verificar==0)) {
				contato = msg;
				liberar++;
				System.out.println("=======================================================================");
				System.out.println("                   Conversando com "+contato+"                         ");
				System.out.println("=======================================================================");
				
			} 
			else if ((liberar==0) && (verificar!=0)){
				System.out.println("Não pode na primeira vez enviar mensagem antes de estabelecer o contato");	
			}
			else{
				// metodo send.
			        msg=hora+"  " +QUEUE_NAME+":"+msg;
					channel.queueDeclare(contato, false, false, false, null);
					channel.basicPublish("", contato, null,msg.getBytes("UTF-8"));
					
		}
		
	}
		 channel.close();
		    connection.close();
		
		/*
		// metodo receive
		
		
	}*/
}}