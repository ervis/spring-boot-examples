package secureapi.service;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
// class used to control randomness
public class DefaultSystemService implements SystemService {
    private final Clock clock;

    public DefaultSystemService(Clock clock) {
        this.clock = clock;
    }

    // use this method when you need to access time
    @Override
    public Instant time() {
        return Instant.now(clock);
    }

    @Override
    public String randomGuid() {
        return UUID.randomUUID().toString();
    }
}
