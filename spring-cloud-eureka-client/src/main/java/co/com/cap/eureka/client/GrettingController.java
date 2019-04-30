package co.com.cap.eureka.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface GrettingController {

	@RequestMapping("/greeting")
    String greeting();
}
