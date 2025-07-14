package com.nugul.settle.dataclass

data class MeetDegree(
    val degreeIdx : String,
    val groupIdx : String,
    val userIdx : String,
    val meetIdx : String,
    val degreePrice : Int,
    val degreePlace : String?,
    val degreeDate : String,
    val degreeMemo : String,
    val degreePayer : String,
    val degreeMember: MutableList<String>,
    val degreeCreateDate : String,
    val degreeUpdateDate : String,
    val degreeIsDeleted : Boolean,
    val degreeDeletedDate : String
)