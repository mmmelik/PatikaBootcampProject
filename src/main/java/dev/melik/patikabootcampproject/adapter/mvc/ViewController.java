package dev.melik.patikabootcampproject.adapter.mvc;

import dev.melik.patikabootcampproject.adapter.rest.credit.CreditApplicationRequest;
import dev.melik.patikabootcampproject.adapter.rest.credit.CreditApplicationResponse;
import dev.melik.patikabootcampproject.adapter.rest.customer.LoginRequest;
import dev.melik.patikabootcampproject.adapter.rest.credit.CreditResponse;
import dev.melik.patikabootcampproject.adapter.rest.customer.RegisterCustomerRequest;
import dev.melik.patikabootcampproject.domain.credit.CreditService;
import dev.melik.patikabootcampproject.domain.customer.Customer;
import dev.melik.patikabootcampproject.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ViewController {

    private final CustomerService customerService;

    private final CreditService creditService;

    @GetMapping("/")
    public String rootPage(){
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("registerRequest", new RegisterCustomerRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid RegisterCustomerRequest registerCustomerRequest){
        customerService.saveCustomer(registerCustomerRequest.toCustomer());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication){
        Long tckn=Long.parseLong(((User)authentication.getPrincipal()).getUsername());
        Customer customer=customerService.getCustomerByTckn(tckn);

        model.addAttribute("customerName",customer.getName());
        model.addAttribute("customerTckn",customer.getTckn());
        model.addAttribute("creditScore",creditService.getCreditScore(customer.getTckn()));
        model.addAttribute("creditApplicationRequest", new CreditApplicationRequest());
        return "credit";
    }

    @GetMapping("/credits/applications")
    public String applications(Model model, Authentication authentication){
        Long tckn=Long.parseLong(((User)authentication.getPrincipal()).getUsername());
        model.addAttribute("credits",creditService.getCreditsOf(tckn));
        return "oldApplications";
    }

    @PostMapping("/credits/applications")
    public String applyCredit(@ModelAttribute @Valid CreditApplicationRequest creditApplicationRequest,Authentication authentication){
        Long tckn=Long.parseLong(((User)authentication.getPrincipal()).getUsername());
        long creditId = CreditApplicationResponse.fromCredit(creditService.apply(tckn,creditApplicationRequest.toCredit())).getId();
        return "redirect:/credits/"+creditId;
    }

    @GetMapping("/credits/{id}")
    public String creditById(Model model, @PathVariable Long id, Principal principal){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Long tckn=Long.parseLong(principal.getName());

        CreditResponse creditResponse=CreditResponse.fromCredit(creditService.getCreditById(tckn,id));
        model.addAttribute("credit",creditResponse);
        model.addAttribute("date",dateTimeFormatter.format(creditResponse.getCreationDate()));
        model.addAttribute("creditScore",creditService.getCreditScore(creditResponse.getCustomerTckn()));
        return "creditResult";
    }
}
