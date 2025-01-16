package practice.rabbitMQ.application.error.dto

import practice.rabbitMQ.application.error.ErrorCode

abstract class RabbitMQException (
    val errorCode: ErrorCode
): Exception()
