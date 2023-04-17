package com.example.spring.data.mongodb;

import com.example.spring.data.mongodb.model.Book;
import com.example.spring.data.mongodb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ExecutableUpdateOperation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@SpringBootTest
class SpringdataMongoDbApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增
     */
    @Test
    public void insetBook() {
        Book book = new Book().builder()
                .id(101)
                .name("testMongoDB1")
                .type("testMongoDB1")
                .description("testMongoDB1")
                .build();
        mongoTemplate.save(book);

    }

    @Test
    public void insetUser() {
        User user = new User().builder()
                .id(10)
                .name("testMongoDB")
                .build();
        mongoTemplate.save(user);

    }

    /**
     * 删除
     */
    @Test
    public void removeBook() {
        //通过query根据id查询出对应对象，通过update对象进行修改
        Book book = new Book().builder()
                .id(101)
                .build();
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        mongoTemplate.remove(query, Book.class);
    }

    /**
     * 改
     */
    @Test
    public void updateBook() {
        //通过query根据id查询出对应对象，通过update对象进行修改
        Book book = new Book().builder()
                .id(101)
                .name("testMongoDB1")
                .type("testMongoDB1")
                .description("testMongoDB1")
                .build();
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Book bookUpdate = new Book().builder()
                .id(101)
                .name("testMongoDB101")
                .build();
        Update update = new Update().set("name", bookUpdate.getName());
        mongoTemplate.updateFirst(query, update, Book.class);
    }


    /**
     * 查询单条记录：精确匹配
     */
    @Test
    public void findOne() {
        //通过query根据id查询出对应对象，通过update对象进行修改
        Book book = new Book().builder()
                .id(10)
                .build();
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Book one = mongoTemplate.findOne(query, Book.class);
        System.out.println(one);
    }

    /**
     * 查询单条记录：模糊匹配
     */
    @Test
    public void findLike() {
        //通过query根据id查询出对应对象，通过update对象进行修改
        Book book = new Book().builder()
                .description("testMongoDB1")
                .build();
        Pattern pattern = Pattern.compile("^.*" + book.getDescription().trim() + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = new Query(Criteria.where("description").regex(pattern));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    /**
     * 查询所有
     */
    @Test
    public void findBook() {
        List<Book> bookList = mongoTemplate.findAll(Book.class);
        bookList.forEach(System.out::println);
    }


}
