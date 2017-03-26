package com.technophobics.snapchef.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ingredient {
    public static Map<String, Ingredient> map = new HashMap<>();

    private String name;
    private String unit;

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public String getName() { return name; }
    public String getNameLower() { return name.toLowerCase(); }
    public String getUnit() { return unit; }

    public static Ingredient get(String name) {
        return map.get(name.toLowerCase());
    }

    public static ArrayList<String> demoList() {
        return new ArrayList<>(map.keySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingredient that = (Ingredient) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
