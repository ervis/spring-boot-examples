package secureapi.service;

import java.time.Instant;

public interface SystemService {
    Instant time();

    String randomGuid();
}
