public class Enemy extends Character{
    public Enemy(String name, int hp, int atk){//コンストラクタ
        super(name, hp, atk);
    }

    public void attack(Hero[] party){//全体攻撃
        System.out.println(">> " + this.getName() + "の全体攻撃!");
        for(int i = 0; i < party.length; i++){
        party[i].damage(this.getAtk());
        }
    }
}