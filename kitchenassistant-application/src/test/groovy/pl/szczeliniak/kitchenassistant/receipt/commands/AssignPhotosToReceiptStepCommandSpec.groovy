package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.common.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.receipt.*
import pl.szczeliniak.kitchenassistant.receipt.commands.dto.AssignPhotosToReceiptStepDto
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class AssignPhotosToReceiptStepCommandSpec extends Specification {

    def receiptDao = Mock(ReceiptDao)
    def fileDao = Mock(FileDao)
    def stepDao = Mock(StepDao)

    @Subject
    def assignPhotosToReceiptStepCommand = new AssignPhotosToReceiptStepCommand(receiptDao, stepDao, fileDao)

    def 'should add photo to receipt'() {
        given:
        def photo = photo()
        def step = step(new ArrayList())
        def receipt = receipt(Collections.singletonList(step))
        receiptDao.findById(1) >> receipt
        fileDao.findById(99) >> photo
        fileDao.save(photo) >> photo
        stepDao.save(step) >> step

        when:
        def result = assignPhotosToReceiptStepCommand.execute(1, 2, assignPhotosToReceiptStepDto())

        then:
        result == new SuccessResponse(2)
        step.photos.size() == 1
        step.photos.contains(photo)
    }

    def 'should not add photo to receipt when photo already added'() {
        given:
        def photo = photo()
        def step = step(Collections.singletonList(photo))
        def receipt = receipt(Collections.singletonList(step))
        receiptDao.findById(1) >> receipt
        stepDao.save(step) >> step

        when:
        def result = assignPhotosToReceiptStepCommand.execute(1, 2, assignPhotosToReceiptStepDto())

        then:
        result == new SuccessResponse(2)
        step.photos.size() == 1
        step.photos.contains(photo)
    }

    private static AssignPhotosToReceiptStepDto assignPhotosToReceiptStepDto() {
        return new AssignPhotosToReceiptStepDto(Collections.singletonList(99))
    }

    private static Receipt receipt(List<Step> steps) {
        return new Receipt(1, 0, "", "", "", "",
                new Category(0, "", 0, false, LocalDateTime.now(), LocalDateTime.now()),
                Collections.emptyList(), steps, Collections.emptyList(), Collections.emptyList(), false, LocalDateTime.now(), LocalDateTime.now())
    }

    private static Step step(List<File> photos) {
        return new Step(2, "", "", 0, photos, false, LocalDateTime.now(), LocalDateTime.now())
    }

    private static File photo() {
        return new File(99, "NAME", 4, false, LocalDateTime.now(), LocalDateTime.now())
    }
}
