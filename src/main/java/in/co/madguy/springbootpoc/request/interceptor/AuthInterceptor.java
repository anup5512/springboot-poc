package in.co.madguy.springbootpoc.request.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private final String readRole;

    public AuthInterceptor(
        @Value("${security.roles.read:cn=app-admin-read,ou=application,dc=madguy,dc=co,dc=in}")
        String readRole
    ) {
        this.readRole = readRole;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HashSet<String> authMemberGrpMap = new HashSet<>();
        String authMemberGrp = request.getHeader("X-MemberOf");
        if (authMemberGrp != null) {
            authMemberGrpMap.addAll(Arrays.asList(authMemberGrp.toLowerCase().split("\\|")));
        }
        if (!authMemberGrpMap.contains(readRole)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            log.info("Unauthorized access detected...");
            return false;
        }
        log.info("Authentication successful...");
        return true;
    }

    /**
     * @see HandlerInterceptor postHandle
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Request intercepted after handling...");
    }

    /**
     * @see HandlerInterceptor afterCompletion
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        log.info("Request intercepted after completion...");
    }
}
