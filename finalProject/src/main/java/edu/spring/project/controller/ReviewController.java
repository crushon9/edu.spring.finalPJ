package edu.spring.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.spring.project.service.ReviewService;

@Controller
@RequestMapping(value = "/review") // url: /project/review
public class ReviewController {
	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	private ReviewService reviewService;

	@GetMapping("/list")
	public void listGET() {
		logger.info("listGET() call");
	}

	@GetMapping("/admin/list")
	public void adlistGET() {
		logger.info("listGET() call");
	}

}
