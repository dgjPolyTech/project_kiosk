public class ShopKiosk_JP extends ShopKiosk{
    int idx = 0;

    public ShopKiosk_JP() {
        this.shopName = "카츠 앤 스시";
        this.shopNumber = 2;
        this.category = "일식";

        addMenu("우동", 1, 6500);
        addMenu("돈까스", 2, 9500);
        addMenu("스시 정식", 3, 12000);
    }

    public ShopKiosk_JP(String mName, int mPrice) {
        this.shopName = "카츠 앤 스시";
        idx++;
        addMenu(mName, idx, mPrice);
    }
}
