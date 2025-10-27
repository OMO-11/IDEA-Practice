package GameParty.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class UserProfile implements Serializable {
    private String username;
    private String password;
    private int totalScore;
    private Map<String, Integer> gameScores = new HashMap<>();      //各游戏得分
    private List<String> favoriteGames = new ArrayList<>();           //偏好游戏
    private LocalDate registrationDate;           //注册日期

    //更新游戏偏好
    public void updatePreferences(String gameName, int enjoymentLevel){}

    public UserProfile(){}
    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
        this.totalScore = 0;
        this.registrationDate = LocalDate.now();
    }

    //JavaBean
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }
    public Integer getTotalScore(){
        return this.totalScore;
    }

    public void setRegistrationDate(LocalDate registrationDate){
        this.registrationDate = registrationDate;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Map<String, Integer> getGameScore(){
        return Collections.unmodifiableMap(gameScores);
    }
    public List<String> getFavoriteGames(){
        return Collections.unmodifiableList(favoriteGames);
    }

    //Map和List的格式化方法
    public String getGameScoresformatted(){
        if(gameScores.isEmpty()){
            return "没有游戏记录喵？先去玩玩游戏吧！";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("来看看你各个游戏都得了多少分吧喵！( •̀ ω •́ )✧\n");
        gameScores.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) //这里是按分数降序
                .forEach(entry ->
                        sb.append(entry.getKey() + ": " + entry.getValue() + "分" + "\n")
                );
        return sb.toString();
    }

    public String getFavoriteGamesFormatted(){
        if(favoriteGames.isEmpty()) {
            return "居然没有喜欢的游戏吗！对不起喵，我会继续努力，直到让你能够喜欢和我玩的！！(；′⌒`)";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("来看看你最喜欢和我玩什么游戏吧！\n");
        for(int i = 0; i < favoriteGames.size(); i++){
            sb.append("   ").append(i + 1).append(". ").append(favoriteGames.get(i)).append("\n");
        }
        return sb.toString();
    }
}
