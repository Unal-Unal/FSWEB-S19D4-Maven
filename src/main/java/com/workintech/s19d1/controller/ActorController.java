package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public ResponseEntity<List<Actor>> findAll() {
        List<Actor> actors = actorService.findAll();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findById(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        return ResponseEntity.ok(actor);
    }

    @PostMapping
    public ResponseEntity<Actor> save(@RequestBody ActorRequest request) {
        Actor actor = request.getActor();

        // DTO'daki List<Movie> -> entity'deki Set<Movie> uyumu
        if (request.getMovies() != null) {
            request.getMovies().forEach(actor::addMovie);
        }

        Actor savedActor = actorService.save(actor);
        return ResponseEntity.ok(savedActor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actor> update(@PathVariable Long id, @RequestBody Actor updated) {
        Actor actor = actorService.findById(id);

        if (actor == null) {
            return ResponseEntity.notFound().build();
        }

        // Güncellemeleri uyguluyoruz
        actor.setFirstName(updated.getFirstName());
        actor.setLastName(updated.getLastName());
        actor.setGender(updated.getGender());
        actor.setBirthDate(updated.getBirthDate());

        // Filmleri güncelle
        actor.getMovies().clear();
        if (updated.getMovies() != null) {
            updated.getMovies().forEach(actor::addMovie);
        }

        Actor savedActor = actorService.save(actor);
        return ResponseEntity.ok(savedActor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Actor> delete(@PathVariable Long id) {
        Actor actor = actorService.findById(id);
        actorService.delete(actor);
        return ResponseEntity.ok(actor);
    }
}