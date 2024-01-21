package co.king.lloydsapp.currencyList.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import co.king.lloydsapp.currencyList.domain.model.Currency


@Composable
fun CurrencyItem(modifier: Modifier, item: Currency) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = item.symbol, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(text = item.name)
        }
        Text(text = item.rate.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
    }
}