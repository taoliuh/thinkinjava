package me.sonaive;

/**
 * Created by liutao on 27/02/2019.
 */
public class Item {

    public int id;

    public Item(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item-" + id;
    }
}
