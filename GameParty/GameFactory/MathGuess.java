package GameParty.GameFactory;

import GameParty.User.UserProfile;

import java.util.Random;
import java.util.Scanner;

public class MathGuess {
    public static void maxAnd_min(String userName){
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int guess = rand.nextInt(100) + 1;

        System.out.println("来猜猜我心里在想什么数字，1~100选一个喵！( •̀ ω •́ )✧");
        int x = 0;
        while(true){
            int num = sc.nextInt();
            sc.nextLine();

            if(num == guess){
                System.out.println("对了喵！恭喜恭喜！！！");
                break;
            }
            else{
                x++;
                System.out.println("不对不对，再试试看吧！");
            }
        }

        if(x == 1){
            System.out.println("才一次就猜中了，不愧是你！");
            System.out.println(userName + "酱得了100分喵！");
        }
        else if(x > 2 && x < 7){
            System.out.println("蛙！！！");
            System.out.println(userName + "酱得了80分喵！");
        }
        else if(x > 7){
            System.out.println("没事的喵，笨笨的也很可爱喵！(*^_^*)");
            System.out.println(userName + "酱及格了喵，得了60分！！");
        }
    }
}
