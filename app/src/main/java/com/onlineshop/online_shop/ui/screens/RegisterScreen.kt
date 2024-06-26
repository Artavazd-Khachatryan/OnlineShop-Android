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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlineshop.online_shop.SignupViewModel

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(signupViewModel: SignupViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Divider(modifier = Modifier, thickness = 50.dp, color = Color.White)

        var firstNametext by remember { mutableStateOf("") }

        TextField(
            value = firstNametext,
            onValueChange = { newText ->
                firstNametext = newText
            },
            placeholder = {
                Text("Firstname")
            }
        )

        Divider(modifier = Modifier, thickness = 8.dp, color = Color.White)

        var lastNametext by remember { mutableStateOf("") }

        TextField(
            value = lastNametext,
            onValueChange = { newText ->
                lastNametext = newText
            },
            placeholder = {
                Text("Lastname")
            }
        )

        Divider(modifier = Modifier, thickness = 8.dp, color = Color.White)

        var eMailText by remember { mutableStateOf("") }

        TextField(
            value = eMailText,
            onValueChange = { newText ->
                eMailText = newText
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

        Divider(modifier = Modifier, thickness = 8.dp, color = Color.White)

        var tryPassword by remember { mutableStateOf("") }
        TextField(
            value = tryPassword,
            onValueChange = { newText ->
                tryPassword = newText
            },
            placeholder = {
                Text("Repeat password")
            }
        )

        Divider(modifier = Modifier, thickness = 50.dp, color = Color.White)

        Button(onClick = {
            signupViewModel.signup(firstNametext, lastNametext, eMailText, passwordText, "USER")
        }) {
            Text(text = "Register")
        }
    }
}