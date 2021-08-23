package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.demo.repository.ReifenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Reifen;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping ("/api")

public class ReifenController {

    @Autowired
    ReifenRepository reifenRepository;

    @GetMapping("/reifen")
    public ResponseEntity<List<Reifen>> getAllReifen(@RequestParam(required = false) String name) {
        try {
            List<Reifen> reifenList = new ArrayList<Reifen>();

            if (name == null)
                reifenRepository.findAll().forEach(reifenList::add);
            else
                reifenRepository.findByNameContaining(name).forEach(reifenList::add);

            if (reifenList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reifenList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/reifen/{id}")
    public ResponseEntity<Reifen> getReifenById(@PathVariable("id") String id) {
        Optional<Reifen> reifenData = reifenRepository.findById(id);

        if (reifenData.isPresent()) {
            return new ResponseEntity<>(reifenData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/reifen")
    public ResponseEntity<Reifen> createReifen(@RequestBody Reifen reifen) {
        try {
            Reifen _reifen = reifenRepository.save(new Reifen(reifen.getName(), reifen.getBreite(), reifen.getHoehe(), reifen.getZoll(), reifen.getBeschreibung()));
            return new ResponseEntity<>(_reifen, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reifen/{id}")
    public ResponseEntity<Reifen> updateReifen(@PathVariable("id") String id, @RequestBody Reifen reifen) {
        Optional<Reifen> reifenData = reifenRepository.findById(id);

        if (reifenData.isPresent()) {
            Reifen _reifen = reifenData.get();
            _reifen.setName(reifen.getName());
            _reifen.setBeschreibung(reifen.getBeschreibung());
            _reifen.setBreite(reifen.getBreite());
            _reifen.setHoehe(reifen.getHoehe());
            _reifen.setZoll(reifen.getZoll());
            return new ResponseEntity<>(reifenRepository.save(_reifen), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reifen/{id}")
    public ResponseEntity<HttpStatus> deleteReifen(@PathVariable("id") String id) {
        try {
            reifenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/reifen")
    public ResponseEntity<HttpStatus> deleteAllReifen() {
        try {
            reifenRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
