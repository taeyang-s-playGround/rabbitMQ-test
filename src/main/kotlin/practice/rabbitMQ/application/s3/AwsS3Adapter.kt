package practice.rabbitMQ.application.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.internal.Mimetypes
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import practice.rabbitMQ.application.error.exception.InvalidImageFormatException
import java.io.IOException
import java.util.*

@Component
class AwsS3Adapter (
    private val awsProperties: AwsS3Properties,
    private val amazonS3Client: AmazonS3Client
)  {

    fun uploadImages(files: List<MultipartFile>): List<String> {
        return files.map { upload(it) }
    }

    private fun upload(file: MultipartFile): String {
        val fileName = "${UUID.randomUUID()}_${file.originalFilename}"
        inputS3(file, fileName)

        return getResourceUrl(fileName)
    }

    private fun inputS3(file: MultipartFile, fileName: String) {
        try {
            val inputStream = file.inputStream
            val objectMetadata = ObjectMetadata().apply {
                this.contentLength = file.size
                this.contentType = Mimetypes.getInstance().getMimetype(file.originalFilename)
            }

            amazonS3Client.putObject(
                PutObjectRequest(awsProperties.bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw InvalidImageFormatException()
        }
    }

    fun getResourceUrl(fileName: String): String {
        return amazonS3Client.getResourceUrl(awsProperties.bucket, fileName)
    }
}
