package pl.szczeliniak.kitchenassistant.receipt.queries.dto

import pl.szczeliniak.kitchenassistant.receipt.Step

data class StepDto(
    val id: Int,
    val name: String,
    val description: String?,
    val sequence: Int?
) {

    companion object {
        fun fromDomain(step: Step): StepDto {
            return StepDto(
                step.id,
                step.name,
                step.description,
                step.sequence
            )
        }
    }
}