package io.wybis.seetu.web.session

import io.wybis.seetu.dto.ResponseDto
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.dto.UserDto
import io.wybis.seetu.service.SessionService
import io.wybis.seetu.service.exceptions.InvalidCredentialException
import io.wybis.seetu.service.exceptions.UnAuthorizedException
import io.wybis.seetu.util.Helper

ResponseDto responseDto = new ResponseDto(type: 0, message: 'Successfully signed in...')
request.responseDto = responseDto

UserDto userDto = jsonCategory.parseJson(request, UserDto.class)
try {

    SessionDto sessionUserDto = sessionService.login(session, userDto)
    //responseDto.data = sessionService.properties(session)
    String loginResponseRedirectURI = session[SessionService.SESSION_LOGIN_REDIRECT_KEY]
    if(loginResponseRedirectURI) {
        loginResponseRedirectURI = loginResponseRedirectURI.substring(10)
        responseDto.data = loginResponseRedirectURI
    }
}
catch (InvalidCredentialException e) {
    responseDto.type = ResponseDto.ERROR
    responseDto.message = 'Invalid User Id or Password...'
}
catch (UnAuthorizedException e) {
    responseDto.type = ResponseDto.ERROR
    responseDto.message = 'Invalid access. Your account may be disabled or not confirmed.'
}
catch (Throwable t) {
    responseDto.type = ResponseDto.UNKNOWN
    responseDto.message = 'Sign In failed...';
    responseDto.data = Helper.getStackTraceAsString(t)
    log.warning(responseDto.message)
    //t.printStackTrace()
}

jsonCategory.respondWithJson(response, responseDto)
