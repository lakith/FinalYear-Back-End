package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "peymant_package")
public class PeymantPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int peymentPlanID;

    private int numberOfotifications;

    private int numberOfGuests;

    private int peymentAmmount;


    public PeymantPackage() {
    }

    public int getPeymentPlanID() {
        return peymentPlanID;
    }

    public void setPeymentPlanID(int peymentPlanID) {
        this.peymentPlanID = peymentPlanID;
    }

    public int getNumberOfotifications() {
        return numberOfotifications;
    }

    public void setNumberOfotifications(int numberOfotifications) {
        this.numberOfotifications = numberOfotifications;
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
