package pl.szczeliniak.kitchenassistant.shoppinglist.persistance

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Repository
class ShoppingListItemRepository(@PersistenceContext private val entityManager: EntityManager) {

    @Transactional
    fun save(entity: ShoppingListItemEntity): ShoppingListItemEntity {
        if (entity.id == 0) {
            entityManager.persist(entity)
        } else {
            entityManager.merge(entity)
        }
        return entity
    }

}