package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.common.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.exceptions.NotAllowedOperationException
import pl.szczeliniak.kitchenassistant.exceptions.NotFoundException
import pl.szczeliniak.kitchenassistant.receipt.Receipt
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import pl.szczeliniak.kitchenassistant.receipt.Step
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class DeleteStepCommandSpec extends Specification {

    def receiptDao = Mock(ReceiptDao)
    @Subject
    def deleteStepCommand = new DeleteStepCommand(receiptDao)

    def 'should delete step'() {
        given:
        def step = step(false)
        def receipt = receipt(Set.of(step))
        receiptDao.findById(1) >> receipt
        receiptDao.save(receipt) >> receipt

        when:
        def result = deleteStepCommand.execute(1, 3)

        then:
        step.deleted
        result == new SuccessResponse(3)
    }

    def 'should throw exception when step not found'() {
        given:
        def receipt = receipt(Collections.emptySet())
        receiptDao.findById(1) >> receipt

        when:
        deleteStepCommand.execute(1, 3)

        then:
        def e = thrown(NotFoundException)
        e.message == "Step not found"
    }

    def 'should throw exception when step is already marked as deleted'() {
        given:
        def step = step(true)
        def receipt = receipt(Set.of(step))
        receiptDao.findById(1) >> receipt

        when:
        deleteStepCommand.execute(1, 3)

        then:
        def e = thrown(NotAllowedOperationException)
        e.message == "Step is already marked as deleted!"
    }

    private static Receipt receipt(Set<Step> steps) {
        return new Receipt(1, 2, '', '', '', '', null, Collections.emptySet(), steps, Collections.emptySet(), Collections.emptySet(), false, LocalDateTime.now(), LocalDateTime.now())
    }

    private static Step step(boolean deleted) {
        return new Step(3, '', '', 0, Collections.emptySet(), deleted, LocalDateTime.now(), LocalDateTime.now())
    }

}
