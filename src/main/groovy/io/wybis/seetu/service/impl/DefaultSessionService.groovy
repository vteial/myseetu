package io.wybis.seetu.service.impl

import groovyx.gaelyk.GaelykBindings
import groovyx.gaelyk.logging.GroovyLogger
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.dto.UserDto
import io.wybis.seetu.model.User
import io.wybis.seetu.model.constants.UserStatus
import io.wybis.seetu.service.SessionService
import io.wybis.seetu.service.UserService
import io.wybis.seetu.service.exceptions.InvalidCredentialException
import io.wybis.seetu.service.exceptions.ModelNotFoundException
import io.wybis.seetu.service.exceptions.PasswordNotMatchException
import io.wybis.seetu.service.exceptions.UnAuthorizedException

import javax.servlet.http.HttpSession

@GaelykBindings
public class DefaultSessionService extends AbstractService implements
        SessionService {

    GroovyLogger log = new GroovyLogger(DefaultSessionService.class.getName())

    UserService userService

    Map<String, Object> app = [:]

    com.google.appengine.api.users.UserService appUserService

    @Override
    public Map<String, Object> properties(HttpSession session) {
        def props = this.app.clone()

        props.localMode = localMode
        props.sessionDto = session.getAttribute(SESSION_USER_KEY)
        props.sessionId = session.id
        props.applicationUser = user

        return props;
    }

    @Override
    public void resetPassword(String userId) throws ModelNotFoundException {
    }

    public SessionDto login(HttpSession session, UserDto userDto)
            throws InvalidCredentialException {
        SessionDto sessionDto = null

        def entitys = datastore.execute {
            from User.class.simpleName
            where userId == userDto.userId
            limit 1
        }

        if (entitys.size() == 0) {
            throw new InvalidCredentialException()
        }
        User aUser = entitys[0] as User

        if (aUser.status == UserStatus.PENDING || aUser.status == UserStatus.PASSIVE) {
            throw new UnAuthorizedException()
        }

        if (!localMode && aUser.password != userDto.password) {
            throw new InvalidCredentialException()
        }

        sessionDto = new SessionDto()
        sessionDto.with {
            id = aUser.id
            userId = aUser.userId
            emailId = aUser.emailId
            firstName = aUser.firstName
            lastName = aUser.lastName
            type = aUser.type
            roleId = aUser.roleId
        }

        session.setAttribute(SESSION_USER_KEY, sessionDto)

        return sessionDto
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute(SESSION_USER_KEY)
    }

    @Override
    public void changeDetails(SessionDto sessionUser, UserDto userDto)
            throws ModelNotFoundException {
        User euser = User.get(sessionUser.id)

        euser.firstName = userDto.firstName
        euser.lastName = userDto.lastName

        euser.preUpdate(sessionUser.id)
        euser.save()

        sessionUser.firstName = euser.firstName
        sessionUser.lastName = euser.lastName
    }

    @Override
    public void changePassword(SessionDto sessionUser, UserDto userDto)
            throws ModelNotFoundException, InvalidCredentialException {
        User euser = User.get(sessionUser.id)

        if (euser.password != userDto.currentPassword) {
            throw new InvalidCredentialException()
        }

        if (userDto.newPassword != userDto.retypeNewPassword) {
            throw new PasswordNotMatchException()
        }

        euser.password = userDto.newPassword

        euser.preUpdate(sessionUser.id)
        euser.save()

    }
}
