package com.nugul.settle.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogWithToday(
    onDateSelected: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val today = remember { System.currentTimeMillis() }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = today)
    val context = LocalContext.current

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    val millis = datePickerState.selectedDateMillis
                    if (millis != null) {
                        val sdf = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
                        val formattedDate = sdf.format(Date(millis))
                        onDateSelected(formattedDate)
                    } else {
                        Toast.makeText(context, "날짜를 선택하세요.", Toast.LENGTH_SHORT).show()
                    }
                    onDismissRequest()
                }
            ) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "확인"

                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = "취소")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = {
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp, start = 16.dp, bottom = 16.dp)
                ){
                    Text (
                        style = MaterialTheme.typography.titleLarge,
                        text = "결제일"
                    )
                }

            },
        )
    }
}


@Composable
fun DatePicker() {
    var selectedDate by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .clickable {
                showDialog = true
            }
    ) {
        val localDateTime: LocalDateTime = LocalDateTime.now()
        val formattedDate = localDateTime.toLocalDate().toString().replace("-",".")
        Text(style = MaterialTheme.typography.bodyMedium,
            text = selectedDate.ifEmpty { formattedDate })
        if (showDialog) {
            DatePickerDialogWithToday(
                onDateSelected = { selectedDate = it },
                onDismissRequest = { showDialog = false }
            )
        }
    }
}
