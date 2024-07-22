package com.github.esgoet.hhjava243springdata;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class AsterixController {
    private final CharacterRepo characterRepo;

    public AsterixController(CharacterRepo characterRepo ) {
        this.characterRepo = characterRepo;
    }

    @GetMapping
    public List<Character> getAllCharacters() {
        return characterRepo.findAll();
    }

    @PostMapping
    public Character saveCharacter(@RequestBody Character character) {
        Character characterToSave = character.withId(String.valueOf(getAllCharacters().size() + 1));
        System.out.println(character);
        System.out.println(characterToSave);
        return characterRepo.save(characterToSave);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        characterRepo.deleteById(id);
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
