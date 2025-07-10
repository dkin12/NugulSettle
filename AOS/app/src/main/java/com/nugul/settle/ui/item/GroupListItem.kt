package com.nugul.settle.ui.item

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nugul.settle.R
import com.nugul.settle.dataclass.Group
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onSizeChanged
import com.nugul.settle.ui.theme.GroupColor
import com.nugul.settle.ui.theme.dropShadow
import com.nugul.settle.ui.theme.userColors

@Composable


fun GroupListItem(item: Group, navController: NavHostController) {
    val groupColor = userColors[item.group_color] ?: userColors["Red"]
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 7.dp)
            .clickable {
                navController.navigate("detail/${item.group_name}")
            }
            .dropShadow(
                shape = RoundedCornerShape(10.dp),
                color = Color.Black.copy(0.25f),
                blur = 4.dp,
                offsetY = 4.dp,
                offsetX = 0.dp,
                spread = 0.dp
            )
    ){
        Box(
            modifier = Modifier
                .width(4.dp)
                .height(72.dp)
                .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 0.dp, bottomEnd = 0.dp, bottomStart = 10.dp))
                .background(groupColor!!.base)
        )
        Box(
            modifier = Modifier
                .defaultMinSize(72.dp)
                .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 0.dp))
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(20))
                        .background(groupColor.light),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = item.group_icon)
                }

                Spacer(Modifier.width(12.dp))

                // 텍스트 영역
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.group_name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(MaterialTheme.colorScheme.background)
                    ){
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "총 ${item.group_member_cnt} 명",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                }

            }

            // 배경 (우측 하단 고정 배치, 반투명)
            Image(
                painter = painterResource(id = R.drawable.group_list_item_face),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(64.dp)
                    .alpha(0.2f),
                contentScale = ContentScale.Fit
            )
        }
    }

}
