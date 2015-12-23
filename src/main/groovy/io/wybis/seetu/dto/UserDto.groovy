package io.wybis.seetu.dto

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
@ToString(includeNames = true)
class UserDto implements Serializable {

    long id

    String userId

    String password

    String currentPassword

    String newPassword

    String retypeNewPassword

    String firstName

    String lastName
}
