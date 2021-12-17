public class Study03_2_pro {
    public static void main(String args[]) {
        Dog_2 dog2 = new Dog_2("ギン", 60, 30, 20, 20);
        Monkey_2 monkey2 = new Monkey_2("ゴクウ", 60, 35, 15, 20);

        dog2.printStatus();
        monkey2.printStatus();

        int i = 1;
        while (dog2.getHp() != 0 && monkey2.getHp() != 0) {
            System.out.println("--------------- ターン" + i + " ---------------");
            monkey2.defend(dog2.attack());
            if (monkey2.getHp() == 0) {
                System.out.println(monkey2.getName() + "のHPが0になった!");
                break;
            }
            System.out.println();
            dog2.defend(monkey2.attack());
            i++;
        }
        if (dog2.getHp() == 0) {
            System.out.println(dog2.getName() + "のHPが0になった!");
        }
    }
}