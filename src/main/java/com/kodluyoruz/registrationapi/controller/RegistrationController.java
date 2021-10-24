package com.kodluyoruz.registrationapi.controller;

import com.kodluyoruz.registrationapi.model.dto.RegistrationDto;
import com.kodluyoruz.registrationapi.model.request.RegistrationRequest;
import com.kodluyoruz.registrationapi.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("registrations")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService service;

    @PostMapping
    public RegistrationDto register(@Valid @RequestBody RegistrationRequest request){
        return service.register(request);
    }
}
