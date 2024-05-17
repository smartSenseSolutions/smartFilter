package com.ss.smartfilter.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ss.smartfilter.R

/**
 * created by Mala Ruparel ON 16/05/24
 */
@Composable
fun FilterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
       TopAppBarWithBackButton(
            title = LocalContext.current.getString(R.string.title),
            onBackPressed = { }
        )
        ExpandableBusType(title = LocalContext.current.getString(R.string.filter_bus_type))
        HorizontalDivider()
        ExpandableSeatType(title = LocalContext.current.getString(R.string.filter_seat_type))
        HorizontalDivider()
        ExpandableDepartureType(title = LocalContext.current.getString(R.string.filter_departure_time))
        HorizontalDivider()
        ExpandableOperatorType(title = LocalContext.current.getString(R.string.filter_operator))
        HorizontalDivider()
        ExpandableBusOperatorType(title = LocalContext.current.getString(R.string.filter_bus_operator))
        HorizontalDivider()
        ExpandableAmentiesType(title = LocalContext.current.getString(R.string.filter_amentiest))
        HorizontalDivider()
        ExpandableDealType(title = LocalContext.current.getString(R.string.filter_Deals))
        HorizontalDivider()
        TipBox()

    }

}


