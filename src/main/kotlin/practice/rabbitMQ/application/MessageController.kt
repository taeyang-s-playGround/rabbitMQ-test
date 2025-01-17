package practice.rabbitMQ.application

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import practice.rabbitMQ.application.s3.UploadImageService
import practice.rabbitMQ.application.error.exception.TestException
import practice.rabbitMQ.producer.RabbitMQProducer

@RestController
@RequestMapping("/messages")
class MessageController(
    private val uploadImageService: UploadImageService,
    private val rabbitMQProducer: RabbitMQProducer
) {
    /*
    @PostMapping
    fun uploadImage(@RequestPart(value = "image", required = false) multipartFiles : List<MultipartFile>): ImageUrlResponse {
        return uploadImageService.execute(multipartFiles)
    }

     */

    @PostMapping
    fun uploadImage(@RequestPart(value = "image", required = false) multipartFiles : List<MultipartFile>) {
        rabbitMQProducer.sendFileUploadRequest(multipartFiles)
    }

    @GetMapping
    fun testAPI() {
        throw TestException()
    }
}
