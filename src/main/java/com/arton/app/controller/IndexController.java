package com.arton.app.controller;

import com.arton.app.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class IndexController {

	@Autowired
	IndexService indexService;

	@GetMapping("/")
	public String indexForm(Model m) {

		/* 메인 슬라이드 섹션*/
		m.addAttribute("slides", indexService.readMainSlide());

		/* 랭킹 섹션*/
		Map rankSectionMap = indexService.readRankSection();
		m.addAttribute("cate1Ranks", rankSectionMap.get("rankCate1"));
		m.addAttribute("cate2Ranks", rankSectionMap.get("rankCate2"));
		m.addAttribute("cate3Ranks", rankSectionMap.get("rankCate3"));

		/* 나머지 섹션들은 DB에서 불러오지 않고 프론트 쪽에서 미리 선택한 이미지 및 문구, 디자인 등으로 세팅되어 고정적으로 출력되도록 되어있다. */

		return "index";
	}
}
