package com.higroup.Buda.api.product.quantitylog;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.higroup.Buda.entities.ProductLeftLog;
import com.higroup.Buda.repositories.ProductLeftLogRepository;
import com.higroup.Buda.repositories.ProductLeftLogRepository.ViewProductLeftLogInfo;

@Service
public class ProductLeftLogService {
    private final ProductLeftLogRepository productLeftLogRepository;
    @Autowired
    public ProductLeftLogService(ProductLeftLogRepository productLeftLogRepository)
    {
        this.productLeftLogRepository = productLeftLogRepository;
    }

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
    public List<ProductLeftLog> findAllProductLeftLogByUserID(Long userID)
    {
        return this.productLeftLogRepository.findAllProductLeftLogByUserID(userID);
    }
    public ProductLeftLogViewDTO findAllFilterProductLeftLogByUserID(Long userID, ViewProductLeftLogFilter viewProductLeftLogFilter, Pageable pageable)
    {
        Page<ViewProductLeftLogInfo> productLeftLogs = this.productLeftLogRepository.findAllFilterproductLeftLogByUserID(
            userID,
            viewProductLeftLogFilter.getProductSKU(), 
            viewProductLeftLogFilter.getName(),
            viewProductLeftLogFilter.getAmountLeft(),
            pageable);
        return new ProductLeftLogViewDTO(productLeftLogs.getTotalElements(), productLeftLogs.toList());
    }
}
