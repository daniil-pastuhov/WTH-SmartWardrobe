package com.smartwardrobe.www.smartwardobe.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by eugene on 18.10.14.
 */

@Table(name = "Apparels")
public class Apparel extends Model {

    @Column(name = "ImagePath")
    public String imagePath;

    @Column(name = "Name")
    public String name;

    @Column(name = "Description")
    public String description;

    @Column(name = "Material")
    public String material;

    @Column(name = "InWash")
    public Boolean inWash;

    @Column(name = "MinWarm")
    public int minWarm = 0;

    @Column(name = "MaxWarm")
    public int maxWarm = 40;

    @Column(name = "LastWahsedDate")
    public long lastWahsedDate = 0;

    @Column(name = "lastWornDate")
    public long lastWornDate;

    @Column(name = "Wear")
    public int wear;

    @Column(name = "Category")
    public Category category;

    public Apparel(){super();};

    public static List<Apparel> getAll() {
        return new Select()
                .from(Apparel.class)
                .execute();
    }



    public static Apparel getbyId(long id) {
        return new Select()
                .from(Apparel.class)
                .where("mId = ?", id)
                .executeSingle();
    }
}
