package com.example.VehicleStore.controller.purchase;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.dto.purchase.ApiPurchase;
import com.example.VehicleStore.entity.enums.PaymentMethod;
import com.example.VehicleStore.entity.items.car.Car;
import com.example.VehicleStore.entity.items.car.CarBrand;
import com.example.VehicleStore.entity.items.moto.MotoBrand;
import com.example.VehicleStore.entity.items.moto.Motorcycle;
import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.entity.purchase.enums.PurchaseStatus;
import com.example.VehicleStore.entity.purchase.enums.PurchaseType;
import com.example.VehicleStore.entity.user.User;
import com.example.VehicleStore.exceptions.NotFoundException;
import com.example.VehicleStore.search.purchase.PurchaseSearch;
import com.example.VehicleStore.search.sort.purchase.PurchaseSort;
import com.example.VehicleStore.service.service.car.CarBrandService;
import com.example.VehicleStore.service.service.car.CarService;
import com.example.VehicleStore.service.service.moto.MotoBrandService;
import com.example.VehicleStore.service.service.moto.MotoService;
import com.example.VehicleStore.service.service.purchase.PurchaseService;
import com.example.VehicleStore.service.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PurchaseController {

    final PurchaseService purchaseService;
    final CarService carService;
    final MotoService motoService;
    final UserService userService;
    final CarBrandService carBrandService;
    final MotoBrandService motoBrandService;


    @PostMapping("/purchase")
    public void saveOrUpdatePurchase(@RequestBody @Valid ApiPurchase apiPurchase) {

        final Purchase purchase;


        if (apiPurchase.getId() == null) {
            purchase = new Purchase();
            purchase.setPurchaseDate(LocalDate.now());
            purchase.setStatus(PurchaseStatus.PENDING);
        } else {
            purchase = purchaseService.findById(apiPurchase.getId());
            if (purchase == null) {
                throw new NotFoundException("Purchase not found.");
            }


            if (purchase.getStatus() == PurchaseStatus.PENDING || purchase.getStatus() == PurchaseStatus.COMPLETED) {
                throw new IllegalStateException("Purchase cannot be executed in its current state: " + purchase.getStatus());
            }
        }


        User client = userService.findById(apiPurchase.getClient().getId());
        if (client == null) {
            throw new NotFoundException("Client not found.");
        }
        purchase.setClient(client);


        User seller = userService.findById(apiPurchase.getSeller().getId());
        if (seller == null) {
            throw new NotFoundException("Seller not found.");
        }
        purchase.setSeller(seller);


        if (apiPurchase.getCar() != null) {
            Car car = carService.findById(apiPurchase.getCar().getId());
            if (car == null) {
                throw new NotFoundException("Car not found.");
            }
            purchase.setCar(car);
            purchase.setMotorcycle(null);
        } else if (apiPurchase.getMotorcycle() != null) {
            Motorcycle motorcycle = motoService.findById(apiPurchase.getMotorcycle().getId());
            if (motorcycle == null) {
                throw new NotFoundException("Motorcycle not found.");
            }
            purchase.setMotorcycle(motorcycle);
            purchase.setCar(null);
        } else {
            throw new IllegalArgumentException("Either car or motorcycle must be provided.");
        }


        if (apiPurchase.getPurchasePrice() == null || apiPurchase.getPurchasePrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Purchase price must be greater than zero.");
        }

        purchase.setPurchasePrice(apiPurchase.getPurchasePrice());

        if (apiPurchase.getPaymentMethod() == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }

        purchase.setPaymentMethod(apiPurchase.getPaymentMethod());
        purchase.setTransactionId(apiPurchase.getTransactionId());

        if (apiPurchase.getDiscount() != null) {
            if (apiPurchase.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Discount cannot be negative.");
            }
            purchase.setDiscount(apiPurchase.getDiscount());
        }


        if (apiPurchase.getPurchaseType() != null) {
            purchase.setPurchaseType(apiPurchase.getPurchaseType());
        }

        purchase.setLocation(apiPurchase.getLocation());


        if (purchase.getStatus() == PurchaseStatus.CANCELED) {
            purchase.setStatus(PurchaseStatus.ACTIVE);
        }


        purchaseService.save(purchase);
    }



    @GetMapping("/purchase/{id}")
    public ApiPurchase getById(@PathVariable Long id){
        final Purchase purchase = purchaseService.findById(id);
        if (purchase == null){
            throw new NotFoundException("Purchase not found!");
        }
        return new ApiPurchase(purchase);
    }




    @GetMapping("/purchases")
    public List<ApiPurchase> getAll(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) Long sellerId,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) Long motorcycleId,
            @RequestParam(required = false) BigDecimal purchasePrice,
            @RequestParam(required = false) BigDecimal purchasePriceFrom,
            @RequestParam(required = false) BigDecimal purchasePriceTo,
            @RequestParam(required = false) LocalDate purchaseDate,
            @RequestParam(required = false) LocalDate purchaseDateFrom,
            @RequestParam(required = false) LocalDate purchaseDateTo,
            @RequestParam(required = false) PurchaseStatus purchaseStatus,
            @RequestParam(required = false) PurchaseType purchaseType,
            @RequestParam(required = false) String transactionId,
            @RequestParam(required = false) BigDecimal discount,
            @RequestParam(required = false) BigDecimal discountFrom,
            @RequestParam(required = false) BigDecimal discountTo,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) PaymentMethod paymentMethod,
            @RequestParam(required = false) Long carBrandId,
            @RequestParam(required = false) Long motoBrandId,
            @RequestParam(required = false) Integer carYear,
            @RequestParam(required = false) Integer motoYear,
            @RequestParam(required = false) PurchaseSort sort,
            Pagination pagination){

        final PurchaseSearch search = new PurchaseSearch()
                .setPurchasePrice(purchasePrice)
                .setPurchasePriceFrom(purchasePriceFrom)
                .setPurchasePriceTo(purchasePriceTo)
                .setPurchaseDate(purchaseDate)
                .setPurchaseDateTo(purchaseDateTo)
                .setPurchaseDateFrom(purchaseDateFrom)
                .setStatus(purchaseStatus)
                .setPurchaseType(purchaseType)
                .setTransactionId(transactionId)
                .setDiscount(discount)
                .setDiscountTo(discountTo)
                .setDiscountFrom(discountFrom)
                .setLocation(location)
                .setPaymentMethod(paymentMethod)
                .setCarYear(carYear)
                .setMotoYear(motoYear);

        if (search.getClient() != null){
            final User client = userService.findById(clientId);
            if (client == null){
                throw new NotFoundException("Client not found");
            }
            search.setClient(client);
        }
        if (search.getSeller() != null){
            final User seller = userService.findById(sellerId);
            if (seller == null){
                throw new NotFoundException("Seller not found");
            }
            search.setSeller(seller);
        }
        if (search.getCar() != null){
            final Car car = carService.findById(carId);
            if (car == null){
                throw new NotFoundException("Car not found.");
            }
            search.setCar(car);
        }
        if (search.getMotorcycle() != null){
            final Motorcycle motorcycle = motoService.findById(motorcycleId);
            if (motorcycle == null){
                throw new NotFoundException("Motorcycle not found.");
            }
            search.setMotorcycle(motorcycle);
        }
        if (search.getCarBrand() != null){
            final CarBrand carBrand = carBrandService.findById(carBrandId);
            if (carBrand == null){
                throw new NotFoundException("Car Brand not found.");
            }
        }
        if (search.getMotoBrand() != null){
            final MotoBrand motoBrand = motoBrandService.findById(motoBrandId);
            if (motoBrand == null){
                throw new NotFoundException("Moto Brand not found.");
            }
            search.setMotoBrand(motoBrand);
        }

        List<Purchase> purchases = purchaseService.findAll(search, pagination, sort);
        return purchases.stream().map(ApiPurchase::new).collect(Collectors.toList());
    }




    @GetMapping("/purchase/{id}")
    public void deletePurchase(@PathVariable Long id){
        purchaseService.softDelete(id);
    }



}
