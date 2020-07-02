package springboot.jpa

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import springboot.jpa.entities.TvShow
import springboot.jpa.repositories.TvShowRepository
import java.util.*
import kotlin.test.assertEquals

@SpringBootTest
@ContextConfiguration(classes = [JpaConfig::class])
class JpaTest {

    @Autowired
    private lateinit var tvShowRepository: TvShowRepository

    @Test
    internal fun save_and_get() {

        val tvShowId = UUID.randomUUID().toString()
        val tvShow = TvShow(
                tvShowId = tvShowId,
                name = "Dr House",
                releaseDate = "November 16, 2004",
                onNetflix = false
        )

        tvShowRepository.save(tvShow)

        val s = tvShowRepository.findById(tvShowId).get()

        assertEquals("Dr House", s.name)
    }
}