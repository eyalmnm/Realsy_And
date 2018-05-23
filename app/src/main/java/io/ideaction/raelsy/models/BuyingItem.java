package io.ideaction.raelsy.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

import io.ideaction.raelsy.network.models.BuyingItemModel;

public class BuyingItem implements Parcelable {

    private int id;
    private int price;

    private String address;
    private String city;
    private String state;
    private String zipCode;

    private int bedRooms;
    private int bathRooms;
    private int apartmentArea;
    private int totalArea;
    private String remarks;
    private String[] imagesUrl;

    private boolean liked;

    public BuyingItem(int id, int price, String address, String city, String state,
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

    public BuyingItem(BuyingItemModel buyingItemModel) {
        this.id = buyingItemModel.getId();
        this.price = buyingItemModel.getPrice();
        this.address = buyingItemModel.getAddress();
        this.city = buyingItemModel.getCity();
        this.state = buyingItemModel.getState();
        this.zipCode = buyingItemModel.getZipCode();
        this.bedRooms = buyingItemModel.getBedRooms();
        this.bathRooms = buyingItemModel.getBathRooms();
        this.apartmentArea = buyingItemModel.getApartmentArea();
        this.totalArea = buyingItemModel.getTotalArea();
        this.remarks = buyingItemModel.getRemarks();
        this.imagesUrl = buyingItemModel.getImagesUrl();
        this.liked = buyingItemModel.isLiked();
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getId() {

        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public int getApartmentArea() {
        return apartmentArea;
    }

    public int getTotalArea() {
        return totalArea;
    }

    public String getRemarks() {
        return remarks;
    }

    public String[] getImagesUrl() {
        return imagesUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    @Override
    public String toString() {
        return "BuyingItem{" +
                "id=" + id +
                ", price=" + price +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", bedRooms=" + bedRooms +
                ", bathRooms=" + bathRooms +
                ", apartmentArea=" + apartmentArea +
                ", totalArea=" + totalArea +
                ", remarks='" + remarks + '\'' +
                ", imagesUrl=" + Arrays.toString(imagesUrl) +
                ", liked=" + liked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BuyingItem that = (BuyingItem) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (bedRooms != that.bedRooms) return false;
        if (bathRooms != that.bathRooms) return false;
        if (apartmentArea != that.apartmentArea) return false;
        if (totalArea != that.totalArea) return false;
        if (liked != that.liked) return false;
        if (!address.equals(that.address)) return false;
        if (!city.equals(that.city)) return false;
        if (!state.equals(that.state)) return false;
        if (!zipCode.equals(that.zipCode)) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(imagesUrl, that.imagesUrl);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + address.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + zipCode.hashCode();
        result = 31 * result + bedRooms;
        result = 31 * result + bathRooms;
        result = 31 * result + apartmentArea;
        result = 31 * result + totalArea;
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(imagesUrl);
        result = 31 * result + (liked ? 1 : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.price);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.zipCode);
        dest.writeInt(this.bedRooms);
        dest.writeInt(this.bathRooms);
        dest.writeInt(this.apartmentArea);
        dest.writeInt(this.totalArea);
        dest.writeString(this.remarks);
        dest.writeStringArray(this.imagesUrl);
        dest.writeByte(this.liked ? (byte) 1 : (byte) 0);
    }

    protected BuyingItem(Parcel in) {
        this.id = in.readInt();
        this.price = in.readInt();
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.zipCode = in.readString();
        this.bedRooms = in.readInt();
        this.bathRooms = in.readInt();
        this.apartmentArea = in.readInt();
        this.totalArea = in.readInt();
        this.remarks = in.readString();
        this.imagesUrl = in.createStringArray();
        this.liked = in.readByte() != 0;
    }

    public static final Creator<BuyingItem> CREATOR = new Creator<BuyingItem>() {
        @Override
        public BuyingItem createFromParcel(Parcel source) {
            return new BuyingItem(source);
        }

        @Override
        public BuyingItem[] newArray(int size) {
            return new BuyingItem[size];
        }
    };
}
