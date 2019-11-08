package br.com.project.resource;

import br.com.project.domain.CustomerDTO;
import br.com.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/clientes")
    public ResponseEntity<List<CustomerDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }
}
