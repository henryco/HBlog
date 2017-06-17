package net.henryco.hblog.mvc.dao.content;

import net.henryco.hblog.mvc.model.post.StandardPostContent;

/**
 * @author Henry on 16/06/17.
 */
public interface PostContentDao {

	StandardPostContent addPostContent(StandardPostContent content);
	StandardPostContent getPostContentById(long id);
	void removePostContentById(Long id);
	boolean isPostContentExists(long id);
}
