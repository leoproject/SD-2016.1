package Chat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.rabbitmq.client.*;

public class ChaTeado {
	//private final static String QUEUE_NAME = "@rafael";

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
		int liberar2 = 0;
		int test=0;
		int um=0,dois=0;
		String cmd = "";
		String contato = "";
		String nickname;
		
		//Aqui o usuario escolhe como vai se chamar no chat e consequentemente fica sendo o nome da fila dele
		Scanner s = new Scanner(System.in);
		System.out.print("Insira seu nickname:");
		nickname=s.nextLine();
		
		
		
		//metodo receive
		channel.queueDeclare(nickname, false, false, false, null);

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
		channel.basicConsume(nickname, true, consumer);
		
		
		// loop até colocar a palavra off
		while (!((msg.equals(off)) || (msg.equals(off)))) {
			
			if (contato.equals("")) {
				System.out.print("->>");
				msg = s.nextLine();
			}
			else {
				System.out.print("->>");
				msg = s.nextLine();
			}

			int verificar3 = msg.indexOf('!');
			if (verificar3 == 0) {
				int i = 1;
				while (msg.charAt(i) != ' ') {
					cmd += msg.charAt(i);
					i++;
				}

				if (cmd.equals("creategroup")) {
					um = msg.indexOf(" ");
				    dois = msg.lastIndexOf("");
					channel.exchangeDeclare(msg.substring(um + 1, dois), "fanout", true);
					cmd = "";
				} else if (cmd.equals("add")) {
					if (test == 0){
						um = msg.indexOf(" ");
						dois = msg.lastIndexOf(" ");
						int tres = msg.lastIndexOf("");
						channel.queueBind(nickname, msg.substring(dois + 1, tres),"");
						test+=1;
					}
					um = msg.indexOf(" ");
					dois = msg.lastIndexOf(" ");
					int tres = msg.lastIndexOf("");
					channel.queueBind(msg.substring(um + 1, dois), msg.substring(dois + 1, tres), "");
					cmd = "";
				} else {
					System.out.println("Comando inválido");
				}
			}
			int verificar = msg.indexOf('@');
			int verificar2 = msg.lastIndexOf('@');
			if ((verificar == 0 && verificar2 == 1)) {
				um = msg.lastIndexOf("@");
				dois = msg.lastIndexOf("");
				contato = msg.substring(um + 1, dois);
				liberar2 = 1;
				liberar = 0;
				System.out.println("=======================================================================");
				System.out.println("                   Conversando com " + contato + "                     ");
				System.out.println("=======================================================================");
			} else if ((liberar2 == 0) && (liberar == 0) && (verificar != 0) && (verificar3 != 0)) {
				System.out.println("Não pode na primeira vez enviar mensagem antes de estabelecer o contato");
			} else if ((verificar3 != 0) && (liberar == 0) && (liberar2 != 0)) {
				msg = nickname+ ">> Grupo " + hora + " -> " + msg;
				channel.basicPublish(contato, "", null, msg.getBytes("UTF-8"));
			}
			if ((verificar == 0 && verificar2 != 1)) {
				um = msg.lastIndexOf("@");
				dois = msg.lastIndexOf("");
				contato = msg.substring(um + 1, dois);
				liberar = 1;
				liberar2 = 0;
				channel.queueDeclare(contato, false, false, false, null);
				System.out.println("=======================================================================");
				System.out.println("                   Conversando com " + contato + "                     ");
				System.out.println("=======================================================================");
			} else if ((liberar2 == 0) && (liberar == 0) && (verificar != 0) && (verificar3 != 0)) {
				System.out.println("Não pode na primeira vez enviar mensagem antes de estabelecer o contato");
			} else if ((verificar3 != 0) && (liberar != 0) && (liberar2 == 0)) {
				msg = nickname + " " + hora + " -> " + msg;
				channel.basicPublish("", contato, null, msg.getBytes("UTF-8"));

			}

		}
		 channel.close();
		    connection.close();
		
		/*
		// metodo receive
		
		
	}*/
}}