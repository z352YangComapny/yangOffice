package com.yangworld.app.commons;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
@Slf4j
@Component // 빈 자동등록!
public class MailSender {

	@Autowired
	private JavaMailSender mailSender;

	private int authNumber;

	public void makeRandomNumber() {
		// 난수의 범위 111111 ~ 999999 (6자리 난수)
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		log.info("checkNum ={}", checkNum);
		authNumber = checkNum;
	}


	//이메일 보낼 양식!
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "yangcompany7@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "SSOY STORY 인증 이메일 입니다."; // 이메일 제목
		String content =
				"저희 SSOY STORY에 방문해주셔서 감사합니다😆" + 	//html 형식으로 작성 !
						"<br><br>" +
						"요쳥하신 인증 번호는 <h3><mark>" + authNumber + "</mark></h3> 입니다." +
						"<br>" +
						"해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNumber);
	}

	//이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content,true);
			mailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


}