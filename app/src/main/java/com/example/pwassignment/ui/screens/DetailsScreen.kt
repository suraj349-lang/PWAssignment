package com.example.pwassignment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pwassignment.viewmodel.CharactersViewModel
import com.example.pwassignment.viewmodel.DetailsViewModel
import com.example.pwassignment.viewmodel.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(navController: NavController, id: String?,detailsViewModel: DetailsViewModel) {
    val scope= rememberCoroutineScope()
    val data= detailsViewModel.data.collectAsState()
    LaunchedEffect(key1 = id){
        scope.launch {  detailsViewModel.getCharacterDetails(id!!.toInt()) }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            Card(modifier = Modifier
                .size(200.dp)
                .padding(20.dp), shape = CircleShape) {
                AsyncImage(model = data.value.image, contentDescription ="", contentScale = ContentScale.Crop )
            }
            Text(text = data.value.name.toString())





        }

    }

}

@Composable
fun DetailsScreenDataAndAction(viewModel: DetailsViewModel, navController: NavController){
    val context= LocalContext.current
    val scope= rememberCoroutineScope()
    when (val result=viewModel.characterDetailsResponse.value){
        is RequestState.Success->{
            viewModel.data.value=result.data
        }
        is RequestState.Error->{
            Toast.makeText(context,"$result", Toast.LENGTH_SHORT).show()
        }
        RequestState.Loading->{
            CircularProgressIndicator(color = Color(0xFF1289BE))
        }
        RequestState.Idle->{

        }

    }


}
