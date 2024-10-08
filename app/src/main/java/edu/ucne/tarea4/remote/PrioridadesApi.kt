package edu.ucne.tarea4.remote

import edu.ucne.tarea4.remote.dto.ClienteDto
import edu.ucne.tarea4.remote.dto.PrioridadDto
import edu.ucne.tarea4.remote.dto.SistemaDto
import edu.ucne.tarea4.remote.dto.TicketDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PrioridadesApi {

    @GET("api/Prioridades/{id}")
    suspend fun getPrioridad(@Path("id") id: Int): PrioridadDto
    @GET("api/Prioridades")
    suspend fun getAllPrioridad(): List<PrioridadDto>
    @POST("api/Prioridades")
    suspend fun SavePrioridad(@Body prioridadDto: PrioridadDto?): PrioridadDto
    @DELETE("api/Prioridades/{id}")
    suspend fun DeletePrioridad(@Path("id") id: Int)



    @GET("api/Clientes/{id}")
    suspend fun getCliente(@Path("id") id: Int): ClienteDto
    @GET("api/Clientes")
    suspend fun getAllCliente(): List<ClienteDto>
    @POST("api/Clientes")
    suspend fun SaveCliente(@Body clienteDto: ClienteDto?): ClienteDto
    @DELETE("api/Clientes/{id}")
    suspend fun DeleteCliente(@Path("id") id: Int)

    @GET("api/Sistemas/{id}")
    suspend fun getSistema(@Path("id") id: Int): SistemaDto
    @GET("api/Sistemas")
    suspend fun getAllSistema(): List<SistemaDto>
    @POST("api/Sistemas")
    suspend fun SaveSistema(@Body sistemaDto: SistemaDto?): SistemaDto
    @DELETE("api/Sistemas/{id}")
    suspend fun DeleteSistema(@Path("id") id: Int)


    @GET("api/Tickets/{id}")
    suspend fun getTicket(@Path("id") id: Int): TicketDto
    @GET("api/Tickets")
    suspend fun getAllTicket(): List<TicketDto>
    @POST("api/Tickets")
    suspend fun SaveTicket(@Body ticketDto: TicketDto?): TicketDto
    @DELETE("api/Tickets/{id}")
    suspend fun DeleteTicket(@Path("id") id: Int)


}