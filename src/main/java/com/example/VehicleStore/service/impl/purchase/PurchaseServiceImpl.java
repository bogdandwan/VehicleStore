package com.example.VehicleStore.service.impl.purchase;

import com.example.VehicleStore.dto.pagination.Pagination;
import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.repository.purchase.PurchaseRepository;
import com.example.VehicleStore.search.purchase.PurchaseSearch;
import com.example.VehicleStore.search.purchase.spec.PurchaseSpec;
import com.example.VehicleStore.search.sort.purchase.PurchaseSort;
import com.example.VehicleStore.service.service.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    final PurchaseRepository purchaseRepository;

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    private Sort buildSort(PurchaseSort sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean asc = sort.name().contains("ASC");
        String property = switch (sort) {
            case PURCHASE_DATE_ASC, PURCHASE_DATE_DESC -> "purchaseDate";
            case PURCHASE_PRICE_ASC, PURCHASE_PRICE_DESC -> "purchasePrice";
            case DISCOUNT_ASC, DISCOUNT_DESC -> "discount";
            case CLIENT_NAME_ASC, CLIENT_NAME_DESC -> "client.name";
            case SELLER_NAME_ASC, SELLER_NAME_DESC -> "seller.name";
            case PURCHASE_STATUS_ASC, PURCHASE_STATUS_DESC -> "status";
            case PURCHASE_TYPE_ASC, PURCHASE_TYPE_DESC -> "purchaseType";
            case LOCATION_ASC, LOCATION_DESC -> "location";
            case PAYMENT_METHOD_ASC, PAYMENT_METHOD_DESC -> "paymentMethod";
            case TRANSACTION_ID_ASC, TRANSACTION_ID_DESC -> "transactionId";
            default -> "id";
        };
        return Sort.by(asc ? Sort.Direction.ASC : Sort.Direction.DESC, property);
    }


    @Override
    public List<Purchase> findAll(PurchaseSearch search, Pagination pagination, PurchaseSort sort) {
        return purchaseRepository.findAll(new PurchaseSpec(search), pagination.pageable(buildSort(sort)));
    }

    @Override
    public void softDelete(Long id) {
        final Purchase purchase = purchaseRepository.findById(id).orElse(null);
        if (purchase != null){
            purchase.setDeletionTime(LocalDateTime.now());
            purchaseRepository.save(purchase);
        }
    }
}
