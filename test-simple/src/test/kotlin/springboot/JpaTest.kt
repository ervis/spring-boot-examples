package springboot

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

// simple test that loads the spring application context. Nothing special here
@SpringBootTest
class JpaTest {

    @Test
    internal fun save() {
        assertTrue(true)
    }
}