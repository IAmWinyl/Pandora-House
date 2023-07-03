package com.serraabak.pandorahouse;

enum ItemType {
    TSHIRT,
    BLOUSE,
    PANTS,
    SHORTS,
    SKIRT,
    DRESS,
    ACCESSORY,
    SHOES;

    public static ItemType fromInteger(int x) {
        switch(x) {
            case 0:
                return TSHIRT;
            case 1:
                return BLOUSE;
            case 2:
                return PANTS;
            case 3:
                return SHORTS;
            case 4:
                return SKIRT;
            case 5:
                return DRESS;
            case 6:
                return ACCESSORY;
            case 7:
                return SHOES;
        }
        return null;
    }
}