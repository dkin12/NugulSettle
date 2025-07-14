package com.nugul.settle.dataclass

data class Meet (
    val meetIdx: String,
    val groupIdx: String,
    val userIdx : String,
    val meetDegree : MutableList<String>,
    val meetMember : MutableList<String>,
    val meetTotalPrice : Int,
    val meetCreateDate: String,
    val meetUpdateDate : String,
    val meetIsDeleted : Boolean,
    val meetDeletedDate : String

)