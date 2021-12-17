public abstract class Character {
    //フィールド変数
    private String name;
    private int baseAtk, maxHp, hp, atk;

    public Character(String name, int hp, int atk) {//コンストラクタ
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.baseAtk = atk;
        this.maxHp = hp;
    }

    public void damage(int atk) {//ダメージ
        if (atk > this.hp) {
            this.hp = 0;
        } else {
            this.hp -= atk;
        }
        System.out.println("\s\s" + "● " + this.name + "の残りHP: " + this.hp);
    }

    public void attack(Character c) {//攻撃
        System.out.println("> " + this.name + "の攻撃!");
        c.damage(this.atk);
    }

    public void showStatus() {//ステータスの表示
        System.out.println(this.name + "のステータス HP: " + this.hp + "  " + "ATK: " + this.atk);
    }

    //getter
    public String getName(){ //名前
        return this.name;
    }
    public int getHp(){ //体力
        return this.hp;
    }
    public int getAtk(){ //攻撃力
        return this.atk;
    }
    public int getMaxHp(){ //最大hp
        return this.maxHp;
    }
    public int getBaseAtk(){ //基本攻撃力
        return this.baseAtk;
    }

    //Setter
    public void setHp(int hp){//体力
        if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public void setAtk(int atk){//攻撃力
        this.atk = atk;
    }
}