package com.mcp.crispy.franchise.mapper;

import com.mcp.crispy.franchise.dto.FranchiseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FranchiseMapperTest {

    @Autowired
    private FranchiseMapper franchiseMapper;

    @Test
    void 가맹점등록() {
        FranchiseDto franchise = FranchiseDto.builder()
                .frnName("가산점")
                .frnOwner("김구디")
                .frnTel("070-1234-1234")
                .frnZip("02050")
                .frnStreet("서울시어쩌구저쩔동")
                .frnDetail("4층")
                .frnJoinDt(LocalDateTime.now())
                .build();

        franchiseMapper.insertFranchise(franchise);
    }



    }