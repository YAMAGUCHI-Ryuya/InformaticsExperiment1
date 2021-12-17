public class Monkey_1 {
    private String name;
    private int hp, power, defense, agility, maxHp;

    public Monkey_1(String name, int hp, int power, int defense, int agility) {//コンストラクタ
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.agility = agility;
        maxHp = this.hp;
    }

    public int attack() {//攻撃
        System.out.println(this.name + "の攻撃!");
        return this.power;
    }

    public void defend(int damage) {//ダメージ数の表示
        int r = new java.util.Random().nextInt(99);//乱数の発生
        if (r < this.agility) {
            System.out.println("しかし、" + this.name + "は素早く回避した!(" + this.hp + " / " + maxHp + ")");
        } else {
            this.hp -= (damage - this.defense);
            if (this.hp < 0) {
                this.hp = 0;
            }
            System.out.println(this.name + "は" + (damage - this.defense) + "のダメージを受けた! (" + this.hp + " / " + maxHp + ")");
        }
    }

    public void printStatus() {//monkey1のステータス表示
        System.out.println("[" + name + "]");
        System.out.println("HP: " + this.hp);
        System.out.println("攻撃力: " + this.power);
        System.out.println("防御力: " + this.defense);
        System.out.println("回避率: " + this.agility);
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