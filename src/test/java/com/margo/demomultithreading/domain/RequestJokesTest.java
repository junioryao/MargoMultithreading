package com.margo.demomultithreading.domain;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


class RequestJokesTest {


  @Test
  void call() {
    Joke joketest = Joke.builder()
                        .id(123)
                        .punchline("testing mockito")
                        .general("test")
                        .build();
    var mockObject = mockStatic(RestServiceCaller.class);
    var restTempleObject = mockObject.when(() -> RestServiceCaller.getObjectSingle())
                                     .thenReturn(new RestTemplate());


  }

}