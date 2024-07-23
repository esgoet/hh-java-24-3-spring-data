package com.github.esgoet.hhjava243springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CharacterRepo characterRepo;

    @Test
    void getAllCharacters() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/characters"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    []
                    """));
    }

    @Test
    @DirtiesContext
    void getCharacter() throws Exception {
        //GIVEN
        characterRepo.save(new Character("1","Asterix",35,"Krieger"));
        //WHEN
        mockMvc.perform(get("/api/characters/" +
                        "1"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "id": "1",
                      "name": "Asterix",
                      "age": 35,
                      "profession": "Krieger"
                    }
                    """));
    }

    @Test
    @DirtiesContext
    void saveCharacter() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(post("/api/characters")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "name": "Asterix",
                            "age": 35,
                            "profession": "Krieger"
                        }
                        """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "name": "Asterix",
                        "age": 35,
                        "profession": "Krieger"
                        }
                        """))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @DirtiesContext
    void deleteCharacter() throws Exception {
        //GIVEN
        characterRepo.save(new Character("1","Asterix",35,"Krieger"));
        //WHEN
        mockMvc.perform(delete("/api/characters/" +
                "1"))
                //THEN
                .andExpect(status().isOk());
        //WHEN
        mockMvc.perform(get("/api/characters/" +
                        "1"))
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
               // .andExpect(content().string(""));
    }

    @Test
    @DirtiesContext
    void updateCharacter_whenCharacterExists() throws Exception {
        //GIVEN
        characterRepo.save(new Character("1","Asterix",35,"Krieger"));
        //WHEN
        mockMvc.perform(put("/api/characters/" +
                "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "profession": "Holzfäller"
                    }
                    """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                      "id": "1",
                      "name": "Asterix",
                      "age": 35,
                      "profession": "Holzfäller"
                    }
                    """));
    }

    @Test
    void updateCharacter_whenCharacterDoesNotExists() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(put("/api/characters/" +
                        "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "profession": "Holzfäller"
                    }
                    """))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}