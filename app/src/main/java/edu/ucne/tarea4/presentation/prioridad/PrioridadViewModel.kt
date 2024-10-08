package edu.ucne.tarea4.presentation.prioridad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.tarea4.remote.dto.PrioridadDto
import edu.ucne.tarea4.repository.PrioridadRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrioridadViewModel @Inject constructor(
    private val prioridadrepository: PrioridadRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getPrioridades()
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
    fun onDescripcionChange(descripcion: String) {
        _uiState.update {
            it.copy(descripcion = descripcion)
        }
    }
    fun onDiasCompromisoChange(diasCompromiso: Int) {
        _uiState.update {
            it.copy(diasCompromiso = diasCompromiso)
        }
    }
}











data class UiState(
    val prioridadId: Int? = null,
    val descripcion: String = "",
    val diasCompromiso: Int = 0,
    val errorMessage: String? = null,
    val prioridades: List<PrioridadDto> = emptyList()

)

fun UiState.toEntity() = PrioridadDto(
        prioridadId = prioridadId,
        descripcion = descripcion,
        diasCompromiso = diasCompromiso
)