package com.example.sportApp.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class StringSplitTest {

    @Test
    fun `test split with hyphen`() {
        val input = "part1-part2"
        val result = input.splitText()
        assertEquals(Pair("part1", "part2"), result)
    }

    @Test
    fun `test split without hyphen`() {
        val input = "singlepart"
        val result = input.splitText()
        assertEquals(Pair("singlepart", "singlepart"), result)
    }

    @Test
    fun `test split with multiple hyphens`() {
        val input = "part1-part2-part3"
        val result = input.splitText()
        assertEquals(Pair("part1", "part2-part3"), result)
    }

    @Test
    fun `test split with empty string`() {
        val input = ""
        val result = input.splitText()
        assertEquals(Pair("", ""), result)
    }

    @Test
    fun `test split with hyphen at the beginning`() {
        val input = "-part1"
        val result = input.splitText()
        assertEquals(Pair("", "part1"), result)
    }

    @Test
    fun `test split with hyphen at the end`() {
        val input = "part1-"
        val result = input.splitText()
        assertEquals(Pair("part1", ""), result)
    }

    @Test
    fun `test split with special characters`() {
        val input = "hello@world-goodbye@earth"
        val result = input.splitText()
        assertEquals(Pair("hello@world", "goodbye@earth"), result)
    }
}