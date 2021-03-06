package pl.szczeliniak.kitchenassistant.user.commands.factories

import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import pl.szczeliniak.kitchenassistant.JunitBaseClass
import pl.szczeliniak.kitchenassistant.user.PasswordEncoder
import pl.szczeliniak.kitchenassistant.user.User
import pl.szczeliniak.kitchenassistant.user.commands.dto.AddUserDto

internal class UserFactoryTest : JunitBaseClass() {

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder

    @InjectMocks
    private lateinit var userFactory: UserFactory

    @Test
    fun shouldCreateUser() {
        whenever(passwordEncoder.encode("PASS")).thenReturn("ENC_PASS")

        val user = userFactory.create(dto())

        assertThat(user).usingRecursiveComparison()
            .ignoringFields("createdAt", "modifiedAt")
            .isEqualTo(user())
    }

    private fun user(): User {
        return User(0, "EMAIL", "ENC_PASS", "NAME")
    }

    private fun dto(): AddUserDto {
        return AddUserDto("EMAIL", "PASS", "NAME")
    }

}