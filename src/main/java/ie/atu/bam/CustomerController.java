package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static javax.security.auth.callback.ConfirmationCallback.OK;

@RequestMapping("/customer")
@RestController
@CrossOrigin
public class CustomerController {
    private final BankClient bankClient;
    private final CustomerService customerService;
    public CustomerController(BankClient bankClient, CustomerService customerService) {
        this.bankClient = bankClient;
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
        if (customerService.createCustomer(customer) == 1) {
            return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Username taken", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @GetMapping("/login/{usrnm}/{psswrd}")
    public ResponseEntity<List<Object>> loginCust(@Valid @PathVariable String usrnm,@PathVariable String psswrd){
        try {
            List<Object> response;
            response = customerService.loginCust(usrnm,psswrd).stream().toList();
            return ResponseEntity.ok(response);
        } catch (RuntimeException eo) {
            return ResponseEntity.status(500).body(Collections.singletonList("Error: " + eo.getMessage()));
        }
    }

    @PutMapping("withDep/{IBAN}/{inout}/{num}")
    public ResponseEntity<String> withdrawDeposit(@Valid @PathVariable int IBAN,@PathVariable String inout, @PathVariable float num){
        try {
            String response = bankClient.withdrawDeposit(IBAN, inout, num).getBody();
            return ResponseEntity.ok(response);
        } catch (RuntimeException er) {
            return ResponseEntity.status(500).body("Error: " + er.getMessage());
        }
    }

    @GetMapping("/getRecs/{uID}")
    public List<Object> getRecs(@PathVariable Long uID){
        return bankClient.getRecs(uID);
    }

    @PutMapping("/transfer/{IBAN1}/{IBAN2}/{num}")
    public ResponseEntity<String> transfer(@PathVariable int IBAN1, @PathVariable int IBAN2, @PathVariable float num){
        try {
            String response = bankClient.transfer(IBAN1,IBAN2,num).getBody();
            return ResponseEntity.ok(response);
        } catch (RuntimeException er) {
            return ResponseEntity.status(500).body("Error: " + er.getMessage());
        }
    }


    @PutMapping("/updateAddress/{uID}/{newaddress}")
    public ResponseEntity<?> updateAddress(@Valid @PathVariable Long uID, @PathVariable String newaddress){
        if(customerService.updateAddress(uID,newaddress)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updateEmail/{uID}/{newemail}")
    public ResponseEntity<?> updateEmail(@Valid @PathVariable Long uID, @PathVariable String newemail){
        if(customerService.updateEmail(uID,newemail)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updatePhonenm/{uID}/{newphonenm}")
    public ResponseEntity<?> updatePhonenm(@Valid @PathVariable Long uID, @PathVariable String newphonenm){
        if(customerService.updatePhonenm(uID,newphonenm)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @DeleteMapping("deleteAcc/{uID}")
    public ResponseEntity<String> deleteAcc(@Valid @PathVariable Long uID){
        try {
            String response = customerService.deleteAcc(uID).getBody();
            return ResponseEntity.ok(response);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(500).body("Error: " + ex.getMessage());
        }
    }

}
