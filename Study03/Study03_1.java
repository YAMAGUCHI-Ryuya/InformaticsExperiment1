public class Study03_1 {
    public static void main(String args[]) {
        Dog_1 dog1 = new Dog_1("ギン", 60, 30, 20, 20);
        Monkey_1 monkey1 = new Monkey_1("ゴクウ", 60, 35, 15, 20);

        dog1.printStatus();
        monkey1.printStatus();

        int i = 1;
        while (dog1.getHp() != 0 && monkey1.getHp() != 0) {//両方のHPがある状態の時実行
            System.out.println("--------------- ターン" + i + " ---------------");
            monkey1.defend(dog1.attack());//dog1の攻撃とmonkey1の防御
            if (monkey1.getHp() == 0) {
                System.out.println(monkey1.getName() + "のHPが0になった!");
                break;
            }
            System.out.println();
            dog1.defend(monkey1.attack());//monkey1の攻撃とdog1の防御
            i++;
        }
        if (dog1.getHp() == 0) {
            System.out.println(dog1.getName() + "のHPが0になった!");
        }
    }
}