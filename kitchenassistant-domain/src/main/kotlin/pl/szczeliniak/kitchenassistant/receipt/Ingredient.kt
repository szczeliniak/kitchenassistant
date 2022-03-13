package pl.szczeliniak.kitchenassistant.receipt

import pl.szczeliniak.kitchenassistant.exceptions.NotAllowedOperationException
import java.time.LocalDateTime

data class Ingredient(
    var id: Int = 0,
    private var name_: String,
    private var quantity_: String,
    private var unit_: IngredientUnit?,
    private var deleted_: Boolean = false,
    private val createdAt_: LocalDateTime = LocalDateTime.now(),
    private var modifiedAt_: LocalDateTime = LocalDateTime.now()
) {
    val name: String get() = name_
    val quantity: String get() = quantity_
    val unit: IngredientUnit? get() = unit_
    val deleted: Boolean get() = deleted_
    val createdAt: LocalDateTime get() = createdAt_
    val modifiedAt: LocalDateTime get() = modifiedAt_

    fun markAsDeleted() {
        if (deleted) {
            throw NotAllowedOperationException("Step is already marked as deleted!")
        }
        this.modifiedAt_ = LocalDateTime.now()
    }
}