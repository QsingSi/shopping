package lyons.page;

import java.util.ArrayList;

import com.sun.corba.se.impl.encoding.MarshalInputStream;

import lyons.dao.GsalesDao;
import lyons.entity.Gsales;
import lyons.tools.ScannerChoice;

/**
 * 当日卖出商品列表界面
 * @author lyons(weizq)
 */

public final class GsalesPage {
    public static void dailySalesGoodsPage() {
        System.out.println("\t正在执行列出当日售出商品列表操作\n");
        ArrayList<Gsales> GsalesList = new GsalesDao().dailyGsales();

        if (GsalesList.size() <= 0) {
            System.out.println("\t!!今日无商品售出!!");
            MainPage.commodityManagementPage();
        } else {
            System.out.println("\t\t\t\t今日售出商品列表\n");
            System.out.println("\t商品名称\t\t商品价格\t\t商品数量\t\t销量\t\t备注\n");

            for (int i, length = GsalesList.size(); i < length; i++) {
                //获取售出商品:gname,gprice,gnum,allSum
                Gsales gSales = GsalesList.get(i);
                System.out.println("\t" + gSales.getGName() + "\t\t" + gSales.getGPrice() + "\t\t" + gSales.getGNum()
                        + "\t\t" + gSales.getAllSnum());
                int gNum = gSales.getGNum();
                if (gNum == 0) {
                    System.out.println("\t\t该商品已售空");
                } else if (gNum < 10) {
                    System.out.println("\t\t该商品不足10件");
                } else {
                    System.out.println("\t\t-");
                }
                System.out.println("\t");
            }
            do {
                System.out.println("\n\n输入 0 返回上一级菜单");
                String choice = ScannerChoice.ScannerInfoString();
                if ("0".equals(choice)) {
                    MainPage.salesManagementPage();
                }
                MainPage.commodityManagementPage();
            } while (1);
        }
    }
}