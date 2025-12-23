package com.proto.mvi.ui.counterscreen

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CounterViewModelTest {

    private lateinit var viewModel: CounterViewModel

    // Setup before each test
    private fun setup() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `initial state has count zero and no loading`() = runTest {
        setup()

        viewModel.state.test {
            val initialState = awaitItem()  // First emission is initial state
            assertEquals(0, initialState.count)
            assertEquals(false, initialState.isLoading)
            assertEquals(null, initialState.error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `increment increases count by 1`() = runTest {
        setup()

        viewModel.state.test {
//            skipItems(1) // Skip initial state
            val initialState = awaitItem()
            assertEquals(0, initialState.count)

            viewModel.processIntent(CounterIntent.Increment)
            val state1 = awaitItem()
            assertEquals(1, state1.count)

            viewModel.processIntent(CounterIntent.Increment)
            val state2 = awaitItem()
            assertEquals(2, state2.count)

            cancelAndIgnoreRemainingEvents()
        }
    }
}