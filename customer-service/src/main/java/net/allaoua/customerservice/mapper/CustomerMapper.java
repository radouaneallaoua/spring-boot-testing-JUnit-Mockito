package net.allaoua.customerservice.mapper;

import net.allaoua.customerservice.dto.CustomerDTO;
import net.allaoua.customerservice.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerMapper {
    private final ModelMapper modelMapper=new ModelMapper();

    public CustomerDTO toCustomerDTO(Customer customer) {
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public Customer toCustomer(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }

    public List<CustomerDTO> fromListCustomers(List<Customer> customers) {
        return customers.stream().map(c->modelMapper.map(c,CustomerDTO.class)).toList();
    }
}
