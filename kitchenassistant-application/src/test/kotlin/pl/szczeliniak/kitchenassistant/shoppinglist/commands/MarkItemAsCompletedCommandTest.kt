package pl.szczeliniak.kitchenassistant.shoppinglist.commands

import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import pl.szczeliniak.kitchenassistant.JunitBaseClass
import pl.szczeliniak.kitchenassistant.shared.dtos.SuccessResponse
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingList
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListDao
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListItem
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListItemDao

internal class MarkItemAsCompletedCommandTest : JunitBaseClass() {

    @Mock
    private lateinit var shoppingListDao: ShoppingListDao

    @Mock
    private lateinit var shoppingListItemDao: ShoppingListItemDao

    @InjectMocks
    private lateinit var markItemAsCompletedCommand: MarkItemAsCompletedCommand

    @Test
    fun shouldMarkShoppingListItemAsDone() {
        val shoppingListItem = shoppingListItem()
        whenever(shoppingListDao.findById(1)).thenReturn(shoppingList(shoppingListItem))

        val result = markItemAsCompletedCommand.execute(1, 2, true)

        assertThat(result).isEqualTo(SuccessResponse(2))
        assertThat(shoppingListItem.completed).isTrue
        Mockito.verify(shoppingListItemDao).save(shoppingListItem)
    }

    private fun shoppingListItem(): ShoppingListItem {
        return ShoppingListItem(id = 2, name = "", quantity = "", sequence = 0, completed = false)
    }

    private fun shoppingList(shoppingListItem: ShoppingListItem): ShoppingList {
        return ShoppingList(
            userId = 4,
            name = "",
            items = mutableSetOf(shoppingListItem)
        )
    }

}