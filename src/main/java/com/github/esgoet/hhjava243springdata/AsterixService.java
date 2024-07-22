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

    public Character saveCharacter(NoIdCharacterDto character) {
        Character characterToSave = Character.builder()
                .id(String.valueOf(findAllCharacters().size() + 1))
                .name(character.name())
                .age(character.age())
                .profession(character.profession())
                .build();
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

    public Character updateCharacterWithId(String id, NoIdCharacterDto character) {
        Optional<Character> optionalCharacter = characterRepo.findById(id);
        if (optionalCharacter.isPresent()) {
            Character characterToUpdate = optionalCharacter.get();
            if (character.age() == 0) {
                character = character.withAge(characterToUpdate.age());
            }
            if (character.name() == null) {
                character = character.withName(characterToUpdate.name());
            }
            if (character.profession() == null) {
                character = character.withProfession(characterToUpdate.profession());
            }
            return characterRepo.save(new Character(characterToUpdate.id(), character.name(), character.age(), character.profession()));
        }
        return null;
    }
}
