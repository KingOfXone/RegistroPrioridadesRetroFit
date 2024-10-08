package edu.ucne.tarea4.repository

import edu.ucne.tarea4.remote.PrioridadesApi
import edu.ucne.tarea4.remote.dto.PrioridadDto
import javax.inject.Inject

class PrioridadRepository @Inject constructor(

    private val prioridadApi: PrioridadesApi
    ) {
        suspend fun savePrioridad (prioridad: PrioridadDto) = prioridadApi.SavePrioridad(prioridad)

        suspend fun deletePrioridad(id: Int) = prioridadApi.DeletePrioridad(id)

        suspend fun getPrioridad(id: Int) = prioridadApi.getPrioridad(id)

        suspend fun getAllPrioridad() = prioridadApi.getAllPrioridad()
}
