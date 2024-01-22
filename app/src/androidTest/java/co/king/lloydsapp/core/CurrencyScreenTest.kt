package co.king.lloydsapp.core

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import co.king.lloydsapp.currencyList.data.remote.CurrencyApi
import co.king.lloydsapp.currencyList.data.remote.dto.ExchangeRateResponse
import co.king.lloydsapp.currencyList.presentation.CurrencyListScreen
import co.king.lloydsapp.ui.theme.LloydsAppTheme
import co.king.lloydsapp.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import retrofit2.Response
import javax.inject.Inject

@HiltAndroidTest
class CurrencyScreenTest {

    @Inject
    lateinit var currencyApi: CurrencyApi

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            LloydsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CurrencyListScreen()
                }
            }
        }
    }

    @Test
    fun Given_successful_network_access_home_screen_should_contain_list_of_currencies() {
        composeRule.onNodeWithTag(TestTags.CURRENCY_LIST_TAG).assertExists()
        composeRule.onNodeWithTag(TestTags.CURRENCY_NAME_TAG).assertExists()
        composeRule.onNodeWithTag(TestTags.CURRENCY_RATE_TAG).assertExists()
        composeRule.onNodeWithTag(TestTags.CURRENCY_SYMBOL_TAG).assertExists()
    }
}