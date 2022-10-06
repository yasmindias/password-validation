package passwordvalidation.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("UnitTest")
internal class ValidationServiceTest {
    private val validationService: ValidationService = ValidationService()

    @Test
    fun success(){
        /* GIVEN */
        val password = "AbTp9!fok"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isTrue
    }

    @Test
    fun `failure - has less than 9 characters`(){
        /* GIVEN */
        val password = "AbTp9!"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - has no digits`(){
        /* GIVEN */
        val password = "AbTpi!foe"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - has no lowercase characters`(){
        /* GIVEN */
        val password = "ABTPI!FO1"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - has no uppercase characters`(){
        /* GIVEN */
        val password = "abtpi!fo1"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - has no special characters`(){
        /* GIVEN */
        val password = "AbtpKnfo1"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - contains whitespace`(){
        /* GIVEN */
        val password = "AbTp9 fok"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

    @Test
    fun `failure - contains repeated characters`(){
        /* GIVEN */
        val password = "AbTp9!foA"

        /* WHEN */
        val response = validationService.validate(password)

        /* THEN */
        assertThat(response).isFalse
    }

}