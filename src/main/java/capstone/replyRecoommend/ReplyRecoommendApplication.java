package capstone.replyRecoommend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ReplyRecoommendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplyRecoommendApplication.class, args);
	}

}
