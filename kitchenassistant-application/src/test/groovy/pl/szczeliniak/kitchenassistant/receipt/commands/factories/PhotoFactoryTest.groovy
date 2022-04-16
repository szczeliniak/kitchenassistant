package pl.szczeliniak.kitchenassistant.receipt.commands.factories

import org.assertj.core.api.Assertions
import pl.szczeliniak.kitchenassistant.receipt.Photo
import spock.lang.Specification
import spock.lang.Subject

import java.time.LocalDateTime

class PhotoFactoryTest extends Specification {

    @Subject
    def photoFactory = new PhotoFactory()

    def 'should create photo'() {

        when:
        def result = photoFactory.create("PHOTO_NAME")

        then:
        Assertions.assertThat(result).usingRecursiveComparison()
                .ignoringFields("createdAt_", "modifiedAt_")
                .isEqualTo(photo())
    }

    private static Photo photo() {
        return new Photo(0, "PHOTO_NAME", false, LocalDateTime.now(), LocalDateTime.now())
    }

}
