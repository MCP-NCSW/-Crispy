package com.mcp.crispy.franchise.controller;

import com.mcp.crispy.employee.dto.OwnerRegisterDto;
import com.mcp.crispy.franchise.dto.FranchiseDto;
import com.mcp.crispy.franchise.dto.FranchiseRegistrationRequest;
import com.mcp.crispy.franchise.service.FranchiseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/franchise")
public class FranchiseApiController {

    private final FranchiseService franchiseService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerFranchise(@RequestBody FranchiseRegistrationRequest request) {

        franchiseService.registerFranchiseAndOwner(request.getFranchiseDto(), request.getOwnerRegisterDto());
        return ResponseEntity.ok(Map.of("message", "가맹점 등록이 성공적으로 되었습니다."));
    }
}
