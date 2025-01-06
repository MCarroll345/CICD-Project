package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Object> loginCust(@Valid @PathVariable String usrnm,@PathVariable String psswrd){
        return customerService.loginCust(usrnm,psswrd);
    }

    @PutMapping("withDep/{uID}/{inout}/{num}")
    public ResponseEntity<String> withdrawDeposit(@Valid @PathVariable Long uID,@PathVariable String inout, @PathVariable float num){
        return bankClient.withdrawDeposit(uID, inout, num);
    }

    @GetMapping("/getRecs/{uID}")
    public List<Object> getRecs(@PathVariable Long uID){
        return bankClient.getRecs(uID);
    }

    @PutMapping("/transfer/{IBAN1}/{uID1}/{IBAN2}/{uID2}/{num}")
    public ResponseEntity<String> transfer(@PathVariable int IBAN1, @PathVariable Long uID1, @PathVariable int IBAN2, @PathVariable Long uID2, @PathVariable float num){
        return bankClient.transfer(IBAN1, uID1, IBAN2, uID2, num);
    }



    @PutMapping("/updateAddress/{oldaddress}/{newaddress}")
    public ResponseEntity<?> updateAddress(@Valid @PathVariable String oldaddress, @PathVariable String newaddress){
        if(customerService.updateAddress(oldaddress,newaddress)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updateEmail/{oldemail}/{newemail}")
    public ResponseEntity<?> updateEmail(@Valid @PathVariable String oldemail, @PathVariable String newemail){
        if(customerService.updateEmail(oldemail,newemail)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PutMapping("/updatePhonenm/{oldphonenm}/{newphonenm}")
    public ResponseEntity<?> updatePhonenm(@Valid @PathVariable int oldphonenm, @PathVariable int newphonenm){
        if(customerService.updatePhonenm(oldphonenm,newphonenm)==1){
            return new ResponseEntity<>("Update Successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.PRECONDITION_FAILED);
        }
    }

}
