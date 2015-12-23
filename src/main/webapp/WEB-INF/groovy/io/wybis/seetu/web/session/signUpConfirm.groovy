package io.wybis.seetu.web.session

import io.wybis.seetu.model.constants.UserStatus
import io.wybis.seetu.model.User

String path = '/index.html#/message?'
if(params.userId == null || params.userToken == null ) {
    path += 'errorMessage=Missing parameters. Please use the correct confirmation link.'
}
else {
    long userId = params.userId as Long
    User auser = User.get(userId)
    String userToken = params.userToken

    if(auser != null && auser.token == userToken) {
        auser.token = null
        auser.status = UserStatus.ACTIVE
        auser.save()
        path += "successMessage=Your account has been confirmed. Please sign in to proceed...";
    }
    else {
        path += "errorMessage=Invalid id or token. Please use the correct confirmation link.";
    }
}
//console.println path

redirect path
