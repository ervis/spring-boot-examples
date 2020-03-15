package springboot.jpa

import org.springframework.data.jpa.repository.JpaRepository
import springboot.jpa.entities.TvShow

interface TvShowRepository : JpaRepository<TvShow, String> {
    fun findByName(name: String): TvShow
}