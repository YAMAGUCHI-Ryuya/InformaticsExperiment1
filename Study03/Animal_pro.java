public class Animal_pro {
    protected String name;
    protected int hp, power, defense, maxHp;

    public Animal_pro(String name, int hp, int power, int defense){
        this.name = name;
        this.hp = hp;
        this.power = power;
        this.defense = defense;
        this.maxHp = hp;
    }

    public int attack(){
        System.out.println(this.name + "の攻撃!");
        return this.power;
    }

    public void defend(int damage){
        this.hp -= (damage - this.defense);
        if(this.hp < 0){
            this.hp = 0;
        }
        System.out.println(this.name + "は" + (damage - this.defense) + "のダメージを受けた! (" + this.hp + " / " + this.maxHp + ")");
    }

    public void printStatus() {
        System.out.println("[" + this.name + "]");
        System.out.println("HP: " + this.hp);
        System.out.println("攻撃力: " + this.power);
        System.out.println("防御力: " + this.defense);
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }
}