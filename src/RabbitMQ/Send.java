package RabbitMQ;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	  private final static String QUEUE_NAME = "rafael";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("black-boar.rmq.cloudamqp.com"); // parametros para conexão com rabbitmq (black-boar.rmq.cloudamqp.com)
	    factory.setUsername("ytfkfcaf");
	    factory.setPassword("XA-QF47k64tMyZNFcqXEOQAMjBpfKYl2");
	    factory.setVirtualHost("ytfkfcaf");
	    
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Ta osso nem tempo para mulher temos!!!";
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + message + "'");

	    channel.close();
	    connection.close();
	  }


}
