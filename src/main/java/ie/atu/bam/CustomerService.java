package ie.atu.bam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        if(!customerRepository.existsByUsernameAndPassword(customer.getUsername(), customer.getPassword())){
            customerRepository.save(customer);
            System.out.println("Customer created: " + customer);
        }
        else {
            System.out.println("Username already taken");
        }

    }

    public void updateName(String name, String upname){
        return ;
    }

    public List<Customer> loginCust(String usrnm, String psswrd){
        if(customerRepository.existsByUsernameAndPassword(usrnm, psswrd)){
            System.out.println("Success");
            return customerRepository.findByUsername(usrnm);
        }
        else {
            System.out.println("Fail");
            return null;
        }
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

