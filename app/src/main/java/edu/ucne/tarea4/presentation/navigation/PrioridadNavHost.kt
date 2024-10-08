package edu.ucne.tarea4.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.tarea4.presentation.cliente.ClienteListScreen
import edu.ucne.tarea4.presentation.cliente.ClienteScreen
import edu.ucne.tarea4.presentation.home.HomeScreen
import edu.ucne.tarea4.presentation.prioridad.PrioridadListScreen
import edu.ucne.tarea4.presentation.prioridad.PrioridadScreen
import edu.ucne.tarea4.presentation.sistema.SistemaListScreen
import edu.ucne.tarea4.presentation.sistema.SistemaScreen
import edu.ucne.tarea4.presentation.ticket.TicketListScreen
import edu.ucne.tarea4.presentation.ticket.TicketScreen

@Composable
fun PrioridadNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen
    ) {
        composable<Screen.HomeScreen>  {
            HomeScreen(navController = navHostController)
        }
        composable<Screen.SistemaList> {
            SistemaListScreen(
                createSistema = {
                    navHostController.navigate(Screen.Sistema(0, false))
                },
                onEditSistema = {
                    navHostController.navigate(Screen.Sistema(it, false))
                },
                onDeleteSistema = {
                    navHostController.navigate(Screen.Sistema(it, true))
                }
            )
        }
        composable<Screen.Sistema> {
            val args = it.toRoute<Screen.Sistema>()
            SistemaScreen(
                sistemaId = args.sistemaId,
                goBack = {
                    navHostController.navigateUp()
                },
                isSistemaDelete = args.isSistemaDelete
            )
        }
        composable<Screen.ClienteList> {
            ClienteListScreen(
                createCliente = {
                    navHostController.navigate(Screen.Cliente(0, false))
                },
                onEditCliente = {
                    navHostController.navigate(Screen.Cliente(it, false))
                },
                onDeleteCliente = {
                    navHostController.navigate(Screen.Cliente(it, true))
                }
            )
        }
        composable<Screen.Cliente> {
            val args = it.toRoute<Screen.Cliente>()
            ClienteScreen(
                clienteId = args.clienteId,
                goBack = {
                    navHostController.navigateUp()
                },
                isClienteDelete = args.isClienteDelete
            )
        }
        composable<Screen.PrioridadList> {
            PrioridadListScreen(
                createPrioridad = {
                    navHostController.navigate(Screen.Prioridad(0, false))
                },
                onEditPrioridad = {
                    navHostController.navigate(Screen.Prioridad(it, false))
                },
                onDeletePrioridad = {
                    navHostController.navigate(Screen.Prioridad(it, true))
                }
            )
        }
        composable<Screen.Prioridad> {
            val args = it.toRoute<Screen.Prioridad>()
            PrioridadScreen(
                prioiridadId = args.prioridadId,
                goBack = {
                    navHostController.navigateUp()
                }
                //isPrioridadDelete = args.isPrioridadDelete
            )
        }
        composable<Screen.TicketList> {
            TicketListScreen(
                createTicket = {
                    navHostController.navigate(Screen.Ticket(0, false))
                },
                onEditTicket = {
                    navHostController.navigate(Screen.Ticket(it, false))
                },
                onDeleteTicket = {
                    navHostController.navigate(Screen.Ticket(it, true))
                }
            )
        }
        composable<Screen.Ticket> {
            val args = it.toRoute<Screen.Ticket>()
            TicketScreen(
                ticketId = args.ticketId,
                goBack = {
                    navHostController.navigateUp()
                },
                //isTicketDelete = args.isTicketDelete
            )
        }
    }
}