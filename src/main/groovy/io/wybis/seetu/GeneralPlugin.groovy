package io.wybis.seetu

import groovyx.gaelyk.plugins.PluginBaseScript
import io.wybis.seetu.service.impl.DefaultAutoNumberService
import io.wybis.seetu.service.impl.DefaultSessionService
import io.wybis.seetu.service.impl.DefaultUserService

class GeneralPlugin extends PluginBaseScript {

    @Override
    public Object run() {
        log.info "Registering GeneralPlugin started..."

        DefaultAutoNumberService anS = new DefaultAutoNumberService()

        DefaultUserService usrS = new DefaultUserService()
        usrS.autoNumberService = anS

        DefaultSessionService sesS = new DefaultSessionService()
        sesS.autoNumberService = anS
        sesS.userService = usrS
        sesS.appUserService = users

        binding {
            console = System.out
            jsonCategory = JacksonCategory
            jsonObjectMapper = JacksonCategory.jsonObjectMapper
            autoNumberService = anS
            userService = usrS
            sessionService = sesS
        }

        routes {
        }

        before {
            //log.info "Visiting ${request.requestURI}"
            //binding.uri = request.requestURI
        }

        after {
            //log.info "Exiting ${request.requestURI}"
            //log.info "groovlet returned $result from its execution"
        }

        log.info "Registering GeneralPlugin finished..."
    }
}
