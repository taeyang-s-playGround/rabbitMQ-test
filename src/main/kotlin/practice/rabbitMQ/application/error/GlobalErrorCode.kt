package practice.rabbitMQ.application.error

enum class GlobalErrorCode(
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}