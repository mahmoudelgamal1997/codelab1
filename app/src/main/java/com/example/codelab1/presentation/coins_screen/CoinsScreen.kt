package com.example.codelab1.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.airbnb.lottie.model.content.CircleShape
import com.example.codelab1.R
import com.example.codelab1.data.models.Coin
import com.example.codelab1.data.models.CoinsDto
import com.example.codelab1.presentation.coins_screen.coin_screen.CoinsViewModel

@Composable
fun CoinScreen(
    navHostController: NavHostController,
    viewModel: CoinsViewModel = hiltViewModel()
) {

    val coinsViewModel = viewModel.coins.collectAsState()
    var textValue by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier.background(Color.Black)) {
        Text(
            text = "Live Prices", color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 26.sp, fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        SearchBox(textValue) {
            textValue = it
            viewModel.getCoin(textValue)
        }
        CoinsList(coinsViewModel.value.coins)
    }


}

@Composable
fun SearchBox(textSearch: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    var isEmpty by remember {
        mutableStateOf(textSearch != "")
    }

//    var isHintDisplayed by remember {
//        mutableStateOf(textSearch != "")
//    }
//
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = modifier
//            .padding(16.dp)
//            .clip(RoundedCornerShape(35.dp))
//            .background(Color.Gray)
//            .padding(5.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Default.Search,
//            contentDescription = "Search",
//            tint = Color.White,
//            modifier = Modifier
//                .size(35.dp)
//                .padding(start = 12.dp),
//        )
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//
//
//            BasicTextField(
//                value = textSearch,
//                onValueChange = onValueChange,
//                maxLines = 1,
//                singleLine = true,
//                textStyle = TextStyle(color = Color.White),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .onFocusChanged {
//                        isHintDisplayed = !it.isFocused
//                    }
//            )
//
//            if (isHintDisplayed) {
//                Text(
//                    text = "Searching..",
//                    color = Color.White,
//                    modifier = Modifier
//                )
//            }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(
                shape = RoundedCornerShape(35.dp)
            )
            .background(Color.Gray)
            .padding(12.dp),

        ) {

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            tint = Color.White,
        )
        Box(modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            BasicTextField(
                value = textSearch,
                onValueChange = onValueChange,
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(color = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        isEmpty = !it.isFocused
                    }
            )
            if (isEmpty) {
                Text(
                    text = "Searching...",
                    color = Color.White,
                    modifier = Modifier
                )
            }

        }
    }


//    Box(modifier = Modifier.background(Color.Black), contentAlignment = Alignment.Center) {
//        OutlinedTextField(
//
//            value = textSearch,
//            onValueChange = onValueChange,
//            placeholder = {
//                if (textSearch.isNullOrEmpty()) Text(
//                    text = "Searching...",
//                    color = Color.White
//                ) else Text(text = "", color = Color.White)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Gray).padding(16.dp),
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "",
//                    tint = Color.White,
//                )
//
//            },
//            shape = RoundedCornerShape(18.dp)
//        )
//    }
}

@Composable
fun CoinsList(list: List<Coin>) {
    LazyColumn(modifier = Modifier.background(Color.Black)) {
        items(items = list) { item ->
            CoinsItem(item)
        }
    }
}

@Composable
fun CoinsItem(coin: Coin) {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Column (
            horizontalAlignment = Alignment.Start
                ) {


            Box(
                modifier = Modifier
                    .then(Modifier.size(50.dp))
                    .clip(CircleShape)
                    .background(Color.Gray),
                    contentAlignment = Alignment.Center

                ) {
                AsyncImage(
                    model = coin.icon.toString(),
                    contentDescription = "Translated description of what the image contains"
                )
            }
        }
        Column(modifier = Modifier.weight(3f).padding(12.dp)) {
            Text(coin.name, color = Color.White)
            Text(text = coin.symbol, color = Color.White)
        }
        Spacer(modifier = Modifier.width(50.dp))
        Column(modifier = Modifier.weight(4f)) {

            Text(text = coin.price.toString(), color = Color.White)
            Text(text = coin.priceChange1h.toString(), color = Color.White)
        }
    }

    Divider(color = Color.Gray, thickness = .8.dp)

}

/////////////////////
@Composable
fun CoinsWatchList(navHostController: NavHostController) {

    Text(text = "CoinsWatchList")
}

@Composable
fun CoinsNews(navHostController: NavHostController) {

    Text("CoinsNews")
}