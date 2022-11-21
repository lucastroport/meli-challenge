package com.lucas.yourmarket.presentation.converters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lucas.yourmarket.presentation.screens.home.HomeScreenState
import com.lucas.yourmarket.presentation.screens.home.HomeViewModel
import com.lucas.yourmarket.presentation.ui.helpers.wrapInState

@Composable
fun HomeViewModel.toState() = HomeScreenState(
    searchInput = searchField.observeAsState(""),
    loading = spinner.observeAsState(initial = false),
    onItemClicked = ::onItemClicked,
    onSearchEnter = ::onSearchEnter,
    onEntryChanged = { searchField.value = it },
    products = products.collectAsLazyPagingItems()
)
