package guru.springframework.brewery.web.controllers;

import guru.springframework.brewery.domain.Customer;
import guru.springframework.brewery.repositories.CustomerRepository;

import guru.springframework.brewery.web.model.BeerOrderPagedList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BeerOrderControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;
    @Test
    void getOrder() {
        Customer customer =customerRepository.findAll().get(0);
        String url = "/api/v1/customers/" + customer.getId().toString() + "/orders";
        BeerOrderPagedList pagedList = restTemplate.getForObject( url , BeerOrderPagedList.class);
        assertThat(pagedList.getContent()).hasSize(1);
    }
}