package io.wybis.seetu.service.impl

import groovyx.gaelyk.GaelykBindings
import groovyx.gaelyk.logging.GroovyLogger
import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.model.User
import io.wybis.seetu.model.constants.UserStatus
import io.wybis.seetu.service.UserService
import io.wybis.seetu.service.exceptions.ModelAlreadyExistException

@GaelykBindings
class DefaultUserService extends AbstractService implements UserService {

    GroovyLogger log = new GroovyLogger(DefaultUserService.class.getName())

    @Override
    User findByUserId(String iuserId) {
        User user = null

        def entitys = datastore.execute {
            from User.class.simpleName
            where userId == iuserId
        }

        if (entitys.size() > 0) {
            user = entitys[0] as User
        }

        return user
    }

    @Override
    public void add(SessionDto sessionUser, User model)
            throws ModelAlreadyExistException {

        if (this.findByUserId(model.userId)) {
            throw new ModelAlreadyExistException()
        }

        if (model.password == null) {
            model.password = 'wybis123'
        }
        model.token = UUID.randomUUID().toString()
        if(!model.status) {
            model.status = UserStatus.PENDING
        } else {
            model.status = UserStatus.ACTIVE
        }

        model.id = autoNumberService.getNextNumber(sessionUser, User.ID_KEY)

        model.prePersist(sessionUser.id)
        model.save()
    }
}
