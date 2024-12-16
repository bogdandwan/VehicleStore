package com.example.VehicleStore.controller.export;

import com.example.VehicleStore.entity.purchase.Purchase;
import com.example.VehicleStore.entity.rental.Rental;
import com.example.VehicleStore.service.service.purchase.PurchaseService;
import com.example.VehicleStore.service.service.rental.RentalService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {


    private final PurchaseService purchaseService;
    private final RentalService rentalService;


    @GetMapping("/purchases")
    public ResponseEntity<byte[]> exportPurchasesToExcel() throws IOException {

        List<Purchase> purchases = purchaseService.findAll();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Purchases");


        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Client Name");
        headerRow.createCell(2).setCellValue("Seller Name");
        headerRow.createCell(3).setCellValue("Vehicle");
        headerRow.createCell(4).setCellValue("Price");
        headerRow.createCell(5).setCellValue("Purchase Date");


        int rowIndex = 1;
        for (Purchase purchase : purchases) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(purchase.getId());
            row.createCell(1).setCellValue(purchase.getClient().getFirstName() + " " + purchase.getClient().getLastName());
            row.createCell(2).setCellValue(purchase.getSeller().getFirstName() + " " + purchase.getSeller().getLastName());


            if (purchase.getCar() != null) {
                row.createCell(3).setCellValue(purchase.getCar().getCarModel().getName());
            } else if (purchase.getMotorcycle() != null) {
                row.createCell(3).setCellValue(purchase.getMotorcycle().getMotoModel().getName());
            } else {
                row.createCell(3).setCellValue("N/A");
            }

            row.createCell(4).setCellValue(purchase.getPurchasePrice().doubleValue());
            row.createCell(5).setCellValue(purchase.getPurchaseDate().toString());
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();


        byte[] excelData = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("Purchases.xlsx").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }



    @GetMapping("/rentals")
    public ResponseEntity<byte[]> exportRentalsToExcel() throws IOException {

        List<Rental> rentals = rentalService.findAll();


        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Rentals");


        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Client Name");
        headerRow.createCell(2).setCellValue("Vehicle");
        headerRow.createCell(3).setCellValue("Price");
        headerRow.createCell(4).setCellValue("Rental Start Date");
        headerRow.createCell(5).setCellValue("Rental Return Date");
        headerRow.createCell(6).setCellValue("Rental Expiration Date");


        int rowIndex = 1;
        for (Rental rental : rentals) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(rental.getId());
            row.createCell(1).setCellValue(rental.getClient().getFirstName() + " " + rental.getClient().getLastName());


            if (rental.getCar() != null) {
                row.createCell(2).setCellValue(rental.getCar().getCarModel().getName());
            } else if (rental.getMotorcycle() != null) {
                row.createCell(2).setCellValue(rental.getMotorcycle().getMotoModel().getName());
            } else {
                row.createCell(2).setCellValue("N/A");
            }

            row.createCell(3).setCellValue(rental.getRentalPrice().doubleValue());
            row.createCell(4).setCellValue(rental.getStartDate().toString());
            row.createCell(5).setCellValue(rental.getReturnDate().toString());
            row.createCell(6).setCellValue(rental.getExpirationDate().toString());
        }


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();


        byte[] excelData = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("Rentals.xlsx").build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }

}
