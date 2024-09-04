package com.example.pwassignment.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pwassignment.viewmodel.CharactersViewModel
import com.example.pwassignment.viewmodel.DetailsViewModel
import com.example.pwassignment.viewmodel.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun DetailsScreen(navController: NavController, id:String?,detailsViewModel: DetailsViewModel) {
    val scope= rememberCoroutineScope()
    val data= detailsViewModel.data.value
    val context= LocalContext.current

    LaunchedEffect(key1 = true){
        scope.launch {  detailsViewModel.getCharacterDetails(id.toString()) }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(
            rememberScrollState()
        ), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            when (val result=detailsViewModel.characterDetailsResponse.value){
                is RequestState.Success->{
                    detailsViewModel.data.value=result.data
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color(0xFF787E00))) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            , verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                            Card(
                                modifier = Modifier
                                    .size(200.dp)
                                    .padding(20.dp), shape = CircleShape
                            ) {
                                AsyncImage(
                                    model = data.image,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Text(
                                text = if (data.name.toString() != "") data.name.toString() else " ",
                                modifier = Modifier.fillMaxWidth(),
                                color= Color(0xFF070E3A),
                                textAlign = TextAlign.Center,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Status:  "+ (data.status ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Species:  "+ (data.species ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Type:  "+ (data.type ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Gender:  "+ (data.gender ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }
                    if(data.origin!=null){
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Origin:   "+ (data.origin.name ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }}

//                    Text(text = "Origin:   "+ (data.origin!!.name ?: ""))

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .wrapContentHeight(), shape = RoundedCornerShape(6.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFE9ECBE))) {
                        Text(text = "Created at: "+ (data.created ?: ""), modifier= Modifier
                            .fillMaxWidth()
                            .padding(8.dp), fontSize = 16.sp)

                    }
//                    Text(text = "Created at: "+ (data.created ?: ""))
                   // if(data.episode!=null) EpisodeItem(count = data.episode!!.size)
                }
                is RequestState.Error->{
                    Toast.makeText(context,"$result", Toast.LENGTH_SHORT).show()
                    Log.d("Data", "DetailsScreen:${result.error.message} ")
                }
                RequestState.Loading->{
                   Text(text = "Loading")
                }
                RequestState.Idle->{

                }

            }

        }

    }

}

@Composable
fun DataAndAction(detailsViewModel: DetailsViewModel,) {
    val scope= rememberCoroutineScope()
    val context= LocalContext.current

}