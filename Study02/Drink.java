public class Drink {
    private String name;
    private int value;

    public Drink(String name, int value) {//コンストラクタ
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}