package io.wybis.seetu.service.impl

import groovyx.gaelyk.GaelykBindings
import groovyx.gaelyk.logging.GroovyLogger
import io.wybis.seetu.service.SessionService

import javax.servlet.http.HttpSession

@GaelykBindings
public class DefaultSessionService extends AbstractService implements
        SessionService {

    GroovyLogger log = new GroovyLogger(DefaultSessionService.class.getName())

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
}
