package com.nugul.settle.dataclass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Group(
    val group_id : Int?,
    val user_id : Int,
    val group_name : String,
    val group_icon : String,
    val group_member_cnt : Int,
    val group_color : String,
    val group_meeting_cnt : Int,
    val group_create_date : String,
    val group_update_date : String,
    val group_is_deleted : Boolean,
    val group_deleted_date : String
)