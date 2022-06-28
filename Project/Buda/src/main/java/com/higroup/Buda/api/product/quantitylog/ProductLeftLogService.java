package com.higroup.Buda.api.product.quantitylog;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.entities.User;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductRepository;
import com.higroup.Buda.repositories.UserRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository.ViewProductLeftLogInfo;
import com.higroup.Buda.util.Checker.PresentChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductLeftLogService {
    private final ProductLeftLogRepository productLeftLogRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductLeftLogService(ProductLeftLogRepository productLeftLogRepository, UserRepository userRepository, ProductRepository productRepository)
    {
        this.userRepository = userRepository;
        this.productLeftLogRepository = productLeftLogRepository;
        this.productRepository = productRepository;
    }

    @Autowired
    private PresentChecker presentChecker;

    public ProductLeftLog findProductLeftLogByProductLeftLogID(Long userID, Long productLeftLogID)
    {
        Optional<ProductLeftLog> productLeftLog = this.productLeftLogRepository.findProductLeftLogByProductLeftLogID(productLeftLogID);
        if ((productLeftLog.isPresent()) && (productLeftLog.get().getUserID().equals(userID)))
        {
            return productLeftLog.get();
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Log not found");
    }
    public ResponseEntity<?> registerNewProductLeftLog(Long userID, ProductLeftLog productLeftLog)
    {
        productLeftLog.setUserID(userID);
        this.productLeftLogRepository.save(productLeftLog);
        return ResponseEntity.ok().body(productLeftLog);
    }
    public List<ProductLeftLog> findAllProductLeftLogByProduct(Long userID, Long productID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByProduct(userID, productID);
    }
    public List<ProductLeftLog> findAllProductLeftLogByStaffID(Long userID, Long staffID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByStaffID(userID, staffID);
    }
    public List<ViewProductLeftLogInfo> findAllProductLeftLogByUserID(Long userID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByUserID(userID);
    }
}
