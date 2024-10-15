package com.example.syscredit.ui.unlocks.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.syscredit.R
import com.example.syscredit.data.model.Blocked
import com.example.syscredit.ui.unlocks.BlockedViewModel
import com.example.syscredit.utils.Status
import java.text.SimpleDateFormat

@Composable
fun BlockedScreen(
    modifier: Modifier,
    viewModel: BlockedViewModel
) {
    val blocked by viewModel.blockedList.observeAsState()

    blocked?.let { blocked ->
        when (blocked.status) {
            Status.LOADING ->
                Text(text = "Loading...")

            Status.SUCCESS ->
                BlockedList(blockedList = blocked.data, viewModel = viewModel)

            Status.ERROR ->
                Text(text = "Error")
        }
    }
}

@Composable
fun BlockedList(
    blockedList: List<Blocked>?,
    viewModel: BlockedViewModel
) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(blockedList?.size?:0) { index ->
            val blocked = blockedList?.get(index)
            blocked?.let {
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    shape = RoundedCornerShape(
                        2.dp
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Column (
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_middle))
                    ) {
                        Text(
                            text = blocked.customer?.nombre ?: "",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Fecha de solicitud: ${blocked.createdAt}"
                        )
                        Text(
                            text = "Gerente: ${blocked.managerName}"
                        )
                        Text(
                            text = "Supervisor: ${blocked.supervisorName}"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(1.dp))
        }
    }
}