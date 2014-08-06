package com.nsn.cmatrix.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nsn.cmatrix.controller.BaseController;

@Controller
@RequestMapping("/entry")
public class EntryController extends BaseController
{
	@RequestMapping(method = RequestMethod.GET)
	public String index (ModelMap model) 
	{
		model.addAttribute("module", "entry");
		return "/app/home/entry/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add (ModelMap model) 
	{
		model.addAttribute("module", "entry");
		return "/app/home/entry/list";
	}
}
