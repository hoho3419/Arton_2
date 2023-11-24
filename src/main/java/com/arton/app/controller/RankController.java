package com.arton.app.controller;

import com.arton.app.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rank")
public class RankController {

	@Autowired
	RankService rankService;

	@GetMapping("/rank")
	public String rankForm(@RequestParam(required = false) Integer cateCode,
						   @RequestParam(required = false) String rankDate,
						   @RequestParam(required = false) String period,
						   Model m) {

		/* 조건에 맞는 랭킹 가져오기 */
		m.addAttribute("ranks", rankService.readRank(cateCode, rankDate, period));

		return "rank";
	}
}
