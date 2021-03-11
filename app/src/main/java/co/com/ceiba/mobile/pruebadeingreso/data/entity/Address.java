package co.com.ceiba.mobile.pruebadeingreso.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import org.parceler.Parcel;

import lombok.Data;

@Parcel
@Data
public final class Address {

    @ColumnInfo(name = "street")
    public String street;
    @ColumnInfo(name = "suite")
    public String suite;
    @ColumnInfo(name = "city")
    public String city;
    @ColumnInfo(name = "zipcode")
    public String zipcode;
    @Embedded
    public Geo geo;
}
