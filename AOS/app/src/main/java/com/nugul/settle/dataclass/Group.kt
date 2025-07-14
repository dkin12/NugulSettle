package com.nugul.settle.dataclass

data class Group(
    val groupIdx : String,
    val userIdx : String,
    val groupName : String,
    val groupIcon : String,
    val groupMemberCnt : Int,
    val groupColor : String,
    val groupMeetingCnt : Int,
    val groupCreateDate : String,
    val groupUpdateDate : String,
    val groupIsDeleted : Boolean,
    val groupDeletedDate : String
)