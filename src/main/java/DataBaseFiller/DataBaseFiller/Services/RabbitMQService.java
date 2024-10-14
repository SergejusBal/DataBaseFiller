package DataBaseFiller.DataBaseFiller.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQService {

    private String queueName;
    private static final String HOST = "localhost";
    private final ConnectionFactory factory;
    private final ObjectMapper objectMapper;

    public RabbitMQService(String queueName) {
        this.factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        this.objectMapper = new ObjectMapper();
        this.queueName = queueName;
    }

    public  void  sendObjectToQueue(Object obj) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(queueName, false, false, false, null);

            String jsonMessage = objectMapper.writeValueAsString(obj);

            channel.basicPublish("", queueName, null, jsonMessage.getBytes());
            //System.out.println("Issiustas JSON: " + jsonMessage);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
