package pl.szczeliniak.kitchenassistant.receipt

import org.springframework.stereotype.Component
import pl.szczeliniak.kitchenassistant.receipt.persistance.TagRepository

@Component
class TagDaoImpl(
    private val tagRepository: TagRepository,
    private val tagMapper: TagMapper
) : TagDao {

    override fun save(tag: Tag): Tag {
        return tagMapper.toDomain(tagRepository.save(tagMapper.toEntity(tag)))
    }

    override fun saveAll(tags: List<Tag>) {
        tags.forEach { tagRepository.save(tagMapper.toEntity(it)) }
    }

    override fun findByName(name: String, userId: Int): Tag? {
        return tagRepository.findByName(name, userId)?.let { tagMapper.toDomain(it) }
    }

    override fun findAll(criteria: TagCriteria): List<Tag> {
        return tagRepository.findAll(TagRepository.SearchCriteria(criteria.name, criteria.userId))
            .map { tagMapper.toDomain(it) }
    }

}