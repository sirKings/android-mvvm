package co.king.lloydsapp.currencyList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.king.lloydsapp.R
import co.king.lloydsapp.currencyList.presentation.components.CurrencyItem
import co.king.lloydsapp.util.TestTags


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyListScreen() {

    val viewModel = hiltViewModel<CurrencyViewModel>()
    val state = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text =
                        stringResource(R.string.title),
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.shadow(2.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(modifier = Modifier.padding(16.dp).testTag(TestTags.CURRENCY_LIST_TAG)) {
                items(state.value.items.size){
                    CurrencyItem(modifier = Modifier, item = state.value.items[it])
                    Divider(modifier = Modifier.fillMaxWidth().padding(top = 4.dp, bottom = 4.dp))
                }
            }
        }
    }
}