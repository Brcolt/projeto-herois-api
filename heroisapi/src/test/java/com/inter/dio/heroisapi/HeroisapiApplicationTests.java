package com.inter.dio.heroisapi;


import com.inter.dio.heroisapi.repository.HeroesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.inter.dio.heroisapi.constants.HeroesConstat.HEROES_ENDPOINT;


@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroisapiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroeById() {

		webTestClient.get().uri(HEROES_ENDPOINT.concat("/{id}"), "10")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeronotFound(){

		webTestClient.get().uri(HEROES_ENDPOINT.concat("/{id}"),"10")
				.exchange()
				.expectStatus().isNotFound();

	}


	@Test
	public void deleteHero(){

		webTestClient.delete().uri(HEROES_ENDPOINT.concat("/{id}"),"10")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);

	}
}
