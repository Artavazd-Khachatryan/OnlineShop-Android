package com.onlineshop.online_shop.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onlineshop.online_shop.data.dtomodels.ProductDTO

@Composable
fun ProductInformationScreen(product: ProductDTO) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = AnnotatedString(product.price.toString()),
            style = MaterialTheme
                .typography
                .bodyMedium
                .copy(fontSize = 26.sp)
        )
        Divider(modifier = Modifier, thickness = 1.dp, color = Color.Black)

        Text(
            text = AnnotatedString(product.category ?: ""),
            style = MaterialTheme
                .typography
                .bodyMedium
                .copy(fontSize = 26.sp)
        )
        Divider(modifier = Modifier, thickness = 1.dp, color = Color.Black)

        Text(
            text = AnnotatedString(product.description ?: ""),
            style = MaterialTheme
                .typography
                .bodyMedium
                .copy(fontSize = 26.sp)
        )
        Divider(modifier = Modifier, thickness = 1.dp, color = Color.Black)
    }
}