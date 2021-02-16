package com.example.a91_guessword.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a91_guessword.LOG_TAG

class GameViewModel: ViewModel() {

    var score = 0
        private set

    private val _wordLiveData = MutableLiveData<String?>()
    val wordLiveData: LiveData<String?>
        get() = _wordLiveData

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
            return wordList.removeAt(0)
        }
        return null
    }

    fun skip() {
        score--
        _wordLiveData.value = getSingleWord()
    }

    fun next() {
        score++
        _wordLiveData.value = getSingleWord()
    }

    fun getFirstWord() {
        if (wordList.isNotEmpty())
            _wordLiveData.value = wordList.removeAt(0)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG,"GameViewModel is destroyed")
    }
}