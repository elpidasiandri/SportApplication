package com.example.sportApp.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class TimeFormattingTest {
    @Test
    fun `test positive time`() {
        val timeInMillis: Long = 3661000 // 1 hour, 1 minute, 1 second in milliseconds
        val formattedTime = timeInMillis.formatTime()
        assertEquals("01:01:01", formattedTime)
    }

    @Test
    fun `test zero time`() {
        val timeInMillis: Long = 0 // 0 milliseconds
        val formattedTime = timeInMillis.formatTime()
        assertEquals("00:00:00", formattedTime)
    }

    @Test
    fun `test negative time`() {
        val timeInMillis: Long = -3661000 // 1 hour, 1 minute, 1 second in milliseconds (negative)
        val formattedTime = timeInMillis.formatTime()
        assertEquals("Event has been over 01:01:01", formattedTime)
    }

    @Test
    fun `test small negative time`() {
        val timeInMillis: Long = -1000 // 1 second (negative)
        val formattedTime = timeInMillis.formatTime()
        assertEquals("Event has been over 00:00:01", formattedTime)
    }

    @Test
    fun `test large time`() {
        val timeInMillis: Long = 9876543210 // A large number of milliseconds
        val formattedTime = timeInMillis.formatTime()
        assertEquals("2743:29:03", formattedTime)
    }
}