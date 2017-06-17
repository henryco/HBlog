package net.henryco.hblog.mvc.servives;

import net.henryco.hblog.mvc.dao.content.PostContentDao;
import net.henryco.hblog.mvc.dao.preview.PostPreviewDao;
import net.henryco.hblog.mvc.model.StandardPostContent;
import net.henryco.hblog.mvc.model.StandardPostPreview;
import net.henryco.hblog.utils.Utils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Henry on 17/06/17.
 */
@Service
public class ArticlePageService {

	private final PostPreviewDao previewDao;
	private final PostContentDao contentDao;

	@Autowired
	public ArticlePageService(@Qualifier("testMockDao") PostPreviewDao previewDao,
							  PostContentDao contentDao) {
		this.previewDao = previewDao;
		this.contentDao = contentDao;
	}


	public StandardPostContent getPostContentById(long id) {
		return contentDao.getPostContentById(id);
	}

	public StandardPostPreview getPostPreviewById(long id) {
		return previewDao.getPostById(id);
	}

	public long getPostOlderThen(long id) {
		StandardPostPreview preview = previewDao.getPostOlderThen(id);
		return preview == null ? id : preview.getId();
	}

	public long getPostYoungerThen(long id) {
		StandardPostPreview preview = previewDao.getPostYoungerThen(id);
		return preview == null ? id : preview.getId();
	}

	public List<StandardPostPreview> getLastPostPreviews(long numb) {
		return previewDao.getLastPosts(numb);
	}
}