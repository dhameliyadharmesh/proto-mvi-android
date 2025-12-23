package com.proto.mvi.ui.counterscreen

sealed class CounterIntent {
    object Increment : CounterIntent()
    object Decrement : CounterIntent()
    object SimulateLoading : CounterIntent()
}