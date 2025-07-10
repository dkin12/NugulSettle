package com.nugul.settle.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.nugul.settle.dataclass.Group
import com.nugul.settle.ui.item.GroupListItem

@Composable
fun HomeGroupScreen(navController: NavHostController) {
    val exampleGroups = listOf(
        Group(
            group_id = 1,
            user_id = 101,
            group_name = "삼겹살 번개 모임",
            group_icon = "🐷",
            group_member_cnt = 4,
            group_color = "Red",
            group_meeting_cnt = 2,
            group_create_date = "2025-07-01",
            group_update_date = "2025-07-08",
            group_is_deleted = false,
            group_deleted_date = ""
        ),
        Group(
            group_id = 2,
            user_id = 102,
            group_name = "코딩스터디 정산",
            group_icon = "💻",
            group_member_cnt = 3,
            group_color = "Green",
            group_meeting_cnt = 5,
            group_create_date = "2025-06-15",
            group_update_date = "2025-07-09",
            group_is_deleted = false,
            group_deleted_date = ""
        ),
        Group(
            group_id = 3,
            user_id = 103,
            group_name = "2025 졸업여행 TF",
            group_icon = "✈️",
            group_member_cnt = 6,
            group_color = "Blue",
            group_meeting_cnt = 1,
            group_create_date = "2025-05-22",
            group_update_date = "2025-06-10",
            group_is_deleted = false,
            group_deleted_date = ""
        )
    )

    LazyColumn {
        items(exampleGroups){ item ->
            GroupListItem(item,navController)
        }
    }
}
