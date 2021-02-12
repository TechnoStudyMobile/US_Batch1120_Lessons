package com.example.a91_guessword.game

import android.util.Log
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a91_guessword.LOG_TAG

class GameViewModel: ViewModel() {

    var score = 0
        private set

    val wordLiveData = MutableLiveData<String?>()
    val isCholateLiveData = MutableLiveData<Boolean>()
    val isCreamLiveData = MutableLiveData<Boolean>()
    val totalCountLiveData = MutableLiveData<Int>()

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
        wordLiveData.value = getSingleWord()
    }

    fun next() {
        score++
        wordLiveData.value = getSingleWord()
    }

    fun getFirstWord() {
        if (wordList.isNotEmpty())
            wordLiveData.value = wordList.removeAt(0)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG,"GameViewModel is destroyed")
    }
}