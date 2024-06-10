package com.mcp.crispy.approval.controller;

import com.mcp.crispy.approval.dto.ApplicantDto;
import com.mcp.crispy.approval.dto.ApprovalDto;
import com.mcp.crispy.approval.service.ApprovalService;
import com.mcp.crispy.auth.domain.EmployeePrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("crispy")
@RequiredArgsConstructor
public class ApprovalController {

	private final ApprovalService approvalService;

	/**
	 * 휴가, 휴직 신청 페이지
	 * 우혜진 (24. 05. 20.)
	 *
	 * @return forward (time-off-approval.html)
	 */
	@GetMapping("time-off-approval")
	public String timeOffAppr() {
		return "approval/time-off-approval";
	}

	/**
	 * 문서 양식 변경
	 * 우혜진 (24. 06. 06.)
	 *
	 * @param timeOffCtNo
	 * @return result
	 */
	@GetMapping("change-time-off-ct")
	public String changeTimeOffCt(@RequestParam("timeOffCtNo") int timeOffCtNo) {

		String path = null;

		switch (timeOffCtNo) {
			case 0: path = "document/vacation-req :: vacation-req"; break;
			case 1: path = "document/leave-of-absence-req :: leave-of-absence-req"; break;
		}

		return path;

	}

	/**
	 * 직원 정보 조회
	 * 우혜진 (24. 06. 05.)
	 *
	 * @param authentication
	 * @return result
	 */
	@GetMapping("get-emp-info")
	public ResponseEntity<ApplicantDto> getEmpInfo(Authentication authentication) {
		EmployeePrincipal userDetails = (EmployeePrincipal) authentication.getPrincipal();
		return ResponseEntity.ok(approvalService.getEmpInfo(userDetails.getEmpNo()));
	}

	/**
	 * 임시저장 값 존재 여부 확인
	 * 우혜진 (24. 06. 05.)
	 *
	 * @param authentication
	 * @return result
	 */
	@GetMapping("check-time-off-temp")
	public ResponseEntity<?> checkTimeOffTemp(Authentication authentication,
											  @RequestParam("timeOffCtNo") int timeOffCtNo) {
		EmployeePrincipal userDetails = (EmployeePrincipal) authentication.getPrincipal();
		return ResponseEntity.ok(approvalService.checkTimeOffTemp(userDetails.getEmpNo(), timeOffCtNo));
	}

	/**
	 * 휴가, 휴직 임시저장
	 * 우혜진 (24. 06. 06.)
	 *
	 * @param authentication
	 * @param approvalDto
	 * @return result
	 */
	@PostMapping("time-off-temp")
	public ResponseEntity<?> timeOffTemp(Authentication authentication,
										 @RequestBody @ModelAttribute ApprovalDto approvalDto) {

		EmployeePrincipal userDetails = (EmployeePrincipal) authentication.getPrincipal();
		approvalDto.setEmpNo(userDetails.getEmpNo());

		return ResponseEntity.ok(approvalService.insertTimeOffTemp(approvalDto));

	}

	/**
	 * 임시저장 내용 불러오기
	 * 우혜진 (24. 06. 07)
	 *
	 * @param authentication
	 * @param timeOffCtNo
	 * @param model
	 * @return result
	 */
	@GetMapping("get-time-off-temp")
	public String getTimeOffTemp(Authentication authentication,
								 @RequestParam("timeOffCtNo") int timeOffCtNo,
								 Model model) {

		EmployeePrincipal userDetails = (EmployeePrincipal) authentication.getPrincipal();

		ApprovalDto approvalDto = approvalService.getTimeOffTemp(userDetails.getEmpNo(), timeOffCtNo);
		model.addAttribute("approvalDto", approvalDto);

		String path = null;

		switch (timeOffCtNo) {
			case 0: path = "document/vacation-req :: vacation-req"; break;
			case 1: path = "document/leave-of-absence-req :: leave-of-absence-req"; break;
		}

		return path;

	}

	/**
	 * 휴가, 휴직 신청
	 * 우혜진 (24. 06. 09.)
	 *
	 * @param authentication
	 * @param approvalDto
	 * @return redirect (apprList())
	 */
	@PostMapping("insert-time-off-appr")
	public String insertTimeOffAppr(Authentication authentication,
									@ModelAttribute ApprovalDto approvalDto) {

		EmployeePrincipal userDetails = (EmployeePrincipal) authentication.getPrincipal();
		approvalDto.setEmpNo(userDetails.getEmpNo());

		approvalService.insertTimeOffAppr(approvalDto);

		return "redirect:/crispy/approval-list";

	}

	/** 휴가 및 휴직 신청 목록 조회
	 * 
	 * @return forward (approval-list.html)
	 */
	@GetMapping("approval-list")
	public String apprList() {
		return "approval/approval-list";
	}
	
	/** 결재 문서 열람
	 * 
	 * @return forward (approval-detail.html)
	 */
	@GetMapping("approval-detail")
	public String apprDetail() {
		return "approval/approval-detail";
	}

}
