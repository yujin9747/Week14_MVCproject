package com.project2.myapp.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

	@Autowired
	BoardDAO boardDAO ;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String boardList(Model model) {
		model.addAttribute("list", boardDAO.getBoardList() );
		return "posts" ;
	}
	
	@RequestMapping(value = "/board/add", method = RequestMethod.GET)
	public String addPost() {
		return "addpostform" ;
	}
	
	@RequestMapping(value = "/board/addok", method = RequestMethod.POST)
	public String addPostOk(BoardVO vo) {
		int i = boardDAO.insertBoard(vo) ;
		if(i==0) System.out.println("데이터 추가 실패!") ;
		else System.out.println("데이터 추가 성공!") ;
		return "redirect:list" ;
	}
	
	@RequestMapping(value = "/board/editpost/{id}", method = RequestMethod.GET)
	public String editPost(@PathVariable("id") int id, Model model) {
		BoardVO boardVO = boardDAO.getBoard(id) ;
		model.addAttribute("boardVO", boardVO) ;
		return "editform" ;
	}
	
	@RequestMapping(value = "/board/editok", method = RequestMethod.POST)
	public String editPostOk(BoardVO vo) {
		int i = boardDAO.updateBoard(vo) ;
		if(i==0) System.out.println("데이터 수정 실패!") ;
		else System.out.println("데이터 수정 성공!") ;
		return "redirect:list" ;
	}
	
	
	@RequestMapping(value = "/board/deleteok/{id}", method = RequestMethod.GET)
	public String deletePost(@PathVariable("id") int id) {
		int i = boardDAO.deleteBoard(id) ;
		if(i==0) System.out.println("데이터 삭제 실패!") ;
		else System.out.println("데이터 삭제 성공!") ;
		return "redirect:../list" ;
	}
	

}
