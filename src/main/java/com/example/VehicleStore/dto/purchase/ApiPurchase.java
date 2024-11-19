package com.example.VehicleStore.dto.purchase;

import com.example.VehicleStore.dto.car.ApiCar;
import com.example.VehicleStore.dto.moto.ApiMoto;
import com.example.VehicleStore.dto.user.ApiUser;
import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.entity.enums.PaymentMethod;
import com.example.VehicleStore.entity.purchase.enums.PurchaseStatus;
import com.example.VehicleStore.entity.purchase.enums.PurchaseType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ApiPurchase {

    private Long id;
    private ApiUser client;
    private ApiUser seller;
    private ApiCar car;
    private ApiMoto motorcycle;
    private BigDecimal purchasePrice;
    private LocalDate purchaseDate;
    private PaymentMethod paymentMethod;
    private PurchaseStatus status;
    private BigDecimal discount;
    private PurchaseType purchaseType;
    private String location;
    private String transactionId;

    public ApiPurchase (Purchase purchase){
        id = purchase.getId();
        purchasePrice = purchase.getPurchasePrice();
        purchaseDate = purchase.getPurchaseDate();
        status = purchase.getStatus();
        paymentMethod = purchase.getPaymentMethod();
        discount = purchase.getDiscount();
        purchaseType = purchase.getPurchaseType();
        location = purchase.getLocation();
        transactionId = purchase.getTransactionId();


        if (purchase.getClient() != null){
            client = new ApiUser(purchase.getClient());
        }
        if (purchase.getSeller() != null){
            seller = new ApiUser(purchase.getSeller());
        }
        if (purchase.getCar() != null){
            car = new ApiCar(purchase.getCar());
        }
        if (purchase.getMotorcycle() != null){
            motorcycle = new ApiMoto(purchase.getMotorcycle());
        }
    }

}
