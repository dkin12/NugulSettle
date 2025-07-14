package com.nugul.settle.ui

import com.nugul.settle.dataclass.Group
import com.nugul.settle.dataclass.GroupDetail
import com.nugul.settle.dataclass.Meeting
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
        userIdx = "102",
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
        userIdx = "103",
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
    Member("m1", "g1", "삼겹살 번개 모임", "민지"),
    Member("m2", "g1", "삼겹살 번개 모임", "현우"),
    Member("m3", "g1", "삼겹살 번개 모임", "지후"),
    Member("m4", "g1", "삼겹살 번개 모임", "수빈")
)

val sampleMeetings = mutableListOf(
    Meeting(
        meetingIdx = "mt1",
        groupIdx = "g1",
        meetingPrice = "40000",
        meetingPlace = "홍대 고기집",
        meetingDate = "2025-01-05",
        meetingMemo = "삼겹살 4인분",
        meetingPayer = "민지",
        meetingMember = sampleMembers,
        meetingCreateDate = "2025-01-05",
        meetingUpdateDate = "2025-01-06",
        meetingIsDeleted = false,
        meetingDeletedDate = ""
    ),
    Meeting(
        meetingIdx = "mt2",
        groupIdx = "g1",
        meetingPrice = "50000",
        meetingPlace = "신촌 고깃집",
        meetingDate = "2025-01-15",
        meetingMemo = "회식 자리",
        meetingPayer = "지후",
        meetingMember = sampleMembers,
        meetingCreateDate = "2025-01-15",
        meetingUpdateDate = "2025-01-15",
        meetingIsDeleted = false,
        meetingDeletedDate = ""
    )
)

val sampleGroupDetail = mutableListOf(
    GroupDetail(
        groupDetailIdx = "gd1",
        groupIdx = "g1",
        groupName = "삼겹살 번개 모임",
        meetingIdxList = sampleMeetings,
        groupCreateDate = "2025-01-01",
        groupUpdateDate = "2025-01-10",
        groupIsDeleted = false,
        groupDeletedDate = ""
    )
)
