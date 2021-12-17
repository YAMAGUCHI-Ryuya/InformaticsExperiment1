public class Animal {
    private String name;
    private int hp, power, defense, maxHp;

    public Animal(String name, int hp, int power, int defense){//コンストラクタ
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.maxHp = hp;
    }

    public int attack(){//攻撃
        System.out.println(this.name + "の攻撃!");
        return this.power;
    }

    public void defend(int damage){//ダメージ数の表示
        this.hp -= (damage - this.defense);
        if(this.hp < 0){
            this.hp = 0;
        }
        System.out.println(this.name + "は" + (damage - this.defense) + "のダメージを受けた! (" + this.hp + " / " + this.maxHp + ")");
    }

    public void printStatus() {//共通のステータス
        System.out.println("[" + this.name + "]");
        System.out.println("HP: " + this.hp);
        System.out.println("攻撃力: " + this.power);
        System.out.println("防御力: " + this.defense);
    }

    //Getter
    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public int getPower() {
        return this.power;
    }
}