package com.itheima;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * ATM系统的入口类
 */
public class ATMSystem {
    public static void main(String[] args) {
        //1.定义账户类
        //2.定义一个集合容器，负责以后存储全部的账户对象，进行相关的业务操作
        ArrayList<Account> accounts = new ArrayList<>();
        //3.展示系统的首页
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===========黑马ATM系统===========");
            System.out.println("1.账户登录");
            System.out.println("2.账户开户");
            System.out.println("3.退出");
            System.out.println("请选择操作：");
            int command = sc.nextInt();
            switch (command) {
                case 1 ->
                        //用户登录
                        login(accounts, sc);
                case 2 ->
                        //用户账户开户
                        register(accounts, sc);
                case 3 -> {
                    //退出
                    System.out.println("退出成功！");
                    return;
                }
                default -> System.out.println("您输入的命令操作不存在");
            }
        }
    }

    /**
     * 登录功能
     * @param accounts 全部账户对象的集合
     * @param sc 扫描器
     */
    private static void login(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("=======系统登录操作=======");
        //1.判断账户集合中是否存在账户,如果不存在,登录功能不能进行。
        if(accounts.size() == 0){
            System.out.println("当前系统中无任何账户,请先开户,再来登录~~~");
            return; //结束方法执行
        }

        //2.进入登录操作
        while (true) {
            System.out.println("请您输入登录卡号：");
            String cardID = sc.next();
            //3.判断卡号是否存在：根据卡号去账户集合中查询账户对象。
            Account acc = getAccountByCarID(cardID, accounts);
            if (acc != null){
                while (true) {
                    //卡号存在!
                    //4.让用户输入密码,并且判断密码是否正确
                    System.out.println("请您输入登录密码：");
                    String passWord = sc.next();
                    //判断密码是否正确
                    if(acc.getPassWord().equals(passWord)){
                        //密码正确
                        System.out.println("登录成功！" + acc.getUserName() + "先生/女士,您的卡号是：" + acc.getCardId());
                        //查询 转账 存款 取款
                        //展示登录后的操作页
                        showUserCommand(sc, acc, accounts);
                        return; //结束登录方法
                    }else{
                        //密码错误
                        System.out.println("密码错误！");
                    }
                }
            }else{
                // 卡号不存在！
                System.out.println("对不起,您输入的卡号不存在！");
            }
        }
    }

    /**
     * 展示登录后的操作页
     */
    private static void showUserCommand(Scanner sc,Account acc,ArrayList<Account> accounts) {
        while (true) {
            System.out.println("=======用户操作页=======");
            System.out.println("1.查询账户");
            System.out.println("2.存款");
            System.out.println("3.取款");
            System.out.println("4.转账");
            System.out.println("5.修改密码");
            System.out.println("6.退出");
            System.out.println("7.注销账户");
            System.out.println("请选择：");
            int command = sc.nextInt();
            switch (command){
                case 1:
                    //查询账户
                    showAccount(acc);
                    break;
                case 2:
                    //存款
                    depositMoney(acc, sc);
                    break;
                case 3:
                    //取款
                    drawMoney(acc, sc);
                    break;
                case 4:
                    //转账
                    transferMoney(sc, acc, accounts);
                    break;
                case 5:
                    //修改密码
                    updatePassWord(sc,acc);
                    return; //回到首页(登录页面)
                case 6:
                    //退出
                    System.out.println("退出成功！");
                    return; //让当前方法停止执行
                case 7:
                    //注销账户
                    if (deleteAccount(acc, sc, accounts)){
                        return; //销户成功,返回首页
                    }else{
                        break; //销户失败,留在操作页
                    }
                default:
                    System.out.println("您输入的名字不存在！");
            }
        }
    }

    /**
     * 销户功能
     * @param acc 当前账户对象
     * @param sc 扫描器
     * @param accounts 全部账户的集合对象
     */
    private static boolean deleteAccount(Account acc, Scanner sc, ArrayList<Account> accounts) {
        System.out.println("=======用户销户=======");
        System.out.println("请问您真的要销户嘛?(y/n)");
        String rs = sc.next();
        if ("y".equals(rs)) {
            //销户
            //从当前账户集合中删除账户对象,销户就完成了！
            if (acc.getMoney() > 0) {
                System.out.println("您当前账户的余额大于0,不允许销户！");
            } else {
                //销户
                accounts.remove(acc);
                System.out.println("销户成功！");
                return true;
            }
        } else {
            System.out.println("好的,当前账户继续保留");
        }
        return false;
    }

    /**
     * 修改密码功能
     * @param sc 扫描器
     * @param acc 当前登录成功的账户对象
     */
    private static void updatePassWord(Scanner sc, Account acc) {
        System.out.println("=======用户密码修改=======");
        System.out.println("请您输入当前密码：");
        String passWord = sc.next();
        if (acc.getPassWord().equals(passWord)){
            while (true) {
                //密码输入正确,可以更改密码
                //输入新密码
                System.out.println("请您输入新密码：");
                String newPassWord = sc.next();
                System.out.println("请您再次输入新密码：");
                String okPassWord = sc.next();
                if(newPassWord.equals(okPassWord)){
                    //两次密码输入一致,更改密码！
                    acc.setPassWord(newPassWord);
                    System.out.println("密码修改成功！");
                    return; //结束修改密码功能！
                }else{
                    //两次密码输入不一致
                    System.out.println("两次密码输入不一致！");
                }
            }
        }else{
            System.out.println("密码错误！");
        }
    }


    /**
     * 转账功能
     * @param sc 扫描器
     * @param acc 自己的账户对象(当前登录的账户)
     * @param accounts 全部账户对象的集合
     */
    private static void transferMoney(Scanner sc, Account acc, ArrayList<Account> accounts) {
        System.out.println("=======用户转账操作=======");
        //1.判断是否足够2个账户
        if(accounts.size() < 2){
            System.out.println("当前系统中账户数量不足2个，无法完成转账！");
            return; //结束当前方法
        }
        //2.判断自己的账户是否有钱
        if(acc.getMoney() == 0){
            System.out.println("您当前账户余额为0,无法转账");
            return; //结束当前方法
        }
        while (true) {
            //3.开始转账
            System.out.println("请输入对方卡号：");
            String cardID = sc.next();
            //这个卡号不能是自己的卡号
            if (cardID.equals(acc.getCardId())){
                System.out.println("不能给自己转账！");
                continue; //结束本次循环开始下一次循环
            }
            //判断这个卡号是否存在
            Account account = getAccountByCarID(cardID, accounts);
            if (account == null){
                System.out.println("您输入的卡号不存在！");
            }else{
                //这个账户对象存在
                //验证对方姓名
                String userName = account.getUserName();
                String tip = "*" + userName.substring(1);   //给用户名打码
                System.out.println("请您输入[" + tip + "]的姓氏");
                String preName = sc.next();
                //认证姓氏是否正确
                if(userName.startsWith(preName)){
                    while (true) {
                        //认证通过,开始转账
                        System.out.println("请您输入转账金额：");
                        double money = sc.nextDouble();
                        //判断余额是否足够
                        if (money > acc.getMoney()){
                            System.out.println("余额不足！您最多可以转账：" + acc.getMoney() + "元");
                        }else{
                            //余额足够,可以转账
                            acc.setMoney(acc.getMoney() - money);
                            account.setMoney(account.getMoney() + money);
                            System.out.println("转账成功！您当前账户余额：" + acc.getMoney());
                            return; //结束转账方法！
                        }
                    }
                }else{
                    //认证失败
                    System.out.println("您输入的信息有误！");
                }
            }
        }
    }


    /**
     * 取款功能
     * @param acc 当前账户对象
     * @param sc 扫描器
     */
    private static void drawMoney(Account acc, Scanner sc) {
        System.out.println("=======用户取款操作=======");
        //1.判断是否足够100元
        if(acc.getMoney() < 100){
            System.out.println("当前账户中余额不足100元,无法取款！");
            return;
        }
        while (true) {
            //2.提示用户输入取款金额
            System.out.println("请您输入取款金额：");
            double money = sc.nextDouble();

            //3.判断是否满足限额要求
            if (money > acc.getQuotaMoney()){
                System.out.println("对不去,您本次取款大于单次限额,无法取款！单次限额：" + acc.getQuotaMoney());
            }else{
                //4.没有超过限额
                //是否超过账户总余额
                if(money > acc.getMoney()){
                    System.out.println("余额不足！您目前的余额是：" + acc.getMoney());
                }else{
                    //可以取款了
                    System.out.println("您本次取款：" + money);
                    //更新余额
                    acc.setMoney(acc.getMoney() - money);
                    //取钱结束 展示信息
                    showAccount(acc);
                    return; //终止取款方法
                }
            }
        }
    }

    /**
     * 存钱
     * @param acc 当前账户对象
     * @param sc 扫描器
     */
    private static void depositMoney(Account acc, Scanner sc) {
        System.out.println("=======用户存钱操作=======");
        System.out.println("请您输入存款金额：");
        double money = sc.nextDouble();
        //更新账户余额：原来的钱 + 新存入的钱
        acc.setMoney(acc.getMoney() + money);
        System.out.println("完成！当前账户信息如下：");
        showAccount(acc);
    }


    /**
     * 展示账户信息
     * @param acc   当前登录的账户
     */
    private static void showAccount(Account acc) {
        System.out.println("=======当前账户信息如下=======");
        System.out.println("卡号：" + acc.getCardId());
        System.out.println("姓名：" + acc.getUserName());
        System.out.println("余额：" + acc.getMoney());
        System.out.println("限额：" + acc.getQuotaMoney());
    }


    /**
     * 用户开户功能的实现
     * @param accounts  接受账户的集合
     */
    private static void register(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("=======系统开户操作=======");
        //1.创建一个账户对象，用于后期封装账户信息
        Account account = new Account();
        //2.录入当前这个账户的信息，注入到账户对象中去
        System.out.println("请您输入账户用户名：");
        String userName = sc.next();
        account.setUserName(userName);  //设置用户名
        //下面开始设置用户密码
        while (true) {
            System.out.println("请您输入账户密码：");
            String passWord = sc.next();
            System.out.println("请您再次输入账户密码：");
            String okPassWord = sc.next();
            if (okPassWord.equals(passWord)){
                System.out.println("密码设置成功！");
                account.setPassWord(okPassWord);
                break;  //密码录入成功，结束死循环
            }else {
                System.out.println("两次密码不一致，请重新输入：");
            }
        }
        //为账户设置额度
        System.out.println("请您输入当次限额：");
        double quotaMoney = sc.nextDouble();
        account.setQuotaMoney(quotaMoney);
        // 为账户随机一个8位且与其他账户不同的卡号！！！(独立功能,独立成方法)
        String cardID = getRandomCardID(accounts);
        account.setCardId(cardID);
        //3.把账户对象添加到账户集合中去
        accounts.add(account);
        System.out.println("恭喜您：" + userName + "先生," + "您开户成功，您的卡号是：" + cardID + ",请妥善保管！");
    }


    /**
     * 为账户生成8位与其他卡号不同的数字
     * @return  cardID
     */
    private static String getRandomCardID(ArrayList<Account> accounts) {
        Random r = new Random();
        while (true) {
            //1.生成8位数字
            String cardID = "";
            for (int i = 0; i < 8; i++) {
                cardID += r.nextInt(10);
            }
            //2.判断这个8位的卡号是否与其他用户重复了
            //根据这个卡号去查询账户的对象
            Account acc = getAccountByCarID(cardID, accounts);
            if (acc == null){
                //说明此时cardID没有重复,可以使用这个新卡号了
                return cardID;
            }
        }
    }


    /**
     * 根据卡号查询出一个账户信息出来
     * @param cardID    卡号
     * @param accounts  全部账户的信息
     * @return  账户对象 | null
     */
    private static Account getAccountByCarID(String cardID, ArrayList<Account> accounts){
        //循环遍历所有用户
        //当前账户
        for (Account acc : accounts) {
            //如果当前的账户的卡号和我要找的卡号相同
            if (acc.getCardId().equals(cardID)) {
                return acc;
            }
        }
        return null;//没有找到这个账号
    }
}
