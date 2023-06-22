package com.hsinha610.daznassignmenths.ui.Screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hsinha610.daznassignmenths.data.DataList
import com.hsinha610.daznassignmenths.data.DataListItem
import com.hsinha610.daznassignmenths.ui.ErrorScreen
import com.hsinha610.daznassignmenths.ui.FragmentComposeView
import com.hsinha610.daznassignmenths.ui.LoadingScreen
import com.hsinha610.daznassignmenths.ui.viewmodel.MainViewModel
import com.hsinha610.daznassignmenths.ui.viewmodel.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return context?.let { context ->
            FragmentComposeView(context) {
                UI()
            }
        }
    }

    @Composable
    fun UI() {
        LaunchedEffect(key1 = Unit, block = {
            viewModel.getData()
        })
        val uiState by viewModel.ld.observeAsState()
        when (uiState) {
            is UiState.Loading -> {
                LoadingScreen()
            }

            is UiState.Error -> {
                ErrorScreen(error = (uiState as UiState.Error).error.message ?: "")
            }

            is UiState.Success -> {
                (uiState as UiState.Success).data?.let {
                    HomeScreen(dataList = it)
                }
            }

            null -> {
                ErrorScreen(error = "null data")
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun HomeScreen(dataList: DataList) {

        Scaffold(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp, 12.dp, 12.dp, 0.dp)
            ) {
                itemsIndexed(
                    dataList.list,
                    key = { _, item -> item.url }) { index, item ->
                    ListItem(index, item)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }

    @Composable
    fun ListItem(index: Int, item: DataListItem) {

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
            viewModel.openDetailFragment(itemIndex = index)
        }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.url)
                    .crossfade(true)
                    .build(), contentDescription = "image", modifier = Modifier
                    .size(60.dp)
                    .clip(
                        RoundedCornerShape(12.dp)
                    ), contentScale = ContentScale.Crop
            )
            Text(
                item.title, textAlign = TextAlign.Start, modifier = Modifier
                    .padding(12.dp)
                    .weight(1f)
            )
        }
    }
}


