package com.higroup.Buda.services;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.WarrantyOrder;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.WarrantyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WarrantyOrderService {
    private final WarrantyOrderRepository warrantyOrderRepository;
    private final UserRepository userRepository;
    private final SellOrderRepository sellOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public WarrantyOrderService(WarrantyOrderRepository warrantyOrderRepository, UserRepository userRepository, SellOrderRepository sellOrderRepository,
    ProductRepository productRepository, CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.userRepository = userRepository;
        this.warrantyOrderRepository = warrantyOrderRepository;
    }
    public List<WarrantyOrder> findAllWarrantyOrderByUserID(Long userID)
    {
        return this.warrantyOrderRepository.findAllByUserID(userID);
    }
    public List<WarrantyOrder> findAllWarrantyOrderByProductID(Long userID, Long productID)
    {
        Product product = this.productRepository.findProductByProductID(productID);
        if ((product!=null) && (product.getUserID().equals(userID)))
        {
            return this.warrantyOrderRepository.findAllByProductID(productID);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    public List<WarrantyOrder> findAllWarrantyOrderByCustomerID(Long userID, Long customerID)
    {
        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if ((customer.isPresent()) && (customer.get().getUserID().equals(userID)))
        {
            return this.warrantyOrderRepository.findAllByCustomerID(customerID);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
    }
}
