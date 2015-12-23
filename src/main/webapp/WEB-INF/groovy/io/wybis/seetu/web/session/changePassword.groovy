package io.wybis.seetu.web.session

import io.wybis.seetu.dto.ResponseDto
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.dto.UserDto
import io.wybis.seetu.service.SessionService
import io.wybis.seetu.service.exceptions.InvalidCredentialException
import io.wybis.seetu.service.exceptions.PasswordNotMatchException
import io.wybis.seetu.util.Helper

ResponseDto responseDto = new ResponseDto(type: 0, message: 'Successfully saved...')

SessionDto sessionDto = session[SessionService.SESSION_USER_KEY]

UserDto userDto = jsonCategory.parseJson(request, UserDto.class)
try {
    sessionService.changePassword(sessionDto, userDto)
}
catch (InvalidCredentialException e) {
    responseDto.type = ResponseDto.ERROR
    responseDto.message = 'Invalid current password...'
}
catch (PasswordNotMatchException e) {
    responseDto.type = ResponseDto.ERROR
    responseDto.message = 'New password and retype new password is not matched...'
}
catch (Throwable t) {
    responseDto.type = ResponseDto.UNKNOWN
    responseDto.message = 'Saving password failed...';
    responseDto.data = Helper.getStackTraceAsString(t)
    log.warning(responseDto.message)
    //t.printStackTrace()
}

jsonCategory.respondWithJson(response, responseDto)