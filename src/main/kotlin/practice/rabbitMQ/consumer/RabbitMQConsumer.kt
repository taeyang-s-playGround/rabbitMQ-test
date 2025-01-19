package practice.rabbitMQ.consumer

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.mock.web.MockMultipartFile
import org.springframework.stereotype.Component
import practice.rabbitMQ.application.s3.AwsS3Adapter
import practice.rabbitMQ.producer.FileUploadRequest
import practice.rabbitMQ.producer.config.RabbitMQConfig

@Component
class RabbitMQConsumer(
    private val awsS3Adapter: AwsS3Adapter
) {

    //@RabbitListener 어노테이션이 붙은 메서드는 'IMAGE_S3_QUEUE'라는 이름의 메세지가 큐에 들어온다면,
    //Spring AMQP가 이를 감지하여 자동으로 호출됨
    @RabbitListener(queues = [RabbitMQConfig.IMAGE_S3_QUEUE])
    fun consumeFileUploadRequest(requests: List<FileUploadRequest>) {
        println("Processing file upload requests: ${requests.map { it.fileName }}")

        val files = requests.map {
            MockMultipartFile(it.fileName, it.fileName, null, it.fileData.inputStream())
        }
        awsS3Adapter.uploadImages(files)
    }
}
