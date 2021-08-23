package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.model.Kunde;
import com.example.demo.repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8081") //nur für CORS (kann weg)
@RestController
@RequestMapping ("/api")

public class KundeController {

    @Autowired
    KundeRepository kundeRepository;

    @GetMapping("/kunde")
    public ResponseEntity<List<Kunde>> getAllKunden() {
        try {
            List<Kunde> kundeList = new ArrayList<Kunde>();

                kundeRepository.findAll().forEach(kundeList::add);

            if (kundeList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kundeList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/kunde/{id}")
    public ResponseEntity<Kunde> getKundeById(@PathVariable("id") String id) {
        Optional<Kunde> kundeData = kundeRepository.findById(id);

        if (kundeData.isPresent()) {
            return new ResponseEntity<>(kundeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/kunde")
    public ResponseEntity<Kunde> createKunde(@RequestBody Kunde kunde) {
        try {
            Kunde _kunde = kundeRepository.save(new Kunde(kunde.getVorname(), kunde.getNachname(), kunde.getEmail(), kunde.getPasswort()));
            return new ResponseEntity<>(_kunde, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/kunde/{id}")
    public ResponseEntity<Kunde> updateKunde(@PathVariable("id") String id, @RequestBody Kunde kunde) {
        Optional<Kunde> kundeData = kundeRepository.findById(id);

        if (kundeData.isPresent()) {
            Kunde _kunde = kundeData.get();
            _kunde.setVorname(kunde.getVorname());
            _kunde.setNachname(kunde.getNachname());
            _kunde.setEmail(kunde.getEmail());
            _kunde.setPasswort(kunde.getPasswort());
            return new ResponseEntity<>(kundeRepository.save(_kunde), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/kunde/{id}")
    public ResponseEntity<HttpStatus> deleteKunde(@PathVariable("id") String id) {
        try {
            kundeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/kunde")
    public ResponseEntity<HttpStatus> deleteAllKunde() {
        try {
            kundeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
