package pl.szczeliniak.kitchenassistant.shoppinglist.queries

import pl.szczeliniak.kitchenassistant.common.dto.Pagination
import pl.szczeliniak.kitchenassistant.common.dto.PaginationUtils
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListCriteria
import pl.szczeliniak.kitchenassistant.shoppinglist.ShoppingListDao
import pl.szczeliniak.kitchenassistant.shoppinglist.queries.dto.ShoppingListDto
import pl.szczeliniak.kitchenassistant.shoppinglist.queries.dto.ShoppingListsResponse

class GetShoppingListsQuery(private val shoppingListDao: ShoppingListDao) {

    fun execute(page: Long?, limit: Int?, criteria: ShoppingListCriteria): ShoppingListsResponse {
        val currentPage = PaginationUtils.calculatePageNumber(page)
        val currentLimit = PaginationUtils.calculateLimit(limit)
        val offset = PaginationUtils.calculateOffset(currentPage, currentLimit)
        val totalNumberOfPages = PaginationUtils.calculateNumberOfPages(currentLimit, shoppingListDao.count(criteria))
        return ShoppingListsResponse(
            shoppingListDao.findAll(criteria, offset, currentLimit)
                .map { ShoppingListDto.fromDomain(it) }.toSet(),
            Pagination(currentPage, currentLimit, totalNumberOfPages)
        )
    }

}