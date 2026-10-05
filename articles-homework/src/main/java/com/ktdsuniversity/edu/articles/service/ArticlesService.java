package com.ktdsuniversity.edu.articles.service;

import com.ktdsuniversity.edu.articles.vo.request.RegistArticleVO;
import com.ktdsuniversity.edu.articles.vo.response.ArticlesVO;

public interface ArticlesService {

	ArticlesVO createNewArticle(RegistArticleVO registArticleVO);

	ArticlesVO readArticle(String articleId);

}
