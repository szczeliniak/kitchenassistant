package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.exceptions.NotFoundException
import pl.szczeliniak.kitchenassistant.receipt.IngredientDao
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import pl.szczeliniak.kitchenassistant.receipt.commands.dto.UpdateIngredientDto

class UpdateIngredientCommand(private val receiptDao: ReceiptDao, private val ingredientDao: IngredientDao) {

    fun execute(receiptId: Int, ingredientId: Int, dto: UpdateIngredientDto): SuccessResponse {
        val receipt = receiptDao.findById(receiptId) ?: throw NotFoundException("Receipt not found")

        val ingredient =
            receipt.ingredients.firstOrNull { it.id == ingredientId } ?: throw NotFoundException("Ingredient not found")

        ingredient.update(dto.name, dto.quantity)

        return SuccessResponse(ingredientDao.save(ingredient).id)
    }

}