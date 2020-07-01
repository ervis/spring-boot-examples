package secureapi.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import secureapi.service.TracingService;

/**
 * Filter used to add the trace-id in the response header.
 * This can be useful for debugging purposes since we can
 * easily query the logs using the trace-id.
 */
@Component
public class TraceIdHeaderFilter extends OncePerRequestFilter {
    private static final String TRACE_ID_HEADER = "X-Request-Trace-Id";
    private final TracingService tracingService;

    public TraceIdHeaderFilter(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } finally {
            // TODO(ervis) do we need the check if the response is committed?
            response.addHeader(TRACE_ID_HEADER, tracingService.traceId());
        }
    }
}
