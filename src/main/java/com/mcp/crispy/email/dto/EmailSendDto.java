package com.mcp.crispy.email.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailSendDto {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(?![a-zA-Z0-9.-]*xn--)[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "유효하지 않은 이메일 형식입니다.")
    private String verifyEmail;
}
