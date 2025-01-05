package ie.atu.bam;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name="bank-service", url = "http://localhost:8081/bank")
public interface BankClient {

    @PostMapping("/create/{uID}")
    String makeAccount(@PathVariable Long uID);

    @GetMapping("/login/{uID}")
    List<Object> loginAcc(@PathVariable Long uID);

    @PutMapping("withDep/{uID}/{inout}/{num}")
    ResponseEntity<String> withdrawDeposit(@Valid @PathVariable Long uID, @PathVariable String inout, @PathVariable float num);

}