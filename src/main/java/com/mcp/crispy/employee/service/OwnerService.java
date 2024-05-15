package com.mcp.crispy.employee.service;

import com.mcp.crispy.employee.dto.OwnerRegisterDto;
import com.mcp.crispy.employee.dto.Position;
import com.mcp.crispy.employee.mapper.OwnerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.mcp.crispy.common.utils.RandomCodeUtils.generateTempPassword;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerMapper ownerMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public int registerOwner(OwnerRegisterDto ownerRegisterDto, int frnNo) {
        String tempPassword = generateTempPassword();
        String encodedPassword = passwordEncoder.encode(tempPassword);
        ownerRegisterDto.setEmpPw(encodedPassword);
        ownerRegisterDto.setFrnNo(frnNo);

        OwnerRegisterDto registerDto = OwnerRegisterDto.builder()
                .empId(ownerRegisterDto.getEmpId())
                .empPw(encodedPassword)
                .empName(ownerRegisterDto.getEmpName())
                .empEmail(ownerRegisterDto.getEmpEmail())
                .empPhone(ownerRegisterDto.getEmpPhone())
                .posNo(Position.OWNER.getCode())
                .frnNo(frnNo)
                .build();
        log.info("registerDto, frn: {}", frnNo);
        log.info("registerDto, posNo: {}", registerDto.getPosNo());
        ownerMapper.insertOwner(ownerRegisterDto);
        return registerDto.getEmpNo();
    }
}
