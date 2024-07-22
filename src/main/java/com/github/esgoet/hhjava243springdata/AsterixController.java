package com.github.esgoet.hhjava243springdata;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
public class AsterixController {
    private final AsterixService asterixService;

    @GetMapping
    public List<Character> getAllCharacters() {
        return asterixService.findAllCharacters();
    }

    @PostMapping
    public Character saveCharacter(@RequestBody CharacterCreationDto character) {
        return asterixService.saveCharacter(character);
    }

    @DeleteMapping("/{id}")
    public Character deleteCharacter(@PathVariable String id) {
        return asterixService.deleteCharacterWithId(id);
    }

//    @PutMapping("/{id}")
//    public Character updateCharacter(@PathVariable String id, @RequestBody Character character) {
//        Optional<Character> optionalCharacter = characterRepo.findById(id);
//        optionalCharacter.ifPresent(characterToUpdate -> {
//            characterRepo.save(character.withId(characterToUpdate.id()));
//            return character.withId(characterToUpdate.id());
//        });
//        return null;
//    }
}
