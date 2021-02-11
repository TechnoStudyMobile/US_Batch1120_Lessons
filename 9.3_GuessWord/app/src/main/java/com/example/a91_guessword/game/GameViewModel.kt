package com.example.a91_guessword.game

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.a91_guessword.LOG_TAG

class GameViewModel: ViewModel() {

    var score = 0
        private set

    private var wordList = mutableListOf<String>()

    fun resetWords() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail"
            /*"soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"*/
        )
        score = 0
        wordList.shuffle()
    }

    private fun getSingleWord(): String? {
        if (wordList.isNotEmpty()) {
            //val random = (0 until wordList.size).random()
            return wordList.removeAt(0)
        }
        return null
    }

    fun skip(): String? {
        score--
        return getSingleWord()
    }

    fun next(): String? {
        score++
        return getSingleWord()
    }

    fun getFirstWord(): String {
        return if (wordList.isNotEmpty()) wordList.removeAt(0) else ""
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG,"GameViewModel is destroyed")
    }
}