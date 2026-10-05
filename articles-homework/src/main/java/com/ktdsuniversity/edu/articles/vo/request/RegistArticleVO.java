package com.ktdsuniversity.edu.articles.vo.request;

import lombok.Data;

@Data
public class RegistArticleVO {

	private String id;
	private String subject;
	private String content;
	private String email;
	private String crtDt;
	private String fileSetId;
}
