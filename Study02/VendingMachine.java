public class VendingMachine {
    private int money = 0;
    private int max = 4;
    private Drink[] regist = new Drink[5];// drinkを格納する配列

    public void registDrink(int index, Drink drink) {// drinkの登録
        if (this.regist[index] != null) {
            System.out.println((index + 1) + "番には飲み物が既に登録されています");
        } else {
            this.regist[index] = drink;
            System.out.println((index + 1) + "番に「" + this.regist[index].getName() + "」を登録しました");
        }
    }

    public void insertMoney(int money) {//お金を投入
        this.money += money;
        System.out.println(this.money + "円を投入しました");
        System.out.println("現在" + this.money + "円入っています");
    }

    public void buyDrink(int index) {//drinkを買う
        if (this.regist[index - 1] == null) {
            System.out.println("購入できませんでした(" + index + "番には飲み物が登録されていません)");
        } else if (this.money >= this.regist[index - 1].getValue()) {
            System.out.println(index + "番の「" + this.regist[index - 1].getName() + "」を購入しました");
            this.money -= this.regist[index - 1].getValue();
        } else {
            System.out.println("購入できませんでした(お金が足りません)");
        }
    }

    public void returnMoney() {//お釣りの出力
        System.out.println(this.money + "円のお釣りを出力しました");
        this.money = 0;
    }

    public void printInfo() {//メニューの出力
        for (int i = 0; i <= 4; i++) {
            if (this.regist[i] != null) {
                System.out.print((i + 1) + "  " + this.regist[i].getName());
                for(int j = 0; j <= (max - this.regist[i].getName().length()); j++){
                    System.out.print("  ");
                }
                System.out.println(this.regist[i].getValue() + "円");
                //System.out.println();
            } else {
                System.out.println((i + 1) + "  --- 未登録 ---  ");
            }
        }
    }
}