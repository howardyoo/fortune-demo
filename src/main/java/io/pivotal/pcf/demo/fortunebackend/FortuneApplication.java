package io.pivotal.pcf.demo.fortunebackend;

// import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
// import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FortuneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FortuneApplication.class, args);
	}

	@Autowired
	private BackendController controller;

	@Override
	public void run(String... args) {

		try {
			if(!controller.all().isEmpty()) return;

			controller.save(new Fortune("Success lies in the hands of those who wants it."));
			controller.save(new Fortune("To avoid criticism, do nothing, say nothing, be nothing."));
			controller.save(new Fortune("One that would have the fruit must climb the tree."));
			controller.save(new Fortune("It takes less time to do a thing right than it does to explain why you did it wrong."));
			controller.save(new Fortune("Big journeys begin with a single step."));
			controller.save(new Fortune("Of all our human resources, the most precious is the desire to improve."));
			controller.save(new Fortune("People learn little from success, but much from failure."));
			controller.save(new Fortune("We must always have old memories and young hopes."));
			controller.save(new Fortune("It’s amazing how much good you can do if you don’t care who gets the credit."));
			controller.save(new Fortune("Never forget that a half truth is a whole lie."));
			controller.save(new Fortune("Yet by calling full, you created emptiness."));
			controller.save(new Fortune("A smile is your passport into the hearts of others."));
			controller.save(new Fortune("Change can hurt, but it leads a path to something better."));
			controller.save(new Fortune("Land is always on the mind of a flying bird."));
			controller.save(new Fortune("The greatest risk is not taking one."));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(
			@Value("${spring.application.name}") String applicationName) {
		return (registry) -> registry.config().commonTags("application", applicationName);
	}
	*/
}
