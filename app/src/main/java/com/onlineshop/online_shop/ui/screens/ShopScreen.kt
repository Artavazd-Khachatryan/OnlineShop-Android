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
import com.onlineshop.online_shop.data.dtomodels.ShopDTO

@Composable
fun ShopsScreen(shops: List<ShopDTO>, onItemClick: (Long) -> Unit) {
    LazyColumn {
        items(shops) { shopDTO ->
            val name = shopDTO.name ?: return@items
            ClickableText(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                style = MaterialTheme
                    .typography
                    .bodyMedium
                    .copy(fontSize = 26.sp),
                text = AnnotatedString(name),
                onClick = { onItemClick(shopDTO.id) })
            Divider(modifier = Modifier, thickness = 1.dp, color = Color.Black)
        }
    }
}