package com.example.lykkehjulet.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.random.Random

class LykkehjulGameData() {
    var hasWheelBeenSpined by mutableStateOf(false)
    var pointTotal by mutableStateOf(0)
    var wheelPlacement by mutableStateOf("")
    var alert by mutableStateOf(-1)
    var alertText by mutableStateOf("")
    var currentPoint by mutableStateOf(-1)
    var lifeTotal by mutableStateOf(3)
    var keyBoard by mutableStateOf("consonant")
    var buttonIsClickList by mutableStateOf(mutableStateListOf(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false))
    var category = randomCategory()
    var word = randomWord(category)
    var hashWord by mutableStateOf(wordReplaceWithHash(word))
    var gameRunning by mutableStateOf(true)
    var gameWon by mutableStateOf(false)
    var wordGuess by mutableStateOf("")

    private fun wordReplaceWithHash(word: String): String {
        var hashWord = ""
        for (char in word) {
            hashWord += if (char == ' ') {
                " "
            } else {
                "#"
            }

        }
        return hashWord
    }
    private fun randomCategory(): String {
        val categories = listOf("Sport", "Mad", "Personer", "Drinks", "Dyr")
        val randomCategoryIndex = Random.nextInt(categories.size)
        return categories[randomCategoryIndex]

    }
    private fun randomWord(category: String): String {
        var index = -1
        when(category){
            "Sport" -> index = 0
            "Mad" -> index = 1
            "Personer" -> index = 2
            "Drinks" -> index = 3
            "Dyr" -> index = 4
        }
        val words = listOf(listOf("Fodbold","Basketbold","Baseball","Cricket","Gurling"), listOf("Durum Rulle","pasta carbonara", "Boller i karry", "Svinemørbrad", "Stuvet Kål"), listOf("Stedfortræder", "Verve", "Forælder", "Kusine", "Bolværksmatros"), listOf("prinsesse Drink","Cosmopolitan","Mojito","Tequila Sunrise", "Strawberry Daiquiri"), listOf("Edderkop", "Menneske", "Pingvin", "Myresluger", "Chimpanser"))
        val randomWordIndex = Random.nextInt(words[index].size)
        return words[index][randomWordIndex]
    }
}