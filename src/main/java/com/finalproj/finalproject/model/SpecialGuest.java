package com.finalproj.finalproject.model;

import com.finalproj.finalproject.Enums.MealPreferance;
import com.finalproj.finalproject.Enums.UserConfirmation;

import javax.persistence.*;

@Entity
@Table(name = "special_guest")
public class SpecialGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int specialGuestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "special_user")
    private User specialUser;

    @Enumerated(EnumType.STRING)
    private UserConfirmation confirmation;

    @Enumerated(EnumType.STRING)
    private MealPreferance mealPreferance;

    public SpecialGuest() {
    }

    public int getSpecialGuestId() {
        return specialGuestId;
    }

    public void setSpecialGuestId(int specialGuestId) {
        this.specialGuestId = specialGuestId;
    }

    public User getSpecialUser() {
        return specialUser;
    }

    public void setSpecialUser(User specialUser) {
        this.specialUser = specialUser;
    }

    public UserConfirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(UserConfirmation confirmation) {
        this.confirmation = confirmation;
    }

    public MealPreferance getMealPreferance() {
        return mealPreferance;
    }

    public void setMealPreferance(MealPreferance mealPreferance) {
        this.mealPreferance = mealPreferance;
    }
}
