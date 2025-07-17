package com.nugul.settle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.dataclass.MeetDegree
import com.nugul.settle.ui.sampleMeetDegree
import com.nugul.settle.ui.sampleMembers
import com.nugul.settle.utils.DatePickerDialogWithToday
import java.text.DecimalFormat
import java.util.UUID
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MeetDegreeScreen(meetIdx : String, navController: NavController) {
    var degreeList = sampleMeetDegree.filter { it.meetIdx == meetIdx }.toMutableList()
    // 페이지 수 동적 설정
    val pagerState = rememberPagerState(pageCount = {degreeList.size})
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
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 0.dp)
                .fillMaxSize()
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
                    var textFieldValue by remember {
                        mutableStateOf(TextFieldValue(text = "", selection = TextRange(0)))
                    }

                    val formatted = remember(rawInput) {
                        val number = rawInput.toLongOrNull() ?: 0L
                        DecimalFormat("#,###").format(number.coerceAtMost(5_000_000L))
                    }

                    // 커서 위치 유지
                    LaunchedEffect(formatted) {
                        textFieldValue = TextFieldValue(
                            text = formatted,
                            selection = TextRange(formatted.length)
                        )
                    }

                    TextField(
                        value = textFieldValue,
                        onValueChange = { newValue ->
                            // 쉼표 제거한 숫자만 처리
                            val clean = newValue.text.replace(",", "")
                            if (clean.all { it.isDigit() }) {
                                rawInput = clean
                            }
                        },
                        placeholder = {Text("금액을 입력하세요.")},
                        trailingIcon = {
                            if (rawInput.isNotEmpty()) {
                                IconButton(onClick = {
                                    rawInput = ""
                                }) {
                                    androidx.compose.material3.Icon(
                                        painterResource(R.drawable.icon_close),
                                        contentDescription = "More options"
                                    )
                                }
                            }
                        },
                        supportingText = {
                            Text(
                                text = "최대 500만원까지 입력할 수 있습니다.",
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    var place by remember { mutableStateOf("") }

                    Text(
                        text = "결제 장소",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top=10.dp)
                    )
                    var maxLength = 50
                    TextField(
                        value = place,
                        onValueChange = { newValue ->
                            if(newValue.length <= maxLength){
                                place = newValue
                            }
                        },
                        placeholder = {Text("장소를 입력하세요.")},
                        trailingIcon = {
                            if (place.isNotEmpty()) {
                                IconButton(onClick = {
                                    place = ""
                                }) {
                                    androidx.compose.material3.Icon(
                                        painterResource(R.drawable.icon_close),
                                        contentDescription = "More options"
                                    )
                                }
                            }
                        },
                        supportingText = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ){
                                Text(
                                    text = "${place.length} / $maxLength",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {}
                        )
                        Text(
                            text = "결제 완료 여부",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        IconButton(onClick = {
                            rawInput = ""
                        }) {
                            androidx.compose.material3.Icon(
                                painterResource(R.drawable.icon_question),
                                contentDescription = "결제 완료 여부"
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){

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
                            androidx.compose.material3.Icon(
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


                }
            }
        }
    }
}
@Composable
fun DatePicker() {
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        Button(
            onClick = { showDialog = true }
        ) {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = if (selectedDate.isEmpty()) "날짜 선택" else selectedDate
            )

        }
        if (showDialog) {
            DatePickerDialogWithToday(
                onDateSelected = { selectedDate = it },
                onDismissRequest = { showDialog = false }
            )
        }
    }
}
