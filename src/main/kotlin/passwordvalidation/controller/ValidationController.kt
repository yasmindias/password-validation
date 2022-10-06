package passwordvalidation.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import passwordvalidation.model.Message
import passwordvalidation.service.ValidationService

@RestController
class ValidationController {
    @Autowired
    private lateinit var validationService: ValidationService

    @PostMapping("/validate")
    fun validate(@RequestBody message: Message): ResponseEntity<Boolean> {
        return ResponseEntity.ok(validationService.validate(message.password))
    }
}