package com.nugul.settle.dataclass

data class GroupDetail (
    val groupDetailIdx: String,
    val groupIdx: String,
    val groupName : String,
    val meetingIdxList : MutableList<Meeting>,
    val groupCreateDate: String,
    val groupUpdateDate : String,
    val groupIsDeleted : Boolean,
    val groupDeletedDate : String

)