package io.github.cellzer.yuezhihu.yuezhihu.model;

import org.litepal.crud.DataSupport;

public class NewsListItem extends DataSupport {
	private String title;
	private String id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
