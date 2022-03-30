package com.higroup.Buda.api.business.sell.warranty.create;

import com.higroup.Buda.customDTO.RegisterWarrantyOrder;
import com.higroup.Buda.entities.*;
import com.higroup.Buda.entities.enumeration.Status;
import com.higroup.Buda.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class WarrantyOrderCreateService {
    private final WarrantyOrderRepository warrantyOrderRepository;
    private final UserRepository userRepository;
    private final SellOrderRepository sellOrderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    @Autowired
    public WarrantyOrderCreateService(WarrantyOrderRepository warrantyOrderRepository, UserRepository userRepository, SellOrderRepository sellOrderRepository,
                                      ProductRepository productRepository, CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.sellOrderRepository = sellOrderRepository;
        this.userRepository = userRepository;
        this.warrantyOrderRepository = warrantyOrderRepository;
    }
    @Transactional
    public WarrantyOrder createNewWarrantyOrder(Long userID, RegisterWarrantyOrder registerWarrantyOrder)
    {
        Optional<User> user = this.userRepository.findUserByUserID(userID);
        if (user.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        Long productID = registerWarrantyOrder.getProductID();
        Long sellOrderID = registerWarrantyOrder.getSellOrderID();
        Long customerID = registerWarrantyOrder.getCustomerID();
        Optional<Product> opProduct = this.productRepository.findProductByProductID(productID);
        if(!opProduct.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = opProduct.get();
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Over Warranty period");
        }
        WarrantyOrder warrantyOrder = new WarrantyOrder();
        warrantyOrder.setStatus(Status.RECEIVING);
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
