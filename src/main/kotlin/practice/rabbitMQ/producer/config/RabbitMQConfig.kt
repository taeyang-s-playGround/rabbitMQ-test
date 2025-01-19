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
        const val IMAGE_S3_QUEUE = "s3-upload-queue"
        //프로듀서는 메세지를 큐에 직접 보내는 대신, 교환기(excahnge)로 메세지를 보낸다
        const val EXCHANGE_NAME = "s3-upload-exchange"
        //교환기는 이 라우팅 키로 어떤 큐에 메세지를 보낼지 결정한다.
        const val ROUTING_KEY = "s3-upload-key" //
    }

    @Bean
    fun queue(): Queue = Queue(IMAGE_S3_QUEUE, true)

    @Bean
    fun exchange(): DirectExchange = DirectExchange(EXCHANGE_NAME)

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY)
}
