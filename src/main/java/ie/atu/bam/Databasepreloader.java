package ie.atu.bam;

import ie.atu.bam.Customer;
import ie.atu.bam.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Databasepreloader implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    public Databasepreloader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Customer cust1 = new Customer("Mark Carroll", "mark.carroll", "12345678", 21, "mark@atu.ie", "0871234567", "My house, Galway, Galway");
        Customer cust2 = new Customer("Alex Paton", "alex.paton", "23456789", 60, "alex@atu.ie", "0871234567", "His house, Galway, Galway");
        Customer cust3 = new Customer("Nathan Ferry", "nathan.ferry", "34567891", 17, "nathan@atu.ie", "0871234567", "His other house, Galway, Galway");
        Customer cust4 = new Customer("Shine Sujith", "shine.sujith", "45678912", 21, "shine@atu.ie", "0871234567", "His other other house, Galway, Galway");
        customerRepository.save(cust1);
        customerRepository.save(cust2);
        customerRepository.save(cust3);
        customerRepository.save(cust4);
    }
}
