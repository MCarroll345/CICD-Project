package ie.atu.bam;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private BankClient bankClient;
    private CustomerRepository customerRepository;

    public CustomerService(BankClient bankClient, CustomerRepository customerRepository) {
        this.bankClient = bankClient;
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public List<Object> returnRec(Long uID){
        return bankClient.getRecs(uID);
    }


    public int createCustomer(Customer customer){
        if(!customerRepository.existsByUsernameAndPassword(customer.getUsername(), customer.getPassword())){
            customerRepository.save(customer);
            System.out.println("Customer created: " + customer + bankClient.makeAccount(customer.getId()));
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
        if(customerRepository.emailUpdate(olde,newe) == 1){
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

    public List<Object> loginCust(String usrnm, String psswrd){
        if(customerRepository.existsByUsernameAndPassword(usrnm, psswrd)){
            System.out.println("Login successful");
            List<Object> CombList = new ArrayList<Object>();
            CombList.addAll(customerRepository.findByUsername(usrnm));
            CombList.addAll(bankClient.loginAcc(customerRepository.getuID(usrnm)));
            return CombList;
        }
        else {
            System.out.println("Login failed");
            return null;
        }
    }

}

