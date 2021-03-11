package co.com.ceiba.mobile.pruebadeingreso.data.entity;

import androidx.room.ColumnInfo;

import org.parceler.Parcel;

import lombok.Data;


@Parcel
@Data
public final  class Geo {

    @ColumnInfo(name = "lat")
    public String lat;
    @ColumnInfo(name = "lng")
    public String lng;
}
