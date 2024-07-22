package com.github.esgoet.hhjava243springdata;

import lombok.Builder;
import lombok.With;
import org.springframework.data.mongodb.core.mapping.Document;

@With
@Document("characters")
@Builder
public record Character(
        String id,
        String name,
        int age,
        String profession
) {
}
