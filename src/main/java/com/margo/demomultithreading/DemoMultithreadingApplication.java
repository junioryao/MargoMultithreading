package com.margo.demomultithreading;

import com.margo.demomultithreading.domain.Joke;
import com.margo.demomultithreading.domain.RequestJokes;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class DemoMultithreadingApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(DemoMultithreadingApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    ExecutorService threadService = Executors.newFixedThreadPool(10); // run task async
    // to execute a thread
    List<Future<Joke>> mydata = new ArrayList<>();
    for (int i = 0; i < 520000; i++) {
      Future<Joke> data = threadService.submit(new RequestJokes());
      mydata.add(data);
    }
    List<Joke> mydataresult =  new ArrayList<>() ;
    mydata.forEach(jokeFuture -> {
      try {
        mydataresult.add(jokeFuture.get()) ;
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    });
    Collections.sort(mydataresult); // sort the result
    mydata.forEach(System.out::println);
  }
}
