package practice.rabbitMQ.application.error

enum class ErrorCode (
    val status: Int,
    val message: String
) {

    //file
    INVALID_IMAGE_FORMAT(400, "Invalid Image Format"),

    //test
    TEST_API_ERROR(400, "API Test Error"),

    //server
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}