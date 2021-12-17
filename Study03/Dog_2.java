public class Dog_2 extends Animal{
    private int critical;

    public Dog_2(String name, int hp, int power, int defense, int critical) {//コンストラクタ
        super(name, hp, power, defense);
        this.critical = critical;
    }

    public int attack() {//攻撃
        super.attack();
        int r = new java.util.Random().nextInt(100);//乱数発生させる
        if (r < this.critical) {
            System.out.println(this.getName() + "は強く噛み付いた!");
            return this.getPower() * 3/2;
        } else {
            return this.getPower();
        }
    }

    public void printStatus() {//dog2ステータスの表示
        super.printStatus();//AnimalのprintStatusをよぶ
        System.out.println("会心率: " + this.critical);
        System.out.println();
    }
}