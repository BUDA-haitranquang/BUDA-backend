package com.higroup.Buda.api.business.sell.warranty.view;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.Customer;
import com.higroup.Buda.entities.Product;
import com.higroup.Buda.entities.WarrantyOrder;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.WarrantyOrderRepository;

@Service
public class WarrantyOrderViewService {
    private final WarrantyOrderRepository warrantyOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public WarrantyOrderViewService(WarrantyOrderRepository warrantyOrderRepository,
                                    ProductRepository productRepository, CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.warrantyOrderRepository = warrantyOrderRepository;
    }
    public List<WarrantyOrder> findAllWarrantyOrderByUserID(Long userID)
    {
        return this.warrantyOrderRepository.findAllByUserID(userID);
    }
    public List<WarrantyOrder> findAllWarrantyOrderByProductID(Long userID, Long productID)
    {
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
        if (product.getUserID().equals(userID))
        {
            return this.warrantyOrderRepository.findAllByProductID(productID);
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not belong to user");
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
