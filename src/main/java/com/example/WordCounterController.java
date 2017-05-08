package com.example;

import com.example.WordCounter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/words")
public class WordCounterController {

    private WordCounter wordCounter;

    public WordCounterController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
        assert( this.wordCounter != null );
    }

    @PostMapping("/count")
    public Map<String, Integer> getWordCount(@RequestBody String input) {
        Map<String, Integer> test = this.wordCounter.countWords(input);
        return test;
    }

}