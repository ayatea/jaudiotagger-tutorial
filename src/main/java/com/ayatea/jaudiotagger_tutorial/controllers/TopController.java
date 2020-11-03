package com.ayatea.jaudiotagger_tutorial.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * トップ画面のコントローラークラス
 * @author ayatea
 */
@Controller
@RequestMapping("/")
public class TopController {

	// ===================================================================================
	//                                                                               Index
	//                                                                           =========
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
        return "top";
	}
}
