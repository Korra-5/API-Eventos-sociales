package com.example.API_Organizador_Eventos.error.exception

class NotAuthorizedException(message: String) : RuntimeException("Not Authorized Exception (401). $message") {
}