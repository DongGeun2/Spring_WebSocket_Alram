package kr.or.bit.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.dto.Member;
import kr.or.bit.dto.Message;
import kr.or.bit.service.MemberService;

@Controller
public class FrontController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@RequestMapping(value="/index.do")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value="/join.do", method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join.do", method = RequestMethod.POST)
	public String join(Member member) {
		String view = "";
		
		member.setPwd(this.bCryptPasswordEncoder.encode(member.getPwd()));
		System.out.println(member);
		
		int result = service.insertMember(member);
		System.out.println("insert : " + result);
		
		if(result > 0) {
			view = "redirect:login.do";
		}else {
			view = "join";
		}
		return view;
	}
	
	@RequestMapping(value="/login.do")
	public String login() {
		return "login";
	}
		
	@RequestMapping(value="/message.do", method = RequestMethod.GET)
	public String messageView(Model model,Principal principal) {
		List<Member> listmember = service.listMember();
		List<Message> msglist = service.listMessage(principal.getName());
		service.updateMessage(principal.getName());
		
		model.addAttribute("listmember", listmember);
		
		model.addAttribute("msglist", msglist);
		
		return "message";
	}
	

}
