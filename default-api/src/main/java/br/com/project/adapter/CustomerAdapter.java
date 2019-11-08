package br.com.project.adapter;

import br.com.project.entity.Customer;
import br.com.project.domain.CustomerDTO;

public class CustomerAdapter {

    public static CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .setId(customer.getId())
                .setNome(customer.getNome())
                .build();
    }
}
