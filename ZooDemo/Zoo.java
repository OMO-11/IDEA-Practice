package ZooDemo;

class Zoo {
    Animal[] animals;
    int count;

    // 构造方法，初始化动物园容量
    public Zoo(int size) {
        animals = new Animal[size];
        count = 0;
    }

    // 添加动物到动物园
    public void addAnimal(Animal animal) {
        if (count < animals.length) {
            animals[count] = animal;
            count++;
            System.out.println("添加: " + animals[count - 1].name);
            System.out.println("动物园容量还剩：" + (animals.length - count));
        } else {
            System.out.println("动物园已经塞满了！");
        }
    }

    //修改动物信息
    public void updateAnimal(int index, String newName, int newAge) {
        if (index >= 0 && index < count) {
            Animal animal = animals[index];
            String oldInfo = animal.toString();

            if (newName != null && !newName.isEmpty()) {
                animal.setName(newName);
            }
            if (newAge > 0) {
                animal.setAge(newAge);
            }
            System.out.println("动物信息更新成功");
        } else {
            System.out.println("索引" + index + "错误，正确索引为" + 0 + "~" + (count - 1));
        }
    }

    //获取动物索引
    public int findAnimalIndex(String name) {
        for (int i = 0; i < count; i++) {
            if (animals[i].getName().equals(name)) {
                return i;
            }
        }
        return -1; // 未找到
    }

    // 显示动物园信息
    public void displayZooInfo() {
        System.out.println("动物园的动物:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + animals[i].name + ", Age: " + animals[i].age);
        }
    }
}
