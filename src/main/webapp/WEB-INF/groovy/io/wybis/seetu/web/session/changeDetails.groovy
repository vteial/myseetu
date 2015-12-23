package io.wybis.seetu.web.session

import io.wybis.seetu.dto.ResponseDto
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.dto.UserDto
import io.wybis.seetu.service.SessionService
import io.wybis.seetu.util.Helper

ResponseDto responseDto = new ResponseDto(type: 0, message: 'Successfully saved...')

SessionDto sessionDto = session[SessionService.SESSION_USER_KEY]

UserDto userDto = jsonCategory.parseJson(request, UserDto.class)
try {
    sessionService.changeDetails(sessionDto, userDto)
}
catch (Throwable t) {
    responseDto.type = ResponseDto.UNKNOWN
    responseDto.message = 'Saving user details failed...';
    responseDto.data = Helper.getStackTraceAsString(t)
    log.warning(responseDto.message)
    //t.printStackTrace()
}

jsonCategory.respondWithJson(response, responseDto)