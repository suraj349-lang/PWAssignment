package com.example.pwassignment.ui.screens

import androidx.compose.foundation.background
import androidx.navigation.NavController
import com.example.pwassignment.viewmodel.CharactersViewModel
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.pwassignment.data.models.Character


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: CharactersViewModel) {
    val characters = viewModel.pager.collectAsLazyPagingItems()
    val screenHeight= LocalConfiguration.current.screenHeightDp
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Rick and Monty", color = Color.White)}, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(
            0xFF787E00
        )
        )) },
        content ={
            HomeScreenUI(characters,it,screenHeight){id->
                navController.navigate("details/$id")
            }
        })
}

@Composable
fun HomeScreenUI(
    characters: LazyPagingItems<Character>,
    paddingValues: PaddingValues,
    screenHeight: Int,
    onCharacterClicked: (Int) -> Unit
) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier
                    .height(screenHeight.dp)
                    .fillMaxWidth()){

                    LazyColumn {
                        items(characters) { character ->
                            if (character != null) {
                                CharacterItem(item = character,onCharacterClicked)
                            }

                        }

                    }
                }}

        }
    }


@Composable
fun CharacterItem(item:Character,onCharacterClicked:(Int)->Unit) {
    Surface(Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.clickable { onCharacterClicked(item.id) }
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 2.dp, end = 2.dp)
                .border(width = 1.dp, color = Color.LightGray), colors = CardDefaults.cardColors(containerColor = Color(
                0xFFD9DBB2
            )
            )
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Card(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.3f)) {
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(model = item.image, contentDescription = "", modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp), contentScale = ContentScale.Crop)
                    }
                }
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.Start) {
                    Text(text = item.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1C0941))
                    Text(text = item.species+", " +item.gender+", "+item.status, fontSize = 12.sp, color = Color(
                        0xFF3C0101
                    ), fontWeight = FontWeight.SemiBold)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Text(text = "Origin :", color = Color.DarkGray, fontSize = 13.sp)
                        Text(text = item.origin.name, color = Color(0xFF070E3A), fontSize = 13.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
                    }

                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Text(text ="Type :" , color = Color.DarkGray, fontSize = 13.sp)
                        Text(text = if(item.type!="") item.type else "Unknown", color = Color.DarkGray, fontSize = 13.sp)
                    }


                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Text(text = "Location :", color = Color.DarkGray, fontSize = 13.sp)
                        Text(text = item.location.name, color = Color.Red, fontSize = 13.sp, overflow = TextOverflow.Ellipsis, maxLines = 1)
                    }

                    Text(text = "Episodes :", color = Color.DarkGray, fontSize = 13.sp)
                    LazyRow{
                        items(item.episode.size){ count->
                            EpisodeItem(count = count)
                            
                            
                        }
                    }
                    
                    



                }
            }



        }

    }
}

@Composable
fun EpisodeItem(count:Int) {
    Card(modifier = Modifier
        .size(35.dp)
        .padding(end = 8.dp,top=2.dp) , shape = RoundedCornerShape(8.dp), colors = CardDefaults.cardColors(containerColor = Color(
        0xFFF3F0F0
    )
    )) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = (count+1).toString(), fontSize = 20.sp, color = Color.Black)
        }

        
        
    }
    
}