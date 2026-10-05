package com.ktdsuniversity.edu.articles.vo.response;

public class ArticleListVO {
	
	/** 게시글 총 개수 */
	private long articlesCount;
	
	/** 게시글 정보 */
	private ArticlesVO articlesVO;

	public long getArticlesCount() {
		return articlesCount;
	}

	public void setArticlesCount(long articlesCount) {
		this.articlesCount = articlesCount;
	}

	public ArticlesVO getArticlesVO() {
		return articlesVO;
	}

	public void setArticlesVO(ArticlesVO articlesVO) {
		this.articlesVO = articlesVO;
	}
	
	

}
