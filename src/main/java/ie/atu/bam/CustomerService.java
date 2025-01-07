package ie.atu.bam;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
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

    public int updateAddress(Long uID, String newa){
        if(customerRepository.addressUpdate(uID,newa) == 1){
            System.out.println("Update successful");
            return 1;
        }
        else{
            System.out.println("Update failed");
            return 0;
        }
    }

    public int updateEmail(Long uID, String newe){
        if(customerRepository.emailUpdate(uID,newe) == 1){
            System.out.println("Update successful");
            return 1;
        }
        else{
            System.out.println("Update failed");
            return 0;
        }
    }

    public int updatePhonenm(Long uID, String newnm){
        if(customerRepository.phonenmUpdate(uID,newnm) == 1){
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

    public ResponseEntity<String> deleteAcc(Long uID){
        customerRepository.deleteAcc(uID);
        System.out.println("Account ID "+uID+" Deleted");
        return new ResponseEntity<>("Account deleted "+ bankClient.deleteAcc(uID), HttpStatus.OK);
    }

}

