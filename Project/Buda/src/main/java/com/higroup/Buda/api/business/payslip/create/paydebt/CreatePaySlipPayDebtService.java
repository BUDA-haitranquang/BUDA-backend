package com.higroup.Buda.api.business.payslip.create.paydebt;

import com.higroup.Buda.entities.BuyOrder;
import com.higroup.Buda.entities.PaySlip;
import com.higroup.Buda.entities.Supplier;
import com.higroup.Buda.repositories.BuyOrderRepository;
import com.higroup.Buda.repositories.PaySlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CreatePaySlipPayDebtService {
    private final PaySlipRepository paySlipRepository;
    private final BuyOrderRepository buyOrderRepository;

    @Autowired
    public CreatePaySlipPayDebtService(PaySlipRepository paySlipRepository, BuyOrderRepository buyOrderRepository) {
        this.paySlipRepository = paySlipRepository;
        this.buyOrderRepository = buyOrderRepository;
    }

    public PaySlip createPaySlipPayDebt(Long userID, List<Long> buyOrderIDs){
        Double sum = 0.0;
        if (buyOrderIDs.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "List buy order must not be empty");
        }
        Optional<BuyOrder> b = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderIDs.get(0));
        if (b.isEmpty() || !(b.get().getUserID().equals(userID)))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order not found");
        }
        Supplier supplier =b.get().getSupplier();
        for (Long buyOrderID : buyOrderIDs) {
            Optional<BuyOrder> buyOrder = this.buyOrderRepository.findBuyOrderByBuyOrderID(buyOrderID);
            if (buyOrder.isEmpty())
            {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order not found");
            }
            if (!(buyOrder.get().getUserID().equals(userID))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Buy order not found");
            }
            sum += buyOrder.get().getTotalCost();
        }
        PaySlip paySlip = new PaySlip();
        paySlip.setSupplier(supplier);
        paySlip.setReceiverName(supplier.getName());
        paySlip.setReceiverContact(supplier.getPhoneNumber());
        paySlip.setCreationTime(ZonedDateTime.now());
        paySlip.setTotalCost(sum);
        paySlip.setUserID(userID);
        this.paySlipRepository.save(paySlip);
        return paySlip;
    }
}
