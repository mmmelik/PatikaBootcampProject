package dev.melik.patikabootcampproject.controller.customer;

import dev.melik.patikabootcampproject.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<RegisterCustomerResponse> register(@RequestBody @Valid RegisterCustomerRequest registerCustomerRequest){
        return new ResponseEntity<>(RegisterCustomerResponse.fromCustomer(customerService.saveCustomer(registerCustomerRequest.toCustomer())),HttpStatus.CREATED);
    }

}
