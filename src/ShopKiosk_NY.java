public class ShopKiosk_NY extends ShopKiosk{
    public ShopKiosk_NY() {
        this.shopName = "뉴욕 레스토랑";
        addMenu("스파게티", 1, 10000);
        addMenu("햄버그 스테이크", 2, 12000);
        addMenu("치킨&맥주 세트", 3, 16000);
    }

    public ShopKiosk_NY(String mName, int mPrice) {
        this.shopName = "뉴욕 레스토랑";
        idx++;
        addMenu(mName, idx, mPrice);
    }
}
