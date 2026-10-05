package com.ktdsuniversity.edu.articles.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.articles.vo.request.RegistArticleVO;
import com.ktdsuniversity.edu.articles.vo.response.ArticlesVO;

@Mapper
public interface ArticlesDao {

	int insertNewArticle(RegistArticleVO registArticleVO);

	ArticlesVO selectArticleById(String articleId);

}
