package ie.atu.bam;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
@RestController
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

    @PostMapping("/createPerson")
    public ResponseEntity<String>create(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
    }


}
