package GameParty.User;

import GameParty.User.File.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.util.Scanner;

public class User_function {
    private static final String INFORMATION_PATH = "W:\\Machine\\IDEA\\JavaBox\\untitled\\GameParty\\User\\User_Information\\";

    public static String displayDateTime(UserProfile user){
        LocalDate date = user.getRegistrationDate();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(dtf);  // 直接返回格式化后的字符串
    }

    public static void displayInformation(UserProfile user) {
        System.out.println("下面是你的账号信息喵！ヾ(≧▽≦*)o");
        System.out.println("用户名：" + user.getUsername());
        System.out.println("游戏最高得分！：" + user.getTotalScore());
        System.out.println("注册月份：" + displayDateTime(user));
        System.out.println(user.getGameScoresformatted());
        System.out.println(user.getFavoriteGamesFormatted());
    }

    //注册
    public static void registerUser(UserProfile user) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你的名字和密码喵！");
        String username = sc.nextLine();
        user.setUsername(username);
        String password = sc.nextLine();
        user.setPassword(password);
        System.out.println(username + "酱！我记住你了喵！！要常来和M9li2e玩哦！");

        upload.uploadFile(user);
    }

    //登录
}
