package week4.day21;

public record Item (String name, int cost, int damage, int armor){
    @Override
    public String toString() {
        return name + " " + cost + " " + damage + " " + armor;
    }
}
