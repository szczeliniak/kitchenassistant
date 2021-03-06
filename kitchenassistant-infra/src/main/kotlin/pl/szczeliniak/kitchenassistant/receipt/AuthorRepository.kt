package pl.szczeliniak.kitchenassistant.receipt

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Repository
class AuthorRepository(@PersistenceContext private val entityManager: EntityManager) : AuthorDao {

    @Transactional
    override fun save(author: Author): Author {
        if (author.id == 0) {
            entityManager.persist(author)
        } else {
            entityManager.merge(author)
        }
        return author
    }

    override fun saveAll(authors: Set<Author>) {
        authors.forEach { save(it) }
    }

    override fun findById(id: Int): Author? {
        return entityManager
            .createQuery(
                "SELECT r FROM Author r WHERE r.id = :id",
                Author::class.java
            )
            .setParameter("id", id)
            .resultList
            .stream()
            .findFirst()
            .orElse(null)
    }

    override fun findByName(name: String, userId: Int): Author? {
        return entityManager
            .createQuery(
                "SELECT r FROM Author r WHERE r.name = :name AND r.userId = :userId",
                Author::class.java
            )
            .setParameter("name", name)
            .setParameter("userId", userId)
            .resultList
            .stream()
            .findFirst()
            .orElse(null)
    }

    override fun findAll(criteria: AuthorCriteria): Set<Author> {
        var query = "SELECT r FROM Author r WHERE r.id IS NOT NULL"
        if (criteria.name != null) {
            query += " AND LOWER(r.name) LIKE (:name)"
        }
        if (criteria.userId != null) {
            query += " AND r.userId = :userId"
        }

        var typedQuery = entityManager.createQuery(query, Author::class.java)
        if (criteria.name != null) {
            typedQuery = typedQuery.setParameter("name", "%" + criteria.name + "%")
        }
        if (criteria.userId != null) {
            typedQuery = typedQuery.setParameter("userId", criteria.userId)
        }

        return typedQuery.resultList.toMutableSet()
    }

}