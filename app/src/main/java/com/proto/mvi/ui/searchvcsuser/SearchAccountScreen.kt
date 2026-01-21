package com.proto.mvi.ui.searchvcsuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.proto.mvi.R
import com.proto.mvi.common.widgets.ButtonView
import com.proto.mvi.common.widgets.TextFieldView
import com.proto.mvi.common.widgets.TextView
import com.proto.mvi.ui.theme.Typography
import com.proto.mvi.ui.theme.WrapperScreen

@Composable
fun SearchAccountScreen(
    navController: NavController
) {
    SearchAccountScreen()
}


@Preview
@Composable
fun SearchAccountScreen(

) {
    val navController = rememberNavController()
    val list = mutableListOf<Repository>()
    list.add(Repository("Repo1"))
    list.add(Repository("Repo2"))
    list.add(Repository("Repo3"))


    WrapperScreen {
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

            TextView(
                text = stringResource(R.string.lbl_search_github_user),
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(list.size) { index ->
                    val item = list[index]
                    RepositoryCard(item)
                }
            }
        }
    }
}

@Composable
fun RepositoryCard(item: Repository) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) { TextView(text = item.name, modifier = Modifier.padding(12.dp)) }
}