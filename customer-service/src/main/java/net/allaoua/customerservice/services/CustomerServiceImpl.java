package net.allaoua.customerservice.services;


import net.allaoua.customerservice.dto.CustomerDTO;
import net.allaoua.customerservice.entities.Customer;
import net.allaoua.customerservice.exceptions.CustomerNotFoundException;
import net.allaoua.customerservice.exceptions.EmailAlreadyExistException;
import net.allaoua.customerservice.mapper.CustomerMapper;
import net.allaoua.customerservice.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException {
        log.info(String.format("Saving new Customer => %s ", customerDTO.toString()));
        Optional<Customer> byEmail = customerRepository.findByEmail(customerDTO.getEmail());
        if(byEmail.isPresent()) {
            log.error(String.format("This email %s already exist", customerDTO.getEmail()));
            throw new EmailAlreadyExistException();
        }
        Customer customerToSave = customerMapper.toCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customerToSave);
        CustomerDTO result = customerMapper.toCustomerDTO(savedCustomer);
        return result;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerRepository.findAll();
        return customerMapper.fromListCustomers(allCustomers);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) throw new CustomerNotFoundException();
        return customerMapper.toCustomerDTO(customer.get());
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        return customerMapper.fromListCustomers(customers);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()) throw new CustomerNotFoundException();
        customerDTO.setId(id);
        Customer customerToUpdate = customerMapper.toCustomer(customerDTO);
        Customer updatedCustomer = customerRepository.save(customerToUpdate);
        return customerMapper.toCustomerDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()) throw new CustomerNotFoundException();
        customerRepository.deleteById(id);
    }
}