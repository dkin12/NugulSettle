package com.nugul.settle.ui.item

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nugul.settle.R
import com.nugul.settle.dataclass.Group
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import com.nugul.settle.ui.theme.dropShadow
import com.nugul.settle.ui.theme.userColors

@Composable


fun GroupListItem(itemIndex:Int ,item: Group, navController: NavHostController) {
    val groupColor = userColors[item.group_color] ?: userColors["Red"]
    Row (
        modifier = Modifier
            .fillMaxWidth()
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

                Spacer(Modifier.width(24.dp))

                // 텍스트 영역
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(
                            modifier = Modifier.weight(1f),
                            text = item.group_name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        var expanded by remember { mutableStateOf(false) }
                        Box{
                            IconButton(
                                modifier = Modifier.size(16.dp),
                                onClick = { expanded = !expanded }
                            ) {
                                Icon(painterResource(R.drawable.icon_kebab), contentDescription = "More options")
                            }
                            DropdownMenu(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.background),
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text(
                                        text = "수정",
                                        style = MaterialTheme.typography.bodyLarge
                                    ) },
                                    onClick = {
                                        // TODO : id로 수정으로 넘기기
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text(
                                        text = "삭제",
                                        style = MaterialTheme.typography.bodyLarge
                                    ) },
                                    onClick = {
                                        // TODO : 컨펌 메시지 및 삭제 달기
                                    }
                                )
                            }
                        }
                        }



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

            Image(
                painter = if(itemIndex%2!=0){
                   painterResource(id = R.drawable.group_list_item_foot)
                }else{
                    painterResource(id = R.drawable.group_list_item_face)
                },
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    // TODO : 수치 탭 사이즈에서 보고 수정할것
                    .offset(y = (10).dp,x = (-24).dp)
                    .size(64.dp)
                    .alpha(0.2f),
                contentScale = ContentScale.Fit
            )
        }
    }

}

@Composable
fun GroupAddItem(navController: NavHostController){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("detail/새그룹")
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
                .background(MaterialTheme.colorScheme.primary)
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
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+")
                }

                Spacer(Modifier.width(24.dp))

                // 텍스트 영역
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "새그룹",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}
