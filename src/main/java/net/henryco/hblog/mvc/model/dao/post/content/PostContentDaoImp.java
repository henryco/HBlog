package net.henryco.hblog.mvc.model.dao.post.content;

import net.henryco.hblog.mvc.model.entity.post.StandardPostContent;
import net.henryco.hblog.mvc.model.repository.PostContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Henry on 16/06/17.
 */
@Repository
public class PostContentDaoImp implements PostContentDao {

	private final PostContentRepository contentRepository;

	@Autowired
	public PostContentDaoImp(PostContentRepository contentRepository) {
		this.contentRepository = contentRepository;
	}

	@Override
	public StandardPostContent addPostContent(StandardPostContent content) {
		return contentRepository.save(content);
	}

	@Override
	public StandardPostContent getPostContentById(long id) {
		return contentRepository.getOne(id);
	}

	@Override
	public void removePostContentById(Long id) {
		contentRepository.delete(id);
	}

	@Override
	public boolean isPostContentExists(long id) {
		return contentRepository.exists(id);
	}

}
