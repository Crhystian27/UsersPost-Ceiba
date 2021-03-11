package co.com.ceiba.mobile.pruebadeingreso.data.entity;

import androidx.room.ColumnInfo;

import org.parceler.Parcel;

import lombok.Data;

@Parcel
@Data
public final class Company {

    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "catchPhrase")
    public String catchPhrase;
    @ColumnInfo(name = "bs")
    public String bs;
}
