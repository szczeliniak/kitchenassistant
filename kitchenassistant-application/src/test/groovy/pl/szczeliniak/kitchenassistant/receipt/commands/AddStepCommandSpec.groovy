package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.receipt.*
import pl.szczeliniak.kitchenassistant.receipt.commands.dto.NewStepDto
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.StepFactory
import pl.szczeliniak.kitchenassistant.shared.dtos.SuccessResponse
import spock.lang.Specification
import spock.lang.Subject

import java.time.ZonedDateTime

class AddStepCommandSpec extends Specification {
    def receiptDao = Mock(ReceiptDao)
    def stepDao = Mock(StepDao)
    def stepFactory = Mock(StepFactory)
    @Subject
    def addStepCommand = new AddStepCommand(receiptDao, stepDao, stepFactory)

    def 'should save step'() {
        given:
        def receipt = receipt()
        def dto = newStepDto()
        def step = step()
        receiptDao.findById(1) >> receipt
        stepFactory.create(dto) >> step
        stepDao.save(step) >> step
        receiptDao.save(receipt) >> receipt

        when:
        def result = addStepCommand.execute(1, dto)

        then:
        result == new SuccessResponse(2)
        receipt.steps == Set.of(step)
    }

    private static NewStepDto newStepDto() {
        return new NewStepDto("", "", 0)
    }

    private static Step step() {
        return new Step(2, "", "", 0, false, ZonedDateTime.now(), ZonedDateTime.now())
    }

    private static Receipt receipt() {
        return new Receipt(1, "", 0, "", new Author(2, "", 1, ZonedDateTime.now(), ZonedDateTime.now()), "", false, null, Collections.emptySet(), new HashSet<Step>(), Collections.emptySet(), Collections.emptySet(), false, ZonedDateTime.now(), ZonedDateTime.now())
    }

}
