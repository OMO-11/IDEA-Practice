package ZooDemo;

class Animal {
    String name;
    int age;

    // 构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 发出声音的方法
    public void makeSound() {
        System.out.println("动物在叫！");
    }

    //发出特定叫声的方法
    public void makeSound(String sound){
        System.out.println("动物发出了" + sound + "的声音！");
    }

    //吃东西的方法
    public void eating(String food) {
        System.out.println("动物在吃" + food + "！");
    }

    //JavaBean
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }
}

