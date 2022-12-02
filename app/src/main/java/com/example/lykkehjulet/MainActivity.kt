package com.example.lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.lykkehjulet.data.LykkehjuletViewModel
import com.example.lykkehjulet.ui.screens.Lykkehjulet
import com.example.lykkehjulet.ui.theme.LykkehjuletTheme

class MainActivity : ComponentActivity(), LifecycleOwner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewModel = ViewModelProvider(this).get(LykkehjuletViewModel::class.java)
        setContent {
            LykkehjuletTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Lykkehjulet(viewModel)
                }
            }
        }
    }
}
