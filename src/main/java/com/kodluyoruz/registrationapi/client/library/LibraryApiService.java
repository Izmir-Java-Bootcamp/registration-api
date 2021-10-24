package com.kodluyoruz.registrationapi.client.library;

import com.kodluyoruz.registrationapi.client.library.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LibraryApiService {
    private final RestTemplate libraryApiTemplate;

    public BookDto getBookDto(int id) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));

        ResponseEntity<BookDto> responseEntity = libraryApiTemplate.getForEntity("/books/{id}", BookDto.class, params);
        return responseEntity.getBody();
    }

//    public void updateAvailability(int id,boolean availability){
//        libraryApiTemplate.exchange("/books/{id}/availability", HttpMethod.PATCH,)
//    }
}
