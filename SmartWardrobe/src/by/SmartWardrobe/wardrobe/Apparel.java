package by.SmartWardrobe.wardrobe;

import by.idea.SmartWardrobe.R;

public class Apparel {
    public Apparel (int flag, String rank, String country,
                    String population) {
        this.name = rank;
        this.material = country;
        this.date = population;
        this.photo = flag;
    }
    private String name;
    private String material;
    private String date;
    private int photo;
    public String getName() {return name;}
    public String getMaterial() {return material;}
    public String getDate() {return date;}

    public int getImageId() {return R.drawable.ex;};

}
