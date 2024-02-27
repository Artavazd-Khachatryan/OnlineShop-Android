package com.onlineshop.online_shop.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var emailText by remember { mutableStateOf("") }
        TextField(
            value = emailText,
            onValueChange = { newText ->
                emailText = newText
            },
            placeholder = {
                Text("Email")
            }
        )

        Divider(modifier = Modifier, thickness = 8.dp, color = Color.White)

        var passwordText by remember { mutableStateOf("") }

        TextField(
            value = passwordText,
            onValueChange = { newText ->
                passwordText = newText
            },
            placeholder = {
                Text("Password")
            }
        )

        Divider(modifier = Modifier, thickness = 20.dp, color = Color.White)

        Button(
            onClick = onLoginClick
        ) {
            Text(text = "Login")
        }

        Button(onClick = { onRegisterClick() }) {
            Text(text = "Register")
        }
    }
}