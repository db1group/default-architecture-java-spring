package br.com.project.service;

import br.com.project.adapter.CustomerAdapter;
import br.com.project.domain.CustomerDTO;
import br.com.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerAdapter::toDTO)
                .collect(Collectors.toList());
    }
}
