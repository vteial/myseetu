package io.wybis.seetu.service;

import io.wybis.seetu.dto.SessionDto
import io.wybis.seetu.model.User
import io.wybis.seetu.service.exceptions.ModelAlreadyExistException

interface UserService {

    User findByUserId(String userId)

    void add(SessionDto sessionUser, User user) throws ModelAlreadyExistException
}
