package com.example.sportApp.utils

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class FlowErrorHandlingTest {

    @Test
    fun `test catchAndHandleError should call onError with exception details`() = runTest {
        var capturedErrorMessage = ""
        var capturedErrorCode = 0

        val flowWithError = flow<String> {
            emit("First Item")
            throw Exception("Test Exception")
        }

        flowWithError.catchAndHandleError { message, code ->
            capturedErrorMessage = message
            capturedErrorCode = code
        }.collect(){}

        assertEquals("Test Exception", capturedErrorMessage)
        assertEquals(500, capturedErrorCode)
    }

    @Test
    fun `test catchAndHandleError should call onError with default values for unknown error`() = runTest {
        var capturedErrorMessage = ""
        var capturedErrorCode = 0

        val flowWithUnknownError = flow {
            emit("First Item")
            throw Throwable("Unknown error")
        }

        flowWithUnknownError.catchAndHandleError { message, code ->
            capturedErrorMessage = message
            capturedErrorCode = code
        }.collect(){}

        assertEquals("Unknown error", capturedErrorMessage)
        assertEquals(1000, capturedErrorCode)
    }

    @Test
    fun `test catchAndHandleError should allow flow emission without error`() = runTest {
        var capturedErrorMessage = ""
        var capturedErrorCode = 0

        val flowWithoutError = flow {
            emit("First Item")
            emit("Second Item")
        }

        val collectedItems = mutableListOf<String>()
        flowWithoutError.catchAndHandleError { message, code ->
            capturedErrorMessage = message
            capturedErrorCode = code
        }.collect { collectedItems.add(it) }

        assertEquals(listOf("First Item", "Second Item"), collectedItems)

        assertEquals("", capturedErrorMessage)
        assertEquals(0, capturedErrorCode)
    }
}