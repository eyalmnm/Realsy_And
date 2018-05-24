package io.ideaction.raelsy.models;

import android.os.Parcel;
import android.os.Parcelable;

public class OfferItem implements Parcelable {

    private int id;
    private float desiredPrice;
    private float suggestedPrice;
    private float estimatedMortgage;
    private float typical;
    private float withRealsy;
    private float net;

    public OfferItem(int id, float desiredPrice, float suggestedPrice, float estimatedMortgage, float typical, float withRealsy, float net) {
        this.id = id;
        this.desiredPrice = desiredPrice;
        this.suggestedPrice = suggestedPrice;
        this.estimatedMortgage = estimatedMortgage;
        this.typical = typical;
        this.withRealsy = withRealsy;
        this.net = net;
    }

    public int getId() {
        return id;
    }

    public float getDesiredPrice() {
        return desiredPrice;
    }

    public float getSuggestedPrice() {
        return suggestedPrice;
    }

    public float getEstimatedMortgage() {
        return estimatedMortgage;
    }

    public float getTypical() {
        return typical;
    }

    public float getWithRealsy() {
        return withRealsy;
    }

    public float getNet() {
        return net;
    }

    @Override
    public String toString() {
        return "OfferItem{" +
                "id=" + id +
                ", desiredPrice=" + desiredPrice +
                ", suggestedPrice=" + suggestedPrice +
                ", estimatedMortgage=" + estimatedMortgage +
                ", typical=" + typical +
                ", withRealsy=" + withRealsy +
                ", net=" + net +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferItem offerItem = (OfferItem) o;

        if (id != offerItem.id) return false;
        if (Float.compare(offerItem.desiredPrice, desiredPrice) != 0) return false;
        if (Float.compare(offerItem.suggestedPrice, suggestedPrice) != 0) return false;
        if (Float.compare(offerItem.estimatedMortgage, estimatedMortgage) != 0) return false;
        if (Float.compare(offerItem.typical, typical) != 0) return false;
        if (Float.compare(offerItem.withRealsy, withRealsy) != 0) return false;
        return Float.compare(offerItem.net, net) == 0;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (desiredPrice != +0.0f ? Float.floatToIntBits(desiredPrice) : 0);
        result = 31 * result + (suggestedPrice != +0.0f ? Float.floatToIntBits(suggestedPrice) : 0);
        result = 31 * result + (estimatedMortgage != +0.0f ? Float.floatToIntBits(estimatedMortgage) : 0);
        result = 31 * result + (typical != +0.0f ? Float.floatToIntBits(typical) : 0);
        result = 31 * result + (withRealsy != +0.0f ? Float.floatToIntBits(withRealsy) : 0);
        result = 31 * result + (net != +0.0f ? Float.floatToIntBits(net) : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeFloat(this.desiredPrice);
        dest.writeFloat(this.suggestedPrice);
        dest.writeFloat(this.estimatedMortgage);
        dest.writeFloat(this.typical);
        dest.writeFloat(this.withRealsy);
        dest.writeFloat(this.net);
    }

    protected OfferItem(Parcel in) {
        this.id = in.readInt();
        this.desiredPrice = in.readFloat();
        this.suggestedPrice = in.readFloat();
        this.estimatedMortgage = in.readFloat();
        this.typical = in.readFloat();
        this.withRealsy = in.readFloat();
        this.net = in.readFloat();
    }

    public static final Creator<OfferItem> CREATOR = new Creator<OfferItem>() {
        @Override
        public OfferItem createFromParcel(Parcel source) {
            return new OfferItem(source);
        }

        @Override
        public OfferItem[] newArray(int size) {
            return new OfferItem[size];
        }
    };
}
