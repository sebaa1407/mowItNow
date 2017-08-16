package fr.xebia.mowitNow;
import javax.annotation.Resource;
 
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.xebia.mowitNow.storage.StorageService;
 
/**
 * 
 * @author sebaa
 *
 */
 
@SpringBootApplication
public class MowItNowTestSpringBootApplication implements CommandLineRunner{

	@Resource
	StorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(MowItNowTestSpringBootApplication.class, args);
	}
 
	@Override
	public void run(String... args) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
