package pl.szczeliniak.kitchenassistant.receipt.queries.dto

import pl.szczeliniak.kitchenassistant.receipt.Step
import java.time.LocalDateTime

data class StepDto(
    val id: Int,
    val name: String,
    val description: String?,
    val sequence: Int?,
    val createdAt: LocalDateTime,
    val modifiedAt: LocalDateTime
) {

    companion object {
        fun fromDomain(step: Step): StepDto {
            return StepDto(
                step.id,
                step.name,
                step.description,
                step.sequence,
                step.createdAt,
                step.modifiedAt
            )
        }
    }
}