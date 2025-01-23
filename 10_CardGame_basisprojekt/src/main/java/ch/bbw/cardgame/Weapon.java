package ch.bbw.cardgame;

public class Weapon {
    private String imageUrl;
    private String tradeName;
    private String model;
    private double dps;
    private double damage;
    private int magazineSize;
    private double fireRate;
    private double relodeTime;
    private double structureDamage;


    public Weapon(String imageUrl, String tradeName, String model, double dps, double damage, double magazineSize, double fireRate, double relodeTime, double structureDamage) {
        this.imageUrl = imageUrl;
        this.tradeName = tradeName;
        this.model = model;
        this.dps = dps;
        this.damage = damage;
        this.fireRate = fireRate;
        this.relodeTime = relodeTime;
        this.structureDamage = structureDamage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getDps() {
        return dps;
    }

    public void setDps(double dps) {
        this.dps = dps;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public double getRelodeTime() {
        return relodeTime;
    }

    public void setRelodeTime(double relodeTime) {
        this.relodeTime = relodeTime;
    }

    public double getStructureDamage() {
        return structureDamage;
    }

    public void setStructureDamage(double structureDamage) {
        this.structureDamage = structureDamage;
    }


}
