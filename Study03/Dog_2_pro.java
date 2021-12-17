public class Dog_2_pro extends Animal_pro{
        private int critical;
    
        public Dog_2_pro(String name, int hp, int power, int defense, int critical) {
            super(name, hp, power, defense);
            this.critical = critical;
        }
    
        public int attack() {
            super.attack();
            int r = new java.util.Random().nextInt(100);
            if (r < this.critical) {
                System.out.println(this.name + "は強く噛み付いた!");
                return this.power * 3/2;
            } else {
                return this.power;
            }
        }
    
        public void printStatus() {
            super.printStatus();
            System.out.println("会心率: " + this.critical);
            System.out.println();
        }
}