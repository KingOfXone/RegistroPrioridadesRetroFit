package edu.ucne.tarea4.repository

import edu.ucne.tarea4.remote.PrioridadesApi
import edu.ucne.tarea4.remote.dto.SistemaDto
import javax.inject.Inject

class SistemasRepository @Inject constructor(

    private val prioridadApi: PrioridadesApi
) {
    suspend fun saveSistema(sistema: SistemaDto) = prioridadApi.SaveSistema(sistema)

    suspend fun deleteSistema(id: Int) = prioridadApi.DeleteSistema(id)

    suspend fun getSistema(id: Int) = prioridadApi.getSistema(id)

    suspend fun getAllSistema() = prioridadApi.getAllSistema()
}