package practice.rabbitMQ.application.error

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.io.IOException
import org.springframework.web.filter.OncePerRequestFilter
import practice.rabbitMQ.application.error.dto.RabbitMQException
import scul.projectscul.global.security.error.ErrorResponse

class ExceptionFilter (
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: RabbitMQException) {
            writerErrorCode(response, e.errorCode)
        } catch (e: Exception) {
            e.printStackTrace()
            writerErrorCode(response, GlobalErrorCode.INTERNAL_SERVER_ERROR)
        }
    }

    @Throws(IOException::class)
    private fun writerErrorCode(response: HttpServletResponse, errorCode: ErrorCode) {
        println(errorCode.toString() + "1111111111111111111111")
        response.apply {
            status = errorCode.status
            characterEncoding = "UTF-8"
            contentType = "application/json"
            writer.write(objectMapper.writeValueAsString(ErrorResponse(errorCode.status, errorCode.message)))
            writer.flush()
        }
    }

    @Throws(IOException::class)
    private fun writerErrorCode(response: HttpServletResponse, globalErrorCode: GlobalErrorCode) {
        println(globalErrorCode.toString() + "55555555555555555555")
        response.apply {
            status = globalErrorCode.status
            characterEncoding = "UTF-8"
            contentType = "application/json"
            writer.write(objectMapper.writeValueAsString(ErrorResponse(globalErrorCode.status, globalErrorCode.message)))
            writer.flush()
        }
    }
}