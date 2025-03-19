package org.example.libraryApi.book.externalApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "library-service-api")
public interface LibraryServiceApi {

    @GetMapping("/getFreeBooks")
    ResponseEntity<List<Integer>> getFreeBooks();

}
