package br.com.project.repository;

import br.com.project.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerRepository {

    public List<Customer> findAll() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setNome("Customer");
        return List.of(customer);
    }
}
