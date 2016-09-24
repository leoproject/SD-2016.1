package Chat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.rabbitmq.client.*;

public class ChaTeado {
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
		
        //pegar data e hora do sistema com suas respectivas mascaras
		String data = "dd/MM/yyyy";
		String hora = "HH:mm";
		String data1, hora1;
		java.util.Date agora = new java.util.Date();;
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
	   
		
		String contato = "";
		String nickname;
		String msg = " ";
		String off = "desligar";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		
		int liberar = 0;
		int liberar2 = 0;
		int test=0;
		
		int verificar,verificar2,verificar3;
		int um=0,dois=0;
		String cmd = "";
		
		
		//Aqui o usuario escolhe o nome dele e com quem vai falar
		Scanner s = new Scanner(System.in);
		System.out.print("Insira seu nickname:");
		nickname=s.nextLine();
		
		System.out.println("");
		System.out.println("Olá "+nickname+" você está conectado no chat #ChaTeado! Fale com seu amigo @nickdousuario");
		System.out.println("Para sair digite: desligar");
		System.out.println("");
		
		//metodo receive para receber as mensagens no formato XML
		Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
		        {
		    	  try {
		    		  
		    		    String message = new String(body, "UTF-8");	
		    		    SAXBuilder builder = new SAXBuilder();
		    		    Document doc = builder.build(new StringReader(message));
		    	        ByteArrayInputStream stream = new ByteArrayInputStream (message.getBytes("UTF-8"));
			            System.out.println(message);
				  //Document doc = builder.build(stream);
				  
				  //Element classElement = doc.getRootElement();
				  } catch (JDOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				  };
		      }
		    };
		    channel.basicConsume(nickname, true, consumer);
		
		
		
	    
	    
		
		// loop até colocar a palavra off
		while (!((msg.equals(off)) || (msg.equals(off)))) {
			//verificar se foi criado seu nickname e mensagem
			if (contato.equals("")) {
				System.out.print("#--#");
				msg = s.nextLine();
			}
			else {
				System.out.print("#--#");
				msg = s.nextLine();
			}
			
            //verifica se o comando inserido foi relacionado ao grupo
			verificar3 = msg.indexOf('!');
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
					//aqui coloco se foi ele quem criou o grupo ele deve está no grupo
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
			
			//Verificar se esta entrando na conversa com o grupo 
			verificar = msg.indexOf('@');
			verificar2 = msg.lastIndexOf('@');
			if ((verificar == 0 && verificar2 == 1)) {
				um = msg.lastIndexOf("@");
				dois = msg.lastIndexOf("");
				contato = msg.substring(um + 1, dois);
				liberar2 = 1;
				liberar = 0;
				System.out.println("=======================================================================");
				System.out.println("         "+nickname+" conversando com " + contato + "                  ");
				System.out.println("=======================================================================");
			} else if ((liberar2 == 0) && (liberar == 0) && (verificar != 0) && (verificar3 != 0)) {
				System.out.println("Não pode na primeira vez enviar mensagem antes de estabelecer o contato");
			} else if ((verificar3 != 0) && (liberar == 0) && (liberar2 != 0)) {
				test=msg.indexOf("@");
				if (test != 1) {
					Document doc = new Document();

					Element root = new Element("message");

					Element sender = new Element("sender");
					sender.setText(nickname);
					root.addContent(sender);

					Element date = new Element("date");
					date.setText(data);
					root.addContent(date);

					Element time = new Element("time");
					time.setText(hora1);
					root.addContent(time);

					Element content = new Element("content");
					content.setText(msg);
					root.addContent(content);

					doc.setRootElement(root);

					XMLOutputter xout = new XMLOutputter(
							Format.getPrettyFormat());
					xout.output(doc, baos);

					channel.queueDeclare(contato, false, false, false, null);
					channel.basicPublish("", contato, null,baos.toByteArray());
				}
			}
			// verifica que estaá falando com um contato
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
				Document doc = new Document();

				Element root = new Element("message");

				Element sender = new Element("sender");
				sender.setText(nickname);
				root.addContent(sender);

				Element date = new Element("date");
				date.setText(data);
				root.addContent(date);

				Element time = new Element("time");
				time.setText(hora1);
				root.addContent(time);

				Element content = new Element("content");
				content.setText(msg);
				root.addContent(content);

				doc.setRootElement(root);

				XMLOutputter xout = new XMLOutputter(
						Format.getPrettyFormat());
				xout.output(doc, baos);

				channel.queueDeclare(contato, false, false, false, null);
				channel.basicPublish("", contato, null,baos.toByteArray());

			}

		}
		 channel.close();
		    connection.close();
		
		/*
		// metodo receive
		
		
	}*/
}}