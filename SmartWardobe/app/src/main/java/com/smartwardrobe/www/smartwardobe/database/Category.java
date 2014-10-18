package com.smartwardrobe.www.smartwardobe.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by eugene on 18.10.14.
 */

@Table(name = "Categories")
public class Category extends Model {

    @Column(name = "Name")
    public String name;
}
