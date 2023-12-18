package com.zhuang.aspect.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisSingleSignOn {

    @Autowired
    private static Jedis jedis;

    private static final String USER_INFO_KEY = "token";
    private static final String USER_ID_KEY = "userid";
    private static final String SECRET_KEY = "your_secret_key"; // 用于加密和解密的密钥

    public static void main(String[] args) {
        //login();
        repeatLogin();
    }

    static {
        // 创建Jedis实例连接Redis服务器
        jedis = new Jedis("localhost", 6379);
    }

    public static void login() {
        // 登录系统生成Token并存储到Redis中
        String userId = "user1"; // 假设有一个用户ID为"user1"的用户登录了系统
        String password = "password1"; // 假设用户输入的密码为"password1"
        if (isValidUser(userId, password)) {
            String token = generateToken(userId); // 生成Token的逻辑可以根据你的需求实现
            jedis.set(USER_INFO_KEY, token); // 将Token存储到Redis中，并设置过期时间
            jedis.set(USER_ID_KEY + ":" + userId, token); // 将Token与用户ID关联存储到Redis中
            System.out.println("User " + userId + " logged in successfully.");
        } else {
            System.out.println("Invalid user or password.");
        }
    }

    public static void repeatLogin(){
        loginAgain("user1");
    }

    // 检查用户名和密码是否正确，这里只是一个示例代码片段，需要根据实际情况实现验证逻辑
    private static boolean isValidUser(String userId, String password) {
        // 检查用户名和密码是否正确的逻辑实现...
        return true; // 返回一个布尔值表示用户名和密码是否正确，这里只是为了示例说明，实际上需要实现用户验证逻辑
    }

    // 生成Token的逻辑可以根据你的需求实现，这里只是一个示例代码片段，使用UUID作为示例Token生成方式
    private static String generateToken(String userId) {
        return UUID.randomUUID().toString(); // 使用UUID生成一个唯一的Token字符串示例
    }

    // 当用户在其他电脑再次登录时，先查出userid，并生成新的token，并继续放入两个k-v
    public static void loginAgain(String userId) {
        // 从Redis中获取与用户ID关联的Token
        String oldToken = jedis.get(USER_ID_KEY + ":" + userId);
        if (oldToken != null) { // 如果获取到了旧Token，则更新为新Token并存储到Redis中
            String newToken = generateToken(userId); // 生成新Token
            jedis.set(USER_INFO_KEY, newToken); // 将新Token存储到Redis中，并设置过期时间
            jedis.set(USER_ID_KEY + ":" + userId, newToken); // 将新Token与用户ID关联存储到Redis中
            System.out.println("User " + userId + " logged in again.");
        } else { // 如果未获取到旧Token，则无法登录，返回错误信息
            System.out.println("Invalid token.");
        }
    }

    // 当用户在上一次登录的地方，再次点击查询时（把token和userid带过来），会通过userid去redis查询出token，如果token和浏览器中的不一致，就被其他人挤出。
    public static void checkToken(String userId, String token) {
        // 从Redis中获取与用户ID关联的Token
        String redisToken = jedis.get(USER_ID_KEY + ":" + userId);
        if (redisToken != null && redisToken.equals(token)) { // 如果获取到了旧Token且与浏览器中的一致，则允许访问被授权系统
            System.out.println("User " + userId + " is authenticated and allowed to access the system.");
        } else { // 如果未获取到旧Token或与浏览器中的不一致，则拒绝访问被授权系统并显示错误信息。
            System.out.println("Invalid or expired token.");
        }
    }
}
