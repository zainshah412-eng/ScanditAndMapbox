package com.project.androidairportr.models;

import android.os.Parcel;
import android.os.Parcelable;

public class  NavigationModel implements Parcelable {
    Double originLatitude;
    Double originLongitude;
    Double destinationLatitude;
    Double destinationLongitude;
    int routeIndex=0;

    public NavigationModel(Double originLatitude, Double originLongitude, Double destinationLatitude, Double destinationLongitude, int routeIndex) {
        this.originLatitude = originLatitude;
        this.originLongitude = originLongitude;
        this.destinationLatitude = destinationLatitude;
        this.destinationLongitude = destinationLongitude;
        this.routeIndex = routeIndex;
    }

    protected NavigationModel(Parcel in) {
        if (in.readByte() == 0) {
            originLatitude = null;
        } else {
            originLatitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            originLongitude = null;
        } else {
            originLongitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            destinationLatitude = null;
        } else {
            destinationLatitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            destinationLongitude = null;
        } else {
            destinationLongitude = in.readDouble();
        }
        routeIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (originLatitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(originLatitude);
        }
        if (originLongitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(originLongitude);
        }
        if (destinationLatitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(destinationLatitude);
        }
        if (destinationLongitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(destinationLongitude);
        }
        dest.writeInt(routeIndex);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<NavigationModel> CREATOR = new Parcelable.Creator<NavigationModel>() {
        @Override
        public NavigationModel createFromParcel(Parcel in) {
            return new NavigationModel(in);
        }

        @Override
        public NavigationModel[] newArray(int size) {
            return new NavigationModel[size];
        }
    };

    public Double getOriginLatitude() {
        return originLatitude;
    }

    public void setOriginLatitude(Double originLatitude) {
        this.originLatitude = originLatitude;
    }

    public Double getOriginLongitude() {
        return originLongitude;
    }

    public void setOriginLongitude(Double originLongitude) {
        this.originLongitude = originLongitude;
    }

    public Double getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(Double destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
    }

    public Double getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(Double destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public int getRouteIndex() {
        return routeIndex;
    }

    public void setRouteIndex(int routeIndex) {
        this.routeIndex = routeIndex;
    }
}

