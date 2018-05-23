package io.ideaction.raelsy.network.models;

import com.google.gson.annotations.SerializedName;

public class BuyingItemModel {

    @SerializedName("id")
    private int id;
    @SerializedName("price")
    private int price;

    @SerializedName("address")
    private String address;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("zipCode")
    private String zipCode;

    @SerializedName("bedRooms")
    private int bedRooms;
    @SerializedName("bathRooms")
    private int bathRooms;
    @SerializedName("apartmentArea")
    private int apartmentArea;
    @SerializedName("totalArea")
    private int totalArea;
    @SerializedName("remarks")
    private String remarks;
    @SerializedName("imagesUrl")
    private String[] imagesUrl;

    @SerializedName("liked")
    private boolean liked;

    public BuyingItemModel(int id, int price, String address, String city, String state,
                           String zipCode, int bedRooms, int bathRooms, int apartmentArea,
                           int totalArea, String remarks, String[] imagesUrl, boolean liked) {
        this.id = id;
        this.price = price;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.bedRooms = bedRooms;
        this.bathRooms = bathRooms;
        this.apartmentArea = apartmentArea;
        this.totalArea = totalArea;
        this.remarks = remarks;
        this.imagesUrl = imagesUrl;
        this.liked = liked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public int getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(int apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(int totalArea) {
        this.totalArea = totalArea;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String[] getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String[] imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
