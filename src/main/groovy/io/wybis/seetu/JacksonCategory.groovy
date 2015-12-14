package io.wybis.seetu

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.CollectionType
import io.wybis.seetu.util.Helper

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JacksonCategory extends Helper {

    static ObjectMapper jsonObjectMapper = new ObjectMapper()

    static Object parseJson(HttpServletRequest request, Class clazz) {
        return jsonObjectMapper.readValue(request.reader.text, clazz);
    }

    static Object parseJson(HttpServletRequest request, Class clazz, Class subClazz) {
        final CollectionType finalCalzz = jsonObjectMapper.getTypeFactory().constructCollectionType(clazz, subClazz);
        return jsonObjectMapper.readValue(request.reader.text, finalCalzz);
    }

    static respondWithJson(HttpServletResponse response, def object) {
        response.contentType = 'application/json'
        jsonObjectMapper.writeValue(response.getWriter(), object)
    }
}
