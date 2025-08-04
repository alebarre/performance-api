package io.com.performance.service;

import io.com.performance.DTO.UserDTO;
import io.com.performance.domain.Customer;
import io.com.performance.domain.Invoice;
import org.springframework.data.domain.Page;

public interface CustomerService {

    //Customers functionalities
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Page<Customer> getCustomers(int page, int size);
    Iterable<Customer> getCustomers();
    Customer getCustomer(Long id);
    Page<Customer> serachCustomers(String name, int page, int size);

    //Invoice functionalities
    Invoice  createInvoice(Invoice invoice);
    Page<Invoice> getInvoices(int page, int size);
    void addInvoiceToACustomer(Long id, Invoice invoice);

    Invoice getInvoice(Long id);
}
