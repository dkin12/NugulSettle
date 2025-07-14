package com.nugul.settle.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nugul.settle.ui.sampleGroups
import com.nugul.settle.ui.item.GroupAddItem
import com.nugul.settle.ui.item.GroupListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeGroupScreen(navController: NavHostController, scrollBehavior: TopAppBarScrollBehavior) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxHeight()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    )
    {
        itemsIndexed(sampleGroups){ index, item ->
            GroupListItem(index,item,navController)
        }
        item {
            GroupAddItem(navController)
        }
    }
}
