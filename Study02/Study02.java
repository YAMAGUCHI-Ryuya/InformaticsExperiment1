public class Study02 {
    public static void main(String args[]) {
        Drink drink1 = new Drink("お茶", 130);
        Drink drink2 = new Drink("コーラ", 160);
        Drink drink3 = new Drink("ソーダ", 160);
        Drink drink4 = new Drink("ココア", 120);
        Drink drink5 = new Drink("コーヒー", 110);

        VendingMachine vm = new VendingMachine();

        vm.registDrink(0, drink1);
        vm.registDrink(0,drink2);
        vm.registDrink(1, drink2);
        vm.registDrink(2, drink3);
        //vm.registDrink(3, drink4);
        vm.registDrink(4, drink5);

        System.out.println();
        System.out.println("=====================");
        System.out.printf("%5s %7s","名前","値段");
        System.out.println();
        vm.printInfo();
        System.out.println("=====================");
        System.out.println();

        System.out.println("行いたい操作を指定してください");
        System.out.println("(1:お金の投入, 2:飲み物の購入, 3:お釣りの出力, 4:終了)");
        System.out.print("> ");
        int input = new java.util.Scanner(System.in).nextInt();

        while (input != 4) {//終了されない限りループ
            if (input == 1) {
                System.out.println("投入する金額を指定してください");
                System.out.print("> ");
                int money = new java.util.Scanner(System.in).nextInt();
                vm.insertMoney(money);
            } else if (input == 2) {
                System.out.println("購入する飲み物の番号を指定してください");
                System.out.print("> ");
                int num = new java.util.Scanner(System.in).nextInt();
                vm.buyDrink(num);
            } else if (input == 3) {
                vm.returnMoney();
            } 
            System.out.println();
            System.out.println("行いたい操作を指定してください");
            System.out.println("(1:お金の投入, 2:飲み物の購入, 3:お釣りの出力, 4:終了)");
            System.out.print("> ");
            input = new java.util.Scanner(System.in).nextInt();
        }
    }
}