package com.finalproj.finalproject.model;

import com.finalproj.finalproject.Enums.MealPreferance;
import com.finalproj.finalproject.Enums.UserConfirmation;

import javax.persistence.*;

@Entity
@Table(name = "general_user")
public class GeneralGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genaralGuestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genaral_user")
    private User genaralUser;

    @Enumerated(EnumType.STRING)
    private UserConfirmation confirmation;

    @Enumerated(EnumType.STRING)
    private MealPreferance mealPreferance;

    public GeneralGuest() {
    }

    public int getGenaralGuestId() {
        return genaralGuestId;
    }

    public void setGenaralGuestId(int genaralGuestId) {
        this.genaralGuestId = genaralGuestId;
    }

    public User getGenaralUser() {
        return genaralUser;
    }

    public void setGenaralUser(User genaralUser) {
        this.genaralUser = genaralUser;
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
