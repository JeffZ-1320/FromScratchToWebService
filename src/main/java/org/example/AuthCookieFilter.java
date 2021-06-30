package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;


@Component
public class AuthCookieFilter implements Filter {
    private final AuthSessionRepository authSessionRepository;

    @Autowired
    public AuthCookieFilter(AuthSessionRepository authSessionRepository) {
        this.authSessionRepository = authSessionRepository;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String currentPath = request.getRequestURI();
        Cookie[] cookies = request.getCookies();

//        if(!currentPath.equals("/sign_in")) {
            if(!currentPath.contains("/secure")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                for (int i = 0; i < cookies.length; i++) {
                    if(cookies[i].getName().equals("authenticate")){
                        Cookie authCookie = cookies[i];
                        AuthSessionEntity authSessionDb = authSessionRepository.findById(authCookie.getValue()).orElse(null);
                        if(authSessionDb != null){
                            Timestamp timeNow = Timestamp.from(Instant.now());
                            if(timeNow.compareTo(authSessionDb.getValidUntil()) < 0){
                                filterChain.doFilter(servletRequest, servletResponse);
                                return;
                            }else{
                                response.sendRedirect("/sign_in");
                                return;
                            }
                        }else{
                            response.sendRedirect("/sign_in");
                            return;
                        }
                    }
                }
                response.sendRedirect("/sign_in");
            }
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }
}
