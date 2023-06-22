package com.hsinha610.daznassignmenths.ui.Screens

import android.os.Bundle
import android.provider.Contacts
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hsinha610.daznassignmenths.R
import com.hsinha610.daznassignmenths.ui.FragmentComposeView
import com.hsinha610.daznassignmenths.ui.viewmodel.MainViewModel

class DetailFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var selectedItemIndex: Int? = null

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
            selectedItemIndex = it.getInt(SELECTED_ITEM_INDEX_KEY,-1)
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
    fun UI(){
    }


}