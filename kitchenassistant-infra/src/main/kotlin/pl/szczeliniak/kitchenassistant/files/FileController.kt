package pl.szczeliniak.kitchenassistant.files

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import pl.szczeliniak.kitchenassistant.files.commands.DeletePhotoFileCommand
import pl.szczeliniak.kitchenassistant.files.commands.UploadPhotoFileCommand
import pl.szczeliniak.kitchenassistant.files.commands.dto.AddPhotoFileResponse
import pl.szczeliniak.kitchenassistant.files.queries.DownloadPhotoFileCommand

@RestController
@RequestMapping("/files")
class FileController(
    private val uploadPhotoFileCommand: UploadPhotoFileCommand,
    private val downloadPhotoFileCommand: DownloadPhotoFileCommand,
    private val deletePhotoFileCommand: DeletePhotoFileCommand
) {

    @PostMapping("/photos")
    fun uploadPhoto(@RequestParam("photo") file: MultipartFile): AddPhotoFileResponse {
        return uploadPhotoFileCommand.execute(file.originalFilename ?: file.name, file.bytes)
    }

    @GetMapping("/photos/{name}")
    fun downloadPhoto(@PathVariable name: String): ResponseEntity<ByteArray> {
        val photoResponse = downloadPhotoFileCommand.execute(name)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(photoResponse.mediaType.mimeType))
            .body(photoResponse.body)
    }

    @DeleteMapping("/photos/{name}")
    fun deletePhoto(@PathVariable name: String): String {
        deletePhotoFileCommand.execute(name)
        return ""
    }

}