package springboot.jpa

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("springboot.jpa.repositories")
@EntityScan("springboot.jpa.entities")
class JpaConfig {

}