package dev.melik.patikabootcampproject.adapter.rest.credit;

import dev.melik.patikabootcampproject.domain.credit.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @PostMapping("/credits/applications")
    public ResponseEntity<CreditApplicationResponse> applyForCredit(@RequestBody @Valid CreditApplicationRequest creditApplicationRequest, Principal principal){
        Long tckn=Long.parseLong(principal.getName());
        return new ResponseEntity<>(
                CreditApplicationResponse.fromCredit(creditService.apply(tckn,creditApplicationRequest.toCredit()))
                ,HttpStatus.CREATED
        );
    }

    @GetMapping("/credits/applications")
    public ResponseEntity<List<CreditResponse>> getAllCreditApplications(Principal principal){
        Long tckn=Long.parseLong(principal.getName());
        return new ResponseEntity<>(CreditResponse.fromCredit(creditService.getCreditsOf(tckn)),HttpStatus.OK);
    }

    @GetMapping("/credits/{id}")
    public ResponseEntity<CreditResponse> getCreditApplicationById(@PathVariable Long id, Principal principal){
        Long tckn=Long.parseLong(principal.getName());
        return new ResponseEntity<>(CreditResponse.fromCredit(creditService.getCreditById(tckn,id)),HttpStatus.OK);
    }

}
