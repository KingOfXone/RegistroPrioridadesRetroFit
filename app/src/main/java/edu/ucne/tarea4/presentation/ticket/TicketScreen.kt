package edu.ucne.tarea4.presentation.ticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Date

@Composable
fun TicketScreen(
    ticketId: Int,
    viewModel: TicketViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TicketBodyScreen(
        uiState = uiState,
        onFechaChange = viewModel::onFechaChange,
        onPrioridadIdChange = viewModel::onPrioridadIdChange,
        onClienteIdChange = viewModel::onClienteIdChange,
        onSistemaIdChange = viewModel::onSistemaIdChange,
        onSolicitadoPorChange = viewModel::onSolicitadoporChange,
        onAsuntoChange = viewModel::onAsuntoChange,
        onDescripcionChange = viewModel::onDescripcionChange,
        savePrioridad = viewModel::save,
        goBack = goBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketBodyScreen(
    uiState: UiState,
    onFechaChange: (Date) -> Unit,
    onPrioridadIdChange: (Int) -> Unit,
    onClienteIdChange: (Int) -> Unit,
    onSistemaIdChange: (Int) -> Unit,
    onSolicitadoPorChange: (String) -> Unit,
    onAsuntoChange: (String) -> Unit,
    onDescripcionChange: (String) -> Unit,
    savePrioridad: () -> Unit,
    goBack: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            Text(
                text = "Registro de Prioridades",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = uiState.fecha.toString(),
                onValueChange = { },
                label = { Text("Fecha") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.prioridadId.toString(),
                onValueChange = { },
                label = { Text("Prioridad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.clienteId.toString(),
                onValueChange = { },
                label = { Text("Cliente") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.sistemaId.toString(),
                onValueChange = { },
                label = { Text("Sistema") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.solicitadopor,
                onValueChange = { },
                label = { Text("Solicitado por") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.asunto,
                onValueChange = { },
                label = { Text("Asunto") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = uiState.descripcion,
                onValueChange = { },
                label = { Text("Descripcion") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(8.dp))
            uiState.errorMessage?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it,
                        color = Color.Red
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = goBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver"
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Volver")
                }
                OutlinedButton(onClick = {
                    savePrioridad()
                }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Guardar"
                    )
                    Text("Guardar")
                }
            }
        }
    }
}