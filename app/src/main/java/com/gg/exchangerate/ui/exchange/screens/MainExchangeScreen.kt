package com.gg.exchangerate.ui.exchange.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gg.domain.currency.model.ICurrency
import com.gg.domain.enums.SortDirection
import com.gg.domain.enums.SortField
import com.gg.domain.enums.SortType
import com.gg.exchangerate.R
import com.gg.exchangerate.component.*
import com.gg.exchangerate.ui.exchange.ExchangeRateViewModel
import com.gg.exchangerate.ui.theme.ExchangeRateTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun MainExchangeScreen(
    navController: NavHostController,
    viewModel: ExchangeRateViewModel
) {
    val currenciesPopular by viewModel.currencies.collectAsState()
    val currenciesFavourite by viewModel.currenciesFavourite.collectAsState()
    val codes by viewModel.codes.collectAsState(emptyList())
    val baseCurrency by viewModel.baseCurrency.collectAsState()
    val scope = rememberCoroutineScope()
    var page by remember { mutableStateOf(RatePages.POPULAR) }

    Scaffold(topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = ExchangeRateTheme.colors.background2)
                .height(80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExcRateTextHeader(
                modifier = Modifier.padding(top = 6.dp),
                text = stringResource(id = R.string.currency_rates)
            )
            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically
            ) {
                DropdownCurrencyContent(
                    baseCurrency = baseCurrency,
                    currencies = codes,
                    onCurrencyChoose = viewModel::setBaseCurrency
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { scope.launch { viewModel.updateCurrencyValues() } }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_refresh),
                        contentDescription = "update"
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                DropdownSortContent(viewModel::setSortType)
            }
        }
    }, bottomBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = ExchangeRateTheme.colors.background2)
                .padding(10.dp)
        ) {
            ExcRateButton1(modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
                text = stringResource(id = R.string.popular),
                onClick = {
                    page = RatePages.POPULAR
                })
            Spacer(modifier = Modifier.width(10.dp))
            ExcRateButton1(modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
                text = stringResource(id = R.string.favourite),
                onClick = {
                    page = RatePages.FAVOURITE
                })
        }
    }) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(color = ExchangeRateTheme.colors.background1)
                .fillMaxSize()
        ) {
            RateSourceComponent(
                ratePage = page,
                curDataPopular = currenciesPopular,
                curDataFavourite = currenciesFavourite,
                onFavoriteChoose = { scope.launch { viewModel.switchFavourite(it) } }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RateSourceComponent(
    ratePage: RatePages,
    curDataPopular: List<ICurrency>,
    curDataFavourite: List<ICurrency>,
    onFavoriteChoose: (currency: ICurrency) -> Unit
) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = RatePages.values().size - 1, state = pagerState
    ) { page ->
        when (page) {
            0 -> {
                CurrencyListContent(
                    currencyList = curDataPopular, onFavoriteChoose = onFavoriteChoose
                )
            }

            1 -> {
                CurrencyListContent(
                    currencyList = curDataFavourite, onFavoriteChoose = onFavoriteChoose
                )
            }
            else -> RatePages.UNDEFINED
        }
    }

    LaunchedEffect(key1 = ratePage) {
        pagerState.scrollToPage(
            when (ratePage) {
                RatePages.POPULAR -> 0
                RatePages.FAVOURITE -> 1
                else -> 0
            }
        )
    }
}

@Composable
fun CurrencyListContent(
    currencyList: List<ICurrency>, onFavoriteChoose: (currency: ICurrency) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        this.items(currencyList) { currency ->
            Row(verticalAlignment = CenterVertically) {
                IconButton(onClick = { onFavoriteChoose(currency) }) {
                    Icon(
                        painterResource(
                            id = if (currency.isFavourite) R.drawable.ic_start_active
                            else R.drawable.ic_start_passive
                        ), contentDescription = "Is favorite"
                    )
                }
                ExcRateText2(text = "${currency.code} : ${currency.description}")
                Spacer(modifier = Modifier.weight(1f))
                ExcRateText2(
                    modifier = Modifier.padding(end = 5.dp),
                    text = "${currency.value}"
                )
            }
            Divider()
        }
    }
}

@Composable
fun DropdownSortContent(
    onSortChoose: (SortType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(painterResource(id = R.drawable.ic_sort), contentDescription = "Show sort type")
        }
        DropdownMenu(modifier = Modifier.heightIn(max = 160.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }

        ) {
            SortType.values().filter { it != SortType.UNKNOWN }.forEach { sortType ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onSortChoose(sortType)
                }) {
                    ExcRateText3(
                        text = stringResource(
                            id = if (sortType.sortField == SortField.BY_NAME) R.string.by_name
                            else R.string.by_value
                        ),
                        modifier = Modifier.padding(10.dp)
                    )
                    when (sortType.direction) {
                        SortDirection.ASCENDING -> Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Sort direction"
                        )
                        SortDirection.DESCENDING -> Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Sort direction"
                        )
                        else -> {}
                    }
                }
                Divider()
            }
        }
    }
}

@Composable
fun DropdownCurrencyContent(
    baseCurrency: String,
    currencies: List<String>,
    onCurrencyChoose: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(
            modifier = Modifier.width(100.dp),
            onClick = { expanded = true },
            contentPadding = PaddingValues(5.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
            )
        ) {
            ExcRateText1(text = baseCurrency)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Outlined.ArrowDropDown, contentDescription = "Show currencies")
        }
        DropdownMenu(modifier = Modifier
            .heightIn(max = 160.dp)
            .widthIn(max = 100.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            currencies.forEachIndexed { id, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onCurrencyChoose(currencies[id])
                }) {
                    ExcRateText1(text = text)
                }
                Divider()
            }
        }
    }

}

enum class RatePages {
    UNDEFINED, POPULAR, FAVOURITE
}