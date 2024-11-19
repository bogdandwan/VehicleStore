package com.example.VehicleStore.search.purchase;

import com.example.VehicleStore.entity.enums.PaymentMethod;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.purchase.enums.PurchaseStatus;
import com.example.VehicleStore.entity.purchase.enums.PurchaseType;
import com.example.VehicleStore.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class PurchaseSearch {

    private User client;
    private User seller;
    private Car car;
    private Motorcycle motorcycle;
    private BigDecimal purchasePrice;
    private BigDecimal purchasePriceTo;
    private BigDecimal purchasePriceFrom;
    private LocalDate purchaseDate;
    private LocalDate purchaseDateTo;
    private LocalDate purchaseDateFrom;
    private PurchaseStatus status;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private BigDecimal discount;
    private BigDecimal discountTo;
    private BigDecimal discountFrom;
    private PurchaseType purchaseType;
    private String location;
    private CarBrand carBrand;
    private MotoBrand motoBrand;
    private Integer carYear;
    private Integer motoYear;

}
