package com.gg.exchangerate.ui.exchange.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gg.exchangerate.R
import com.gg.exchangerate.component.ExcRateText2
import com.gg.exchangerate.component.ExcRateTextHeader


@Composable
fun AboutScreen(
    navController: NavHostController,
) {
    BackHandler(enabled = true) {
        navController.popBackStack()
    }
    AboutContent()
}

@Composable
fun AboutContent(){
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        ExcRateTextHeader(
            modifier = Modifier.padding(top = 6.dp),
            text = stringResource(id = R.string.about)
        )
        ExcRateText2(text = "1. Token have to be input in build.gradle of app module, otherwise there will be no updates")
        ExcRateText2(text = "2. There is no error handling in UI, all errors are in logcat")
        ExcRateText2(text = "3. There is no polling, as free token have only 250 requests")
        ExcRateText2(text = "4. For update values you can push \"update button\" or change value")
        ExcRateText2(text = "5. Popular and favourite screens can be switched by button or swipe")
        ExcRateText2(text = "6. Press back button to go back to main screen")

    }

}
