package com.nugul.settle.ui.item

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.dataclass.Member

@Composable
fun MemberListItem(member: Member, isSelected:Boolean, onSelectionChanged:(Boolean)->Unit){

    Box(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(topStart = 5.dp, topEnd = 0.dp, bottomEnd = 5.dp, bottomStart = 0.dp))
            .clip(RoundedCornerShape( topStart = 5.dp, topEnd = 0.dp, bottomEnd = 5.dp, bottomStart = 0.dp))
            .background(MaterialTheme.colorScheme.surface)

    ){
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    disabledCheckedColor = MaterialTheme.colorScheme.onSurface,
                    uncheckedColor = MaterialTheme.colorScheme.onSurface,
                    checkmarkColor = MaterialTheme.colorScheme.surface
                ),
                checked = isSelected,
                onCheckedChange = onSelectionChanged,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                text = member.memberName
            )
        }

    }
}
