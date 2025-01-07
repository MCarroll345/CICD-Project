package ie.atu.bam;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="bank-service", url = "http://localhost:8081/bank")
public interface BankClient {

    @PostMapping("/create/{uID}")
    String makeAccount(@PathVariable Long uID);

    @GetMapping("/login/{uID}")
    List<Object> loginAcc(@PathVariable Long uID);

    @PutMapping("withDep/{IBAN}/{inout}/{num}")
    ResponseEntity<String> withdrawDeposit(@Valid @PathVariable int IBAN, @PathVariable String inout, @PathVariable float num);

    @GetMapping("/getRecs/{uID}")
    List<Object> getRecs(@PathVariable Long uID);

    @PutMapping("/transfer/{IBAN1}/{IBAN2}/{num}")
    ResponseEntity<String> transfer(@PathVariable int IBAN1,@PathVariable int IBAN2,@PathVariable float num);

    @DeleteMapping("deleteAcc/{uID}")
    ResponseEntity<String> deleteAcc(@Valid @PathVariable Long uID);
}
