package space.bum.spring_boot;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import space.bum.spring_boot.domain.Book;

public class SpringBootBootstrapLiveTest {

  @Test
  public void whenGetCreatedBookById_thenOK() {
    Book book = createRandomBook();
    String location = createBookAsUri(book);
    Response response = RestAssured.get(location);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    assertEquals(book.getTitle(), response.jsonPath()
        .get("title"));
  }

  @Test
  public void whenGetBooksByTitle_thenOK() {
    Book book = createRandomBook();
    createBookAsUri(book);
    Response response = RestAssured.get(
        API_ROOT + "/title/" + book.getTitle());

    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    assertTrue(response.as(List.class)
        .size() > 0);
  }

  @Test
  public void whenGetAllBooks_thenOK() {
    Response response = RestAssured.get(API_ROOT);

    assertEquals(HttpStatus.OK.value(), response.getStatusCode());
  }

  private static final String API_ROOT = "http://localhost:8081/api/books";

  private Book createRandomBook() {
    Book book = new Book();
    book.setTitle(randomAlphabetic(10));
    book.setAuthor(randomAlphabetic(15));
    return book;
  }

  private String createBookAsUri(Book book) {
    Response response = RestAssured.given()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(book)
        .post(API_ROOT);
    return API_ROOT + "/" + response.jsonPath().get("id");
  }
}
