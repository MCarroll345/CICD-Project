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

    @NotBlank
    @Min(8)
    @Max(20)
    private String username;

    @Min(8)
    @Max(20)
    @NotBlank
    private String password;

    @Min(16)
    private int age;

    @Email
    private String email;

    @NotBlank
    private String accountId;

    @NotEmpty
    private float bankBalance;

}
