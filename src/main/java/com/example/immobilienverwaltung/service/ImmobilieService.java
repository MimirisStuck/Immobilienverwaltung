package com.example.immobilienverwaltung.service;

import com.example.immobilienverwaltung.model.Immobilie;
import com.example.immobilienverwaltung.repository.ImmobilieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImmobilieService {

    private final ImmobilieRepository repo;

    public ImmobilieService(ImmobilieRepository repo) {
        this.repo = repo;
    }

    public List<Immobilie> findAll() {
        return repo.findAll();
    }

    public Immobilie save(Immobilie i) {
        return repo.save(i);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Immobilie update(Long id, Immobilie neu) {
        Immobilie alt = repo.findById(id).orElseThrow();

        alt.setBezeichnung(neu.getBezeichnung());
        alt.setStrasse(neu.getStrasse());
        alt.setHausnummer(neu.getHausnummer());
        alt.setPlz(neu.getPlz());
        alt.setOrt(neu.getOrt());

        return repo.update(id, alt);
    }
}
