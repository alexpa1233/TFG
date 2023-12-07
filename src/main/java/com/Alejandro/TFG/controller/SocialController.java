package com.Alejandro.TFG.controller;

import com.Alejandro.TFG.Service.SocialService;
import com.Alejandro.TFG.model.Social;
import com.Alejandro.TFG.exception.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/social")
public class SocialController {

    @Autowired
    private SocialService socialService;

    @GetMapping("/{id}")
    public ResponseEntity<Social> getSocialById(@PathVariable Long id) {
        Social social = socialService.getSocialById(id);
        return new ResponseEntity<>(social, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Social>> getAllSocials() {
        List<Social> socials = socialService.getAllSocials();
        return new ResponseEntity<>(socials, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Social> saveSocial(@RequestBody Social social) {
        Social createdSocial = socialService.saveSocial(social);
        return new ResponseEntity<>(createdSocial, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSocial(@PathVariable Long id) {
        socialService.deleteSocial(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}