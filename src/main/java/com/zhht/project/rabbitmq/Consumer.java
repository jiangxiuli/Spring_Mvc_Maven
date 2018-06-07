package com.zhht.project.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Consumer {
    // 消息交换机名称
    public static String XCHG_NAME = "exchange_topic";

    // 队列名称
    private static final String QUEUE_NAME = "quene_topic";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 连接rabbitmq服务器
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.42.136");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String queueName = QUEUE_NAME;

        Producer.XT xt = Producer.XT.TOPIC;

        switch (xt) {
        case DEFAULT:
            // 队列的相关参数需要与第一次定义该队列时相同，否则会出错，使用channel.queueDeclarePassive()可只被动绑定已有队列，而不创建
            // 默认，向指定的队列发送消息，消息只会被一个consumer处理,多个消费者消息会轮训处理,消息发送时如果没有consumer，消息不会丢失
            // 为消息通道绑定一个队列
            // 队列的相关参数需要与第一次定义该队列时相同，否则会出错

            // 参数1：队列名称
            // 参数2：为true时server重启队列不会消失
            // 参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
            // 参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
            // 参数5：建立队列时的其他参数
            channel.queueDeclare(queueName, true, false, true, null);
            break;
        case FANOUT:
            // 接收端也声明一个fanout交换机
            channel.exchangeDeclare(XCHG_NAME, "fanout", true, false, null);
            // channel.exchangeDeclarePassive() 可以使用该函数使用一个已经建立的exchange
            // 声明一个临时队列，该队列会在使用完比后自动销毁
            // queueName = channel.queueDeclare().getQueue();
            channel.queueDeclare(queueName, true, false, false, null);
            // 将队列绑定到交换机,参数3无意义此时
            channel.queueBind(queueName, XCHG_NAME, "info");
            break;
        case DIRECT:
            channel.exchangeDeclare(XCHG_NAME, "direct", true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            // 绑定一个routing
            channel.queueBind(queueName, XCHG_NAME, "info");
            // key，可以绑定多个
            channel.queueBind(queueName, XCHG_NAME, "warning");
            break;
        case TOPIC:
            Map<String, Object> arguments = new HashMap<String, Object>();
            arguments.put("alternate-exchange", "exchange_alternate");
            // 声明交换器，备份交换器已指定
            channel.exchangeDeclare(XCHG_NAME, "topic", true, false, arguments);
            // 声明备份交换器
            channel.exchangeDeclare("exchange_alternate", "fanout", true, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            // 监听两种模式 #匹配一个或多个单词
            channel.queueBind(queueName, XCHG_NAME, "warning.#");
            // *匹配一个单词或0个
            channel.queueBind(queueName, XCHG_NAME, "*.blue");
            // 备份交换器的对列
            channel.queueDeclare("quene_alternate", true, false, false, null);
            // 将死信对列与死信交换器绑定起来
            channel.queueBind("quene_alternate", "exchange_alternate", "");
            break;
        case HEADERS:
            // 定义一个交换机
            // 参数1：交换机名称
            // 参数2：交换机类型
            // 参数3：交换机持久性，如果为true则服务器重启时不会丢失
            // 参数4：交换机在不被使用时是否删除
            // 参数5：交换机的其他属性
            channel.exchangeDeclare(XCHG_NAME, "headers", true, true, null);
            queueName = channel.queueDeclare().getQueue();
            Map<String, Object> headers = new HashMap<String, Object>();
            // all==匹配所有条件，any==匹配任意条件
            headers.put("x-match", "any");
            headers.put("name", "test");
            headers.put("sex", "male");
            channel.queueBind(queueName, XCHG_NAME, "", headers);
            break;
        }
        System.out.println(" A Waiting for messages. To exit press CTRL+C");
        // 在同一时间不要给一个worker一个以上的消息。
        // 不要将一个新的消息分发给worker知道它处理完了并且返回了前一个消息的通知标志（acknowledged）
        // 替代的，消息将会分发给下一个不忙的worker。
        channel.basicQos(1); // server push消息时的队列长度

        // 用来缓存服务器推送过来的消息
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 为channel声明一个consumer，服务器会推送消息
        // 参数1:队列名称
        // 参数2：是否自动应答，如果为真，消息一旦被消费者收到，服务端就知道该消息已经投递，从而从队列中将消息剔除，
        // 否则，需要在消费者端手工调用channel.basicAck()方法通知服务端，
        // 如果没有调用，消息将会进入unacknowledged状态，并且当消费者连接断开后变成ready状态重新进入队列
        // 参数3：消费者
        channel.basicConsume(queueName, false, consumer);
        // channel.basicGet() //使用该函数主动去服务器检索是否有新消息，而不是等待服务器推送

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            System.out.println("Received " + new String(delivery.getBody()));

            // 回复ack包，如果不回复，消息不会在服务器删除
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            // channel.basicReject();
            // channel.basicNack();
            // 可以通过这两个函数拒绝消息，可以指定消息在服务器删除还是继续投递给其他消费者
        }
    }
}