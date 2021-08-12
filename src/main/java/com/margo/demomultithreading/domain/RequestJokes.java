package com.margo.demomultithreading.domain;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.Callable;

import static com.margo.demomultithreading.domain.Timer.WAIT_FOR_16MIN;

public class RequestJokes implements Callable<Joke> {
  private static final File file = new File("./src/data.txt");

  public RequestJokes() throws IOException {
    file.createNewFile();
  }

  @Override
  public Joke call() throws Exception {
    ResponseEntity<Joke> result = null;
    try {
      result = RestServiceCaller.getObjectSingle()
                                .getForEntity("http://official-joke-api.appspot.com/random_joke", Joke.class);
    } catch (HttpStatusCodeException e) {
      System.out.println("Thread " +Thread.currentThread().getId() +" is waiting");
      Thread.sleep(WAIT_FOR_16MIN.getValue()); // it will make each thread wait ( async thread ) Max thread is 10

    }
    // perform  writing into a file
    if (result != null) {
      RequestJokes.writeIntoFile(Objects.requireNonNull(result.getBody()));
      return Objects.requireNonNull(result.getBody());
    }
    throw new Exception("Something went wrong");
  }

  private static void writeIntoFile(Joke joke) throws IOException {
    synchronized (joke) {
      Files.writeString(file.toPath(), joke.toString().concat("\n"), StandardOpenOption.APPEND);
      System.out.println("Thread " + Thread.currentThread().getId() +"is writing");
    }
  }

}
