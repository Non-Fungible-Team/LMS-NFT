package kr.co.nft.lms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.nft.lms.service.MemberService;
import kr.co.nft.lms.util.A;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MemberScheduler {
	@Autowired MemberService memberService;
	
	@Scheduled(cron = "0 0 12 1 3 *")
	public void modifyDormantMember() {
		int row = memberService.modifyDormantMember();
		if ( row > 0 ) {
			
			// 몇명이 휴면 처리가 되었는지 확인 
			log.debug(A.Z+"[MemberScheduler.modifyDormantMember] row : "+row+A.R);
		}
	}
	
}
