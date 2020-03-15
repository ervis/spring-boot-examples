package springboot.jpa

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import springboot.jpa.entities.TvShow
import java.util.*
import kotlin.test.assertEquals

@DataJpaTest
class JpaTest {

    @Autowired
    private lateinit var tvShowRepository: TvShowRepository

    @Test
    internal fun save_and_get() {

        val tvShow = TvShow(
                tvShowId = UUID.randomUUID().toString(),
                name = "Dr House",
                releaseDate = "November 16, 2004",
                onNetflix = false
        )

        tvShowRepository.save(tvShow)

        val s = tvShowRepository.findByName("Dr House")

        assertEquals("Dr House", s.name)
    }
}