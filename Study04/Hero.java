public class Hero extends Character {
    //フィールド変数
    private static int count = 0;
    private int id;
    
    public Hero(String name, int hp, int atk){//コンストラクタ
        super(name, hp, atk);
        this.id = count;
        count++;
    }

    public void showStatus(){//ステータスを表示させる
        super.showStatus();
        System.out.println("ID: " + this.id);
    }

    public void resetStatus(){//攻撃力を戻す
        this.setAtk(getBaseAtk());
    }

    //Getter
    public int getId(){//キャラクターのID
        return this.id;
    }
}