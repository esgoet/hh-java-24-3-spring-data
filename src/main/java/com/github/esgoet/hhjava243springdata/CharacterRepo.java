package com.github.esgoet.hhjava243springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CharacterRepo extends MongoRepository<Character, String> {
    List<Character> findCharactersByAgeAfter(int age);
}
