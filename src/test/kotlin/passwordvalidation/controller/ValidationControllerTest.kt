package passwordvalidation.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import passwordvalidation.model.Message

@SpringBootTest
@AutoConfigureMockMvc
class ValidationControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    @Test
    fun `password is valid`() {
        val message = Message("AbTp9!fok")

        mockMvc.post("/validate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(message)
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$") { value(true) }
            }
        }
    }

    @Test
    fun `password is not valid`() {
        val message = Message("AbTp9fok")

        mockMvc.post("/validate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(message)
        }.andExpect {
            status { isOk() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$") { value(false) }
            }
        }
    }

    @Test
    fun `request without parameters - returns bad request`() {
        mockMvc.post("/validate") {
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }
    }

    @Test
    fun `request with wrong parameters - returns bad request`() {
        mockMvc.post("/validate") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString("test")
        }.andExpect {
            status { isBadRequest() }
        }
    }
}