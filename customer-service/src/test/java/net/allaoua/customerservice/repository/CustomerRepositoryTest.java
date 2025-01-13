package net.allaoua.customerservice.repository;

import net.allaoua.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {
     @Autowired
     private CustomerRepository customerRepository;

     @BeforeEach //avant chaque test
     public void setUp(){
          customerRepository.save(Customer.builder().firstName("allaoua1").lastName("radouane1").email("email1@gmail.com").build());
          customerRepository.save(Customer.builder().firstName("sassi").lastName("radouan2").email("email2@gmail.com").build());
          customerRepository.save(Customer.builder().firstName("toto").lastName("radouane3").email("email3@gmail.com").build());
     }


     @Test
     public void shouldFindCustomerByEmail(){
          String givenEmail="email1@gmail.com";
          Optional<Customer> result = customerRepository.findByEmail(givenEmail);
          assertThat(result).isPresent();

     }

     @Test
     public void shouldNotFindCustomerByEmail(){
          String givenEmail="test@gmail.com";
          Optional<Customer> result = customerRepository.findByEmail(givenEmail);
          assertThat(result).isEmpty();
     }

     @Test
     public void shouldFindCustomersByFirstName(){
          String keyword="a";
          List<Customer> expected=List.of(
                  Customer.builder().firstName("allaoua1").lastName("radouane1").email("email1@gmail.com").build(),
                  Customer.builder().firstName("sassi").lastName("radouan2").email("email2@gmail.com").build()
          );
          List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
          assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
     }

}