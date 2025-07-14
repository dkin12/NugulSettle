package com.nugul.settle.ui.item

import android.icu.text.DecimalFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nugul.settle.R
import com.nugul.settle.dataclass.Meet
import com.nugul.settle.ui.theme.userColors

@Composable
fun MeetingListItem(groupColor: String, item: Meet, navController: NavController){
    val groupColor = userColors[groupColor] ?: userColors["Red"]
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 72.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .border(width = 0.5.dp, color = MaterialTheme.colorScheme.onSurface, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp))
            .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 5.dp, bottomEnd = 5.dp, bottomStart = 5.dp))
            .background(color = MaterialTheme.colorScheme.background)
            .clickable{
                navController.navigate("detail/${item.groupIdx}/${item.meetIdx}")
            }
    ){
        Image(
            painter = painterResource(id = R.drawable.rectangel_meeting),
            colorFilter = ColorFilter.tint(groupColor!!.base),
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    style = MaterialTheme.typography.bodyMedium,
                    text = item.meetCreateDate,
                )
                Column (
                    modifier = Modifier.fillMaxWidth(),
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ){
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            text = "총 "
                        )
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            color = groupColor.base,
                            text = "${item.meetDegree.size}"
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodySmall,
                            text = "차"
                        )
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            text = "참석자 ${item.meetMember.size}명"
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Absolute.Right

                ){
                    val meetTotalPrice = DecimalFormat("#,###").format(item.meetTotalPrice)
                    Text(
                        style = MaterialTheme.typography.bodySmall,
                        text = "$meetTotalPrice 원"
                    )
                }

            }
        }


    }

}


@Composable
fun MeetingAddItem(navController: NavController){
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 72.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .border(width = 0.5.dp, color = MaterialTheme.colorScheme.onSurface)
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ){
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall,
            text = "+ 모임 추가하기"
        )
    }
}