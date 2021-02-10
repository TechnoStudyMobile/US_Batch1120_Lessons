package com.example.a91_guessword

import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private var score = 0

    fun increaseBy(value: Int) {
        score += value
    }

    fun retrieveScore(): String {
        return score.toString()
    }

    override fun onCleared() {
        super.onCleared()
        // Called when ViewModel is gets destroyed.
    }
}