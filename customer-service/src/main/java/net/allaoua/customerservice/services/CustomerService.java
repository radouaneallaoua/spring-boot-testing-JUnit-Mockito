package net.allaoua.customerservice.services;
import net.allaoua.customerservice.dto.CustomerDTO;
import net.allaoua.customerservice.exceptions.CustomerNotFoundException;
import net.allaoua.customerservice.exceptions.EmailAlreadyExistException;


import java.util.List;

public interface CustomerService {
    CustomerDTO saveNewCustomer(CustomerDTO customerDTO) throws EmailAlreadyExistException;
    List<CustomerDTO> getAllCustomers();
    CustomerDTO findCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDTO> searchCustomers(String keyword);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO)throws CustomerNotFoundException;
    void deleteCustomer(Long id)throws CustomerNotFoundException;
}