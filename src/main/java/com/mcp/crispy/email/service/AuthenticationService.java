package com.mcp.crispy.email.service;

import com.mcp.crispy.email.dto.EmailVerificationDto;
import com.mcp.crispy.email.mapper.EmailVerificationMapper;
import com.mcp.crispy.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.mcp.crispy.common.utils.RandomCodeUtils.generateVerificationCode;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final EmailVerificationMapper emailVerificationMapper;
    private final EmailService emailService;

    //인증번호 전송 및 인증테이블에 저장
    public void sendAndSaveVerificationCode(String verifyEmail) {

        String verificationCode = generateVerificationCode();
        EmailVerificationDto emailVerificationDto = EmailVerificationDto.builder()
                .verifyEmail(verifyEmail)
                .verifyCode(verificationCode)
                .verifyEndDt(LocalDateTime.now().plusMinutes(5))
                .build();

        emailVerificationMapper.insertVerification(emailVerificationDto);

        emailService.sendVerificationEmail(verifyEmail, verificationCode);
    }


    public boolean verifyCode(String email, String code) {
        Optional<EmailVerificationDto> emailVerificationDto = emailVerificationMapper.findByEmail(email);
        if(emailVerificationDto.isPresent()) {
            EmailVerificationDto emailVerification = emailVerificationDto.get();
            log.info("인증 정보: email={}, savedCode={}, expiryDateTime={}",
                    emailVerification.getVerifyEmail(), emailVerification.getVerifyCode(), emailVerification.getVerifyEndDt());

            LocalDateTime expiryDateTime = emailVerification.getVerifyEndDt();
            boolean isExpired = LocalDateTime.now().isAfter(expiryDateTime);
            if(isExpired) {
                log.info("인증 코드 만료 : {}", email);
                return false;
            }

            return emailVerification.getVerifyCode().equals(code);
        } else {
            log.info("인증 이메일 정보 없음: email={}", email);
            return false;
        }
    }

}
