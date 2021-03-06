package pl.szczeliniak.kitchenassistant.dayplan

import pl.szczeliniak.kitchenassistant.dayplan.queries.dto.DayPlanCriteria

interface DayPlanDao {

    fun save(dayPlan: DayPlan): DayPlan

    fun save(dayPlans: Set<DayPlan>)

    fun findAll(criteria: DayPlanCriteria, offset: Int? = null, limit: Int? = null): Set<DayPlan>

    fun count(criteria: DayPlanCriteria): Long

    fun findById(id: Int): DayPlan?

}