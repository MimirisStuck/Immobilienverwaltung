package com.example.immobilienverwaltung.repository;

import com.example.immobilienverwaltung.model.Immobilie;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ImmobilieRepository {

    private final File file = new File("immobilien.json");
    private final ObjectMapper mapper = new ObjectMapper();

    private List<Immobilie> loadAll() {
        try {
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Immobilie>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAll(List<Immobilie> list) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Immobilie> findAll() {
        return loadAll();
    }

    public Immobilie save(Immobilie i) {
        List<Immobilie> list = loadAll();
        // ID generieren
        Long newId = list.stream().mapToLong(Immobilie::getId).max().orElse(0L) + 1;
        i.setId(newId);
        list.add(i);
        saveAll(list);
        return i;
    }

    public void deleteById(Long id) {
        List<Immobilie> list = loadAll();
        list.removeIf(i -> i.getId().equals(id));
        saveAll(list);
    }

    public Optional<Immobilie> findById(Long id) {
        return loadAll().stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Immobilie update(Long id, Immobilie updated) {
        List<Immobilie> list = loadAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {

                Immobilie alt = list.get(i);
                alt.setBezeichnung(updated.getBezeichnung());
                alt.setStrasse(updated.getStrasse());
                alt.setHausnummer(updated.getHausnummer());
                alt.setPlz(updated.getPlz());
                alt.setOrt(updated.getOrt());

                list.set(i, alt);
                saveAll(list);
                return alt;
            }
        }
        throw new RuntimeException("Immobilie mit ID " + id + " nicht gefunden");
    }
}