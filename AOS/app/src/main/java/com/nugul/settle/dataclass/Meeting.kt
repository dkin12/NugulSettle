package com.nugul.settle.dataclass

data class Meeting(
    val meetingIdx : String,
    val groupIdx : String,
    val meetingPrice : String,
    val meetingPlace : String?,
    val meetingDate : String,
    val meetingMemo : String,
    val meetingPayer : String,
    val meetingMember: MutableList<Member>,
    val meetingCreateDate : String,
    val meetingUpdateDate : String,
    val meetingIsDeleted : Boolean,
    val meetingDeletedDate : String
)