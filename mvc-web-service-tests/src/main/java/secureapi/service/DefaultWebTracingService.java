package secureapi.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultWebTracingService implements TracingService {
    private final Tracer tracer;

    public DefaultWebTracingService(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public String traceId() {
        return tracer.currentSpan().;
    }
}
