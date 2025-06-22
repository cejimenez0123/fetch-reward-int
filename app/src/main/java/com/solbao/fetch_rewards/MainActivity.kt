package com.solbao.fetch_rewards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.solbao.fetch_rewards.data.models.ResponseItem
import com.solbao.fetch_rewards.ui.theme.FetchrewardsTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FetchrewardsTheme {
              var dataSet by remember {
                  mutableStateOf(emptyList<ResponseItem>())
              }
                dataSet =viewModel.dataSet.collectAsState().value
                Scaffold(modifier = Modifier.fillMaxSize().padding(18.dp
                        )) { innerPadding ->
                     Column(modifier = Modifier.padding(innerPadding)) {
                         Button(onClick = {viewModel.sort()}){
                             Text(text = "Sort")
                         }

                       LazyColumn { items(dataSet){
                           Row(Modifier.padding(8.dp)){
                               Text(modifier = Modifier
                                   ,text ="id:"+it.id.toString())
                               Spacer(Modifier.weight(1F))
                               Column {
                               Text(text ="listId:"+it.listId.toString())
                               Text(text ="name:"+it.name.toString())
                                   }



                           }
                       } }
                   }
                }
            }
        }
    }
}


