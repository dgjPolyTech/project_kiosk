public class ShopKiosk_KR extends ShopKiosk{
    int idx = 0;

    public ShopKiosk_KR() {
        this.shopName = "우리집 밥상";
        this.shopNumber = 1;
        this.category = "한식";

        addMenu("비빔밥", 1, 5500);
        addMenu("부대찌개", 2, 9000);
        addMenu("불고기", 3, 10000);
    }

    // ShopKiosk_KR 오버로딩
    // 매개변수 지정 후 사용 시, 사용자가 직접 메뉴를 추가할 수 있다.
    public ShopKiosk_KR(String mName, int mPrice) {
        this.shopName = "우리집 밥상";
        idx++;
        addMenu(mName, idx, mPrice);
    }
    
    // ShopKiosk의 showMenus 오버라이딩
    public void showMenus(){
        System.out.println("<"+this.shopName+"> - 어머니의 손맛...");
        System.out.println("====================");
        for(int i=0;i<menuList.size();i++){
            System.out.println(menuList.get(i).number+")"+menuList.get(i).name+"("+menuList.get(i).price+")");
        }
        System.out.println("====================");
    }
}
