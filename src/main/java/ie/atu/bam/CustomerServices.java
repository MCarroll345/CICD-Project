package ie.atu.bam;

import org.springframework.stereotype.Service;

@Service
public class CustomerServices {
    private CustomerRepository customerRepository;

    public CustomerServices(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer){
        customerRepository.save(customer);
        System.out.println("Customer created: " + customer);
    }
}
