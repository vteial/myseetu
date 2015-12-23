package io.wybis.seetu.service

import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.dto.UserDto
import io.wybis.seetu.service.exceptions.InvalidCredentialException
import io.wybis.seetu.service.exceptions.ModelNotFoundException

import javax.servlet.http.HttpSession

public interface SessionService {

    static String SESSION_USER_KEY = 'user'

    static String SESSION_LOGIN_REDIRECT_KEY = 'loginRedirectKey'

    Map<String, Object> properties(HttpSession session)

    void resetPassword(String userId) throws ModelNotFoundException

    SessionDto login(HttpSession session, UserDto userDto)
            throws InvalidCredentialException

    void logout(HttpSession session)

    void changeDetails(SessionDto sessionUser, UserDto userDto)
            throws ModelNotFoundException

    void changePassword(SessionDto sessionUser, UserDto userDto)
            throws ModelNotFoundException, InvalidCredentialException

}
