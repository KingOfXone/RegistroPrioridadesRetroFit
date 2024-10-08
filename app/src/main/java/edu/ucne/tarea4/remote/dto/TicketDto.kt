package edu.ucne.tarea4.remote.dto

import java.util.Date

data class TicketDto (
    val ticketId: Int?,
    val fecha: Date,
    val prioridadId: Int,
    val clienteId: Int,
    val sistemaId: Int,
    val solicitadopor: String? ,
    val asunto: String?,
    val descripcion: String?

)


