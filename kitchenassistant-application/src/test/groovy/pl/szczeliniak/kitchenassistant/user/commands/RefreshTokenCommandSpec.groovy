package pl.szczeliniak.kitchenassistant.user.commands

import pl.szczeliniak.kitchenassistant.shared.RequestContext
import pl.szczeliniak.kitchenassistant.user.User
import pl.szczeliniak.kitchenassistant.user.UserDao
import pl.szczeliniak.kitchenassistant.user.commands.dto.RefreshTokenResponse
import pl.szczeliniak.kitchenassistant.user.commands.factories.TokenFactory
import spock.lang.Specification
import spock.lang.Subject

import java.time.ZoneId
import java.time.ZonedDateTime

class RefreshTokenCommandSpec extends Specification {

    def userDao = Mock(UserDao)
    def tokenFactory = Mock(TokenFactory)
    def requestContext = Mock(RequestContext)

    @Subject
    def refreshTokenCommand = new RefreshTokenCommand(tokenFactory, requestContext, userDao)

    def 'should refresh token'() {
        given:
        requestContext.userId() >> 1
        userDao.findById(1) >> user()
        tokenFactory.create(1) >> token()

        when:
        def result = refreshTokenCommand.execute()

        then:
        result == new RefreshTokenResponse("TOKEN", ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()))
    }

    private static TokenFactory.Token token() {
        return new TokenFactory.Token("TOKEN", ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()))
    }

    private static User user() {
        return new User(1, "", "", "", ZonedDateTime.now(), ZonedDateTime.now())
    }

}
