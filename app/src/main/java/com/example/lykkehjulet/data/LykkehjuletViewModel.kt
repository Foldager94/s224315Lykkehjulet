package com.example.lykkehjulet.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random



class LykkehjuletViewModel: ViewModel() {
    var gameData by  mutableStateOf(LykkehjulGameData())


    fun restartGame(){
        gameData = LykkehjulGameData()
    }


    fun guessWord() {
        if(gameData.wordGuess != ""){
            if(gameData.wordGuess.lowercase() == gameData.word.lowercase()){
                gameData.gameWon = true
                gameData.gameRunning = false
            } else {
                gameData.alert = 1
                gameData.wordGuess = ""
                checkGameStatus()
            }
        }else{
            gameData.alert = 6
        }
    }

    fun spineWheel() {
        val pointList = listOf("100", "100", "300", "500", "500", "500", "500", "500", "600", "600", "800", "800", "800", "800", "800", "1000", "1000", "1500", "Fallit")
        val randomIndex = Random.nextInt(pointList.size)
        gameData.wheelPlacement = pointList[randomIndex]
        wheelOutCome(gameData.wheelPlacement)
    }

    fun changeKeyboard(keyboard: String){
        gameData.keyBoard = keyboard
    }

    fun buyVowelLogic(letter: String){
        if(!gameData.hasWheelBeenSpined) {
            if (!getIsClicked(letter) && gameData.pointTotal >= 500) {
                buyVowel(letter)
                setIsClicked(letter)
                gameData.alert = 0
            } else {
                gameData.alert = 3
            }
        }else {
            gameData.alert = 4
        }
    }

    fun guessConsunant(letter:String){
        var numberOfOcurrences = 0
        val chars = gameData.hashWord.toCharArray()
        for ((index, char) in gameData.word.withIndex()){
            if (letter.lowercase() == char.toString().lowercase()){
                chars[index] = char
                numberOfOcurrences++
            }
        }
        if (numberOfOcurrences == 0){
            gameData.lifeTotal--
        }else{
            gameData.pointTotal += numberOfOcurrences * gameData.currentPoint
        }
        gameData.hashWord = String(chars)
        checkGameStatus()
    }
    fun buyVowel(letter:String){
        gameData.pointTotal -= 500
        val chars = gameData.hashWord.toCharArray()
        for ((index, char) in gameData.word.withIndex()) {
            if (letter.lowercase() == char.toString().lowercase()) {
                chars[index] = char
            }
        }
        gameData.hashWord = String(chars)
    }

    fun checkGameStatus(){
        if(gameData.lifeTotal == 0){
            gameData.gameRunning = false
            gameData.gameWon = false
        }
    }

    fun guessConsunantLogic(letter: String){
        if (!getIsClicked(letter) && gameData.hasWheelBeenSpined){
            guessConsunant(letter)
            setIsClicked(letter)
            gameData.hasWheelBeenSpined = false
            gameData.alert = 0
        }else{
            gameData.alert = 2
        }
    }

    private fun wheelOutCome(outCome: String){
        when (outCome){
            "100" -> gameData.currentPoint = 100
            "300" -> gameData.currentPoint = 300
            "500" -> gameData.currentPoint = 500
            "600" -> gameData.currentPoint = 600
            "800" -> gameData.currentPoint = 800
            "1000" -> gameData.currentPoint = 1000
            "1500" -> gameData.currentPoint = 1500
            "Fallit" -> {gameData.pointTotal = 0; gameData.currentPoint = 0; gameData.hasWheelBeenSpined = false}
        }
    }

    fun setIsClicked(letter: String){
        when (letter) {
            "A" -> gameData.buttonIsClickList[0] = true
            "B" -> gameData.buttonIsClickList[1] = true
            "C" -> gameData.buttonIsClickList[2] = true
            "D" -> gameData.buttonIsClickList[3] = true
            "E" -> gameData.buttonIsClickList[4] = true
            "F" -> gameData.buttonIsClickList[5] = true
            "G" -> gameData.buttonIsClickList[6] = true
            "H" -> gameData.buttonIsClickList[7] = true
            "I" -> gameData.buttonIsClickList[8] = true
            "J" -> gameData.buttonIsClickList[9] = true
            "K" -> gameData.buttonIsClickList[10] = true
            "L" -> gameData.buttonIsClickList[11] = true
            "M" -> gameData.buttonIsClickList[12] = true
            "N" -> gameData.buttonIsClickList[13] = true
            "O" -> gameData.buttonIsClickList[14] = true
            "P" -> gameData.buttonIsClickList[15] = true
            "Q" -> gameData.buttonIsClickList[16] = true
            "R" -> gameData.buttonIsClickList[17] = true
            "S" -> gameData.buttonIsClickList[18] = true
            "T" -> gameData.buttonIsClickList[19] = true
            "U" -> gameData.buttonIsClickList[20] = true
            "V" -> gameData.buttonIsClickList[21] = true
            "W" -> gameData.buttonIsClickList[22] = true
            "X" -> gameData.buttonIsClickList[23] = true
            "Y" -> gameData.buttonIsClickList[24] = true
            "Z" -> gameData.buttonIsClickList[25] = true
            "Æ" -> gameData.buttonIsClickList[26] = true
            "Ø" -> gameData.buttonIsClickList[27] = true
            "Å" -> gameData.buttonIsClickList[28] = true
            }
        }
    fun getIsClicked(letter: String): Boolean {
        when (letter) {
            "A" -> return gameData.buttonIsClickList[0]
            "B" -> return gameData.buttonIsClickList[1]
            "C" -> return gameData.buttonIsClickList[2]
            "D" -> return gameData.buttonIsClickList[3]
            "E" -> return gameData.buttonIsClickList[4]
            "F" -> return gameData.buttonIsClickList[5]
            "G" -> return gameData.buttonIsClickList[6]
            "H" -> return gameData.buttonIsClickList[7]
            "I" -> return gameData.buttonIsClickList[8]
            "J" -> return gameData.buttonIsClickList[9]
            "K" -> return gameData.buttonIsClickList[10]
            "L" -> return gameData.buttonIsClickList[11]
            "M" -> return gameData.buttonIsClickList[12]
            "N" -> return gameData.buttonIsClickList[13]
            "O" -> return gameData.buttonIsClickList[14]
            "P" -> return gameData.buttonIsClickList[15]
            "Q" -> return gameData.buttonIsClickList[16]
            "R" -> return gameData.buttonIsClickList[17]
            "S" -> return gameData.buttonIsClickList[18]
            "T" -> return gameData.buttonIsClickList[19]
            "U" -> return gameData.buttonIsClickList[20]
            "V" -> return gameData.buttonIsClickList[21]
            "W" -> return gameData.buttonIsClickList[22]
            "X" -> return gameData.buttonIsClickList[23]
            "Y" -> return gameData.buttonIsClickList[24]
            "Z" -> return gameData.buttonIsClickList[25]
            "Æ" -> return gameData.buttonIsClickList[26]
            "Ø" -> return gameData.buttonIsClickList[27]
            "Å" -> return gameData.buttonIsClickList[28]
            else ->
                return false
            }
        }

    fun spinWheelLogic() {
        if (!gameData.hasWheelBeenSpined) {
            gameData.hasWheelBeenSpined = true
            spineWheel()
            gameData.alert = 0
        }else{
            gameData.alert = 5
        }
    }
}
