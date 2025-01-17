package practice.rabbitMQ.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import practice.rabbitMQ.application.s3.AwsS3Adapter
import practice.rabbitMQ.producer.FileUploadRequest
import practice.rabbitMQ.producer.config.RabbitMQConfig

@Component
class RabbitMQConsumer(
    private val awsS3Adapter: AwsS3Adapter
) {

    @RabbitListener(queues = [RabbitMQConfig.QUEUE_NAME])
    fun consumeFileUploadRequest(requests: List<FileUploadRequest>) {
        println("Processing file upload requests: ${requests.map { it.fileName }}")

        // 요청을 MultipartFile로 변환 후 S3에 업로드
        val files = requests.map {
            MockMultipartFile(it.fileName, it.fileName, null, it.fileData.inputStream())
        }
        awsS3Adapter.uploadImages(files)
    }
}