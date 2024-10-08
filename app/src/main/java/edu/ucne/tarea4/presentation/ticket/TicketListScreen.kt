package edu.ucne.tarea4.presentation.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.tarea4.remote.dto.TicketDto

@Composable
fun TicketListScreen(
    viewModel: TicketViewModel = hiltViewModel(),
    createTicket: () -> Unit,
    onEditTicket: (Int) -> Unit,
    onDeleteTicket: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    TikcetListBodyScreen(
        uiState = uiState,
        createTicket = createTicket,
        onEditTicket = onEditTicket,
        onDeleteTicket = onDeleteTicket
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TikcetListBodyScreen(
    uiState: UiState,
    createTicket: () -> Unit,
    onEditTicket: (Int) -> Unit,
    onDeleteTicket: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de Tickets") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createTicket,
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Ticket"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(uiState.tickets) { ticket ->
                    ListRow(
                        ticket = ticket,
                        onEditTicket = onEditTicket,
                        onDeleteTicket = onDeleteTicket
                    )
                }
            }
        }
    }
}

@Composable
fun ListRow(
    ticket: TicketDto,
    onEditTicket: (Int) -> Unit,
    onDeleteTicket: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = "ID: " + ticket.ticketId.toString(),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Cliente: " + ticket.clienteId.toString(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Prioridad: " + ticket.prioridadId.toString(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Fecha: " + ticket.fecha.toString(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Asunto: " + ticket.asunto.toString(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Descripcion: " + ticket.descripcion.toString(),
                    textAlign = TextAlign.Center
                )
            }
        }
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(
//                text = { Text("Editar") },
//                onClick = {
//                    expanded = false
//                    onEditPrioridad(prioridad.prioridadId!!)
//                }
//            )
//            DropdownMenuItem(
//                text = { Text("Eliminar") },
//                onClick = {
//                    expanded = false
//                    onDeletePrioridad(prioridad.prioridadId!!)
//                }
//            )
//        }
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
    }
}