package com.margo.demomultithreading.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  static Logger loggerFactory = LoggerFactory.getLogger(RequestJokes.class);

  public RequestJokes() throws IOException {
    file.createNewFile();
  }

  public static void writeIntoFile(Joke joke) throws IOException {
    synchronized (joke) {
      Files.writeString(file.toPath(), joke.toString().concat("\n"), StandardOpenOption.APPEND);
      loggerFactory.info("Thread " + Thread.currentThread().getId() + " is writing");
    }
  }

  @Override
  public Joke call() throws Exception {
    Joke result = null;
    try {
      result = RestServiceCaller.getObjectSingle()
                                .getForObject("https://official-joke-api.appspot.com/random_joke", Joke.class);
    } catch (HttpStatusCodeException e) {
      loggerFactory.info("thread is waiting : " + Thread.currentThread().getId());
      Thread.sleep(WAIT_FOR_16MIN.getValue()); // it will make each thread wait ( async thread ) Max thread is 10
    }
    // perform  writing into a file
    if (result != null) {
      RequestJokes.writeIntoFile(Objects.requireNonNull(result));
      return Objects.requireNonNull(result);
    }
    throw new Exception("Something went wrong");
  }

}
