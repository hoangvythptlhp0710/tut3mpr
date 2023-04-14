package com.asm2.mycart.models;

public enum Category {
    lights, watches, helmets, earphones;

    public static Category getCategory(String category) {
        switch (category) {
            case "lights":
                return lights;
            case "watches":
                return watches;
            case "helmets":
                return helmets;
            case "earphones":
                return earphones;
            default:
                return null;
        }
    }

}
