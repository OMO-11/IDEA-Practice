package ZooDemo;

public class ZooDemo {
    public static void main(String[] args) {
        //设置动物园容量：(size-1)
        Zoo zoo = new Zoo(5);

        //添加动物
        zoo.addAnimal(new Lion("狮子1",6));
        zoo.addAnimal(new Lion("狮子2",7));
        zoo.addAnimal(new Elephant("大象",8));

        //展示所有动物
        zoo.displayZooInfo();

        //获取索引并修改信息,
        int index1 = zoo.findAnimalIndex("狮子1");
        int index2 = zoo.findAnimalIndex("狮子2");
        int index3 = zoo.findAnimalIndex("大象");

        zoo.updateAnimal(index1,"狮子猫", -1);
        zoo.updateAnimal(index2,"狮子狗", 9);
        zoo.updateAnimal(index3,null, 10);

        //再次展示
        zoo.displayZooInfo();
    }
}
