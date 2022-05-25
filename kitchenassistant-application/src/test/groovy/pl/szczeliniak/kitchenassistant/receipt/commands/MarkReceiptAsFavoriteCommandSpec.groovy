package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.receipt.Receipt
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import pl.szczeliniak.kitchenassistant.shared.dtos.SuccessResponse
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class MarkReceiptAsFavoriteCommandSpec extends Specification {

    def receiptDao = Mock(ReceiptDao)

    @Subject
    def markReceiptAsFavoriteCommand = new MarkReceiptAsFavoriteCommand(receiptDao)

    def 'should mark receipt as favorite'() {
        given:
        def receipt = receipt()
        receiptDao.findById(1) >> receipt
        receiptDao.save(receipt) >> receipt

        when:
        def result = markReceiptAsFavoriteCommand.execute(1, true)

        then:
        receipt.favorite
        result == new SuccessResponse(1)
    }

    private static Receipt receipt() {
        return new Receipt(1, 2, '', '', '', '', false, null, Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), false, LocalDateTime.now(), LocalDateTime.now())
    }

}