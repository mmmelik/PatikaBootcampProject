package dev.melik.patikabootcampproject.controller.credit;

import dev.melik.patikabootcampproject.service.credit.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/credits/applications")
    public ResponseEntity<CreditApplicationResponse> applyForCredit(@RequestBody @Valid CreditApplicationRequest creditApplicationRequest){

        System.out.println(creditApplicationRequest.toString());
        return new ResponseEntity<>(
                CreditApplicationResponse.fromCredit(creditService.apply(creditApplicationRequest.toCredit()))
                ,HttpStatus.CREATED
        );
    }

    @GetMapping("/credits/{id}")
    public ResponseEntity<CreditApplicationResponse> getCreditApplicationById(@PathVariable Long id){

        return null;
    }

}
