package edu.ucne.tarea4.presentation.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.tarea4.remote.dto.PrioridadDto
import edu.ucne.tarea4.remote.dto.TicketDto
import edu.ucne.tarea4.repository.PrioridadRepository
import edu.ucne.tarea4.repository.TicketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val prioridadrepository: PrioridadRepository,
    private val ticketrepository: TicketRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getPrioridades()
        getTickets()
    }

    fun save() {
        viewModelScope.launch {
            if (uiState.value.descripcion.isBlank() || uiState.value.diasCompromiso <= 0) {
                _uiState.update { it.copy(errorMessage = "Debe completar todos los campos") }
            } else {
                prioridadrepository.savePrioridad(uiState.value.toEntity())
                nuevo()
            }
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                prioridadId = null,
                descripcion = "",
                diasCompromiso = 0,
                errorMessage = null
            )
        }
    }

    fun selecetedPrioridad(prioridadId: Int) {
        viewModelScope.launch {
            if (prioridadId > 0) {
                val prioridad = prioridadrepository.getPrioridad(prioridadId)
                _uiState.update {
                    it.copy(
                        prioridadId = prioridad.prioridadId,
                        descripcion = prioridad.descripcion ?: "",
                        diasCompromiso = prioridad.diasCompromiso ?: 0
                    )
                }

            }
        }

    }
    fun delete() {
        viewModelScope.launch {
            prioridadrepository.deletePrioridad(uiState.value.prioridadId!!)
            nuevo()
        }
    }

    private fun getPrioridades() {
        viewModelScope.launch {
            val prioridades = prioridadrepository.getAllPrioridad()
            _uiState.update {
                it.copy(prioridades = prioridades)
            }
        }
    }

    private fun getTickets(){
        viewModelScope.launch {
            val tickets = ticketrepository.getAllTicket()
            _uiState.update {
                it.copy(tickets = tickets)
            }
        }
    }
    fun onTicketIdChange(ticketId: Int) {
        _uiState.update {
            it.copy(ticketId = ticketId)
        }
    }
    fun onFechaChange(fecha: Date) {
        _uiState.update {
            it.copy(fecha = fecha)
        }
    }
    fun onPrioridadIdChange(prioridadId: Int) {
        _uiState.update {
            it.copy(prioridadId = prioridadId)
        }
    }
    fun onClienteIdChange(clienteId: Int) {
        _uiState.update {
            it.copy(clienteId = clienteId)
        }
    }
    fun onSistemaIdChange(sistemaId: Int) {
        _uiState.update {
            it.copy(sistemaId = sistemaId)
        }
    }
    fun onSolicitadoporChange(solicitadopor: String) {
        _uiState.update {
            it.copy(solicitadopor = solicitadopor)
        }
    }
    fun onAsuntoChange(asunto: String) {
        _uiState.update {
            it.copy(asunto = asunto)
        }
    }
    fun onDescripcionChange(descripcion: String) {
        _uiState.update {
            it.copy(descripcion = descripcion)
        }
    }


}


data class UiState(
    val ticketId: Int? = null,
    val fecha: Date = Date(),
    val prioridadId: Int? = null,
    val clienteId: Int? = null,
    val sistemaId: Int? = null,
    val solicitadopor: String = "",
    val asunto: String = "",
    val descripcion: String = "",
    val prioridades: List<PrioridadDto> = emptyList(),
    val tickets: List<TicketDto> = emptyList(),
    val diasCompromiso: Int = 0,
    val errorMessage: String? = null
)

fun UiState.toEntity() = PrioridadDto(
    prioridadId = prioridadId,
    descripcion = descripcion,
    diasCompromiso = diasCompromiso
)