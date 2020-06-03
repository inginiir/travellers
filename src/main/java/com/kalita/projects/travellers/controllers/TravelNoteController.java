package com.kalita.projects.travellers.controllers;

import com.kalita.projects.travellers.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("note")
public class TravelNoteController {

    private int count = 4;
    private List<Map<String, String>> notes = new ArrayList<Map<String, String>>(){{
        add(new HashMap<String, String>() {{ put("id", "1"); put("text", "First note");}});
        add(new HashMap<String, String>() {{ put("id", "2"); put("text", "Second note");}});
        add(new HashMap<String, String>() {{ put("id", "3"); put("text", "Third note");}});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return notes;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getNote(id);
    }

    private Map<String, String> getNote(@PathVariable String id) {
        return notes.stream()
                .filter(notes -> notes.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> createNote(@RequestBody Map<String, String> note) {
        note.put("id", String.valueOf(count++));
        notes.add(note);
        return note;
    }

    @PutMapping("{id}")
    public Map<String, String> updateNote(@PathVariable String id, @RequestBody Map<String, String> note) {
        Map<String, String> noteFromDB = getNote(id);

        noteFromDB.putAll(note);
        noteFromDB.put("id", id);

        return noteFromDB;
    }

    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable String id) {
        Map<String, String> note = getNote(id);
        notes.remove(note);
    }
}
