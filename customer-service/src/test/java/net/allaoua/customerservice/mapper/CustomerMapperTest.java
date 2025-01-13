package net.allaoua.customerservice.mapper;

import net.allaoua.customerservice.dto.CustomerDTO;
import net.allaoua.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class CustomerMapperTest {

    CustomerMapper underTest = new CustomerMapper();

    @Test
    public void shouldMapCustomerToCustomerDTO(){
        Customer givenCustomer = Customer.builder()
                .lastName("test")
                .firstName("radouane")
                .email("test@gmail.com")
                .build();
        CustomerDTO expected = CustomerDTO.builder()
                .lastName("test")
                .firstName("radouane")
                .email("test@gmail.com")
                .build();
        CustomerDTO result = underTest.toCustomerDTO(givenCustomer);
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldMapCustomerDTOToCustomer(){
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L)
                .lastName("test")
                .firstName("radouane")
                .email("test@gmail.com")
                .build();
        Customer expected = Customer.builder()
                .id(1L)
                .lastName("test")
                .firstName("radouane")
                .email("test@gmail.com")
                .build();
        Customer result = underTest.toCustomer(givenCustomerDTO);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldMapListCustomersToListCustomersDTO(){
        List<Customer> givenCustomers =List.of(
                Customer.builder()
                    .id(1L)
                    .lastName("test1")
                    .firstName("radouane1")
                    .email("test1@gmail.com")
                    .build(),
                Customer.builder()
                    .id(2L)
                    .lastName("test2")
                    .firstName("radouane2")
                    .email("test2@gmail.com")
                    .build()
        );
        List<CustomerDTO> expected = List.of(CustomerDTO.builder()
                        .id(1L)
                        .lastName("test1")
                        .firstName("radouane1")
                        .email("test1@gmail.com")
                        .build(),
                CustomerDTO.builder()
                        .id(2L)
                        .lastName("test2")
                        .firstName("radouane2")
                        .email("test2@gmail.com")
                        .build()
        );
        List<CustomerDTO> result = underTest.fromListCustomers(givenCustomers);
        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void shouldNotMapCustomerToCustomerDTO(){
        Customer givenCustomer = null;
        assertThatThrownBy(()->underTest.toCustomerDTO(givenCustomer)).isInstanceOf(IllegalArgumentException.class);
    }

}