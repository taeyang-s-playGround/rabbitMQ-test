package practice.rabbitMQ.producer.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
@Configuration
class RabbitMQConfig {

    companion object {
        const val QUEUE_NAME = "s3-upload-queue"
        const val EXCHANGE_NAME = "s3-upload-exchange"
        const val ROUTING_KEY = "s3-upload-key"
    }

    @Bean
    fun queue(): Queue = Queue(QUEUE_NAME, true)

    @Bean
    fun exchange(): DirectExchange = DirectExchange(EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
}
