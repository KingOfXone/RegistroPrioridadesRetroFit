package edu.ucne.tarea4.repository


import edu.ucne.tarea4.remote.PrioridadesApi
import edu.ucne.tarea4.remote.dto.ClienteDto
import javax.inject.Inject

class ClienteRepository @Inject constructor(
    private val prioridadApi: PrioridadesApi
) {
    suspend fun saveCliente (cliente: ClienteDto) = prioridadApi.SaveCliente(cliente)

    suspend fun deleteDelete(id: Int) = prioridadApi.DeleteCliente(id)

    suspend fun getCliente(id: Int) = prioridadApi.getCliente(id)

    suspend fun getAllCliente() = prioridadApi.getAllCliente()

}