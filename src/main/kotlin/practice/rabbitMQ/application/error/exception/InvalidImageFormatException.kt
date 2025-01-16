package practice.rabbitMQ.application.error.exception

import practice.rabbitMQ.application.error.ErrorCode
import practice.rabbitMQ.application.error.dto.RabbitMQException

class InvalidImageFormatException : RabbitMQException(ErrorCode.INVALID_IMAGE_FORMAT)
