package kr.co.nft.lms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.nft.lms.util.A;
import kr.co.nft.lms.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/student/*")
// 회원 level 4만 이용 가능한 filter
public class StudentLoginFilter implements Filter  {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			log.debug(A.E+"[studentLoginFilter.doFilter] : 브라우저를 통한 요청"+A.R);	
			HttpSession session = ((HttpServletRequest)request).getSession();
			//로그인 정보가 없을시 로그인 contorller으로 redirect
			if(session.getAttribute("sessionLoginMember") == null) {
				log.debug(A.E+"[studentLoginFilter.doFilter] : 로그인 하지 않은 자의 요청 "+A.R);
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
				return;
			}
			//로그인 정보가 있을시 Member 변수에 저장
			Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
			//level이 4가 아닌 경우 메세지와 함께 homeController로 redirect
			if(sessionLoginMember.getMemberLevel() != 4) {
				log.debug(A.E+"[studentLoginFilter.doFilter] : 권한이 아닌 자의 요청 sessionLoginMember : "+sessionLoginMember+A.R);
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/all/home?msg=notStudent");
				return;
			}
			
		} else {
			log.debug(A.E+"[studentLoginFilter.doFilter] : 브라우저가 아닌 경로를 통한 요청"+A.R);	
		}
		chain.doFilter(request, response); //원 요청 처리
		
	}

}
