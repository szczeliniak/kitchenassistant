package pl.szczeliniak.kitchenassistant.receipt.commands

import pl.szczeliniak.kitchenassistant.receipt.ReceiptDao
import pl.szczeliniak.kitchenassistant.receipt.commands.dto.NewReceiptDto
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.ReceiptFactory
import pl.szczeliniak.kitchenassistant.shared.dtos.SuccessResponse

class AddReceiptCommand(private val receiptDao: ReceiptDao, private val receiptFactory: ReceiptFactory) {

    fun execute(dto: NewReceiptDto): SuccessResponse {
        return SuccessResponse(receiptDao.save(receiptFactory.create(dto)).id)
    }

}