package pl.szczeliniak.kitchenassistant.shoppinglist.commands.factories

import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingList
import pl.szczeliniak.kitchenassistant.shoppinglist.commands.dto.NewShoppingListDto

open class ShoppingListFactory(private val shoppingListItemFactory: ShoppingListItemFactory) {

    open fun create(dto: NewShoppingListDto): ShoppingList {
        return ShoppingList(
            userId_ = dto.userId,
            title_ = dto.title,
            description_ = dto.description,
            items_ = dto.items.map { shoppingListItemFactory.create(it) })
    }

}