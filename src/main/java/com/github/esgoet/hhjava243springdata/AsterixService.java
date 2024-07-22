package com.github.esgoet.hhjava243springdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepo characterRepo;

    public List<Character> findAllCharacters() {
        return characterRepo.findAll();
    }

    @PostMapping
    public Character saveCharacter(Character character) {
        Character characterToSave = character.withId(String.valueOf(findAllCharacters().size() + 1));
        return characterRepo.save(characterToSave);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacterWithId(@PathVariable String id) {
        characterRepo.deleteById(id);
    }
}
