package ZooDemo;

class Elephant extends Animal {
    // 构造方法
    public Elephant(String name, int age) {
        super(name, age);
    }

    @Override
    // 大象的叫声
    public void makeSound() {
        System.out.println("大象" + this.name + "叫得很大声！");
    }

    @Override
    // 重写发出特定声音的方法
    public void makeSound(String sound){
        System.out.println("大象" + this.name + "发出了" + sound + "的叫声！");
    }

    @Override
    // 大象进食的方法
    public void eating(String leaf) {
        System.out.println("大象" + this.name + "在吃" + leaf + "！");
    }
}
