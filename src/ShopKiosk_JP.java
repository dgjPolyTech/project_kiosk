public class ShopKiosk_JP extends ShopKiosk {
    public ShopKiosk_JP() {
        this.shopName = "카츠 앤 스시";
        addMenu("우동", 1, 6000);
        addMenu("돈까스", 2, 9500);
        addMenu("모듬 스시", 3, 13000);
    }

    public ShopKiosk_JP(String mName, int mPrice) {
        this.shopName = "카츠 앤 스시";
        idx++;
        addMenu(mName, idx, mPrice);
    }
}
