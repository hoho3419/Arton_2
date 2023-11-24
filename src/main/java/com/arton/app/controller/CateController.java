package com.arton.app.controller;

import com.arton.app.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cate")
public class CateController {

	@Autowired
	CateService cateService;

	@GetMapping("/cate")
	public String cateForm(@RequestParam(required = false) Integer cateCode, Model m) {

		if (cateCode == null || "null".equals(cateCode)) {
			cateCode = 1;
		}

		/* 카테 메인 슬라이드 - 랜덤 */
		m.addAttribute("slides", cateService.readMainSlide(cateCode));

		/* What's Hot 섹션 - 찜랭킹 */
		m.addAttribute("hots", cateService.readLikeCountRank(cateCode));

		/* Weekly Ranking - 랭킹(주간) */
		m.addAttribute("weeklyRanks", cateService.readWeeklyRank(cateCode));

		/* Arton's Pick - 미리 정해둔 리스트(id 순서로 8개) */
		m.addAttribute("picks", cateService.readArtonPick(cateCode));

		return "cate";
	}


}
