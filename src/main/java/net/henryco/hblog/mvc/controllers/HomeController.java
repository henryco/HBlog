package net.henryco.hblog.mvc.controllers;

import net.henryco.hblog.mvc.model.post.StandardPostPreview;
import net.henryco.hblog.mvc.model.promo.PinnedNews;
import net.henryco.hblog.mvc.servives.HomePageService;
import net.henryco.hblog.mvc.servives.SimpExtraMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Henry on 14/06/17.
 */
@Controller
@RequestMapping("/")
public class HomeController {

	private final HomePageService homePageService;
	private final SimpExtraMediaService mediaService;


	@Autowired
	public HomeController(HomePageService postFormService,
						  SimpExtraMediaService mediaService) {
		this.homePageService = postFormService;
		this.mediaService = mediaService;
	}

	@RequestMapping(method = GET)
	public String home(Model model) {

		List<StandardPostPreview> posts = homePageService.getLastPosts(5);
		List<PinnedNews> pinnedNews = mediaService.getActualNews(1);
		List<StandardPostPreview> actualNews = new ArrayList<>();

		if (posts == null || posts.isEmpty()) return "index";
		if (pinnedNews != null)
			for (PinnedNews news: pinnedNews)
				actualNews.add(homePageService.getPostPreviewById(news.getPostId()));

		StandardPostPreview temp;

		if (actualNews.isEmpty())
			actualNews.add(posts.remove(0));

		else if (posts.contains(temp = actualNews.get(0)))
			posts.remove(temp);

		if (posts.size() > 4) posts.remove(4);


		model.addAttribute("actual_news", actualNews);
		model.addAttribute("previews", posts);

		return "index";
	}



	@RequestMapping(path = "/index", method = GET)
	public String index() {
		return "redirect:/";
	}

}
