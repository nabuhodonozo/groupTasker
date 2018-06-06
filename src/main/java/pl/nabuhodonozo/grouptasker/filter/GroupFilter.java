package pl.nabuhodonozo.grouptasker.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import pl.nabuhodonozo.grouptasker.repository.GroupRepository;
import pl.nabuhodonozo.grouptasker.repository.UserRepository;


@WebFilter("/app/group/manage/*")
public class GroupFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

    @Autowired
    UserRepository userRepository;
    @Autowired
    GroupRepository groupRepository;
    
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession session = httpServletRequest.getSession();

//		Long id = Long.parseLong(session.getAttribute("user_id").toString());
//		User user = userRepository.findOne(id);
//		List<Group> groups = user.getGroup();
//		//tutaj find group from url
		System.out.println(httpServletRequest.getRequestURL());
		String[] urlParts = httpServletRequest.getRequestURL().toString().split("/");
		System.out.println(Arrays.toString(urlParts));
		System.out.println(urlParts[urlParts.length-1]);
        if (true)
//      if (groups.contains(arg0))
        {
            chain.doFilter(request, response);
        }
        else
        {
            redirectToLoginPage(httpServletRequest, httpServletResponse);
        }
    }
    private void redirectToLoginPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/auth/login");
    }
    
	@Override
	public void destroy() {
		
	}
}
