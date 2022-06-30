package kr.co.nft.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class UtilService {
	@Autowired private JavaMailSender javaMailSender;
	//인증 메일 발송 기능
	public void sendMail(String email,String verificationCode) {
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(email);
		simpleMessage.setSubject("이메일 인증");
		simpleMessage.setText("인증번호: "+verificationCode);
		javaMailSender.send(simpleMessage);
	}

}
