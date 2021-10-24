package com.kodluyoruz.registrationapi.client.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodluyoruz.registrationapi.client.library.model.dto.BookDto;
import com.kodluyoruz.registrationapi.client.library.model.request.UpdateAvailableRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.*;
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

    @SneakyThrows
    public void updateAvailability(int id, boolean availability) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(id));

        UpdateAvailableRequest request = UpdateAvailableRequest.builder()
                .availability(availability)
                .build();
        HttpHeaders reqHeaders = new HttpHeaders();
        reqHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UpdateAvailableRequest> entity = new HttpEntity<>(request, reqHeaders);
        ResponseEntity<Void> responseEntity = libraryApiTemplate.exchange("/books/{id}/availability",
                HttpMethod.PUT,
                entity,
                Void.class,
                params);
    }
}
