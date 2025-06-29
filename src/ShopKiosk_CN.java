public class ShopKiosk_CN extends ShopKiosk {
    public ShopKiosk_CN() {
        this.shopName = "백일각";
        addMenu("짜장면", 1, 5000);
        addMenu("짬뽕", 2, 6000);
        addMenu("탕수육", 3, 13000);
    }

    public ShopKiosk_CN(String mName, int mPrice) {
        this.shopName = "백일각";
        idx++;
        addMenu(mName, idx, mPrice);
    }
}
