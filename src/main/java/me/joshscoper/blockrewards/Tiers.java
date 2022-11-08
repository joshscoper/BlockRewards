package me.joshscoper.blockrewards;

public enum Tiers {

    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    EPIC("Epic"),
    LEGENDARY("Legendary");

    private String tier;

    Tiers(String tier){
        this.tier = tier;
    }

    public String getTier(){ return tier;}
}
