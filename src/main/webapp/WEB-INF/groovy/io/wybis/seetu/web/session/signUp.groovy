package io.wybis.seetu.web.session

import io.wybis.seetu.dto.ResponseDto
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.model.User
import io.wybis.seetu.service.exceptions.ModelAlreadyExistException
import io.wybis.seetu.util.Helper

ResponseDto responseDto = new ResponseDto(type: 0, message: 'Successfully signed up...')
request.responseDto = responseDto

User auser = jsonCategory.parseJson(request, User.class)
try {
    if(auser.userId) {
        auser.userId = auser.userId.toLowerCase()
    }
    auser.emailId = auser.userId
    SessionDto sessionUserDto = new SessionDto()

    userService.add(sessionUserDto, auser)

    String confirmUrl = Helper.getDomainPrefix(request, app)
    confirmUrl = "${confirmUrl}/sessions/sign-up-confirm?userId=${auser.id}&userToken=${auser.token}"
    responseDto.data = confirmUrl

    try {
        String mailContent = """
Hi ${auser.firstName},

    Thanks for sign up. Please use this link ${confirmUrl} to confirm your email account.

Thanks and Regards,
App Admin,
seetu.appspot.com
"""
        //console.println(mailContent)
        mail.send(from: 'vteial@gmail.com',
                to: auser.emailId,
                subject: "MySeetu account confirmation",
                textBody: mailContent)
    }
    catch (Throwable t) {
        t.printStackTrace()
    }

    responseDto.message = 'Successfully signed up... An email has been sent to your email id, Please confirm your email id using the link in the email.'
}
catch (ModelAlreadyExistException e) {
    responseDto.type = ResponseDto.ERROR
    responseDto.message = 'User Id or Email Id already exists...'
}
catch (Throwable t) {
    responseDto.type = ResponseDto.UNKNOWN
    responseDto.message = 'Sign Up failed...';
    responseDto.data = Helper.getStackTraceAsString(t)
    log.warning(responseDto.message)
    //t.printStackTrace()
}

jsonCategory.respondWithJson(response, responseDto)