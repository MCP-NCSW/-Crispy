package com.mcp.crispy.franchise.service;

import com.mcp.crispy.employee.dto.OwnerRegisterDto;
import com.mcp.crispy.employee.service.OwnerService;
import com.mcp.crispy.franchise.dto.FranchiseDto;
import com.mcp.crispy.franchise.mapper.FranchiseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseMapper franchiseMapper;

    private final OwnerService ownerService;

    /**
     * 가맹점 및 점주 등록
     *  2024.05.15
     */
    @Transactional
    public void registerFranchiseAndOwner(FranchiseDto franchiseDto, OwnerRegisterDto ownerRegisterDto) {
        int frnNo = registerFranchise(franchiseDto);
        log.info("Franchise registered with no {} ", frnNo);
        ownerService.registerOwner(ownerRegisterDto, frnNo);
    }

    @Transactional
    public int registerFranchise(FranchiseDto franchiseDto) {

        FranchiseDto franchise = FranchiseDto.builder()
                .frnName(franchiseDto.getFrnName())
                .frnOwner(franchiseDto.getFrnOwner())
                .frnTel(franchiseDto.getFrnTel())
                .frnZip(franchiseDto.getFrnZip())
                .frnStreet(franchiseDto.getFrnStreet())
                .frnDetail(franchiseDto.getFrnDetail())
                .frnJoinDt(LocalDateTime.now())
                .build();

        franchiseMapper.insertFranchise(franchise);

        return franchise.getFrnNo();
    }
}
