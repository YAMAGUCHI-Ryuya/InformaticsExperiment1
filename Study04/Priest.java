public class Priest extends Hero implements Member {
    public Priest(String name, int hp, int atk) {//コンストラクタ
        super(name, hp, atk);
    }

    public void skill(Character c) {//スキル発動
        c.setHp(c.getMaxHp());
        System.out.println("> " + this.getName() + "のスキル発動!");
        System.out.println("\s\s" + "● " + c.getName() + "の体力が全回復した!");
    }

}