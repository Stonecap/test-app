package com.stonecap.wardrobe.core.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.Dispatchers
import kotlin.random.Random

class MainCloset : ComponentActivity() {

    private var list = mutableListOf<ItemData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView(list = list)
        }
        lifecycleScope.launch(Dispatchers.Main.immediate){
            list.clear()
            for(i in 0 until 1000){
                list.add(
                    ItemData(
                        index = i,
                        height= Random.nextInt(100, 300),
                        content = "index $i"
                    )
                )
            }
        }
    }
}
@Composable
private fun ItemView(itemData: ItemData){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemData.height.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(pointcolor1),
        contentAlignment = Alignment.Center
    ){

    }
}
data class ItemData(
    val index: Int,
    val content: String,
    val height:Int
)
@Composable
fun TopAppBarCompose(
){
    val context = LocalContext.current
    SmallTopAppBar(
        modifier = Modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Home, contentDescription = "home")
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Search")
            }
        },

        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = totalbgcolor
        )
    )
}
//
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ContentView(list:List<ItemData>){
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = pointcolor2,
        topBar = { TopAppBarCompose()}){
        LazyVerticalStaggeredGrid(
            state = rememberLazyStaggeredGridState(),
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            itemsIndexed(
                items = list,
                key = {_:Int, item: ItemData ->
                    item.index
                }
            ){ _, item ->
                ItemView(itemData = item)
            }
        }
    }

}
