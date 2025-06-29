public class ShopKiosk_NY extends  ShopKiosk{
    int idx = 0;

    public ShopKiosk_NY() {
        this.shopName = "뉴욕 레스토랑";
        this.shopNumber = 4;
        this.category = "양식";

        addMenu("스파게티", 1, 10000);
        addMenu("햄버그 스테이크", 2, 12000);
        addMenu("치킨 & 맥주", 3, 16000);
    }

    public ShopKiosk_NY(String mName, int mPrice) {
        this.shopName = "뉴욕 레스토랑";
        idx++;
        addMenu(mName, idx, mPrice);
    }

    public void showMenus(){
        System.out.println("<"+this.shopName+"> - Enjoy Delicious Food!");
        System.out.println("====================");
        for(int i=0;i<menuList.size();i++){
            System.out.println(menuList.get(i).number+")"+menuList.get(i).name+"("+menuList.get(i).price+")");
        }
        System.out.println("====================");
    }
}
