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
@WebFilter("/all/*")
public class AllLoginFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			log.debug(A.E+"[AllLoginFilter.doFilter] : 브라우저를 통한 요청"+A.R);	
			HttpSession session = ((HttpServletRequest)request).getSession();
			//로그인 정보가 없을시 로그인 contorller으로 redirect
			if(session.getAttribute("sessionLoginMember") == null) {
				log.debug(A.E+"[AllLoginFilter.doFilter] : 로그인 하지 않은 자의 요청 "+A.R);
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/member/login");
				return;
			}
			//로그인 정보가 있을시 Member 변수에 저장
			Member sessionLoginMember = (Member)session.getAttribute("sessionLoginMember");
			//level이 1,2,3 인 경우 승인 대기 메세지와 함께 homeController로 redirect
			if(sessionLoginMember.getMemberLevel() == 1 || sessionLoginMember.getMemberLevel() == 2 || sessionLoginMember.getMemberLevel() == 3) {
				log.debug(A.E+"[AllLoginFilter.doFilter] : 가입 승인대기자의 요청 sessionLoginMember : "+sessionLoginMember+A.R);
				// ((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/homeController?msg=waitingApproval");
					((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/all/logout");
					return;
			//level이 -4,-5,-6인 경우 휴면 계정 관리 페이지 이동
			}else if(sessionLoginMember.getMemberLevel() == -4 || sessionLoginMember.getMemberLevel() == -5 || sessionLoginMember.getMemberLevel() == -6) {
				log.debug(A.E+"[AllLoginFilter.doFilter] : 휴면 계정의 요청 sessionLoginMember : "+sessionLoginMember+A.R);
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/humanController?msg=humanAccount");///!!!! 꼭 명칭 변경 !!!!!
			//level이 -14,-15,-16인 경우 회원 탈퇴 메세지 출력
			}else if(sessionLoginMember.getMemberLevel() == -14 || sessionLoginMember.getMemberLevel() == -15 || sessionLoginMember.getMemberLevel() == -16) {
				log.debug(A.E+"[AllLoginFilter.doFilter] : 탈퇴회원의 요청 sessionLoginMember : "+sessionLoginMember+A.R);
				((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/humanController?msg=humanAccount");///!!!! 꼭 명칭 변경 !!!!!
			} 
			
		} else {
			log.debug(A.E+"[AllLoginFilter.doFilter] : 브라우저가 아닌 경로를 통한 요청"+A.R);	
		}
		chain.doFilter(request, response); //원 요청 처리
		
	}

}
