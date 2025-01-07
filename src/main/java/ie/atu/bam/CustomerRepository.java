package ie.atu.bam;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Customer c set c.address = ?2 where c.Id =?1")
    int addressUpdate(Long uID, String newaddress);

    @Transactional
    @Modifying
    @Query("update Customer c set c.phonenm = ?2 where c.Id =?1")
    int phonenmUpdate(Long uID, String newphonenm);

    @Transactional
    @Modifying
    @Query("update Customer c set c.email = ?2 where c.Id =?1")
    int emailUpdate(Long uID, String newemail);

    @Query("select c.Id from Customer c where c.username = ?1")
    Long getuID(String usrnm);
    boolean existsByUsernameAndPassword(String username, String password);

    @Transactional
    @Modifying
    @Query("delete Customer c where c.Id = ?1")
    int deleteAcc(Long uID);

}
