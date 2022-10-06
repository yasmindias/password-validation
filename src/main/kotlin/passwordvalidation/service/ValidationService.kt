package passwordvalidation.service

import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class ValidationService {
    private val PATTERN = Pattern.compile(
        "^" +
                "(?=.*[a-z])" + //no mínimo uma letra minuscula
                "(?=.*[A-Z])" + //no mínimo uma letra maiuscula
                "(?=.*\\d)" + //no mínimo um dígito
                "(?=.*[!@#\$%^&*()-+])" +  //no mínimo um caracter especial
                "([A-Za-z\\d!@#\$%^&*()-+]{9,})" + //no mínimo 9 caracteres
              "\$"
    )

    fun validate(password: String): Boolean {
        return PATTERN.matcher(password).matches()
    }
}