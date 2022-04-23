package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.common.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.exceptions.NotAllowedOperationException
import pl.szczeliniak.kitchenassistant.exceptions.NotFoundException
import pl.szczeliniak.kitchenassistant.receipt.File
import pl.szczeliniak.kitchenassistant.receipt.Receipt
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class DivestFileFromReceiptCommandSpec extends Specification {

    def receiptDao = Mock(ReceiptDao)
    @Subject
    def deletePhotoCommand = new DivestPhotoFromReceiptCommand(receiptDao)

    def 'should delete photo'() {
        given:
        def photo = photo(false)
        def receipt = receipt(Collections.singletonList(photo))
        receiptDao.findById(1) >> receipt
        receiptDao.save(receipt) >> receipt

        when:
        def result = deletePhotoCommand.execute(1, "NAME")

        then:
        photo.deleted
        result == new SuccessResponse(1)
    }

    def 'should throw exception when photo not found'() {
        given:
        def receipt = receipt(Collections.emptyList())
        receiptDao.findById(1) >> receipt

        when:
        deletePhotoCommand.execute(1, "NAME")

        then:
        def e = thrown(NotFoundException)
        e.message == "Photo not found"
    }

    def 'should throw exception when photo is already marked as deleted'() {
        given:
        def photo = photo(true)
        def receipt = receipt(Collections.singletonList(photo))
        receiptDao.findById(1) >> receipt

        when:
        deletePhotoCommand.execute(1, "NAME")

        then:
        def e = thrown(NotAllowedOperationException)
        e.message == "File is already marked as deleted!"
    }

    private static Receipt receipt(List<File> photos) {
        return new Receipt(1, 2, '', '', '', '', null, Collections.emptyList(), Collections.emptyList(), photos, Collections.emptyList(), false, LocalDateTime.now(), LocalDateTime.now())
    }

    private static File photo(boolean deleted) {
        return new File(0, "NAME", 4, deleted, LocalDateTime.now(), LocalDateTime.now())
    }

}
