package co.com.ceiba.mobile.pruebadeingreso;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.data.entity.Address;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.Company;
import co.com.ceiba.mobile.pruebadeingreso.data.entity.User;

public class UserTestBuilder {

    public int id;
    public String name;
    public String username;
    public String email;
    public Address address;
    public String phone;
    public String website;
    public Company company;
    public static List<User> list;

    public UserTestBuilder() {
        this.id = (int) (Math.random() * 100);
        this.name = "Cristian David";
        this.address = new Address();
        this.company = new Company();
        this.username = "Crhystian";
        this.email = "cdavid2127gmail.com";
        this.phone = "3122357081";
        this.website = "hola.com.co";
    }


    public User build() {
        return new User(id, name, username, email, address, phone, website, company);
    }

    public static List<User> getUserList() {
        list = new ArrayList<>();
        list.add(new UserTestBuilder().build());
        list.add(new UserTestBuilder().build());
        list.add(new UserTestBuilder().build());
        return list;
    }

}
