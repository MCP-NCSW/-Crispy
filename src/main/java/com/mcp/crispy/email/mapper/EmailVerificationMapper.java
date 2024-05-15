package com.mcp.crispy.email.mapper;

import com.mcp.crispy.email.dto.EmailVerificationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface EmailVerificationMapper {
    @Select("SELECT VERIFY_EMAIL, VERIFY_END_DT, VERIFY_CODE, VERIFY_STAT FROM VERIFICATION_T WHERE VERIFY_EMAIL = #{verifyEmail}")
    Optional<EmailVerificationDto> findByEmail(@Param("verifyEmail") String verifyEmail);

    void insertVerification(EmailVerificationDto emailVerificationDto);


}
