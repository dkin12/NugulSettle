package com.nugul.settle.ui.screens

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.dataclass.MeetDegree
import com.nugul.settle.ui.sampleMeetDegree
import com.nugul.settle.ui.sampleMembers
import com.nugul.settle.component.DatePicker
import com.nugul.settle.dataclass.Member
import com.nugul.settle.ui.components.CommonTextField
import com.nugul.settle.ui.components.formatPriceWithComma
import com.nugul.settle.ui.item.MemberListItem
import java.text.DecimalFormat
import java.util.UUID
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MeetDegreeScreen(meetIdx : String, navController: NavController, scrollBehavior: TopAppBarScrollBehavior) {
    var degreeList = sampleMeetDegree.filter { it.meetIdx == meetIdx }.toMutableList()
    var memberList = sampleMembers.filter{it.groupIdx == degreeList[0].groupIdx}
    // 페이지 수 동적 설정
    val pagerState = rememberPagerState(pageCount = {degreeList.size})
    var searchMember by remember { mutableStateOf(false) }
    var memberItems = listOf<Member>() + memberList
    val chunckedItem = memberItems.chunked(2)
    var selectedMembers by remember { mutableStateOf<List<Member>>(emptyList()) }
    LaunchedEffect(degreeList.size) {
        if(pagerState.currentPage >= degreeList.size && degreeList.isNotEmpty()) {
            pagerState.animateScrollToPage(degreeList.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp,top = 10.dp,bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "정산하기 (${pagerState.currentPage + 1} / ${degreeList.size})",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
            ){
                Text(
                    text = "+ 차수 추가",
                    modifier = Modifier
                        .clickable{
                            val newDegree = MeetDegree(
                                degreeIdx = UUID.randomUUID().toString(),
                                groupIdx = degreeList[0].groupIdx,
                                userIdx = degreeList[0].userIdx,
                                meetIdx = degreeList[0].meetIdx,
                                degreePrice = 0,
                                degreePlace = "",
                                degreeDate = "",
                                degreeMemo = "",
                                degreePayer = "",
                                degreeMember = mutableListOf(),
                                degreeCreateDate = "",
                                degreeUpdateDate = "",
                                degreeIsDeleted = false,
                                degreeDeletedDate = ""
                            )
                            degreeList.add(newDegree)
                        },
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

        }
        Row(
            modifier = Modifier.fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 0.dp)
                    .weight(1f)
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp))
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ){
                Box(
                    Modifier.padding(horizontal = 12.dp, vertical = 30.dp)
                ){
                    Column {
                        Row {
                            Text(
                                text = "결제 금액",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = " *",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        var rawInput by remember { mutableStateOf("") }
                        val formatted = formatPriceWithComma(rawInput)

                        CommonTextField(
                            value = formatted,
                            onValueChange = { newValue ->
                                val clean = newValue.replace(",", "")
                                if (clean.all { it.isDigit() }) {
                                    rawInput = clean
                                }
                            },
                            placeholderText = "금액을 입력하세요.",
                            supportingText = "최대 500만원까지 입력할 수 있습니다.",
                            keyboardType = KeyboardType.Number
                        )
                        var place by remember { mutableStateOf("") }

                        Text(
                            text = "결제 장소",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(top=10.dp)
                        )
                        var maxLength = 50
                        CommonTextField(
                            value = place,
                            onValueChange = { newValue ->
                                if (newValue.length <= maxLength) {
                                    place = newValue
                                }
                            },
                            placeholderText = "장소를 입력하세요.",
                            supportingText = "${place.length} / $maxLength",
                            keyboardType = KeyboardType.Text,
                            singleLine = true,
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = {}
                            )
                            Text(
                                text = "결제 완료 여부",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(onClick = {
                                rawInput = ""
                            }) {
                                Icon(
                                    painterResource(R.drawable.icon_question),
                                    contentDescription = "결제 완료 여부"
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "결제자",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            OutlinedButton(
                                onClick = { /* TODO */ },
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                                shape = RoundedCornerShape(100.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_flag),
                                    contentDescription = degreeList[0].degreePayer,
                                    modifier = Modifier.size(24.dp),
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    text = sampleMembers.find { it.userIdx == degreeList[0].degreePayer } ?.memberName ?: ""
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "결제일",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            DatePicker()
                        }
                        Row (
                            modifier = Modifier
                                .padding(top = 10.dp)
                        ){
                            Text(
                                text = "참가인원 ${degreeList[0].degreeMember.size}명",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            IconButton(
                                modifier = Modifier.size(16.dp),
                                onClick = { searchMember = !searchMember },
                            ) {
                                Icon(
                                    painterResource(R.drawable.icon_search),
                                    contentDescription = "More options"
                                )
                            }
                        }
                        if(searchMember){
                            Row(){
                                CommonTextField(
                                    value = place,
                                    onValueChange = { newValue ->
                                        if (newValue.length <= maxLength) {
                                            place = newValue
                                        }
                                    },
                                    placeholderText = "장소를 입력하세요.",
                                    supportingText = "${place.length} / $maxLength",
                                    keyboardType = KeyboardType.Text,
                                    singleLine = true,
                                )
                            }
                        }
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(color = MaterialTheme.colorScheme.surface)
                                .nestedScroll(scrollBehavior.nestedScrollConnection),
                        ) {

                            items(chunckedItem) { rowItems ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    rowItems.forEach { member ->
                                        Box(
                                            modifier = Modifier
                                                .weight(1f)
                                        ) {
                                            MemberListItem(
                                                member = member,
                                                isSelected = selectedMembers.contains(member),
                                                onSelectionChanged = { selected ->
                                                    if (selected) {
                                                        selectedMembers = selectedMembers + member
                                                    } else {
                                                        selectedMembers = selectedMembers - member
                                                    }
                                                }
                                            )
                                        }
                                    }
                                    // 짝수 아닌 경우 빈 공간 채우기
                                    if (rowItems.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 24.dp, vertical = 0.dp)
                                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp))
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        ){
                            Icon(
                                painterResource(R.drawable.icon_question),
                                contentDescription = "결제 완료 여부"
                            )
                            Text(
                                text = "정산하기",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            }
        }

    }
}
