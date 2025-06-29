import java.sql.SQLOutput;
import java.util.*;

public class Main {
    List<Kart> kartList = new ArrayList<>();

    public void showUI() {
        System.out.println("[카트 확인: K/k] || [결재: G/g] || [프로그램 종료:X/x]");
    }

    // 카트 목록 확인
    public void showKartList(List<Kart> kartList) {
        Scanner scK = new Scanner(System.in);

        if (kartList.size() == 0) {
            System.out.println("카트에 아무것도 담겨있지 않습니다.");
        } else {
            System.out.println("[장바구니 목록]");

            while (true) {
                // ✅ 최신 데이터를 기반으로 항상 다시 그룹핑
                Map<String, List<Kart>> groupedShop = new HashMap<>();
                for (Kart kartItem : kartList) {
                    String shop = kartItem.menu.mShopName;
                    groupedShop.computeIfAbsent(shop, k -> new ArrayList<>()).add(kartItem);
                }

                System.out.println("=================================");
                if (groupedShop.isEmpty()) {
                    System.out.println("카트가 비었습니다.");
                } else {
                    for (String shopName : groupedShop.keySet()) {
                        System.out.println("가게: " + shopName);
                        for (Kart k : groupedShop.get(shopName)) {
                            System.out.println("* " + k.menu.number + ")" + k.menu.name + " / " + k.menu.price + "원 x " + k.amount + "개");
                        }
                        System.out.println();
                    }
                }
                System.out.println("=================================");
                System.out.println("|| [확인 완료: B/b] || [완전 초기화: C/c] ||");

                String choice = scK.nextLine();

                switch (choice) {
                    case "B":
                    case "b":
                        return;
                    case "C":
                    case "c":
                        kartList.clear();
                        System.out.println("장바구니가 초기화되었습니다.");
                        break;
                    default:
                        System.out.println("입력값을 확인할 수 없습니다. 다시 입력해주십시오.");
                }
            }
        }
    }


    public void buy() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main m = new Main();
        int money = 0;

        System.out.println("소지금을 입력해주십시오.(최소 10000원 이상)");

        while (true) {
            money = sc.nextInt();
            if (money >= 10000) {
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

        while (true) {
            ShopKiosk nowShop = new ShopKiosk();

            // 음식점 선택 화면
            while (true) {
                System.out.println("=================================");
                System.out.println(sKR.shopNumber + ")" + sKR.shopName + "(" + sKR.category + ")");
                System.out.println(sJP.shopNumber + ")" + sJP.shopName + "(" + sJP.category + ")");
                System.out.println(sCN.shopNumber + ")" + sCN.shopName + "(" + sCN.category + ")");
                System.out.println(sNY.shopNumber + ")" + sNY.shopName + "(" + sNY.category + ")");
                System.out.println("=================================");
                m.showUI();
                System.out.print("원하는 음식점의 번호 입력(현재 소지금:" + money + "원) ==> ");

                String next = sc.nextLine();

                switch (next) {
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
                    case "K":
                    case "k":
                        m.showKartList(m.kartList);
                        continue;
                    case "X":
                    case "x":
                        System.out.println("키오스크 종료");
                        System.exit(0);
                    default:
                        System.out.println("입력값을 확인할 수 없습니다. 다시 입력해주십시오.");
                        continue;
                }
                break;
            }

            // 음식점 메뉴 선택 화면
            while (true) {
                nowShop.showMenus();
                System.out.println("[음식점 선택으로 돌아가기: B/b]");
                System.out.print("메뉴와 수량을 띄어쓰기로 입력.(현재 소지금: " + money + "원) ==> ");
                String choice = sc.nextLine();


                // choice 입력값으로 b 이외의 값 입력 시, 강제로 아래의 오류 실행.
                if (choice.equalsIgnoreCase("B")) break;

                try {
                    String[] choiceArr = choice.trim().split(" ");
                    if (choiceArr.length != 2)
                        throw new IllegalArgumentException("(E) 입력 형식이 올바르지 않습니다. 예: 1 2");

                    int menuNum = Integer.parseInt(choiceArr[0]);
                    int amount = Integer.parseInt(choiceArr[1]);

                    boolean found = false;

                    for (Menu menuItem : nowShop.menuList) {
                        if (menuItem.number == menuNum) {
                            Kart intoKart = new Kart();
                            intoKart.mShopNumber = menuItem.mShopNumber;
                            intoKart.mNumber = menuItem.number;
                            intoKart.sName = menuItem.mShopName;
                            intoKart.menu = menuItem;
                            intoKart.amount = amount;

                            boolean isDuplicate = false;
                            for (Kart kartItem : m.kartList) {
                                if (kartItem.menu == intoKart.menu) {
                                    kartItem.amount += amount;
                                    isDuplicate = true;
                                    break;
                                }
                            }

                            if (!isDuplicate) {
                                m.kartList.add(intoKart);
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) { // 메뉴 판에 없는 메뉴 입력
                        System.out.println("(E) 해당 번호의 메뉴가 없습니다.");
                    } else {
                        m.showKartList(m.kartList);
                    }

                } catch (NumberFormatException e) {// 잘못된 숫자 입력
                    System.out.println("(E) 숫자만 입력해주세요. 예: 2 1");
                } catch (IllegalArgumentException e) { //
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("(E) 알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
                }
            }
        }
    }
}
