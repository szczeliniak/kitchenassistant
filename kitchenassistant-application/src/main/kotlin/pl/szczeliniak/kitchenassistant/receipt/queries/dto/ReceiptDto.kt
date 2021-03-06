package pl.szczeliniak.kitchenassistant.receipt.queries.dto

data class ReceiptDto(
    val id: Int,
    val name: String,
    val author: String?,
    val favorite: Boolean?,
    val category: CategoryDto?,
    val tags: Set<String>
)