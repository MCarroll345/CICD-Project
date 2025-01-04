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

    public int createCustomer(Customer customer){
        if(!customerRepository.existsByUsernameAndPassword(customer.getUsername(), customer.getPassword())){
            customerRepository.save(customer);
            System.out.println("Customer created: " + customer);
            return 1;
        }
        else {
            System.out.println("Username already taken");
            return 0;
        }
    }

    public int updateAddress(String olda, String newa){
        if(customerRepository.addressUpdate(olda,newa) == 1){
            System.out.println("Update successful");
            return 1;
        }
        else{
            System.out.println("Update failed");
            return 0;
        }
    }

    public int updateEmail(String olde, String newe){
        if(customerRepository.addressUpdate(olde,newe) == 1){
            System.out.println("Update successful");
            return 1;
        }
        else{
            System.out.println("Update failed");
            return 0;
        }
    }

    public int updatePhonenm(int oldnm, int newnm){
        if(customerRepository.phonenmUpdate(oldnm,newnm) == 1){
            System.out.println("Update successful");
            return 1;
        }
        else{
            System.out.println("Update failed");
            return 0;
        }
    }

    public List<Customer> loginCust(String usrnm, String psswrd){
        if(customerRepository.existsByUsernameAndPassword(usrnm, psswrd)){
            System.out.println("Login successful");
            return customerRepository.findByUsername(usrnm);
        }
        else {
            System.out.println("Login failed");
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

