package com.nugul.settle.utils

import android.widget.Toast
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
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
                        Toast.makeText(context, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    onDismissRequest()
                }
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("취소")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}