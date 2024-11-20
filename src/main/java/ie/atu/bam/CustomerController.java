package ie.atu.bam;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    private final CustomerServices customerServices;

    public CustomerController(CustomerServices customerServices) {
        this.customerServices = customerServices;
    }

    @GetMapping("/customerUser")
    public ResponseEntity<?> getCustomer(@PathVariable String username){
        if(username.length() < 8 || username.isBlank()){
            return ResponseEntity.badRequest().body("Username is invalid");
        }

        Customer customer = customerServices.getCustomer(username);

        if(customer == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }


}
