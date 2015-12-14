package io.wybis.seetu.util

import javax.servlet.http.HttpServletRequest

class Helper {

    static String getDomainPrefix(def request, def app) {
        String appEnvName = app.env.name

        String s = 'http://'
        if (appEnvName.equals('Production')) {
            s += app.id + '.appspot.com'
        } else {
            s += request.localAddr + ':' + request.localPort
        }

        return s
    }

    static String getStackTraceAsString(Throwable t) {
        StringWriter sw = new StringWriter()
        PrintWriter pw = new PrintWriter(sw)
        t.printStackTrace(pw)
        return sw.toString()
    }

    static void printRequestDetails(HttpServletRequest request) {
        Enumeration<String> keys = request.getParameterNames()
        while(keys.hasMoreElements()) {
            String key = keys.nextElement()
            Object val = request.getParameter(key)
            System.out.println("$key = $val")
        }
    }
}
