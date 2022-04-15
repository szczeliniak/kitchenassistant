package pl.szczeliniak.kitchenassistant.shoppinglist

import org.springframework.stereotype.Component
import pl.szczeliniak.kitchenassistant.shoppinglist.persistance.ShoppingListRepository

@Component
class ShoppingListDaoImpl(
    private val shoppingListRepository: ShoppingListRepository,
    private val shoppingListMapper: ShoppingListMapper
) : ShoppingListDao {

    override fun save(shoppingList: ShoppingList): ShoppingList {
        return shoppingListMapper.toDomain(shoppingListRepository.save(shoppingListMapper.toEntity(shoppingList)))
    }

    override fun findById(id: Int): ShoppingList? {
        val byId = shoppingListRepository.findById(id) ?: return null
        return shoppingListMapper.toDomain(byId)
    }

    override fun findAll(criteria: ShoppingListCriteria, offset: Int, limit: Int): List<ShoppingList> {
        return shoppingListRepository.findAll(
            ShoppingListRepository.SearchCriteria(
                criteria.userId,
                criteria.archived,
                criteria.name,
                criteria.date
            ), offset, limit
        )
            .map { shoppingListMapper.toDomain(it) }
    }

    override fun count(criteria: ShoppingListCriteria): Long {
        return shoppingListRepository.count(
            ShoppingListRepository.SearchCriteria(
                criteria.userId,
                criteria.archived,
                criteria.name,
                criteria.date
            )
        )
    }

}