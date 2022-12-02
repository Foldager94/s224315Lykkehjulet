package com.example.lykkehjulet.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lykkehjulet.R
import com.example.lykkehjulet.data.LykkehjuletViewModel

@Composable
fun Lykkehjulet(ViewModel: LykkehjuletViewModel) {
    ScreenSetup() {
        WordToGuess(ViewModel.gameData.hashWord)
        Infobar(totalLife = ViewModel.gameData.lifeTotal, totalPoints = ViewModel.gameData.pointTotal, category = ViewModel.gameData.category)
        if(ViewModel.gameData.gameRunning) {
            AlertWire(ViewModel)
            Wheel(ViewModel)
            WordPicker(ViewModel)
        }else{
            postGameScren(ViewModel)
        }
    }
}



@Composable
fun ScreenSetup(content: @Composable () -> Unit) {
    Box(modifier = Modifier.background(color= Color(0xFF222022)).padding(top = 15.dp, start = 10.dp, end = 10.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}
@Composable
fun WordToGuess(word:String, modifier:Modifier = Modifier){
    Row(modifier = modifier.background(color= Color(0xFF323232), shape = RoundedCornerShape(20))
        .fillMaxHeight(0.1f)
        .fillMaxWidth()
        .border(2.dp, shape = RoundedCornerShape(20), brush = Brush.horizontalGradient(listOf(Color(0xFFED7069), Color(0xFF9F6CF1)))),
        horizontalArrangement = Arrangement.Center){
        Text(text = word, fontSize = 30.sp,
            textAlign = TextAlign.Center, color = Color.White)
    }
}
@Composable
fun Infobar(totalLife:Int, totalPoints:Int, category: String, modifier:Modifier = Modifier){
    Row(modifier = Modifier
        .fillMaxHeight(0.030f)
        .fillMaxWidth(1f)){
        CategoryOfWord(category = category)
        PointTotal(totalPoints)
        LifeTotal(totalLife)
    }

}
@Composable
fun AlertWire(viewModel: LykkehjuletViewModel){
    when(viewModel.gameData.alert){
        0 -> viewModel.gameData.alertText = stringResource(R.string.alert_0)
        1 -> viewModel.gameData.alertText = stringResource(R.string.alert_1)
        2 -> viewModel.gameData.alertText = stringResource(R.string.alert_2)
        3 -> viewModel.gameData.alertText = stringResource(R.string.alert_3)
        4 -> viewModel.gameData.alertText = stringResource(R.string.alert_4)
        5 -> viewModel.gameData.alertText = stringResource(R.string.alert_5)
        6 -> viewModel.gameData.alertText = stringResource(R.string.alert_6)
    }
    Alert(viewModel.gameData.alertText)
}
@Composable
fun Alert(alert: String){
    Row(modifier = Modifier.fillMaxHeight(0.050f), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
        Text(alert, color = Color.Red )
    }
}

@Composable
fun Wheel(viewModel: LykkehjuletViewModel) {
    Row(modifier = Modifier
        .fillMaxHeight(0.4f)) {
        if(viewModel.gameData.currentPoint==0){
            Text(text = stringResource(R.string.wheel_text_broke), color = Color.White, fontSize = 30.sp)
        }else if (viewModel.gameData.currentPoint >0){
            Text(text = stringResource(R.string.wheel_text_points)+" "+viewModel.gameData.currentPoint, color = Color.White, fontSize = 30.sp)
        }
    }
    Row(modifier = Modifier
        .fillMaxHeight(0.20f)) {
        Button(onClick = {
            viewModel.spinWheelLogic()
        }) {
            Text(text = stringResource(R.string.spin_wheel), textAlign = TextAlign.Center)
        }
    }
}
@Composable
fun WordPicker(viewModel: LykkehjuletViewModel) {
    Row(
        ) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.27f), verticalArrangement = Arrangement.Top) {
            Button(modifier = Modifier.padding(bottom = 10.dp), onClick = {
                viewModel.changeKeyboard("consonant")
            }) {
                    Text(text = stringResource(R.string.guess_letter), textAlign = TextAlign.Center, fontSize = 14.sp)
            }
            Button(modifier = Modifier.padding(bottom = 10.dp),onClick = {
                viewModel.changeKeyboard("vowel")
            }) {
                    Text(text = stringResource(R.string.buy_letter), textAlign = TextAlign.Center, fontSize = 14.sp)
            }
            Button(modifier = Modifier.padding(bottom = 10.dp),onClick = {
                viewModel.changeKeyboard("guessWord")
            }) {
                    Text(text = stringResource(R.string.guess_word), textAlign = TextAlign.Center, fontSize = 14.sp)
            }
        }
        Column(modifier = Modifier.background(color= Color(0xFF323232), shape = RoundedCornerShape(10.dp))
            .fillMaxHeight(0.9f)
            .fillMaxWidth(1f)
            .border(5.dp, shape = RoundedCornerShape(10.dp), brush = Brush.horizontalGradient(listOf(Color(0xFFED7069), Color(0xFF9F6CF1)))), verticalArrangement = Arrangement.Center) {
            if (viewModel.gameData.keyBoard == "vowel") {
                Vowels(viewModel)
            } else if (viewModel.gameData.keyBoard == "consonant"){
                Consonants(viewModel)
            } else if (viewModel.gameData.keyBoard == "guessWord"){
                GuessWord(viewModel)
            }
        }
    }
}
@Composable
fun postGameScren(viewModel: LykkehjuletViewModel){
    if(viewModel.gameData.gameWon) {
        Row() {
            Text(text = stringResource(R.string.post_game_screen_won), color = Color.Green, fontSize = 30.sp)
        }
    } else {
        Row() {
            Text(text = stringResource(R.string.post_game_screen_lost), color = Color.Red, fontSize = 30.sp)
    }
    Row(){
        Text(text = stringResource(R.string.post_game_screen_points) + " " +  viewModel.gameData.pointTotal, fontSize = 30.sp, color = Color.White)
    }

    }
    Button(onClick = {viewModel.restartGame()}) {
        Text(text = stringResource(R.string.post_game_screen_button))
    }
}
@Composable
fun CategoryOfWord(modifier:Modifier = Modifier, category: String){
    Column(modifier = modifier.fillMaxWidth(0.33f),  horizontalAlignment = Alignment.Start) {
        Text(stringResource(R.string.category)+": "+category, fontSize = 15.sp,
            textAlign = TextAlign.Center, color = Color.White)
    }
}
@Composable
fun LifeTotal(totalLife:Int){
    Column(modifier = Modifier.fillMaxWidth(1f),  horizontalAlignment = Alignment.End) {
        Text(text = stringResource(R.string.lifes)+": $totalLife", fontSize = 15.sp,
            textAlign = TextAlign.Center, color = Color.White)
    }
}
@Composable
fun PointTotal(totalPoints:Int){
    Column(modifier = Modifier.fillMaxWidth(0.5f), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.points)+": $totalPoints", fontSize = 15.sp,
            textAlign = TextAlign.Center, color = Color.White)
    }
}
@Composable
fun Consonants(viewModel: LykkehjuletViewModel, modifier:Modifier = Modifier){
    Spacer(modifier = Modifier.size(6.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        drawConsuantlLetter("B", viewModel)
        drawConsuantlLetter("C", viewModel)
        drawConsuantlLetter("D", viewModel)
        drawConsuantlLetter("F", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        drawConsuantlLetter("G", viewModel)
        drawConsuantlLetter("H", viewModel)
        drawConsuantlLetter("J", viewModel)
        drawConsuantlLetter("K", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        drawConsuantlLetter("L", viewModel)
        drawConsuantlLetter("M", viewModel)
        drawConsuantlLetter("N", viewModel)
        drawConsuantlLetter("P", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly){
        drawConsuantlLetter("Q", viewModel)
        drawConsuantlLetter("R", viewModel)
        drawConsuantlLetter("S", viewModel)
        drawConsuantlLetter("T", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        drawConsuantlLetter("V", viewModel)
        drawConsuantlLetter("W", viewModel)
        drawConsuantlLetter("X", viewModel)
        drawConsuantlLetter("Z", viewModel)
    }
}

@Composable
fun Vowels(viewModel: LykkehjuletViewModel, modifier: Modifier = Modifier){
    Text(modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.letter_price), fontSize = 20.sp, textAlign = TextAlign.Center, color = Color.White)
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        drawVowelLetter("A", viewModel)
        drawVowelLetter("E", viewModel)
        drawVowelLetter("I", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        drawVowelLetter("O", viewModel)
        drawVowelLetter("E", viewModel)
        drawVowelLetter("Y", viewModel)
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        drawVowelLetter("Æ", viewModel)
        drawVowelLetter("Ø", viewModel)
        drawVowelLetter("Å", viewModel)
    }

}
@Composable
fun GuessWord(viewModel: LykkehjuletViewModel){
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(modifier = Modifier.padding(10.dp),
            value = viewModel.gameData.wordGuess,
            onValueChange = { viewModel.gameData.wordGuess = it })
    }
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp), onClick = { viewModel.guessWord() }) {
            Text(text = stringResource(R.string.guess_word_button))
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun drawVowelLetter(letter:String, viewModel: LykkehjuletViewModel, modifier:Modifier = Modifier){
    var alphaValue by remember { mutableStateOf(1f) }
    if(viewModel.getIsClicked(letter)){
        alphaValue = 0f
    }
    Card(
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
            .alpha(alphaValue),
        border = BorderStroke(2.dp,Color.Black),
        backgroundColor = Color.LightGray,
        onClick = { viewModel.buyVowelLogic(letter) }){
        Text(text = letter, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun drawConsuantlLetter(letter:String, viewModel: LykkehjuletViewModel, modifier:Modifier = Modifier){
    var alphaValue by remember { mutableStateOf(1f) }
    if(viewModel.getIsClicked(letter)){
        alphaValue = 0f
    }
    Card(
        modifier = modifier
            .width(40.dp)
            .height(40.dp)
            .alpha(alphaValue),
        border = BorderStroke(2.dp,Color.Black),
        backgroundColor = Color.LightGray,
        onClick = {
            viewModel.guessConsunantLogic(letter)
        } ){
        Text(text = letter, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}