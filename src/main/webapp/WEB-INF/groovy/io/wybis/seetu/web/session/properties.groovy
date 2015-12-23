package io.wybis.seetu.web.session

import io.wybis.seetu.dto.ResponseDto
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.service.SessionService

ResponseDto responseDto = request.responseDto

def props = sessionService.properties(session), model = [:]

if (responseDto) {
    responseDto.data = props
} else {
    responseDto = new ResponseDto(data: props)
}

SessionDto sessionDto = session[SessionService.SESSION_USER_KEY]

if (sessionDto) {

    model['events'] = sessionService.events(sessionDto)

    responseDto.model = model;
}

jsonCategory.respondWithJson(response, responseDto)

