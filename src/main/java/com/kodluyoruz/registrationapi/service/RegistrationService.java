package com.kodluyoruz.registrationapi.service;

import com.kodluyoruz.registrationapi.client.library.LibraryApiService;
import com.kodluyoruz.registrationapi.client.library.model.dto.BookDto;
import com.kodluyoruz.registrationapi.client.user.UserApiService;
import com.kodluyoruz.registrationapi.client.user.model.dto.UserDto;
import com.kodluyoruz.registrationapi.exception.BusinessException;
import com.kodluyoruz.registrationapi.model.dto.RegistrationDto;
import com.kodluyoruz.registrationapi.model.entity.Registration;
import com.kodluyoruz.registrationapi.model.request.RegistrationRequest;
import com.kodluyoruz.registrationapi.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import static com.kodluyoruz.registrationapi.model.mapper.RegistrationMapper.REGISTRATION_MAPPER;


@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository repository;
    private final UserApiService userApiService;
    private final LibraryApiService libraryApiService;

    public RegistrationDto register(RegistrationRequest request) {
        validateUser(request);
        validateBook(request);

        Registration registration = REGISTRATION_MAPPER.toRegistration(request);
        Registration savedRegistration = repository.save(registration);

        libraryApiService.updateAvailability(request.getBookId(), false);
        userApiService.incrementCount(request.getUserId());

        return REGISTRATION_MAPPER.toRegistrationDto(savedRegistration);
    }

    private void validateBook(RegistrationRequest request) {
        BookDto bookDto = libraryApiService.getBookDto(request.getBookId());
        if (!bookDto.isAvailable()) {
            throw new BusinessException("Book all ready registered!");
        }
    }

    private void validateUser(RegistrationRequest request) {
        UserDto userDto = userApiService.getUserDto(request.getUserId());
        if (userDto.getRegisteredBookCount() >= 3) {
            throw new BusinessException("User registration limit is reached!");
        }
    }
}
