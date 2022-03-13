package pl.szczeliniak.kitchenassistant.shoppinglist.commands

import pl.szczeliniak.kitchenassistant.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListDao
import pl.szczeliniak.kitchenassistant.shoppinglist.commands.dto.NewShoppingListDto
import pl.szczeliniak.kitchenassistant.shoppinglist.commands.factories.ShoppingListFactory

class AddShoppingListCommand(
    private val shoppingListDao: ShoppingListDao,
    private val shoppingListFactory: ShoppingListFactory
) {

    fun execute(dto: NewShoppingListDto): SuccessResponse {
        shoppingListDao.save(shoppingListFactory.create(dto))
        return SuccessResponse()
    }

}