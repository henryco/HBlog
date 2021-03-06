package net.henryco.hblog.mvc.controllers.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Henry on 03/07/17.
 */

@Data @NoArgsConstructor
public class PostForm {

	private Long id;

	@NotNull @Size(min = 2, max = 40, message = "(tittle) size: <2, 40>")
	private String title;

	@Size(min = 5, max = 185, message = "(preview short) size <5, 185>")
	private String previewShort;

	@Size(min = 5, max = 700, message = "(preview long) size <5, 700>")
	private String previewLong;

	@Size(min = 1, max = 102400, message = "(content) size <1, 102400>")
	private String content;

	@NotNull
	private MultipartFile file;

	private List<MultipartFile> attachedFiles;

}
