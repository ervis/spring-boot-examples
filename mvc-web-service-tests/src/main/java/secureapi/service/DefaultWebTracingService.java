package secureapi.service;

import org.springframework.stereotype.Service;

import brave.Tracer;

@Service
public class DefaultWebTracingService implements TracingService {
    private final Tracer tracer;

    public DefaultWebTracingService(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public String traceId() {
        return tracer.currentSpan().context().traceIdString();
    }

    @Override
    public String spanId() {
        return tracer.currentSpan().context().spanIdString();
    }
}
