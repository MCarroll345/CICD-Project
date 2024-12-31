package ie.atu.bam;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer){
        customerRepository.save(customer);
        System.out.println("Customer created: " + customer);
    }

    /*
    public Customer getCustomer(String username){
        for(Customer c:myList){
            if(c.getUsername() == username){
                return getCustomer(username);
            }
        }

        return new Customer();
    }*/
}

