package com.github.esgoet.hhjava243springdata;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsterixServiceTest {
    CharacterRepo characterRepo = mock(CharacterRepo.class);
    IdService idService = mock(IdService.class);
    AsterixService asterixService = new AsterixService(characterRepo, idService);

    @Test
    void findAllCharactersTest() {
        //GIVEN

        //WHEN
        List<Character> actual = asterixService.findAllCharacters(0);
        //THEN
        List<Character> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void findCharacterByIdTest() {
        //GIVEN
        Character character = new Character("1","Asterix",35,"Krieger");
        when(characterRepo.findById("1")).thenReturn(Optional.of(character));
        //WHEN
        Character actual = asterixService.findCharacterById(character.id());
        //THEN
        Character expected = new Character("1","Asterix",35, "Krieger");
        verify(characterRepo).findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void saveCharacterTest() {
        //GIVEN
        NoIdCharacterDto characterDto = new NoIdCharacterDto("Asterix",35,"Krieger");
        Character character = new Character("1","Asterix",35,"Krieger");
        when(idService.randomId()).thenReturn("1");
        when(characterRepo.save(character)).thenReturn(character);
        //WHEN
        Character actual = asterixService.saveCharacter(characterDto);

        //THEN
        Character expected = new Character("1","Asterix",35,"Krieger");
        verify(idService).randomId();
        verify(characterRepo).save(character);
        assertEquals(expected, actual);
    }

    @Test
    void deleteCharacterWithIdTest() {
        //GIVEN
        //WHEN
        asterixService.deleteCharacterWithId("1");
        //THEN
        verify(characterRepo).deleteById("1");
    }

    @Test
    void updateCharacterWithIdTest() {
        //GIVEN
        NoIdCharacterDto characterDto = new NoIdCharacterDto("Asterix", 35,"Holzfäller");
        Character character = new Character("1","Asterix",35,"Krieger");
        Character updatedCharacter = new Character("1",characterDto.name(),characterDto.age(),characterDto.profession());
        when(characterRepo.findById("1")).thenReturn(Optional.of(character));
        when(characterRepo.save(updatedCharacter)).thenReturn(updatedCharacter);

        //WHEN
        Character actual = asterixService.updateCharacterWithId("1", characterDto);

        //THEN
        Character expected = new Character("1","Asterix",35,"Holzfäller");
        verify(characterRepo).findById("1");
        verify(characterRepo).save(updatedCharacter);
        assertEquals(expected, actual);

    }

    @Test
    void updateCharacterWithIdTest_withIncompleteDto() {
        //GIVEN
        NoIdCharacterDto characterDto = new NoIdCharacterDto(null, 35,"Holzfäller");
        Character character = new Character("1","Asterix",35,"Krieger");
        Character updatedCharacter = new Character("1",character.name(),characterDto.age(),characterDto.profession());
        when(characterRepo.findById("1")).thenReturn(Optional.of(character));
        when(characterRepo.save(updatedCharacter)).thenReturn(updatedCharacter);

        //WHEN
        Character actual = asterixService.updateCharacterWithId("1", characterDto);

        //THEN
        Character expected = new Character("1","Asterix",35,"Holzfäller");
        verify(characterRepo).findById("1");
        verify(characterRepo).save(updatedCharacter);
        assertEquals(expected, actual);

    }
}