package Hiane;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Hiane {
    public static class NoPerson extends Exception {
        public NoPerson() {
            super();
        }
        public NoPerson(String msg) {
            super(msg);
        }
        public NoPerson(Throwable cause) {
            super(cause);
        }
        public NoPerson(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static class Person {
        private String id;
        private String name;
        private int age;

        public Person(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        // 添加 getter 方法
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String toString() {
            return "The information = [" + id + ", " + name + ", " + age + "]";
        }
    }

    public static class PersonSystem {
        private Map<String, Person> persons;

        public PersonSystem() {
            persons = new HashMap<>();
        }

        public void addPerson(Person person) throws NoPerson {
            if (person == null) {
                throw new NoPerson("Please enter a valid person!");
            }
            if (persons.containsKey(person.getId())) {
                throw new NoPerson("Person with id " + person.getId() + " already exists!");
            }
            persons.put(person.getId(), person);
            System.out.println("用户已保存！");
        }

        public void removePerson(String personId) throws NoPerson {
            if (personId == null || personId.trim().isEmpty()) {
                throw new NoPerson("Please enter a valid person id!");
            }
            if (!persons.containsKey(personId)) {
                throw new NoPerson("Person with id " + personId + " does not exist!");
            }
            Person removed = persons.remove(personId);
            System.out.println("用户 " + removed.getName() + " 已删除！");
        }

        public void displayPersons() {
            if (persons.isEmpty()) {
                System.out.println("没有用户信息！");
                return;
            }
            System.out.println("用户列表:");
            for (Map.Entry<String, Person> entry : persons.entrySet()) {
                System.out.println(entry.getValue());
            }
        }

        public void menu() {
            System.out.println("\n=== The Person System ===");
            System.out.println("1. Add Person");
            System.out.println("2. Remove Person");
            System.out.println("3. Display Person");
            System.out.println("4. Exit");
            System.out.print("请选择: ");
        }

        // 添加一个获取用户数量的方法
        public int getPersonCount() {
            return persons.size();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PersonSystem per = new PersonSystem();
        int choice = 0;

        while (choice != 4) {
            per.menu();

            // 输入验证
            if (!sc.hasNextInt()) {
                System.out.println("请输入有效的数字！");
                sc.nextLine(); // 清除错误输入
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine(); // 读取换行符

            switch (choice) {
                case 1:
                    try {
                        System.out.print("请输入ID: ");
                        String id = sc.nextLine().trim();
                        if (id.isEmpty()) {
                            System.out.println("ID不能为空！");
                            break;
                        }

                        System.out.print("请输入姓名: ");
                        String name = sc.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("姓名不能为空！");
                            break;
                        }

                        System.out.print("请输入年龄: ");
                        if (!sc.hasNextInt()) {
                            System.out.println("年龄必须是数字！");
                            sc.nextLine(); // 清除错误输入
                            break;
                        }
                        int age = sc.nextInt();
                        sc.nextLine(); // 读取换行符

                        if (age < 0 || age > 150) {
                            System.out.println("年龄必须在0-150之间！");
                            break;
                        }

                        Person person = new Person(id, name, age);
                        per.addPerson(person);
                    } catch (NoPerson e) {
                        System.out.println("错误: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("请输入要删除的用户ID: ");
                    String removeId = sc.nextLine().trim();
                    try {
                        per.removePerson(removeId);
                    } catch (NoPerson e) {
                        System.out.println("错误: " + e.getMessage());
                    }
                    break;

                case 3:
                    per.displayPersons();
                    break;

                case 4:
                    System.out.println("退出系统！当前用户数量: " + per.getPersonCount());
                    break;

                default:
                    System.out.println("无效选择！请选择1-4之间的数字。");
                    break;
            }
        }
        sc.close();
    }
}