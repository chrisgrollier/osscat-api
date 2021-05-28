package net.chrisgrollier.amsuite.osscat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.chrisgrollier.amsuite.osscat.service.ArtefactViewService;

@SpringBootApplication
public class OssCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(OssCatalogApplication.class, args);
	}

	@Bean
	ArtefactViewService artefactViewService() {
		return new ArtefactViewService();
	}

}
