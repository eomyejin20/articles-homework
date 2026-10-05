package com.ktdsuniversity.edu.articles.service;

import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.articles.dao.ArticlesDao;
import com.ktdsuniversity.edu.articles.vo.request.RegistArticleVO;
import com.ktdsuniversity.edu.articles.vo.response.ArticlesVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticlesServiceImpl implements ArticlesService{
	
	private ArticlesDao articlesDao;

	@Override
	public ArticlesVO createNewArticle(RegistArticleVO registArticleVO) {
		int row = this.articlesDao.insertNewArticle(registArticleVO);
		if (row == 0) {
			throw new IllegalArgumentException("입력값이 유효하지 않습니다.");
		}
		
		return this.articlesDao.selectArticleById(registArticleVO.getId());
	}

	@Override
	public ArticlesVO readArticle(String articleId) {
		ArticlesVO article = this.articlesDao.selectArticleById(articleId);
		if (article == null) {
			throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
		}
		return article;
	}

}
