package com.mcp.crispy.approval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("crispy")
public class ApprovalController {

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

	/** 휴가, 휴직 신청
	 *
	 * @return forward (vacation-approval.html)
	 */
	@GetMapping("vacation-approval")
	public String vacationAppr() {
		return "approval/vacation-approval";
	}

	// 결재선 선택 (임시)
	@GetMapping("vacation-approval-2")
	public String vacationAppr2() {
		return "approval/vacation-approval-2";
	}

}