package com.inter.dio.heroisapi.services;


import com.inter.dio.heroisapi.model.Heroes;
import com.inter.dio.heroisapi.repository.HeroesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class HeroesSerivce {

    private HeroesRepository heroesRepository;

    public Flux<Heroes> findAll() {
        return Flux.fromIterable(this.heroesRepository.findAll());
    }

    public Mono<Heroes> findByIdHero(String id) {
        return Mono.justOrEmpty(this.heroesRepository.findById(id));
    }

    public Mono<Heroes> save (Heroes heroes) {
        return Mono.justOrEmpty(this.heroesRepository.save(heroes));
    }

    public Mono <Boolean> deleteByIdHero (String id) {
        heroesRepository.deleteById(id);
        return Mono.just(Boolean.TRUE);
    }
}
