package com.ktdsuniversity.edu.articles.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.articles.service.ArticlesService;
import com.ktdsuniversity.edu.articles.vo.request.RegistArticleVO;
import com.ktdsuniversity.edu.articles.vo.response.ArticlesVO;
import com.ktdsuniversity.edu.commons.util.ApiResponse;

import lombok.AllArgsConstructor;

/**
 * end-point 작성하는 클래스
 */
@Controller 
@AllArgsConstructor
public class ArticlesController {
	
	private ArticlesService articlesService;

	// 게시글 생성
	@PostMapping("/articles")
	public ApiResponse<ArticlesVO> makeNewArticle(RegistArticleVO registArticleVO) {
		ArticlesVO result = this.articlesService.createNewArticle(registArticleVO);
		return ApiResponse.CREATED(result);
	}
	// 게시글 1개 조회
	@GetMapping("/articles/{articleId}") 
	public ApiResponse<ArticlesVO> getArticle(@PathVariable String articleId) {
		ArticlesVO result = this.articlesService.readArticle(articleId);
		return ApiResponse.OK(result);
	}
	
	// 게시글 수정
	
	// 게시글 삭제
	
}
