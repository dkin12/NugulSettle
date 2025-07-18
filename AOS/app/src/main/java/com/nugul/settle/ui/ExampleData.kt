package com.nugul.settle.ui

import com.nugul.settle.dataclass.Group
import com.nugul.settle.dataclass.GroupDetail
import com.nugul.settle.dataclass.Meet
import com.nugul.settle.dataclass.MeetDegree
import com.nugul.settle.dataclass.Member


val sampleGroups = listOf(
    Group(
        groupIdx = "g1",
        userIdx = "101",
        groupName = "삼겹살 번개 모임",
        groupIcon = "🐷",
        groupMemberCnt = 4,
        groupColor = "Red",
        groupMeetingCnt = 2,
        groupCreateDate = "2025-07-01",
        groupUpdateDate = "2025-07-08",
        groupIsDeleted = false,
        groupDeletedDate = ""
    ),
    Group(
        groupIdx = "g2",
        userIdx = "101",
        groupName = "코딩스터디 정산",
        groupIcon = "💻",
        groupMemberCnt = 3,
        groupColor = "Green",
        groupMeetingCnt = 5,
        groupCreateDate = "2025-06-15",
        groupUpdateDate = "2025-07-09",
        groupIsDeleted = false,
        groupDeletedDate = ""
    ),
    Group(
        groupIdx = "g3",
        userIdx = "101",
        groupName = "2025 졸업여행 TF",
        groupIcon = "✈️",
        groupMemberCnt = 6,
        groupColor = "Blue",
        groupMeetingCnt = 1,
        groupCreateDate = "2025-05-22",
        groupUpdateDate = "2025-06-10",
        groupIsDeleted = false,
        groupDeletedDate = ""
    )
)


val sampleMembers = mutableListOf(
    Member("m1", "g1", "101","삼겹살 번개 모임", "민지"),
    Member("m2", "g1","101", "삼겹살 번개 모임", "현우"),
    Member("m3", "g1", "101","삼겹살 번개 모임", "지후"),
    Member("m4", "g1","101", "삼겹살 번개 모임", "수빈"),
    Member("m5", "g1","101", "삼겹살 번개 모임", "현우"),
    Member("m6", "g1", "101","삼겹살 번개 모임", "지후"),
    Member("m7", "g1","101", "삼겹살 번개 모임", "수빈"),
    Member("m1", "g1", "101","삼겹살 번개 모임", "민지"),
    Member("m2", "g1","101", "삼겹살 번개 모임", "현우"),
    Member("m3", "g1", "101","삼겹살 번개 모임", "지후"),
    Member("m4", "g1","101", "삼겹살 번개 모임", "수빈"),
    Member("m5", "g1","101", "삼겹살 번개 모임", "현우"),
    Member("m6", "g1", "101","삼겹살 번개 모임", "지후"),
    Member("m7", "g1","101", "삼겹살 번개 모임", "수빈")

)

val sampleMeet = mutableListOf(
    Meet(
        meetIdx = "mt1",
        groupIdx = "g1",
        userIdx = "101",
        meetDegree = mutableListOf("dg1","dg2"),
        meetMember = mutableListOf("m1","m2","m3","m4"),
        meetTotalPrice = 100000,
        meetCreateDate = "2025-01-05",
        meetUpdateDate = "2025-01-06",
        meetIsDeleted = false,
        meetDeletedDate = ""
    ),
    Meet(
        meetIdx = "mt2",
        groupIdx = "g1",
        userIdx = "101",
        meetDegree = mutableListOf(),
        meetMember = mutableListOf("m1","m2","m3","m4"),
        meetTotalPrice = 0,
        meetCreateDate = "2025-01-05",
        meetUpdateDate = "2025-01-06",
        meetIsDeleted = false,
        meetDeletedDate = ""

    )
)

val sampleMeetDegree = mutableListOf(
    MeetDegree(
        degreeIdx = "dg1",
        groupIdx = "g1",
        meetIdx = "mt1",
        userIdx = "101",
        degreePrice = 40000,
        degreePlace = "홍대 고기집",
        degreeDate = "2025-01-05",
        degreeMemo = "삼겹살 4인분",
        degreePayer = "m1",
        degreeMember = mutableListOf("m1","m2","m3","m4"),
        degreeCreateDate = "2025-01-05",
        degreeUpdateDate = "2025-01-06",
        degreeIsDeleted = false,
        degreeDeletedDate = ""
    ),
    MeetDegree(
        degreeIdx = "dg2",
        groupIdx = "g1",
        meetIdx = "mt1",
        userIdx = "101",
        degreePrice = 50000,
        degreePlace = "신촌 고깃집",
        degreeDate = "2025-01-15",
        degreeMemo = "회식 자리",
        degreePayer = "m2",
        degreeMember = mutableListOf("m1","m2","m3","m4"),
        degreeCreateDate = "2025-01-15",
        degreeUpdateDate = "2025-01-15",
        degreeIsDeleted = false,
        degreeDeletedDate = ""
    )
)

val sampleGroupDetail = mutableListOf(
    GroupDetail(
        groupDetailIdx = "gd1",
        groupIdx = "g1",
        userIdx = "101",
        groupName = "삼겹살 번개 모임",
        meetingIdxList = mutableListOf("mt1","mt2"),
        groupCreateDate = "2025-01-01",
        groupUpdateDate = "2025-01-10",
        groupIsDeleted = false,
        groupDeletedDate = ""
    )
)
