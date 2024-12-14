package com.example.API_Organizador_Eventos.error.exception

class NotFoundException(message: String) : RuntimeException("Not Found Exception (404). $message") {
}