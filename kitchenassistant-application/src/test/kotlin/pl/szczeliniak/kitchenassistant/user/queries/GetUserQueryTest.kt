package pl.szczeliniak.kitchenassistant.user.queries

import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import pl.szczeliniak.kitchenassistant.JunitBaseClass
import pl.szczeliniak.kitchenassistant.user.User
import pl.szczeliniak.kitchenassistant.user.UserDao
import pl.szczeliniak.kitchenassistant.user.queries.dto.UserDto
import pl.szczeliniak.kitchenassistant.user.queries.dto.UserResponse

internal class GetUserQueryTest : JunitBaseClass() {

    @Mock
    private lateinit var userDao: UserDao

    @InjectMocks
    private lateinit var getUserQuery: GetUserQuery

    @Test
    fun shouldReturnUser() {
        val user = user()
        whenever(userDao.findById(1)).thenReturn(user)

        val result = getUserQuery.execute(1)

        assertThat(result).isEqualTo(UserResponse(userDto()))
    }

    private fun userDto(): UserDto {
        return UserDto(0, "EMAIL", "NAME")
    }

    private fun user(): User {
        return User(email_ = "EMAIL", password_ = "", name_ = "NAME")
    }

}