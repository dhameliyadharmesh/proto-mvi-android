package com.proto.mvi.ui.searchvcsuser

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
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
//                        TextView(
//                            text = stringResource(
//                                R.string.lbl_user_has_repos,
//                                username,
//                                repos.size.toString()
//                            ),
//                            modifier = Modifier.padding(horizontal = 12.dp)
//                        )
                        LazyColumn(
                            contentPadding = PaddingValues(all = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(all = 8.dp),
                                    colors = CardColors(
                                        containerColor = MaterialTheme.extendedColors.cardBackgroundColor,
                                        disabledContainerColor = MaterialTheme.extendedColors.cardBackgroundColor,
                                        contentColor = MaterialTheme.extendedColors.text,
                                        disabledContentColor = MaterialTheme.extendedColors.text
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(all = 8.dp),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ){
//                                    Icon(
//                                        painter = painterResource(id = R.drawable.ic_fork),
//                                        contentDescription = null,
//                                        modifier = Modifier.size(16.dp)
//                                    )
                                        AsyncImage(
                                            model = "https://avatars.githubusercontent.com/u/262517?v=4",
                                            contentDescription = null,
                                            onSuccess = {
//                                            isLoading = false
                                            },
                                            onError = {
//                                            isLoading = false
                                            },
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clip(RoundedCornerShape(48.dp))
//                                            .background(MaterialTheme.extendedColors.wallpaperItemCard)
                                                .clickable {
//                                                onWallpaperItemClicked()
                                                },
                                            contentScale = ContentScale.Crop
                                        )
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            TextView(
                                                text = username,
                                                textStyle = MaterialTheme.typography.labelMedium.copy(
                                                    fontSize = 14.sp
                                                ),
                                                modifier = Modifier.padding(start = 4.dp)
                                            )
                                            TextView(
                                                text = repos.size.toString().plus(" Repositories"),
                                                textStyle = MaterialTheme.typography.labelSmall.copy(
                                                    fontSize = 14.sp
                                                ),
                                                modifier = Modifier.padding(start = 4.dp)
                                            )
                                        }
                                    }
                                }

                            }
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
            .padding(horizontal = 8.dp),
        colors = CardColors(
            containerColor = MaterialTheme.extendedColors.cardBackgroundColor,
            disabledContainerColor = MaterialTheme.extendedColors.cardBackgroundColor,
            contentColor = MaterialTheme.extendedColors.text,
            disabledContentColor = MaterialTheme.extendedColors.text
        )
    ) {
        Column {
            TextView(
                text = "Project name: ".plus(item.name),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.End)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fork), contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                TextView(
                    text = item.forks.toString(),
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 14.sp
                    ),
                )
            }
        }
    }
}