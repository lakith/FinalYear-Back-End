package com.finalproj.finalproject.model;

import javax.persistence.*;

@Entity
@Table(name = "paid_event")
public class PaidEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paidEventId;

    private boolean sellOnlineByUser = false;

    private String sellingWebUrl;

    private boolean rsvpSelling= false;

    private String accountOwnerName;

    private String bankAccountNumber;

    private String bankName;

    private String branchName;

    private String address;

    private double price;


    public PaidEvent() {
    }

    public int getPaidEventId() {
        return paidEventId;
    }

    public void setPaidEventId(int paidEventId) {
        this.paidEventId = paidEventId;
    }

    public boolean isSellOnlineByUser() {
        return sellOnlineByUser;
    }

    public void setSellOnlineByUser(boolean sellOnlineByUser) {
        this.sellOnlineByUser = sellOnlineByUser;
    }

    public String getSellingWebUrl() {
        return sellingWebUrl;
    }

    public void setSellingWebUrl(String sellingWebUrl) {
        this.sellingWebUrl = sellingWebUrl;
    }

    public boolean isRsvpSelling() {
        return rsvpSelling;
    }

    public void setRsvpSelling(boolean rsvpSelling) {
        this.rsvpSelling = rsvpSelling;
    }

    public String getAccountOwnerName() {
        return accountOwnerName;
    }

    public void setAccountOwnerName(String accountOwnerName) {
        this.accountOwnerName = accountOwnerName;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
