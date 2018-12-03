package br.com.doghero.dhproject.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Hero {

    @SerializedName("is_superhero")
    private boolean isSuperhero;

    @SerializedName("address_neighborhood")
    private String addresssNeighborhood;

    @SerializedName("price")
    private BigDecimal price;

    private User user;

    public Hero(){
    }

    public boolean isSuperhero() {
        return isSuperhero;
    }

    public void setSuperhero(boolean superhero) {
        isSuperhero = superhero;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddresssNeighborhood() {
        return addresssNeighborhood;
    }

    public void setAddresssNeighborhood(String addresssNeighborhood) {
        this.addresssNeighborhood = addresssNeighborhood;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
