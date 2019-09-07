
package demo.controllers;

import java.io.File;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

	/*
	 * Spring Boot’s
	 * org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
	 * enables multi-part uploads by default. You can create a form with
	 * enctype="multipart/form-data" to upload a file,
	 */

	/*
	 * You can customize multipart configuration using the following properties:
	 * spring.servlet.multipart.enabled=true
	 * spring.servlet.multipart.max-file-size=2MB
	 * spring.servlet.multipart.max-request-size=20MB
	 * spring.servlet.multipart.file-size-threshold=5MB
	 *
	 */

	private static final String UPLOADS_DIR = "myUploadFolder/";

	@GetMapping({ "/fileUpload" })
	public String home(Model model) {
		return "fileUpload";
	}

	@PostMapping(value = "/uploadMyFile")
	public String handleFileUpload(@RequestParam("myFile") MultipartFile file, RedirectAttributes redirectAtttributes) {
		if (!file.isEmpty()) {
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();
				File uploadingDir = new File(UPLOADS_DIR);
				if (!uploadingDir.exists()) {
					uploadingDir.mkdirs();
				}
				Files.write(new File(UPLOADS_DIR + name).toPath(), bytes);
				redirectAtttributes.addFlashAttribute("msg", "File " + name + " uploaded successfully");
			} catch (Exception e) {
				redirectAtttributes.addFlashAttribute("msg",
						"Failed to upload file" + name + ". Cause: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return "redirect:/fileUpload";
	}

}
