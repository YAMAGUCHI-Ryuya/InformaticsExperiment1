public class Study04 {
    public static void main(String args[]) {
        String loseName = new String();
        String result = new String();
        Hero hero = new Hero("勇者", 150, 20);
        Enemy enemy = new Enemy("魔王", 200, 25);
        Warrior w = new Warrior("戦士", 150, 30);
        Priest p = new Priest("僧侶", 100, 10);
        Dancer d = new Dancer("踊り子", 100, 10);
        Hero[] buddy = new Hero[3];// 仲間の配列
        buddy[0] = w;
        buddy[1] = p;
        buddy[2] = d;

        hero.showStatus();// 勇者のステータス
        System.out.println();

        System.out.println(">> 仲間候補のキャラクター");
        printStatus(buddy);
        System.out.println();

        System.out.println(">> 連れていく仲間を一人選んでください(IDを入力)");
        System.out.print("ID: ");
        int input = new java.util.Scanner(System.in).nextInt();// キーボード入力

        Hero[] party = new Hero[2];// 現在のパーティを格納
        party[0] = hero;
        for (int i = 0; i < buddy.length; i++) {
            if (buddy[i].getId() == input) {// buddy[i]の配列のidと入力値の対応付け
                party[1] = buddy[i];
            }
        }
        System.out.println("\s\s" + ">> " + party[1].getName() + "が仲間になった!" + "\n");

        System.out.println(">> 現在のパーティ");
        printStatus(party);// パーティの表示

        System.out.println(enemy.getName() + "が現れた!");
        enemy.showStatus();// 魔王のステータス
        System.out.println();

        System.out.println("-------BATTLE START-------");
        int round = 1;
        while (party[0].getHp() != 0 && enemy.getHp() != 0) {// 両方のHPがある状態で実行
            System.out.println("< ラウンド" + round + " >");
            if (party[1].getHp() > 0) {
                if (party[1].getId() == w.getId()) {
                    w.skill(enemy);
                    w.resetStatus();
                } else if (party[1].getId() == p.getId()) {
                    p.skill(party[0]);
                } else {
                    d.skill(party[0]);
                }
            }
            if (enemy.getHp() != 0) {
                party[0].attack(enemy);//全体攻撃
                if (party[1].getId() == d.getId()) {
                    party[0].resetStatus();
                }
            }
            if (enemy.getHp() == 0) {
                loseName = enemy.getName();
                result = "WIN!";
                break;
            }
            if (round % 2 != 0) {// 2回に1回攻撃
                enemy.attack(party);
            } else {
                enemy.attack(party[0]);// 勇者に攻撃
            }
            round++;
            System.out.println();
        }
        if (party[0].getHp() == 0) {
            loseName = party[0].getName();
            result = "LOSE";
        }
        System.out.println(loseName + "は倒れた!");
        System.out.println("-------YOU " + result + "-------");
    }

    public static void printStatus(Hero[] h) {
        for (int i = 0; i < h.length; i++) {
            h[i].showStatus();// 仲間ステータス表示
        }
    }
}