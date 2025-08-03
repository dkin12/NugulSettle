package com.nugul.settle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.component.DatePicker
import com.nugul.settle.component.DatePickerDialogWithToday
import com.nugul.settle.dataclass.MeetDegree
import com.nugul.settle.ui.item.MemberListItem
import com.nugul.settle.ui.sampleMeetDegree
import com.nugul.settle.ui.sampleMembers
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.UUID


@Preview
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MeetDegreeScreen(
    meetIdx: String,
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior
) {
    val degreeList = sampleMeetDegree.filter { it.meetIdx == meetIdx }.toMutableList()
    val memberList = sampleMembers.filter { it.groupIdx == degreeList[0].groupIdx }
    var selectedMembers by remember { mutableStateOf(degreeList[0].degreeMember.toSet()) }

    val pagerState = rememberPagerState(pageCount = { degreeList.size })
    LaunchedEffect(degreeList.size) {
        if (pagerState.currentPage >= degreeList.size && degreeList.isNotEmpty()) {
            pagerState.animateScrollToPage(degreeList.size - 1)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "정산하기 (${pagerState.currentPage + 1} / ${degreeList.size})",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "+ 차수 추가",
                modifier = Modifier.clickable {
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

        // Content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 12.dp, vertical = 30.dp)
        ) {
            // Fixed content
            AmountSection()
            PlaceSection()
            PaymentStatusSection()
            PayerSection(degreeList, memberList)
            DateSection()

            // Scrollable member list
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(memberList) { member ->
                    MemberListItem(
                        member = member,
                        isSelected = selectedMembers.contains(member.memberIdx),
                        onSelectionChanged = { isSelected ->
                            selectedMembers = if (isSelected) {
                                selectedMembers + member.memberIdx
                            } else {
                                selectedMembers - member.memberIdx
                            }
                        }
                    )
                }
            }
        }

        // Footer
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "정산하기", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun AmountSection() {
    var rawInput by remember { mutableStateOf("") }
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = "", selection = TextRange(0)))
    }

    val formatted = remember(rawInput) {
        val number = rawInput.toLongOrNull() ?: 0L
        DecimalFormat("#,###").format(number.coerceAtMost(5_000_000L))
    }

    LaunchedEffect(formatted) {
        textFieldValue = TextFieldValue(
            text = formatted,
            selection = TextRange(formatted.length)
        )
    }

    Text(text = "결제 금액", style = MaterialTheme.typography.bodyLarge)
    TextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            val clean = newValue.text.replace(",", "")
            if (clean.all { it.isDigit() }) {
                rawInput = clean
            }
        },
        placeholder = { Text("금액을 입력하세요.") },
        trailingIcon = {
            if (rawInput.isNotEmpty()) {
                IconButton(onClick = { rawInput = "" }) {
                    Icon(painterResource(R.drawable.icon_close), contentDescription = "Clear")
                }
            }
        },
        supportingText = { Text("최대 500만원까지 입력할 수 있습니다.") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
private fun PlaceSection() {
    var place by remember { mutableStateOf("") }
    val maxLength = 50

    Text(text = "결제 장소", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 10.dp))
    TextField(
        value = place,
        onValueChange = { newValue ->
            if (newValue.length <= maxLength) {
                place = newValue
            }
        },
        placeholder = { Text("장소를 입력하세요.") },
        trailingIcon = {
            if (place.isNotEmpty()) {
                IconButton(onClick = { place = "" }) {
                    Icon(painterResource(R.drawable.icon_close), contentDescription = "Clear")
                }
            }
        },
        supportingText = { Text("${place.length} / $maxLength") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )
}

@Composable
private fun PaymentStatusSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = false, onCheckedChange = {})
        Text(text = "결제 완료 여부", style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = {}) {
            Icon(painterResource(R.drawable.icon_question), contentDescription = "Help")
        }
    }
}

@Composable
private fun PayerSection(degreeList: List<MeetDegree>, memberList: List<com.nugul.settle.dataclass.Member>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "결제자", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        OutlinedButton(
            onClick = { /* TODO */ },
            shape = RoundedCornerShape(100.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_flag),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = sampleMembers.find { it.memberIdx == degreeList[0].degreePayer }?.memberName ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
private fun DateSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "결제일", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
        DatePicker()
    }
}