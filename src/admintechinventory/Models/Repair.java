package admintechinventory.Models;

import java.util.Date;

public class Repair {

    private String device;
    private String brand;
    private String model;
    private String faultDescription;
    private Date deliveryDate;
    private double repairCost;
    private int idClient;
    private int idMaterial;
    private int idFaultType;
    private int idPaymentMethod;
    private int idStatus;
    private int idService;

    public Repair(String device, String brand, String model, String faultDescription, Date deliveryDate,
            double repairCost, int idClient, int idMaterial, int idFaultType,
            int idPaymentMethod, int idStatus, int idService) {
        this.device = device;
        this.brand = brand;
        this.model = model;
        this.faultDescription = faultDescription;
        this.deliveryDate = deliveryDate;
        this.repairCost = repairCost;
        this.idClient = idClient;
        this.idMaterial = idMaterial;
        this.idFaultType = idFaultType;
        this.idPaymentMethod = idPaymentMethod;
        this.idStatus = idStatus;
        this.idService = idService;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(String faultDescription) {
        this.faultDescription = faultDescription;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(double repairCost) {
        this.repairCost = repairCost;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public int getIdFaultType() {
        return idFaultType;
    }

    public void setIdFaultType(int idFaultType) {
        this.idFaultType = idFaultType;
    }

    public int getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(int idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

}
