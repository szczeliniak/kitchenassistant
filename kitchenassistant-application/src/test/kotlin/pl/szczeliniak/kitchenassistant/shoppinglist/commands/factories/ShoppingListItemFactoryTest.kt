package pl.szczeliniak.kitchenassistant.shoppinglist.commands.factories

import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import pl.szczeliniak.kitchenassistant.JunitBaseClass
import pl.szczeliniak.kitchenassistant.receipt.ReceiptFacade
import pl.szczeliniak.kitchenassistant.receipt.queries.dto.ReceiptDetailsDto
import pl.szczeliniak.kitchenassistant.receipt.queries.dto.ReceiptResponse
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListItem
import pl.szczeliniak.kitchenassistant.shoppinglist.commands.dto.NewShoppingListItemDto
import java.util.*

internal class ShoppingListItemFactoryTest : JunitBaseClass() {

    @Mock
    private lateinit var receiptFacade: ReceiptFacade

    @InjectMocks
    private lateinit var shoppingListItemFactory: ShoppingListItemFactory

    @Test
    fun shouldCreateShoppingList() {
        val newShoppingListItemDto = newShoppingListItemDto()
        whenever(receiptFacade.getReceipt(1)).thenReturn(receiptResponse())

        val result = shoppingListItemFactory.create(newShoppingListItemDto)

        assertThat(result).usingRecursiveComparison()
            .ignoringFields("createdAt", "modifiedAt")
            .isEqualTo(shoppingListItem())
    }

    private fun receiptResponse(): ReceiptResponse {
        return ReceiptResponse(
            ReceiptDetailsDto(
                0,
                "",
                null,
                null,
                null,
                null,
                null,
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet()
            )
        )
    }

    private fun shoppingListItem(): ShoppingListItem {
        return ShoppingListItem(name = "", quantity = null, sequence = null, receiptId = 1)
    }

    private fun newShoppingListItemDto(): NewShoppingListItemDto {
        val dto = NewShoppingListItemDto()
        dto.receiptId = 1
        return dto
    }

}