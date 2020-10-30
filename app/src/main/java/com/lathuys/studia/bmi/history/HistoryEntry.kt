package com.lathuys.studia.bmi.history

import java.util.*

class HistoryEntry(var height: String, var mass: String, var bmi: String, var date: String) {
    companion object {
        fun createFromSerial(serial: String): HistoryEntry {
            val vals = serial.split('|')
            val height = vals[0]
            val mass = vals[1]
            val bmi = vals[2]
            val date = vals[3]

            return HistoryEntry(height, mass, bmi, date)
        }
    }
}