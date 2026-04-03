package com.proto.mvi.ui.searchvcsuser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.proto.mvi.R
import com.proto.mvi.app.AppViewModel
import com.proto.mvi.common.widgets.ButtonView
import com.proto.mvi.common.widgets.TextFieldView
import com.proto.mvi.common.widgets.TextView
import com.proto.mvi.data.model.Repo
import com.proto.mvi.ui.theme.WrapperScreen
import com.proto.mvi.ui.theme.extendedColors

//https://api.github.com/users/mralexgray/repos
@Composable
fun SearchAccountScreen(
    navController: NavController,
    appViewModel: AppViewModel,
    viewModel: SearchAccountViewModel = hiltViewModel()
) {
    var username by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    WrapperScreen {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextFieldView(
                value = username,
                onValueChange = { username = it },
                placeHolderText = stringResource(R.string.lbl_search_github_username),
                enabled = uiState !is SearchUiState.Loading,
                containerModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )
            if (uiState === SearchUiState.Loading) {
                TextView(
                    text = "Loading...",
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                ButtonView(
                    onClick = {
                        viewModel.search(username.trim())
                    },
                    label = stringResource(R.string.lbl_search_github_user),
                    buttonModifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            when (uiState) {
                is SearchUiState.Idle -> {
                    TextView(
                        text = stringResource(R.string.lbl_no_github_user),
                        modifier = Modifier.padding(horizontal = 12.dp)
                    )
                }

                is SearchUiState.Loading -> {
                }

                is SearchUiState.Success -> {
                    val repos = (uiState as SearchUiState.Success).repos
                    if (repos.isEmpty()) {
                        TextView(
                            text = stringResource(R.string.lbl_no_github_user),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    } else {
                        TextView(
                            text = stringResource(
                                R.string.lbl_user_has_repos,
                                username,
                                repos.size.toString()
                            ),
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                        LazyColumn(
                            contentPadding = PaddingValues(all = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(repos) { repo ->
                                RepositoryCard(repo)
                            }
                        }
                    }
                }

                is SearchUiState.Error -> {
                    val msg = (uiState as SearchUiState.Error).message
                    TextView(text = msg, modifier = Modifier.padding(horizontal = 12.dp))
                }
            }
        }
    }
}

@Composable
fun RepositoryCard(item: Repo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp), colors = CardColors(
            containerColor = MaterialTheme.extendedColors.cardBackgroundColor,
            disabledContainerColor = MaterialTheme.extendedColors.cardBackgroundColor,
            contentColor = MaterialTheme.extendedColors.text,
            disabledContentColor = MaterialTheme.extendedColors.text
        )
    ) {
        Column(

        ) {
            TextView(text = item.name, modifier = Modifier.padding(12.dp))

            Row(
                modifier = Modifier.align(
                    Alignment.End
                )
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_fork), contentDescription = null)
                TextView(
                    text = item.forks.toString(), modifier = Modifier
                        .padding(12.dp)

                )
            }
        }

    }
}