package net.allaoua.customerservice;

import net.allaoua.customerservice.entities.Customer;
import net.allaoua.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Profile("!test")
    @Bean
    CommandLineRunner  commandLineRunner(CustomerRepository CustomerRepository, CustomerRepository customerRepository) {
        return (args) -> {
            customerRepository.save(Customer.builder().firstName("allaoua1").lastName("radouane1").email("email1@gmail.com").build());
            customerRepository.save(Customer.builder().firstName("allaoua2").lastName("radouan2").email("email2@gmail.com").build());
            customerRepository.save(Customer.builder().firstName("allaoua3").lastName("radouane3").email("email3@gmail.com").build());
        };
    }

}
