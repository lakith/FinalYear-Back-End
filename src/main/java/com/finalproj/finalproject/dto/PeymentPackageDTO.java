package com.finalproj.finalproject.dto;

public class PeymentPackageDTO {

    private int numberOfnotifications;

    private int numberOfGuests;

    private int peymentAmmount;

    public PeymentPackageDTO() {
    }

    public int getNumberOfnotifications() {
        return numberOfnotifications;
    }

    public void setNumberOfnotifications(int numberOfnotifications) {
        this.numberOfnotifications = numberOfnotifications;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public int getPeymentAmmount() {
        return peymentAmmount;
    }

    public void setPeymentAmmount(int peymentAmmount) {
        this.peymentAmmount = peymentAmmount;
    }
}
