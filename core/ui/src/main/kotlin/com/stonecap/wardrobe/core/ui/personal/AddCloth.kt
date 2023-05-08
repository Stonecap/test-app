package com.stonecap.wardrobe.core.ui.personal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.linecolor
import com.example.myapplication.ui.theme.maincolor
import com.example.myapplication.ui.theme.totalbgcolor

class MainActivity: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(totalbgcolor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ){
                PicButton()
                Divider(color = linecolor, thickness = 0.3.dp)
                ButtonPage()
                Divider(color = linecolor, thickness = 0.3.dp)
                ButtonPage2()
                Divider(color = linecolor, thickness = 0.3.dp)
                ButtonPage3()
                Divider(color = linecolor, thickness = 0.3.dp)
                ButtonPage4()
                Divider(color = linecolor, thickness = 0.3.dp)
                BrandField()
                Divider(color = linecolor, thickness = 0.3.dp)
                Memo()
                Spacer(modifier = Modifier.height(20.dp))
            }
            TopAppBarCompose()
        }
    }
}

data class ButtonItem(
    val index: Int,
    val label: String){}

@Composable
fun PicButton(){
    Spacer(modifier = Modifier.height(50.dp))
    Button(
        onClick = {},
        contentPadding = PaddingValues(5.dp),
        modifier = Modifier
            .size(200.dp, 200.dp)
            .clip(RoundedCornerShape(20.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = maincolor,
            contentColor = Color.White
        )
    ){
        Icon(painter = painterResource(id = R.drawable.camera_1), contentDescription = "add pic")
    }
}
@Composable
fun SelectButton(
    item: ButtonItem,
    isSelected: Boolean,
    onTap:()->Unit,
) {
    val backgroundColor =
        if (isSelected) maincolor
        else totalbgcolor
    val contentColor =
        if (isSelected) Color.White
        else Color.Black

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .size(90.dp, 40.dp)
            .clip(RoundedCornerShape(40.dp))
            .background(color = backgroundColor)
            .padding(8.dp)
            .clickable { onTap() }
    ){
        Text(
            text = item.label,
            color = contentColor
        )
    }
}

@Composable
fun ButtonPage() {
    val buttonItemList = listOf(
        ButtonItem(0, "두꺼움"),
        ButtonItem(1, "보통"),
        ButtonItem(2, "얇음"))
    var selectedIndex by rememberSaveable{ mutableStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
        items(buttonItemList) { item ->
            SelectButton(
                item = item,
                isSelected = selectedIndex == item.index,
                onTap = { selectedIndex = item.index }
            )
        }

    }
}
@Composable
fun ButtonPage2() {
    val buttonItemList = listOf(
        ButtonItem(0, "봄.가을"),
        ButtonItem(1, "여름"),
        ButtonItem(2, "겨울"))
    var selectedIndex by rememberSaveable{ mutableStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
        items(buttonItemList) { item ->
            SelectButton(
                item = item,
                isSelected = selectedIndex == item.index,
                onTap = { selectedIndex = item.index }
            )
        }

    }
}
@Composable
fun ButtonPage3() {
    val buttonItemList = listOf(
        ButtonItem(0, "반팔"),
        ButtonItem(1, "긴팔"),
        ButtonItem(2, "민소매"))
    var selectedIndex by rememberSaveable{ mutableStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
        items(buttonItemList) { item ->
            SelectButton(
                item = item,
                isSelected = selectedIndex == item.index,
                onTap = { selectedIndex = item.index }
            )
        }

    }
}
@Composable
fun ButtonPage4() {
    val buttonItemList = listOf(
        ButtonItem(0, "S"),
        ButtonItem(1, "M"),
        ButtonItem(2, "L")
    )
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(17.dp)) {
        items(buttonItemList) { item ->
            SelectButton(
                item = item,
                isSelected = selectedIndex == item.index,
                onTap = { selectedIndex = item.index }
            )
        }
    }
}

@Composable
fun BrandField(){
    val textState = remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange = {textValue -> textState.value = textValue},
        label = {Text("브랜드")},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = totalbgcolor,
            cursorColor = maincolor,
            focusedIndicatorColor = totalbgcolor,
            unfocusedIndicatorColor = totalbgcolor,
            focusedLabelColor = maincolor
        )
    )
}

@Composable
fun Memo(){
    val textState = remember{
        mutableStateOf("")
    }
    OutlinedTextField(
        value = textState.value,
        onValueChange = {textValue -> textState.value = textValue},
        label = {Text("메모")},
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = totalbgcolor,
            cursorColor = maincolor,
            focusedIndicatorColor = totalbgcolor,
            unfocusedIndicatorColor = totalbgcolor,
            focusedLabelColor = maincolor
        )
    )
}
