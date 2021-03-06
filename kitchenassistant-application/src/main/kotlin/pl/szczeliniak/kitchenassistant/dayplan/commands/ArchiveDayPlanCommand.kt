package pl.szczeliniak.kitchenassistant.dayplan.commands

import pl.szczeliniak.kitchenassistant.dayplan.DayPlanDao
import pl.szczeliniak.kitchenassistant.shared.ErrorCode
import pl.szczeliniak.kitchenassistant.shared.KitchenAssistantException
import pl.szczeliniak.kitchenassistant.shared.dtos.SuccessResponse

class ArchiveDayPlanCommand(private val dayPlanDao: DayPlanDao) {

    fun execute(dayPlanId: Int, archive: Boolean): SuccessResponse {
        val dayPlan = dayPlanDao.findById(dayPlanId) ?: throw KitchenAssistantException(ErrorCode.DAY_PLAN_NOT_FOUND)
        if (dayPlan.archived) {
            throw KitchenAssistantException(ErrorCode.DAY_PLAN_ALREADY_ARCHIVED)
        }
        dayPlan.archived = archive
        return SuccessResponse(dayPlanDao.save(dayPlan).id)
    }

}