package pl.szczeliniak.kitchenassistant.receipt.commands.factories

import pl.szczeliniak.kitchenassistant.receipt.*
import pl.szczeliniak.kitchenassistant.receipt.commands.dto.NewReceiptDto
import pl.szczeliniak.kitchenassistant.shared.ErrorCode
import pl.szczeliniak.kitchenassistant.shared.KitchenAssistantException
import pl.szczeliniak.kitchenassistant.user.queries.GetUserByIdQuery

open class ReceiptFactory(
    private val getUserByIdQuery: GetUserByIdQuery,
    private val stepFactory: StepFactory,
    private val categoryDao: CategoryDao,
    private val tagDao: TagDao,
    private val tagFactory: TagFactory,
    private val authorDao: AuthorDao,
    private val authorFactory: AuthorFactory,
    private val photoDao: PhotoDao,
    private val ingredientGroupFactory: IngredientGroupFactory
) {

    open fun create(dto: NewReceiptDto): Receipt {
        getUserByIdQuery.execute(dto.userId)
        return Receipt(0,
            userId = dto.userId,
            name = dto.name,
            author = dto.author?.let {
                authorDao.findByName(it, dto.userId) ?: authorDao.save(
                    authorFactory.create(
                        it,
                        dto.userId
                    )
                )
            },
            source = dto.source,
            category = dto.categoryId?.let {
                categoryDao.findById(it) ?: throw KitchenAssistantException(ErrorCode.CATEGORY_NOT_FOUND)
            },
            description = dto.description,
            ingredientGroups = dto.ingredientGroups.map { ingredientGroupFactory.create(it) }.toMutableSet(),
            steps = dto.steps.map { stepFactory.create(it) }.toMutableSet(),
            photos = dto.photos.map {
                photoDao.findById(it) ?: throw KitchenAssistantException(ErrorCode.PHOTO_NOT_FOUND)
            }
                .toMutableSet(),
            tags = dto.tags.map { tagDao.findByName(it, dto.userId) ?: tagFactory.create(it, dto.userId) }
                .toMutableSet()
        )
    }

}