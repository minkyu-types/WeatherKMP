package org.dosys.project.presentation.feature.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import co.touchlab.kermit.Logger
import org.dosys.project.presentation.feature.base.LoadState

@Composable
fun TodoMainScreen(
    viewModel: TodoMainViewModel,
    modifier: Modifier = Modifier
) {
    val todoState by viewModel.state.collectAsStateWithLifecycle(null)
    val pagingItems = viewModel.pagingFlow.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is TodoMainSideEffect.ShowAlert -> {

                }

                is TodoMainSideEffect.ShowSortBottomSheet -> {

                }
            }
        }
    }

    when (todoState?.loadState) {
        is LoadState.Success, LoadState.Idle -> {
            PagedTodoList(
                todos = pagingItems,
                onItemEdited = { data ->
                    viewModel.editTodo(data)
                }
            )
        }

        is LoadState.Error -> {
            Logger.d { "TodoMainScreen: LoadState.Error" }
        }

        else -> {
            Logger.d { "TodoMainScreen: LoadState is NULL" }
        }
    }
}

@Composable
private fun PagedTodoList(
    todos: LazyPagingItems<TodoModel>,
    onItemEdited: (TodoModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(todos.itemCount) { index ->
            val todo = todos[index]
            todo?.let {
                TodoItem(
                    todo = todo,
                    onItemEdited = onItemEdited
                )
            }
        }
    }
}

@Composable
private fun TodoList(
    todos: List<TodoModel>,
    onItemEdited: (TodoModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        items(
            items = todos,
            key = { it.id }
        ) { item ->
            TodoItem(
                todo = item,
                onItemEdited = onItemEdited
            )
        }
    }
}

@Composable
private fun TodoItem(
    todo: TodoModel,
    onItemEdited: (TodoModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var content by remember { mutableStateOf(todo.content) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Checkbox(
            checked = todo.isComplete,
            onCheckedChange = { isChecked ->
                val newData = todo.copy(isComplete = isChecked)
                onItemEdited(newData)
            },
            enabled = true
        )
        TextField(
            value = content,
            enabled = !todo.isComplete,
            onValueChange = {
                content = it
            },
            readOnly = todo.isComplete,
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        val newData = todo.copy(content = content)
                        onItemEdited(newData)
                    }
                }
        )
    }
}