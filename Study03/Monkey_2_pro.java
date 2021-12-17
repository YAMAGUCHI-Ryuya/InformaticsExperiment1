public class Monkey_2_pro extends Animal_pro{
    private int agility;

    public Monkey_2_pro(String name, int hp, int power, int defense, int agility) {
        super(name, hp, power, defense);
        this.agility = agility;
    }

    public void defend(int damage) {
        int r = new java.util.Random().nextInt(100);
        if (r < this.agility) {
            System.out.println("しかし、" + this.name + "は素早く回避した! (" + this.hp + " / " + this.maxHp + ")");
        } else {
            super.defend(damage);
        }
    }

    public void printStatus() {
        super.printStatus();
        System.out.println("回避率: " + this.agility);
        System.out.println();
    }
}