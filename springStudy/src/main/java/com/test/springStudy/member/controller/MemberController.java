package com.test.springStudy.member.controller;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.springStudy.common.Util;
import com.test.springStudy.member.model.dao.MemberDAO;
import com.test.springStudy.member.model.dto.MemberDTO;
import com.test.springStudy.member.util.MemberUtil;

@Controller
public class MemberController {
	@Inject
	MemberDAO memberDao;
	
	MemberUtil util = new MemberUtil();
	
    public Map<String, Object> topInfo(
			HttpServletRequest request
			) throws UnknownHostException {
		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];
		
		Map<String, Object> map = util.basicInfo(request);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		return map;
	}
	
	@RequestMapping("member/index.do") //url mapping
	public String index(
			HttpServletRequest request,
			Model model
			
			) throws UnknownHostException {
		
		Map<String, Object> map = topInfo(request);
		String ip = (String) map.get("ip");
		
		String arg01 = request.getParameter("arg01");
		arg01 = util.nullCheck(arg01);
		
		model.addAttribute("menu_gubun","member_index");
		model.addAttribute("ip",ip);
		model.addAttribute("arg01",arg01);
		
		return "main/main";
	}
	
	   @RequestMapping("member/list.do")
	   public String member_list(
	         HttpServletRequest request,
	         Model model
	         ) throws UnknownHostException {
//	      System.out.println("in list");
//	      Map<String, Object> map = util.basicInfo(request);
	      Map<String, Object> map = topInfo(request);
	      int pageNumber = (int) map.get("pageNumber");
	      int no = (int) map.get("no");
	      String search_option = (String) map.get("search_option");
	      String search_data = (String) map.get("search_data");
	      
	      int pageSize = 10;
	      int blockSize = 10;
	      int totalRecord = memberDao.getTotalRecord(map);
	      int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
	      int jj = pagerArray[0];
	      int startRecord = pagerArray[1];
	      int lastRecord = pagerArray[2];
	      int totalPage = pagerArray[3];
	      int startPage = pagerArray[4];
	      int lastPage = pagerArray[5];
//	      System.out.println("pageNum>>>>>"+pageNumber);
//	      System.out.println("startRecord>>>>>"+startRecord);
//	      System.out.println("lastRecord>>>>>"+lastRecord);
	      
	      List<MemberDTO> list = memberDao.getList(startRecord, lastRecord, map);
//	     System.out.println("list >>>>>>>>"+ list.get(0).getNo());
	      model.addAttribute("menu_gubun", "member_list");
	      model.addAttribute("list", list);
	      model.addAttribute("pageNumber", pageNumber);
	      model.addAttribute("pageSize", pageSize);
	      model.addAttribute("blockSize", blockSize);
	      model.addAttribute("totalRecord", totalRecord);
	      model.addAttribute("jj", jj);
	      model.addAttribute("startRecord", startRecord);
	      model.addAttribute("lastRecord", lastRecord);
	      model.addAttribute("totalPage", totalPage);
	      model.addAttribute("startPage", startPage);
	      model.addAttribute("lastPage", lastPage);
	      return "member/list";
	   }
	   
	   @RequestMapping("member/chuga.do")
	   public String member_chuga(
	         HttpServletRequest request,
	         Model model
	         ) throws UnknownHostException{
		   model.addAttribute("menu_gubun", "member_chuga");
		   return "member/chuga";
		   
	   }
	   
	   @RequestMapping("member/login.do")
	   public String member_login(
	         HttpServletRequest request,
	         Model model
	         ) throws UnknownHostException{
		   model.addAttribute("menu_gubun", "member_login");
		   return "member/login";
		   
	   }
	   
	   @ResponseBody
	   @RequestMapping("member/logout.do")
	   public String member_logout(
	         HttpServletRequest request,
	         Model model
	         ) throws UnknownHostException{
		   HttpSession session = request.getSession();
		   session.invalidate();
		   model.addAttribute("menu_gubun", "member_login");
		   return "<script>alert('logout');location.href='index.do?arg01=login'</script>"; 
	   }
	   
	   @ResponseBody
	   @RequestMapping("member/loginProc.do")
	   public String member_loginProc(
	         HttpServletRequest request,
	         @ModelAttribute MemberDTO dto,
	         Model model
	         ) throws UnknownHostException{
		   MemberDTO resultDto = memberDao.login(dto);
		   String way = "";
//		   if(resultDto.getNo() != 0 && resultDto.getId() != null &&  resultDto.getName() != null ) {
		   if(resultDto != null ) {
				HttpSession session = request.getSession();
				System.out.println("cookNo>>>>>>>"+resultDto.getNo());
				System.out.println("cookId>>>>>>>"+resultDto.getId());
				System.out.println("cookName>>>>>>>"+resultDto.getName());
				session.setAttribute("cookNo", resultDto.getNo());
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookName", resultDto.getName());
				way += "<script>alert('seccess login.controller');location.href='/springStudy'</script>"; 
			} else {
				model.addAttribute("menu_gubun", "member_login");
				way += "<script>alert('wrong password or wrong id plz try again.controller');location.href='index.do?arg01=login'</script>"; 
			}
		   	System.out.println("way>>>>>"+way);
		   	return way;
	   }
	   
	   @RequestMapping("member/view.do")
	   public String member_view(
	         HttpServletRequest request,
	     	 @ModelAttribute MemberDTO dto,
	         Model model
	         ) throws UnknownHostException{
		  
	      Map<String, Object> map = topInfo(request);
	      int pageNumber = (int) map.get("pageNumber");
	      String search_option = (String) map.get("search_option");
	      String search_data = (String) map.get("search_data");
		   MemberDTO resultDto = memberDao.getSelectOne(map,dto);
		   model.addAttribute("dto", resultDto );
		   model.addAttribute("menu_gubun", "member_view");
		   return "member/view";
		   
	   }
	   
	   @RequestMapping("member/chugaProc.do")
	   public String member_chugaProc(
	         HttpServletRequest request,
	         @RequestParam(value="no", defaultValue="0") int no,
	      	 @RequestParam(value="sample6_postcode", defaultValue="") String sample6_postcode,
			 @RequestParam(value="sample6_address", defaultValue="") String sample6_address,
		 	 @RequestParam(value="sample6_detailAddress", defaultValue="") String sample6_detailAddress,
			 @RequestParam(value="sample6_extraAddress", defaultValue="") String sample6_extraAddress,
	     	 @ModelAttribute MemberDTO dto,
	     	 Model model
	         ) throws UnknownHostException{
			dto.setPostCode(sample6_postcode);
			dto.setAddress(sample6_address);
			dto.setDetailAddress(sample6_detailAddress);
			dto.setExtraAddress(sample6_extraAddress);
		    System.out.println("dto>>>>>"+dto);
		   model.addAttribute("menu_gubun", "member_chuga");
		   int result = memberDao.insertMember(dto);
		   if(result > 0) {
			   return "member/list"; 
		   }else {
			   return "member/chuga"; 
		   }
		   
	   }
	   
	   @RequestMapping("member/sujung.do")
	   public String member_sujung(
	         HttpServletRequest request,
	         @ModelAttribute MemberDTO dto,
	         Model model
	         ) throws UnknownHostException{
		   Map<String, Object> map = topInfo(request);
		   int pageNumber = (int) map.get("pageNumber");
		   String search_option = (String) map.get("search_option");
		   String search_data = (String) map.get("search_data");
		   MemberDTO dto1 = memberDao.getSelectOne(map,dto);
		   model.addAttribute("dto", dto1 );
		   model.addAttribute("menu_gubun", "member_sujung");
		   return "member/update";
		   
	   }
	   
	   @RequestMapping("member/sujungProc.do")
	   public String member_sujungProc(
	         HttpServletRequest request,
	         @RequestParam(value="no", defaultValue="") int no,
	      	 @RequestParam(value="sample6_postcode", defaultValue="") String sample6_postcode,
			 @RequestParam(value="sample6_address", defaultValue="") String sample6_address,
		 	 @RequestParam(value="sample6_detailAddress", defaultValue="") String sample6_detailAddress,
			 @RequestParam(value="sample6_extraAddress", defaultValue="") String sample6_extraAddress,
	     	 @ModelAttribute MemberDTO dto,
	     	 Model model
	         ) throws UnknownHostException{
		    dto.setNo(no);
			dto.setPostCode(sample6_postcode);
			dto.setAddress(sample6_address);
			dto.setDetailAddress(sample6_detailAddress);
			dto.setExtraAddress(sample6_extraAddress);
		    System.out.println("memeber update in dto>>>>>"+dto);
		    model.addAttribute("menu_gubun", "member_sujung");
		    int result = memberDao.updateMember(dto);
		    if(result > 0) {
			   return "member/list"; 
		    }else {
			   return "member/sujung"; 
		    }
		   
	   }
	   
	   
	   @RequestMapping("member/sakje.do")
	   public String member_sakje(
	         HttpServletRequest request,
	     	 @ModelAttribute MemberDTO dto,
	         Model model
	         ) throws UnknownHostException{
		  
	      Map<String, Object> map = topInfo(request);
	      int pageNumber = (int) map.get("pageNumber");
	      String search_option = (String) map.get("search_option");
	      String search_data = (String) map.get("search_data");
		   MemberDTO dto1 = memberDao.getSelectOne(map,dto);
		   model.addAttribute("dto", dto1 );
		   model.addAttribute("menu_gubun", "member_sakje");
		   return "member/delete";
	   }
	   
	   @ResponseBody
	   @RequestMapping("member/sakjeProc.do")
	   public String member_sakjeProc(
	         HttpServletRequest request,
	     	 @ModelAttribute MemberDTO dto,
	     	 Model model
	         ) throws UnknownHostException{
		   String result = memberDao.deleteMember(dto);
		   System.out.println("result>>>>"+result);
		   if(result.equals("success")) {
			   return "member/index"; 
		   }else if (result.equals("DBFail")){
			   return "<script>alert('DBFail');location.href='index.do'</script>"; 
		   }else{ 
			   return "<script>alert('not right password');location.href='index.do'</script>"; 
		   }
	
		   
//		   if(result.equals("success")) {
//			   System.out.println("success>>>>"+result);
//			   return "member/list"; 
//			   
//		   } else if (result.equals("DBFail")) {
//			   System.out.println("DBFail>>>>"+result);
//			   model.addAttribute("dto", dto );
//			   return "member/delete"; 
//			   
//		   }else {
//			   System.out.println("passwdFail>>>>"+result);
//			   model.addAttribute("no", dto.getNo() );
//			   model.addAttribute("data", "0" );
//			   model.addAttribute("arg01", "sakjeProc" );
//			   return "member/index"; 
//		   }
			
	   }
	   
	   
	   
	   

}
