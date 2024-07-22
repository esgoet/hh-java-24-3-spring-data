package com.github.esgoet.hhjava243springdata;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsterixService {
    private final CharacterRepo characterRepo;

    public List<Character> findAllCharacters() {
        return characterRepo.findAll();
    }

    public Character saveCharacter(Character character) {
        Character characterToSave = character.withId(String.valueOf(findAllCharacters().size() + 1));
        return characterRepo.save(characterToSave);
    }

    public Character deleteCharacterWithId(@PathVariable String id) {
        Optional<Character> characterToDelete = characterRepo.findById(id);
        if (characterToDelete.isPresent()) {
            characterRepo.delete(characterToDelete.get());
            return characterToDelete.get();
        }
        return null;
    }
}
