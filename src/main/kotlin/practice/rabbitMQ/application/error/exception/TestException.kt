package practice.rabbitMQ.application.error.exception

import practice.rabbitMQ.application.error.ErrorCode
import practice.rabbitMQ.application.error.dto.RabbitMQException

class TestException : RabbitMQException(ErrorCode.TEST_API_ERROR)
