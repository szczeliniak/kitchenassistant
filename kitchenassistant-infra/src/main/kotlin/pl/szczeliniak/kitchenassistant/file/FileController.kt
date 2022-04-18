package pl.szczeliniak.kitchenassistant.file

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.szczeliniak.kitchenassistant.common.dto.SuccessResponse
import pl.szczeliniak.kitchenassistant.file.commands.DeleteFileCommand
import pl.szczeliniak.kitchenassistant.file.commands.UploadFileCommand
import pl.szczeliniak.kitchenassistant.file.queries.DownloadFileCommand

@RestController
@RequestMapping("/files")
@Validated
class FileController(
    private val uploadFileCommand: UploadFileCommand,
    private val downloadFileCommand: DownloadFileCommand,
    private val deleteFileCommand: DeleteFileCommand
) {

    @PostMapping
    fun uploadFile(@RequestParam("file") file: MultipartFile): SuccessResponse {
        return uploadFileCommand.execute(file.originalFilename ?: file.name, file.bytes)
    }

    @GetMapping("/{id}")
    fun downloadFile(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val response = downloadFileCommand.execute(id)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(response.mediaType.mimeType))
            .body(response.body)
    }

    @DeleteMapping("/{id}")
    fun deleteFile(@PathVariable id: Int): SuccessResponse {
        return deleteFileCommand.execute(id)
    }

}