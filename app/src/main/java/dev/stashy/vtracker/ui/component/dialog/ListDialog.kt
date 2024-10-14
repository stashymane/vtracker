package dev.stashy.vtracker.ui.component.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.stashy.vtracker.ui.theme.VTrackerTheme

@Composable
fun <T> ListDialog(
    visible: Boolean,
    onDismiss: () -> Unit,
    items: List<T>,
    title: @Composable () -> Unit,
    onSelect: (T) -> Unit,
    item: @Composable (T) -> Unit
) {
    ADialog(visible, onDismiss) {
        LazyColumn() {
            item {
                ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                    Row(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
                        title()
                    }
                }
            }

            items(items) {
                Row(modifier = Modifier
                    .clickable {
                        onSelect(it)
                        onDismiss()
                    }
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .fillMaxWidth()
                ) {
                    item(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListDialogPreview() {
    val items = listOf<String>("Item 1", "Item 2", "Item 3", "Item 4")
    VTrackerTheme {
        ListDialog(true, {}, items, { Text("Dialog preview") }, {}) {
            Text(it)
        }
    }
}
