package pl.szczeliniak.kitchenassistant.receipt.queries

import pl.szczeliniak.kitchenassistant.receipt.Author
import pl.szczeliniak.kitchenassistant.receipt.Receipt
import pl.szczeliniak.kitchenassistant.receipt.ReceiptCriteria
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import pl.szczeliniak.kitchenassistant.receipt.queries.dto.ReceiptDto
import pl.szczeliniak.kitchenassistant.receipt.queries.dto.ReceiptsResponse
import pl.szczeliniak.kitchenassistant.shared.dtos.Pagination
import spock.lang.Specification
import spock.lang.Subject

import java.time.ZonedDateTime

class GetReceiptsQuerySpec extends Specification {

    private ReceiptDao receiptDao = Mock(ReceiptDao)
    private receiptConverter = Mock(ReceiptConverter)

    @Subject
    private GetReceiptsQuery getReceiptsQuery = new GetReceiptsQuery(receiptDao, receiptConverter)

    def "should return receipt"() {
        given:
        def criteria = new ReceiptCriteria(1, 1, '', '')
        def receipt = receipt()
        def receiptDto = receiptDto()
        receiptDao.findAll(criteria, 40, 10) >> Collections.singletonList(receipt)
        receiptDao.count(criteria) >> 413
        receiptConverter.map(receipt) >> receiptDto

        when:
        def result = getReceiptsQuery.execute(5, 10, criteria)

        then:
        result == new ReceiptsResponse(Collections.singletonList(receiptDto),
                new Pagination(5, 10, 42))
    }

    private static Receipt receipt() {
        return new Receipt(1,
                '',
                0,
                '',
                new Author(2, "", 1, ZonedDateTime.now(), ZonedDateTime.now()),
                '',
                false,
                null,
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet(),
                Collections.emptySet(),
                false,
                ZonedDateTime.now(),
                ZonedDateTime.now())
    }

    private static ReceiptDto receiptDto() {
        return new ReceiptDto(1, '', "", false, null, Collections.emptySet())
    }

}
