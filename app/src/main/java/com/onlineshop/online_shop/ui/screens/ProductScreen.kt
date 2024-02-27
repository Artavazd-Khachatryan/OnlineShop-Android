package com.onlineshop.online_shop.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onlineshop.online_shop.data.dtomodels.ProductDTO

@Composable
fun ProductScreen(products: List<ProductDTO>, onItemClick: (Long) -> Unit) {
    LazyColumn {
        items(products) { product ->
            product.title?.let {
                ClickableText(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    text = AnnotatedString(it),
                    onClick = { onItemClick(product.id) },
                    style = MaterialTheme
                        .typography
                        .bodyMedium
                        .copy(
                            fontSize = 26.sp
                        )
                )

                Divider(modifier = Modifier, thickness = 1.dp, color = Color.Black)
            }
        }
    }
}