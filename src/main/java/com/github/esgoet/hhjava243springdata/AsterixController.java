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
    public List<Character> getAllCharacters(@RequestParam(defaultValue = "0") final Integer age) {
        return asterixService.findAllCharacters(age);
    }

    @GetMapping("/{id}")
    public Character getCharacter(@PathVariable String id) {
        return asterixService.findCharacterById(id);
    }

    @PostMapping
    public Character saveCharacter(@RequestBody NoIdCharacterDto character) {
        return asterixService.saveCharacter(character);
    }

    @DeleteMapping("/{id}")
    public void deleteCharacter(@PathVariable String id) {
        asterixService.deleteCharacterWithId(id);
    }

    @PutMapping("/{id}")
    public Character updateCharacter(@PathVariable String id, @RequestBody NoIdCharacterDto character) {
        return asterixService.updateCharacterWithId(id, character);
    }
}
