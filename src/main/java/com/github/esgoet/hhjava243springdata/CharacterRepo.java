package com.github.esgoet.hhjava243springdata;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepo extends MongoRepository<Character, String> {
}
