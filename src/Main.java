import java.sql.SQLOutput;
import java.util.*;

public class Main {
    List<Kart> kartList = new ArrayList<>();

    public void showUI() {
        System.out.println("[카트 확인: K/k] || [결재: G/g] || [프로그램 종료:X/x]");
    }

    public void showKartList(List<Kart>  kartList){
        if(kartList.size() == 0){
            System.out.println("카트에 아무것도 담겨있지 않습니다.");
        } else {
            System.out.println("[장바구니 목록]");
            
            // 가게 별로 그룹을 나눠 출력하기 위한 구문
            Map<String, List<Kart>> groupedShop = new HashMap<>();

            for (Kart kartItem : kartList) {
                String shop = kartItem.menu.mShopName;

                groupedShop.computeIfAbsent(shop, k -> new ArrayList<>()).add(kartItem);
            }

            for (String shopName : groupedShop.keySet()) {
                System.out.println("가게: " + shopName);

                for (Kart k : groupedShop.get(shopName)) {
                    System.out.println(" - " + k.menu.name + " / " + k.menu.price + "원 x " + k.amount + "개");
                }

                System.out.println();
            }
//            for(int i=0;i<kartList.size();i++){
//                System.out.println(kartList.get(i).menu.name);
//            }
        }
    }

    public void buy(){

    }

    public static void main(String[] args) {
        Main m = new Main();
        Scanner sc = new Scanner(System.in);
        int money = 0;

        System.out.println("소지금을 입력해주십시오.(최소 10000원 이상)");

        while(true){
            money = sc.nextInt();
            if(money >= 10000){
                break;
            } else {
                System.out.println("최소 10000원 이상의 금액을 입력해주십시오.");
            }
        }

        sc.nextLine();

        ShopKiosk_KR sKR = new ShopKiosk_KR();
        ShopKiosk_JP sJP = new ShopKiosk_JP();
        ShopKiosk_CN sCN = new ShopKiosk_CN();
        ShopKiosk_NY sNY = new ShopKiosk_NY();

        System.out.println("도마트 푸드코트에 오신 걸 환영 합니다!\n");

        while(true){
            ShopKiosk nowShop = new ShopKiosk();
            
            // 음식점 선택 화면
            while(true){
                System.out.println("=================================");
                System.out.println(sKR.shopNumber+")"+sKR.shopName+"("+sKR.category+")");
                System.out.println(sJP.shopNumber+")"+sJP.shopName+"("+sJP.category+")");
                System.out.println(sCN.shopNumber+")"+sCN.shopName+"("+sCN.category+")");
                System.out.println(sNY.shopNumber+")"+sNY.shopName+"("+sNY.category+")");
                System.out.println("=================================");
                m.showUI();
                System.out.print("원하는 음식점의 번호 입력(현재 소지금:"+money+"원) ==> ");

                String next = sc.nextLine();
                // 위에서 선택한 번호에 따라, 음식점 화면을 출력한다.

                switch(next){
                    case "1":
                        nowShop = sKR;
                        break;
                    case "2":
                        nowShop = sJP;
                        break;
                    case "3":
                        nowShop = sCN;
                        break;
                    case "4":
                        nowShop = sNY;
                        break;
                    case "K", "k":
                        m.showKartList(m.kartList);
                        continue;
                    case "X", "x":
                        System.out.println("키오스크 종료");
                        System.exit(0);
                    default:
                        System.out.println("입력값을 확인할 수 없습니다. 다시 입력해주십시오.");
                        continue;
                }

                break;
            }

            // 음식점 메뉴 선택 화면
            while(true){
                nowShop.showMenus();
                //m.showUI();
                System.out.println("[음식점 선택으로 돌아가기: B/b]");
                System.out.print("메뉴와 수량을 띄어쓰기로 입력.(현재 소지금: "+money+"원) ==> ");
                String choice = sc.nextLine();
                String[] choiceArr = choice.split(" "); // 배열 형식으로 메뉴/수량을 입력받는다.
                try{

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                String choiceMenu = choiceArr[0]; // 선택한 메뉴 번호
                String choiceAmount = choiceArr[1]; // 선택한 메뉴 수량

                for(Menu menuItem : nowShop.menuList){
                    if(menuItem.number == Integer.parseInt(choiceMenu)){
                        Kart intoKart = new Kart();
                        intoKart.sName = menuItem.mShopName;
                        intoKart.menu = menuItem;
                        intoKart.amount = Integer.parseInt(choiceAmount);

                        // 기존 카트에 이미 같은 메뉴가 담겨 있으면, 수량만 추가하기.

                        boolean isDuplicate = false;

                        for (Kart kartItem : m.kartList) {
                            if (intoKart.menu == kartItem.menu) {
                                kartItem.amount += Integer.parseInt(choiceAmount);
                                isDuplicate = true;
                                break;  // 더 볼 필요 없음
                            }
                        }

                        if (!isDuplicate) {
                            m.kartList.add(intoKart);  // 반복문 밖에서 add 실행
                        }
                    }
                }

                m.showKartList(m.kartList);

//                try {
//                    for(int i=0;i<nowShop.menuList.size();i++){
//                        System.out.println(nowShop.menuList.get(i).toString());
//                    }
//                } catch {
//
//                }
            }
        }
    }
}