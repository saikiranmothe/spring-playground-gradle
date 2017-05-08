package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WordCounterTest {

    @Autowired
    private WordCounter wordCounter;

    @Test
    public void testGetWordCounter () {
        WordCounter wordCounter1 = wordCounter.getWordCounter();
        assert ( wordCounter1 != null );
    }

    @Test
    public void testWordCount () {
        String inp = "This is a testing test tester IS test";
        Map<String, Integer> res = wordCounter.countWords(inp);

        assert (res.get("this").equals(1));
        assert (res.get("is").equals(2));
        assert (res.get("a").equals(1));
        assert (res.get("testing").equals(1));
        assert (res.get("test").equals(2));
        assert (res.get("tester").equals(1));
    }
}