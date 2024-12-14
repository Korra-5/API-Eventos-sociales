package com.example.API_Organizador_Eventos.error.exception

class BadRequestException(message: String) : RuntimeException("Bad Request Exception (400). $message") {
}