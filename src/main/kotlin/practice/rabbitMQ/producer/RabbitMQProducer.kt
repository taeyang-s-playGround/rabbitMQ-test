package practice.rabbitMQ.producer
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import practice.rabbitMQ.producer.config.RabbitMQConfig

@Component
class RabbitMQProducer(
    private val rabbitTemplate: RabbitTemplate
) {

    fun sendFileUploadRequest(files: List<MultipartFile>) {
        // 파일 데이터를 RabbitMQ 큐에 전송함
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE_NAME,
            RabbitMQConfig.ROUTING_KEY,
            files.map { FileUploadRequest(it.originalFilename ?: "unknown", it.bytes) }
        )
        println("File upload request sent to RabbitMQ: ${files.map { it.originalFilename }}")
    }
}

data class FileUploadRequest(
    val fileName: String,
    val fileData: ByteArray
)
