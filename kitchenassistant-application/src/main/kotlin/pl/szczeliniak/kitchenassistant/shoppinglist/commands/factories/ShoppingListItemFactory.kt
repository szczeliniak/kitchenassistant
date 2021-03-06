package pl.szczeliniak.kitchenassistant.shoppinglist.commands.factories

import pl.szczeliniak.kitchenassistant.receipt.ReceiptFacade
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListItem
import pl.szczeliniak.kitchenassistant.shoppinglist.commands.dto.NewShoppingListItemDto

open class ShoppingListItemFactory(private val receiptFacade: ReceiptFacade) {

    open fun create(dto: NewShoppingListItemDto): ShoppingListItem {
        dto.receiptId?.let {
            receiptFacade.getReceipt(it)
        }

        return ShoppingListItem(
            name = dto.name,
            quantity = dto.quantity,
            sequence = dto.sequence,
            receiptId = dto.receiptId
        )
    }

}