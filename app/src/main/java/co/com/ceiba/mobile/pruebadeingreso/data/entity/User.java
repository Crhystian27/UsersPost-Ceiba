package co.com.ceiba.mobile.pruebadeingreso.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

import lombok.Data;

@Parcel
@Data
@Entity(tableName = "user_table", indices = {@Index(value = "id", unique = true)})
public class User {

    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "email")
    public String email;
    @Embedded(prefix = "address_")
    public Address address;
    @ColumnInfo(name = "phone")
    public String phone;
    @ColumnInfo(name = "website")
    public String website;
    @Embedded(prefix = "company_")
    public Company company;


    @ParcelConstructor
    public User(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }
}
