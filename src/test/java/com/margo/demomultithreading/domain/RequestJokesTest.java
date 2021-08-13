package com.margo.demomultithreading.domain;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;


class RequestJokesTest {

  @Test
  void call() {
    var mockObject = mockStatic(RestServiceCaller.class);
    var restTempleObject = mockObject.when(() -> RestServiceCaller.getObjectSingle())
                                     .thenReturn(new RestTemplate());


  }

  @Test
  void IoFileTest() throws IOException {
    Joke joketest = Joke.builder()
                        .id(123)
                        .punchline("testing mockito")
                        .type("test")
                        .build();
    File filesTest = new File("../../data.txt");
    ExecutorService executor = Executors.newFixedThreadPool(1);
    executor.submit(new Runnable() {
      @SneakyThrows
      @Override
      public void run() {
        RequestJokes.writeIntoFile(joketest);
      }
    });
    assertThat(filesTest.exists()).isTrue();
  }

}