package edu.ucne.tarea4.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.ucne.tarea4.data.local.dao.PrioridadDao
import edu.ucne.tarea4.data.local.dao.TicketDao
import edu.ucne.tarea4.data.local.entities.PrioridadEntity
import edu.ucne.tarea4.data.local.entities.TicketEntity


@Database(
    entities = [
        PrioridadEntity::class,
        TicketEntity::class],

        version = 1,
        exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PrioridadDb : RoomDatabase() {
    abstract fun prioridadDao(): PrioridadDao
    abstract fun ticketDao(): TicketDao
        }