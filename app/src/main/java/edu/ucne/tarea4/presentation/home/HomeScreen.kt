package edu.ucne.tarea4.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import edu.ucne.tarea4.presentation.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(50.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.TicketList)
            }
        ) {
            Text("Tickets")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.ClienteList)
            }
        ) {
            Text("Clientes")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.SistemaList)
            }
        ) {
            Text("Sistemas")
        }

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.PrioridadList)
            }
        ) {
            Text("Prioridades")
        }
    }
}