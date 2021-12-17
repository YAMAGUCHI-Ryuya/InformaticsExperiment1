public class Dancer extends Hero implements Member{
    public Dancer(String name, int hp, int atk) {//コンストラクタ
        super(name, hp, atk);
    }

    public void skill(Character c) {//スキル発動
        c.setAtk(3 * c.getAtk());
        System.out.println("> " + this.getName() + "のスキル発動!");
        System.out.println("\s\s" + "● " + c.getName() + "の攻撃力が3倍になった!");
    }
}