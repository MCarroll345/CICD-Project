package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    
    @PutMapping("/update/{name}/{upname}")
    public String updateName(@PathVariable String name, @PathVariable String upname){
        customerService.updateName(name,upname);
        return "Updated";
    }

    @GetMapping("/login/{usrnm}/{psswrd}")
    public List<Customer> loginCust(@PathVariable String usrnm,@PathVariable String psswrd){
        return customerService.loginCust(usrnm,psswrd);
    }

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody Customer customer) {
        if (customerService.createCustomer(customer) == 1) {
            return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Username taken", HttpStatus.PRECONDITION_FAILED);
        }
    }


}
