package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.enums.IngredientUnit
import pl.szczeliniak.kitchenassistant.exceptions.NotAllowedOperationException
import pl.szczeliniak.kitchenassistant.exceptions.NotFoundException
import pl.szczeliniak.kitchenassistant.receipt.Ingredient
import pl.szczeliniak.kitchenassistant.receipt.Receipt
import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class DeleteIngredientCommandSpec extends Specification {

    def receiptDao = Mock(ReceiptDao)
    @Subject
    def deleteIngredientCommand = new DeleteIngredientCommand(receiptDao)

    def 'should delete ingredient'() {
        given:
        def ingredient = ingredient(false)
        def receipt = receipt(Collections.singletonList(ingredient))
        receiptDao.findById(1) >> receipt
        receiptDao.save(receipt) >> receipt

        when:
        def result = deleteIngredientCommand.execute(1, 3)

        then:
        ingredient.deleted
        result == new SuccessResponse()
    }

    def 'should throw exception when ingredient not found'() {
        given:
        def receipt = receipt(Collections.emptyList())
        receiptDao.findById(1) >> receipt

        when:
        deleteIngredientCommand.execute(1, 3)

        then:
        def e = thrown(NotFoundException)
        e.message == "Ingredient not found"
    }

    def 'should throw exception when ingredient is already marked as deleted'() {
        given:
        def ingredient = ingredient(true)
        def receipt = receipt(Collections.singletonList(ingredient))
        receiptDao.findById(1) >> receipt

        when:
        deleteIngredientCommand.execute(1, 3)

        then:
        def e = thrown(NotAllowedOperationException)
        e.message == "Ingredient is already marked as deleted!"
    }

    private static Receipt receipt(List<Ingredient> ingredients) {
        return new Receipt(1, 2, '', '', '', '', ingredients, Collections.emptyList(), false, LocalDateTime.now(), LocalDateTime.now())
    }

    private static Ingredient ingredient(boolean deleted) {
        return new Ingredient(3, '', '', IngredientUnit.CUPS, deleted, LocalDateTime.now(), LocalDateTime.now())
    }

}