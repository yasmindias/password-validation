package passwordvalidation.service

import org.springframework.stereotype.Service

@Service
class ValidationService {
    private val validPasswordPattern =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()-+])([\\w\\d!@#\$%^&*()-+]{9,})\$".toRegex()

    private val repeatedCharacterPattern = "(?=^[\\w\\d!@#\$%^&*()-+]+\$)(.)+?.*\\1".toRegex()

    fun validate(password: String): Boolean {
        return if(repeatedCharacterPattern.containsMatchIn(password).not()){
            validPasswordPattern.containsMatchIn(password)
        } else {
            false
        }
    }
}