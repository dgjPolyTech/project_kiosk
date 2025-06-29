import java.util.ArrayList;

public class ShopKiosk {
    String shopName;

    Menu menu = new Menu();

    public void setMenu(String mName, int mNumber, int mPrice) {
        this.menu.name = mName;
        this.menu.number = mNumber;
        this.menu.price = mPrice;
    }

    // 가게 메뉴들이 들어갈 menuList
    ArrayList<Menu> menuList = new ArrayList<>();

    public Menu getMenu() {
        return menu;
    }

    // 하위 자식 클래스에서 메뉴를 추가하게 하기 위한 메소드.
    // main에서 직접 사용하는 것도 가능은 하다.
    public void addMenu(String name, int number, int price) {
        Menu newMenu = new Menu();

        newMenu.name = name;
        newMenu.number = number;
        newMenu.price = price;

        menuList.add(newMenu);
    }

    public void showMenus(){
        System.out.println("<"+this.shopName+">");
        System.out.println("====================");
        for(int i=0;i<menuList.size();i++){
            System.out.println(menuList.get(i).number+")"+menuList.get(i).name+"("+menuList.get(i).price+")");
        }
        System.out.println("====================");
    }
}
