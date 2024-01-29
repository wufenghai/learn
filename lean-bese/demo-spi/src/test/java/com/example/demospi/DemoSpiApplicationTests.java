package com.example.demospi;

import com.example.demospi.service.Search;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.ServiceLoader;

@SpringBootTest
class DemoSpiApplicationTests {

    @Test
    void contextLoads() {
        ServiceLoader<Search> search = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = search.iterator();
        while (iterator.hasNext()) {
            Search next = iterator.next();
            next.searchDoc("hello world");
        }

    }

}
