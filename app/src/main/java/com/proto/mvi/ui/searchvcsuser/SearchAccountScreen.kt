package com.proto.mvi.ui.searchvcsuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.proto.mvi.R
import com.proto.mvi.common.widgets.ButtonView
import com.proto.mvi.common.widgets.TextFieldView
import com.proto.mvi.common.widgets.TextView
import com.proto.mvi.ui.theme.Typography

@Composable
fun SearchAccountScreen(
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextFieldView(
            value = "",
            onValueChange = {

            },
            placeholder = {
                TextView(
                    text = stringResource(R.string.lbl_search_github_username),
                    textStyle = Typography.labelSmall
                )
            },
            containerModifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )
        ButtonView(
            onClick = {

            },
            label = stringResource(R.string.lbl_search_github_user),
            buttonModifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}