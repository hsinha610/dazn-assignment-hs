package com.hsinha610.daznassignmenths.ui.Screens

import android.os.Bundle
import android.provider.Contacts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.hsinha610.daznassignmenths.R
import com.hsinha610.daznassignmenths.data.DataList
import com.hsinha610.daznassignmenths.data.DataListItem
import com.hsinha610.daznassignmenths.ui.ErrorScreen
import com.hsinha610.daznassignmenths.ui.FragmentComposeView
import com.hsinha610.daznassignmenths.ui.LoadingScreen
import com.hsinha610.daznassignmenths.ui.viewmodel.MainViewModel
import com.hsinha610.daznassignmenths.ui.viewmodel.UiState

class DetailFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var selectedItemIndex: Int = 0

    companion object {
        const val TAG = "DetailFragment"
        const val SELECTED_ITEM_INDEX_KEY = "selected_item_idx"

        @JvmStatic
        fun newInstance(selectedItemIndex: Int) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(SELECTED_ITEM_INDEX_KEY, selectedItemIndex)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedItemIndex = it.getInt(SELECTED_ITEM_INDEX_KEY, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return context?.let {
            FragmentComposeView(it) {
                UI()
            }
        }
    }

    @Composable
    fun UI() {
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
                    DetailScreen(it)
                }
            }

            null -> {
                ErrorScreen(error = "null data")
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun DetailScreen(dataList: DataList) {
        val pagerState = rememberPagerState(initialPage = selectedItemIndex)

        val list by remember {
            mutableStateOf(dataList.list)
        }

        Surface {
            HorizontalPager(
                state = pagerState,
                pageCount = list.size,
                modifier = Modifier.fillMaxSize(),
                pageSpacing = 10.dp,
                contentPadding = PaddingValues(6.dp, 6.dp, 6.dp, 0.dp)
            ) { pageNo ->
                val item = list[pageNo]
                DetailPage(item)
            }
        }
    }

    @Composable
    fun DetailPage(item: DataListItem) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.url)
                    .crossfade(true)
                    .build(), contentDescription = "image", modifier = Modifier
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .weight(1f)
                    .background(Color.Transparent), contentScale = ContentScale.Crop
            )
            Text(
                item.title,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(fontWeight = FontWeight.Bold),
                fontSize = 22.sp
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
//                item {
//                    Text(
//                        item.title,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier.fillMaxWidth(),
//                        style = TextStyle(fontWeight = FontWeight.Bold),
//                        fontSize = 22.sp
//                    )
//                }
                item {
                    Text(
                        item.explanation, textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

    }


}