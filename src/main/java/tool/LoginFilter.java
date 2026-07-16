package tool;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        String uri = request.getRequestURI();

        // ログイン不要のURL
        if (uri.endsWith("/login")
                || uri.endsWith("/login_admin")
                || uri.endsWith("/login/login.jsp")
                || uri.endsWith("/login/insert.jsp")
                || uri.endsWith("/login/admin_inssert.jsp")
                || uri.endsWith("/login/admin_check.jsp")
                || uri.endsWith("/login/login-error.jsp")
                || uri.contains("/css/")
                || uri.contains("/js/")
                || uri.contains("/images/")) {

            chain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}