package edu.ucne.tarea4.presentation.sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.tarea4.remote.dto.SistemaDto
import edu.ucne.tarea4.repository.SistemasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SistemasViewModel @Inject constructor(
    private val sistemasRepository: SistemasRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        getSistemas()
    }

    fun save() {
        viewModelScope.launch {
            if (_uiState.value.nombreSistema.isBlank()) {
                _uiState.update {
                    it.copy(errorMessage = "El nombre del sistema no puede estar vacío")
                }
            } else {
                sistemasRepository.saveSistema(_uiState.value.toEntity())
                nuevo()
            }
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                sistemaId = 0,
                nombreSistema = "",
                descripcionSistema = "",  // Añadir un valor por defecto para 'descripcionSistema'
                errorMessage = null
            )
        }
    }

    fun selectedSistema(sistemaId: Int) {
        viewModelScope.launch {
            if (sistemaId > 0) {
                val sistema = sistemasRepository.getSistema(sistemaId) // Llamada al método correcto
                _uiState.update {
                    it.copy(
                        sistemaId = sistema.sistemaId,
                        nombreSistema = sistema.nombreSistema,
                        descripcionSistema = sistema.descripcionSistema // Asegúrate de obtener 'descripcionSistema'
                    )
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            sistemasRepository.deleteSistema(_uiState.value.sistemaId!!)
            nuevo()
        }
    }

    private fun getSistemas() {
        viewModelScope.launch {
            val sistemas = sistemasRepository.getAllSistema() // Esto debe devolver una lista
            _uiState.update {
                it.copy(sistemas = sistemas) // Asegúrate de que aquí estás pasando una lista
            }
        }
    }

    fun onSistemaNombreChange(nombreSistema: String) {
        _uiState.update {
            it.copy(nombreSistema = nombreSistema)
        }
    }

    fun onSistemaIdChange(sistemaId: Int) {
        _uiState.update {
            it.copy(sistemaId = sistemaId)
        }
    }

    fun onDescripcionSistemaChange(descripcionSistema: String) {
        _uiState.update {
            it.copy(descripcionSistema = descripcionSistema)
        }
    }
}

data class UiState(
    val sistemaId: Int? = null,
    val nombreSistema: String = "",
    val descripcionSistema: String = "",  // Añadir el campo 'descripcionSistema'
    val errorMessage: String? = null,
    val sistemas: List<SistemaDto> = emptyList() // Este campo debe contener una lista
)

fun UiState.toEntity() = SistemaDto(
    sistemaId = sistemaId,
    nombreSistema = nombreSistema,
    descripcionSistema = descripcionSistema  // Asegúrate de pasar 'descripcionSistema'
)