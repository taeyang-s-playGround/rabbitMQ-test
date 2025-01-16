package scul.projectscul.global.security.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import practice.rabbitMQ.application.error.dto.RabbitMQException

@RestControllerAdvice
class ExceptionHandler{
    @ExceptionHandler(RabbitMQException::class)
    fun handlingPickException(e: RabbitMQException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        return ResponseEntity(
            ErrorResponse(code.status, code.message),
            HttpStatus.valueOf(code.status)
        )
    }
}
