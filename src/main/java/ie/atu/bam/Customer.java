package ie.atu.bam;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank
    private String name;

    @NotBlank(message = "Please provide a username")
    private String username;

    @Size(min = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Please provide a password")
    private String password;

    @Min(16)
    private int age;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phonenm;

    @NotBlank
    private String address;

    public Customer(String iName, String iUsr, String iPsswrd, int iAge, String iEmail, String iPhonenm, String iAddress) {
        name = iName;
        username = iUsr;
        password = iPsswrd;
        age = iAge;
        email = iEmail;
        phonenm = iPhonenm;
        address = iAddress;
    }
}
