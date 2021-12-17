public class Monkey_2 extends Animal {
    private int agility, maxHp;

    public Monkey_2(String name, int hp, int power, int defense, int agility) {//コンストラクタ
        super(name, hp, power, defense);
        this.agility = agility;
        this.maxHp = hp;
    }

    public void defend(int damage) {//ダメージ数の表示
        int r = new java.util.Random().nextInt(100);//乱数の発生
        if (r < this.agility) {
            System.out.println("しかし、" + this.getName() + "は素早く回避した! (" + this.getHp() + " / " + this.maxHp + ")");
        } else {
            super.defend(damage);
        }
    }

    public void printStatus() {//monkey2ステータスの表示
        super.printStatus();//AnimalクラスのprintStatusを呼ぶ
        System.out.println("回避率: " + this.agility);
        System.out.println();
    }
}