public class Dog_1 {
    private String name;
    private int hp, power, defense, critical, maxhp;

    public Dog_1(String name, int hp, int power, int defense, int critical) {//コンストラクタ
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.critical = critical;
        maxhp = this.hp;
    }

    public int attack() {//攻撃
        System.out.println(this.name + "の攻撃!");
        int r = new java.util.Random().nextInt(99);//乱数の発生
        if (r < this.critical) {
            System.out.println(this.name + "は強く噛み付いた!");
            return this.power * 3/2;
        } else {
            return this.power;
        }
    }

    public void defend(int damage) {//ダメージ数の表示
        this.hp -= (damage - this.defense);
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println(this.name + "は" + (damage - this.defense) + "のダメージを受けた! (" + this.hp + " / " + maxhp + ")");
    }

    public void printStatus() {//dog1ステータスの表示
        System.out.println("[" + name + "]");
        System.out.println("HP: " + this.hp);
        System.out.println("攻撃力: " + this.power);
        System.out.println("防御力: " + this.defense);
        System.out.println("会心率: " + this.critical);
        System.out.println();
    }

    //Getter
    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }
}