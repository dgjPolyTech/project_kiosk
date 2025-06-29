public class ShopKiosk_CN extends ShopKiosk {
    int idx = 0;

    public ShopKiosk_CN() {
        this.shopName = "백일각";
        this.shopNumber = 3;
        this.category = "중식";
        addMenu("짜장면", 1, 5000);
        addMenu("짬뽕", 2, 6000);
        addMenu("탕수육", 3, 13000);
    }

    public ShopKiosk_CN(String mName, int mPrice) {
        this.shopName = "백일각";
        idx++;
        addMenu(mName, idx, mPrice);
    }

    public void showMenus(){
        System.out.println("<"+this.shopName+"> - 든든한 중화요리");
        System.out.println("====================");
        for(int i=0;i<menuList.size();i++){
            System.out.println(menuList.get(i).number+")"+menuList.get(i).name+"("+menuList.get(i).price+")");
        }
        System.out.println("====================");
    }
}
