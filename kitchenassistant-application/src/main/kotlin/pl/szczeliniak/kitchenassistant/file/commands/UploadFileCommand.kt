package pl.szczeliniak.kitchenassistant.file.commands

import pl.szczeliniak.kitchenassistant.common.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.file.FtpClient
import pl.szczeliniak.kitchenassistant.receipt.FileDao
import pl.szczeliniak.kitchenassistant.receipt.commands.factories.FileFactory

class UploadFileCommand(
    private val ftpClient: FtpClient,
    private val fileDao: FileDao,
    private val fileFactory: FileFactory
) {

    fun execute(name: String, content: ByteArray): SuccessResponse {
        val file = fileFactory.create(ftpClient.upload(name, content))
        return SuccessResponse(fileDao.save(file).id)
    }

}