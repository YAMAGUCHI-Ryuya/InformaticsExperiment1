public class Warrior extends Hero implements Member {
    public Warrior(String name, int hp, int atk) {//コンストラクタ
        super(name, hp, atk);
    }

    public void skill(Character c) {//スキル発動
        this.setAtk(2 * this.getAtk());
        System.out.println("> " + this.getName() + "のスキル発動!");
        System.out.println("\s\s" + "● " + this.getName() + "の攻撃力が2倍になった!");
        super.attack(c);
    }
}