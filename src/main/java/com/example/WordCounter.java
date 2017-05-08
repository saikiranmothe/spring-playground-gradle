package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class WordCounter {

    public Map<String, Integer> countWords (String sentence) {
        Map<String, Integer> countMap = new HashMap<>();

        Arrays.stream(sentence.toLowerCase().split(" "))
                .filter( s -> {
                    if (countMap.containsKey(s)) {
                        countMap.put(s, countMap.get(s) + 1);
                        return false;
                    } else {
                        return true;
                    }
                })
                .forEach( s -> countMap.put(s, 1) );

        return countMap;
    }

    @Bean
    public WordCounter getWordCounter() { return new WordCounter(); }
}
