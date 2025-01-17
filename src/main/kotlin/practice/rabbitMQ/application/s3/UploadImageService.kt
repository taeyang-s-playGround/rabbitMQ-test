package practice.rabbitMQ.application.s3

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import practice.rabbitMQ.application.ImageUrlResponse

@Service
@Transactional
class UploadImageService(
    private val awsS3Adapter: AwsS3Adapter
) {
    fun execute(multipartFiles: List<MultipartFile>): ImageUrlResponse {
        val imageUrl: List<String> = awsS3Adapter.uploadImages(multipartFiles)

        return ImageUrlResponse(imageUrl)
    }
}
