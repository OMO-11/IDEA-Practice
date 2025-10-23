package ZooDemo;

class Lion extends Animal {
    // 构造方法
    public Lion(String name, int age) {
        super(name, age);
    }

    @Override
    // 狮子的叫声
    public void makeSound() {
        System.out.println("狮子" + this.name + "叫得很大声！");
    }

    @Override
    // 重写发出特定声音的方法
    public void makeSound(String sound){
        System.out.println("狮子" + this.name + "发出了" + sound + "的叫声！");
    }

    @Override
    // 狮子进食的方法
    public void eating(String meat) {
        System.out.println("狮子" + this.name + "在吃" + meat + "！");
    }
}

