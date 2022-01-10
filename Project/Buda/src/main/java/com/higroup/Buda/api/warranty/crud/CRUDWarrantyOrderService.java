package com.higroup.Buda.api.warranty.crud;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.higroup.Buda.customDTO.RegisterWarrantyOrder;
import com.higroup.Buda.entities.*;
import com.higroup.Buda.repositories.CustomerRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.SellOrderRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.WarrantyOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;

@Service
public class CRUDWarrantyOrderService {
    private final WarrantyOrderRepository warrantyOrderRepository;
    private final UserRepository userRepository;
    private final SellOrderRepository sellOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public CRUDWarrantyOrderService(WarrantyOrderRepository warrantyOrderRepository, UserRepository userRepository, SellOrderRepository sellOrderRepository,
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
    public WarrantyOrder registerNewWarrantyOrder(Long userID, RegisterWarrantyOrder registerWarrantyOrder)
    {
        Long productID = registerWarrantyOrder.getProductID();
        Long sellOrderID = registerWarrantyOrder.getSellOrderID();
        Long customerID = registerWarrantyOrder.getCustomerID();
        Product product = this.productRepository.findProductByProductID(productID);
        if (!product.getUserID().equals(userID))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        Optional<SellOrder> sellOrder = this.sellOrderRepository.findSellOrderBySellOrderID(sellOrderID);
        if (!((sellOrder.isPresent()) && (sellOrder.get().getUserID().equals(userID))))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sell order not found");

        Optional<Customer> customer = this.customerRepository.findCustomerByCustomerID(customerID);
        if (!((customer.isPresent()) && (customer.get().getUserID().equals(userID))))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");

        if (!Objects.equals(customerID, sellOrder.get().getCustomer().getCustomerID()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid customer");
        }
        Set<SellOrderItem> sellOrderItems = sellOrder.get().getSellOrderItems();
        boolean ok = false;
        System.out.println(product);
        for (SellOrderItem sellOrderItem : sellOrderItems)
        {
            System.out.println(sellOrderItem.getProduct());
            if (sellOrderItem.getProduct().equals(product))
            {
                ok = true;
                break;
            }
        }
        if (!ok)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in sell order");
        }
        if (ZonedDateTime.now().isAfter(sellOrder.get().getCreationTime().plusDays(product.getWarrantyPeriod())) || product.getWarrantyPeriod() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Warranty period is over");
        }
        WarrantyOrder warrantyOrder = new WarrantyOrder();
        warrantyOrder.setUserID(userID);
        warrantyOrder.setProduct(product);
        warrantyOrder.setSellOrder(sellOrder.get());
        warrantyOrder.setCustomer(customer.get());
        warrantyOrder.setCreationTime(ZonedDateTime.now());
        warrantyOrder.setCustomerMessage(registerWarrantyOrder.getCustomerMessage());
        this.warrantyOrderRepository.save(warrantyOrder);
        return warrantyOrder;
    }
}