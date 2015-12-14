package io.wybis.seetu.service;

import io.wybis.seetu.dto.SessionDto

interface AutoNumberService {

    long getNextNumber(SessionDto sessionUser, String key)
}
