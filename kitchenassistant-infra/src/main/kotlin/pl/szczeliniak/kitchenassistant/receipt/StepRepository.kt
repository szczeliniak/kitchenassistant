package pl.szczeliniak.kitchenassistant.receipt

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Repository
class StepRepository(@PersistenceContext private val entityManager: EntityManager) : StepDao {

    @Transactional
    override fun save(step: Step): Step {
        if (step.id == 0) {
            entityManager.persist(step)
        } else {
            entityManager.merge(step)
        }
        return step
    }

}