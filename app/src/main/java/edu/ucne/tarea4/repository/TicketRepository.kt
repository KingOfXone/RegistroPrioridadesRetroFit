package edu.ucne.tarea4.repository

import edu.ucne.tarea4.remote.PrioridadesApi
import edu.ucne.tarea4.remote.dto.TicketDto
import javax.inject.Inject

class TicketRepository @Inject constructor( private val prioridadApi: PrioridadesApi
) {
    suspend fun saveTicket(ticket: TicketDto) = prioridadApi.SaveTicket(ticket)

    suspend fun deleteTicket(id: Int) = prioridadApi.DeleteTicket(id)

    suspend fun getTicket(id: Int) = prioridadApi.getTicket(id)

    suspend fun getAllTicket() = prioridadApi.getAllTicket()
}