package com.example.immobilienverwaltung.controller;

import com.example.immobilienverwaltung.model.Immobilie;
import com.example.immobilienverwaltung.repository.ImmobilieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/immobilien")
public class ImmobilieController {

    private final ImmobilieRepository repo;

    public ImmobilieController(ImmobilieRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Immobilie> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Immobilie create(@RequestBody Immobilie i) {
        return repo.save(i);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }

    @PutMapping("/{id}")
    public Immobilie update(@PathVariable Long id, @RequestBody Immobilie i) {
        return repo.update(id, i);
    }
}