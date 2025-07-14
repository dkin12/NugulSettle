package com.nugul.settle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.ui.item.MeetingAddItem
import com.nugul.settle.ui.item.MeetingListItem
import com.nugul.settle.ui.sampleGroupDetail
import com.nugul.settle.ui.sampleGroups
import com.nugul.settle.ui.sampleMeet
import com.nugul.settle.ui.sampleMeetDegree
import com.nugul.settle.ui.theme.userColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(groupIdx: String, navController: NavController, scrollBehavior: TopAppBarScrollBehavior) {
    val group = sampleGroups.find { it.groupIdx == groupIdx }
    val itemDetail = sampleGroupDetail.find { it.groupIdx == groupIdx }
    val meetings = sampleMeet.filter { it.groupIdx == groupIdx }
    var expanded by remember { mutableStateOf(false) }
    val groupColor = userColors[group!!.groupColor] ?: userColors["Red"]

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(groupColor!!.light),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = group.groupIcon)
                            }
                            Column(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)) {
                                Text(
                                    text = group.groupName,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    }

                    Box {
                        IconButton(
                            modifier = Modifier.size(16.dp),
                            onClick = { expanded = !expanded },
                        ) {
                            Icon(
                                painterResource(R.drawable.icon_kebab),
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.background),
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("수정", style = MaterialTheme.typography.bodyLarge) },
                                onClick = { /* TODO */ }
                            )
                            DropdownMenuItem(
                                text = { Text("삭제", style = MaterialTheme.typography.bodyLarge) },
                                onClick = { /* TODO */ }
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Right
                ) {
                    OutlinedButton(
                        onClick = { /* TODO */ },
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                        shape = RoundedCornerShape(100.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_filter),
                            contentDescription = "상세 필터",
                            modifier = Modifier.size(24.dp),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground,
                            text = "상세 필터"
                        )
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        text = "총 ${itemDetail!!.meetingIdxList.size} 모임"
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.heightIn(max = 1000.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        MeetingAddItem(navController)
                    }
                    items(meetings) { it ->
                        MeetingListItem(group.groupColor, it, navController)
                    }
                }
            }
        }

        if (itemDetail == null) {
            item {
                Text("등록된 모임이 없습니다.")
            }
        } else {
            item {
                Text("모임이 존재합니다.~")
            }
        }
    }
}
