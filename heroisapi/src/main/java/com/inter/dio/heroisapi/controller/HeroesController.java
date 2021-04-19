package com.inter.dio.heroisapi.controller;


import com.inter.dio.heroisapi.model.Heroes;
import com.inter.dio.heroisapi.repository.HeroesRepository;
import com.inter.dio.heroisapi.services.HeroesSerivce;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.inter.dio.heroisapi.constants.HeroesConstat.HEROES_ENDPOINT;

@SuppressWarnings("ALL")
@RestController
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class HeroesController {

    HeroesSerivce heroesSerivce;
    HeroesRepository heroesRepository;

    private static  final org.slf4j.Logger log =
            org.slf4j.LoggerFactory.getLogger(HeroesController.class);

    @GetMapping(HEROES_ENDPOINT)
    public Flux<Heroes> getAllItens() {
        log.info("requesting the list off all heroes");
        return heroesSerivce.findAll();
    }

    @GetMapping(HEROES_ENDPOINT+"id")
    public Mono <ResponseEntity<Heroes>> getByIdHero(@PathVariable String id) {
        log.info("Requesting the hero with id {}", id);
        return heroesSerivce.findByIdHero(id)
                .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(HEROES_ENDPOINT)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Heroes> createHero(@RequestBody Heroes heroes) {
        log.info("A new Hero was Created");
        return heroesSerivce.save(heroes);

    }

    @DeleteMapping(HEROES_ENDPOINT + "/{id}")
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public Mono<HttpStatus> deletebyIDHero(@PathVariable String id) {
        heroesSerivce.deleteByIdHero(id);
        log.info("Deleting the hero with id {}", id);
        return Mono.just(HttpStatus.NOT_FOUND);
    }



}
