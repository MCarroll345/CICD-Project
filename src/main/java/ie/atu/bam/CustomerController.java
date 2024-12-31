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

    /*
    @GetMapping("/{customerUser}")
    public ResponseEntity<?> getCustomer(@PathVariable String customerUser){
        if(customerUser.length() < 8 || customerUser.isBlank()){
            return ResponseEntity.badRequest().body("Username is invalid");
        }

        Customer customer = customerService.getCustomer(customerUser);

        if(customer == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }*/

    @GetMapping("/getAll")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }



    @PostMapping("/createPerson")
    public ResponseEntity<String>create(@Valid @RequestBody Customer customer) {
        customerService.createCustomer(customer);
        return new ResponseEntity<>("Person created successfully", HttpStatus.OK);
    }


}
