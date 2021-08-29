package com.web.demo.utils;

import com.web.demo.dtos.CropInsuranceDTO;
import com.web.demo.dtos.Customer;
import com.web.demo.dtos.DistrictsDTO;
import com.web.demo.dtos.DistrictsHospitalsDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelperUtil {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "Title", "Description", "BankName"};
    static String SHEET = "CropDetails";

    public static ByteArrayInputStream exportCropToExcel(List<CropInsuranceDTO> customers) throws IOException {
        String[] COLUMNs = {"Id", "Name", "Address"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Customers");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (CropInsuranceDTO customer : customers) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getBankName());
                row.createCell(2).setCellValue(customer.getMandalName());

            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream customersToExcel(List<Customer> customers) throws IOException {
        String[] COLUMNs = {"Id", "Name", "Address", "Age"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Customers");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (Customer customer : customers) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(customer.getId());
                row.createCell(1).setCellValue(customer.getName());
                row.createCell(2).setCellValue(customer.getAddress());

                Cell ageCell = row.createCell(3);
                ageCell.setCellValue(customer.getAge());
                ageCell.setCellStyle(ageCellStyle);
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static List<Customer> parseExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet("Customers");
            Iterator<Row> rows = sheet.iterator();

            List<Customer> lstCustomers = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Customer cust = null;//Customer.builder().build();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    if (cellIndex == 0) { // ID
                        cust.setId((long) currentCell.getNumericCellValue());
                    } else if (cellIndex == 1) { // Name
                        cust.setName(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) { // Address
                        cust.setAddress(currentCell.getStringCellValue());
                    } else if (cellIndex == 3) { // Age
                        cust.setAge((int) currentCell.getNumericCellValue());
                    }

                    cellIndex++;
                }

                lstCustomers.add(cust);
            }
            workbook.close();

            return lstCustomers;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static List<DistrictsDTO> parseDistrictExcelFile(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet("districts");
            Iterator<Row> rows = sheet.iterator();

            List<DistrictsDTO> lstCustomers = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                DistrictsDTO cust = new DistrictsDTO();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (cellIndex == 0) {
                        cust.setId((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 1) {
                        cust.setDistrict(currentCell.getStringCellValue());
                    } else if (cellIndex == 2) {
                        cust.setHospitals((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 3) {
                        cust.setIcuBedsTotal((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 4) {
                        cust.setIcuBedsOccupaid((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 5) {
                        cust.setIcuBedsAvailable((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 6) {
                        cust.setGeneralBedsTotal((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 7) {
                        cust.setGeneralBedsOccupied((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 8) {
                        cust.setGeneralBedsAvailable((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 9) {
                        cust.setVentilatorTotal((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 10) {
                        cust.setVentilatorOccupied((int) currentCell.getNumericCellValue());
                    } else if (cellIndex == 11) {
                        cust.setVentilatorAvailable((int) currentCell.getNumericCellValue());
                    }
                    cellIndex++;
                }
                lstCustomers.add(cust);
            }
            workbook.close();
            return lstCustomers;
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

    public static ByteArrayInputStream cropToExcel(List<CropInsuranceDTO> cropInsurances) throws IOException {
        String[] COLUMNs = {"Id", "MandalName", "Village"};
        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("CropInsuranceDTO");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (CropInsuranceDTO crop : cropInsurances) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(crop.getId());
                row.createCell(1).setCellValue(crop.getMandalName());
                row.createCell(2).setCellValue(crop.getVillageName());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public static ByteArrayInputStream hospitalsToExcel(List<DistrictsHospitalsDTO> hospitals) {

        String[] COLUMNs = {"Id", "District", "HospitalName", "HelpDeskNumber", "NodalOfficerNumber", "AarogyasriEmpanelmentStatus", "ICUBedsTotal", "ICUBedsOccupied",
                "ICUBedsAvailable", "GeneralBedsTotal", "GeneralBedsOccupied", "GeneralBedsAvailable", "VentilatorTotal", "VentilatorOccupied", "VentilatorAvailable"};

        try (
                Workbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
        ) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("Hospitals");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            // CellStyle for Age
            CellStyle ageCellStyle = workbook.createCellStyle();
            ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

            int rowIdx = 1;
            for (DistrictsHospitalsDTO hosp : hospitals) {
                Row row = sheet.createRow(rowIdx++);

                String district = hosp.getDistrict().split("\\.")[0];

                row.createCell(0).setCellValue(rowIdx);
                row.createCell(1).setCellValue(district);
                row.createCell(2).setCellValue(hosp.getName());
                row.createCell(3).setCellValue(hosp.getHelpDeskNumber());
                row.createCell(4).setCellValue(hosp.getNodalOfficeNumber());
                row.createCell(5).setCellValue(hosp.getAarogyasriStatus());
                row.createCell(6).setCellValue(hosp.getIcuBedsTotal());
                row.createCell(7).setCellValue(hosp.getIcuBedsOccupaid());
                row.createCell(8).setCellValue(hosp.getIcuBedsAvailable());
                row.createCell(9).setCellValue(hosp.getGeneralBedsTotal());
                row.createCell(10).setCellValue(hosp.getGeneralBedsOccupied());
                row.createCell(11).setCellValue(hosp.getGeneralBedsAvailable());
                row.createCell(12).setCellValue(hosp.getVentilatorTotal());
                row.createCell(13).setCellValue(hosp.getVentilatorOccupied());
                row.createCell(14).setCellValue(hosp.getVentilatorAvailable());

            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
