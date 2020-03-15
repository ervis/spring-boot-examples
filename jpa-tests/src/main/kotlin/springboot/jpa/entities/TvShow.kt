package springboot.jpa.entities

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tv_show")
data class TvShow(
        @Id
        @Column(name = "tv_show_id")
        val tvShowId: String,

        @Column(name = "name")
        val name: String,

        @Column(name = "release_date")
        val releaseDate: String?,

        @Column(name = "on_netflix")
        val onNetflix: Boolean
)

