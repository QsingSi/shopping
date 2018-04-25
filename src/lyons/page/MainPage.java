package lyons.page;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;

import com.sun.deploy.jcp.Main;
import com.sun.java_cup.internal.runtime.Scanner;
import com.sun.jmx.snmp.internal.SnmpSubSystem;
import com.sun.tools.jdeprscan.scan.Scan;

import lyons.dao.GoodsDao;
import lyons.dao.GsalesDao;
import lyons.dao.SalesManDao;
import lyons.entity.Goods;
import lyons.entity.Gsales;
import lyons.entity.SalesMan;
import lyons.tools.Arith;
import lyons.tools.QueryPrint;
import lyons.tools.ScannerChoice;
import sun.plugin.PluginURLJarFileCallBack;

/**
 * 商超购物管理系统主界面
 * @author weizq
 * @version 1.0
 */
public final class MainPage extends ScannerChoice {
    /**
     * 入口函数
     */
    public static void main(Strings[] args) {
        MainPage.mainPage();
    }

    /**
     * 主界面 已实现！ 已校验！
     */
    public static void mainPage() {
        System.out.println("***********************");
        System.out.println("\t1.商品维护\n");
        System.out.println("\t2.前台收银\n");
        System.out.println("\t3.商品管理\n");
        System.out.println("***********************");

        System.out.println("\n请输入选项，或者按0退出.");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-3]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                case 0:
                    System.out.println("-----------------");
                    System.out.println("您已经退出系统!");
                    System.exit(1);
                    break;
                case 1:
                    MaintenancePage();
                    break;
                case 2:
                    checkstandLogPage();
                    break;
                case 3:
                    commodityManagementPage();
                    break;
                default:
                    break;
                }
            }
            System.out.println("！输入有误！");
            System.out.println("重新选择或者按0退出.");
        } while (1);
    }

    /**
     * 1.商品维护界面
     */
    public static void MaintenancePage() {
        System.out.println("************************");
        System.out.println("\t1.添加商品\n");
        System.out.println("\t2.更改商品\n");
        System.out.println("\t3.删除商品\n");
        System.out.println("\t4.查询商品\n");
        System.out.println("\t5.显示所有商品\n");
        System.out.println("************************");

        System.out.println("\n请输入选项，或者按 0 返回上一级菜单.");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                case 0:
                    mainPage();
                    break;
                case 1:
                    GoodsPage.addGoodsPage();
                    break;
                case 2:
                    GoodsPage.updateGoodsPage();
                    break;
                case 3:
                    GoodsPage.deleteGoodsPage();
                    break;
                case 4:
                    GoodsPage.queryGoodsPage();
                    break;
                case 5:
                    GoodsPage.displayGoodsPage();
                    break;
                default:
                    break;
                }
            }
            System.out.println("!输入有误!");
            System.out.println("重新输入或者按 0 返回上一级菜单");
        } while (1);
    }

    /**
     * 2.前台收银登录界面
     */
    public static void checkstandLogPage() {
        System.out.println("\n*******欢迎使用商超购物管理系统*********\n");
        System.out.println("\t 1.登录系统\n");
        System.out.println("\t 2.退出\n");
        System.out.println("------------------------------");
        System.out.println("请输入选项，或者按 0 返回上一级菜单.");

        do {
            String choice = ScannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                case 0:
                    mainPage();
                    break;
                case 1:
                    int loginTimes = 3;
                    while (loginTimes != 0) {
                        loginTimes--;
                        System.out.println("---用户名---");
                        String sName = ScannerInfoString();
                        System.out.println("---密码---");
                        String sPassword = ScannerInfoString();

                        ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName);
                        if (salesManInfo == null || salesManInfo.size() == 0) {
                            System.out.println("\t!!用户名输入有误!!\n");
                            System.out.println("\n剩余登陆次数: " + loginTimes);
                        } else {
                            SalesMan salesMan = salesManInfo.get(0);
                            if (sPassword.equals(salesMan.getSPassWord())) {
                                System.out.println("\t ---账户登录成功---");
                                shoppingSettlementPage(salesMan.getSId());
                            } else {
                                System.err.println("\t!!密码错误!!\n");
                                System.out.println("\n剩余登陆次数: " + loginTimes);
                            }
                        }
                    }
                    System.out.println("----------------");
                    System.out.println("\t!!您已被强制退出系统!!");
                    System.exit(1);
                    break;
                case 2:
                    System.out.println("----------------");
                    System.out.println("您已经退出系统!");
                    System.exit(1);
                    break;
                default:
                    break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单");
        } while (1);
    }

    /**
     * 3.商品管理界面
     */
    public static void commodityManagementPage() {
        System.out.println("***********************");
        System.out.println("\t 1.售货员管理\n");
        System.out.println("\t 2.列出当日卖出列表\n");
        System.out.println("***********************");

        System.out.println("\n请输入选项，或者按 0 返回上一级菜单.");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-2]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                case 0:
                    mainPage();
                    break;
                case 1:
                    salesManManagementPage();
                    break;
                case 2:
                    GsalesPage.dailySalesGoodsPage();
                    break;
                default:
                    break;
                }
            }
            System.err.println("!输入有误!");
            System.out.println("重新输入或按 0 返回上一级菜单");
        } while (1);
    }

    /**
     * 购物结算界面
     */
    public static void shoppingSettlementPage(int salesManSid) {
        System.out.println("\n\t********购物结算*******\n");
        do {
            System.out.println("按 S 开始购物结算.按 0 返回账户登录页面");
            String choNext = ScannerInfoString();
            if ("0".equals(choNext)) {
                checkstandLogPage();
            } else if ("s".equals(choNext) || "S".equals(choNext)) {
                System.out.println("\n--请输入商品关键字--");
                int gid = QueryPrint.querySettlement(); //当商品件数有且只有一件时返回商品gid号，商品已售空时返回-1，>1时返回-2.查无此商品时返回-3
                switch (gid) {
                case -3:
                    //无此商品，重新循环
                    break;
                case -1:
                    System.err.println("\t--抱歉，该商品已售空--");
                    break;
                default:
                    System.out.println("--按商品号选择商品--");
                    int shoppingGid = ScannerNum();
                    ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid, null);
                    if (goodsList == null || goodsList.size() == 0) {
                        System.err.println("\t!!查无此商品!!\n");
                    } else {
                        Goods goods = goodsList.get(0);
                        int gNum = goods.getGnum();
                        double gPrice = goods.getGprice();

                        System.out.println("--请输入购买数量--");
                        do {
                            int choicegoodsNum = ScannerNum();

                            if (choicegoodsNum > gNum) {
                                System.err.println("\t!!仓库储备不足!!");
                                System.out.println("--请重新输入购物数量--");
                            } else {
                                double allPrice = Arith.mul(choicegoodsNum, gPrice);
                                System.out.println("\t\t\t 购物车结算\n");
                                System.out.println("\t\t商品名称\t商品单价\t购买数量\t总价\n");
                                System.out.println("\t\t" + goods.getGname() + "\t" + goods.getGprice() + "$\t"
                                        + choicegoodsNum + "\t" + allPrice + "$\n");

                                do {
                                    System.out.println("确认购买:Y/N");
                                    String choShopping = ScannerInfoString();
                                    if ("y".equals(choShopping) || "Y".equals(choShopping)) {
                                        System.out.println("\n总价: "+ a llPrice+ " $");
                                        System.out.println("\n实际缴费金额");

                                        do {
                                            double amount = ScannerInfo();
                                            double balance = Arith.sub(amount, allPrice);
                                            if (balance < 0) {
                                                System.out.println("\t!!缴纳金额不足!!");
                                                System.out.println("\n请重新输入缴纳金额($)");
                                            } else {
                                                /*1.更改goods表数量
                                                  2.增加sales表数量
                                                  原商品数量gNum，结算员id salesManSid*/

                                                //对sales表操作
                                                Gsales gSales = new Gsales(goods.getGid(), salesManSid, choicegoodsNum);
                                                boolean insert = new GsalesDao().shoppingSettlement(gSalse);

                                                int goodsNewNum = gNum - choicegoodsNum;
                                                Goods newGoods = new Goods(goods.getGid(), goodsNewNum);
                                                boolean update = new GoodsDao().updateGoods(3, newGoods);

                                                if (update && insert) {
                                                    System.out.println("找零: "+ b alance);
                                                    System.out.println("\n谢谢光临，欢迎下次惠顾");
                                                } else {
                                                    System.err.println("!支付失败!");
                                                }
                                                shoppingSettlementPage(salesManSid);
                                            }
                                        } while (1);
                                    } else if ("n".equals(choShopping) || "N".equals(choShopping)) {
                                        shoppingSettlementPage(salesManSid);
                                    }
                                    System.err.println("\t!!请确认购买意向!!");
                                } while (1);
                            }
                        } while (1);
                    }
                    break;
                }
            } else {
                System.err.println("\t!!请输入合法字符!!\n");
            }
        } while (1);
    }

    /**
     * 售货员管理界面
     */
    public static void salesManManagementPage() {
        System.out.println("**********************");
        System.out.println("\t 1.添加售货员\n");
        System.out.println("\t 2.更改售货员\n");
        System.out.println("\t 3.删除售货员\n");
        System.out.println("\t 4.查询售货员\n");
        System.out.println("\t 5.显示所有售货员\n");
        System.out.println("**********************");

        System.out.println("\n请输入选项，或者按 0 返回上级菜单.");
        do {
            String choice = ScannerInfoString();
            String regex = "[0-5]";
            if (choice.matches(regex)) {
                int info = Integer.parseInt(choice);
                switch (info) {
                case 0:
                    commodityManagementPage();
                    break;
                case 1:
                    SalesManPage.addSalesManPage();
                    break;
                case 2:
                    SalesManPage.updateSalesManPage();
                    break;
                case 3:
                    SalesManPage.deleteSalesManPage();
                    break;
                case 4:
                    SalesManPage.querySalesManPage();
                case 5:
                    SalesManPage.displaySalesManPage();
                    break;
                default:
                    break;
                }
            }
            System.err.println("\t！输入有误!");
            System.out.println("重新输入或者按 0 返回上级菜单");
        } while (1);
    }
}