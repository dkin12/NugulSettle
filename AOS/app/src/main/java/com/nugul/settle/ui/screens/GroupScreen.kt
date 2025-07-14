package com.nugul.settle.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nugul.settle.ui.sampleGroups
import com.nugul.settle.ui.item.GroupAddItem
import com.nugul.settle.ui.item.GroupListItem


@Composable
fun HomeGroupScreen(navController: NavHostController) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxHeight()
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
