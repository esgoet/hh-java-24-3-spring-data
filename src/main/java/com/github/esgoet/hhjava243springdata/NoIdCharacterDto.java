package com.github.esgoet.hhjava243springdata;

import lombok.With;

@With
public record NoIdCharacterDto(
        String name,
        int age,
        String profession
) {


}
