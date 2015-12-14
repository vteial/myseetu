package io.wybis.seetu.service

import javax.servlet.http.HttpSession

public interface SessionService {

    static String SESSION_USER_KEY = 'user'

    Map<String, Object> properties(HttpSession session)

}
