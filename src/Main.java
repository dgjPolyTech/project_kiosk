import java.util.*;

public class Main {
    List<Cart> cartList = new ArrayList<>();

    public void showUI() {
        System.out.println("[장바구니 확인: K/k] || [결재: G/g] || [프로그램 종료:X/x]");
    }

    // 장바구니 목록 확인
    public void showKartList(List<Cart> cartList) {
        Scanner scK = new Scanner(System.in);

        if (cartList.size() == 0) {
            System.out.println("❗장바구니에 아무것도 담겨있지 않습니다.");
        } else {
            System.out.println("[장바구니 목록]");

            while (true) {
                Map<String, List<Cart>> groupedShop = new HashMap<>();
                for (Cart cartItem : cartList) {
                    String shop = cartItem.menu.mShopName;
                    // 카트에 담긴 요소(cartItem)의 메뉴.가게명을 뽑아, 키 값으로 만듬.
                    // 없다면 키 값으로 등록하고, 이미 있다면 그 키에 추가.
                    groupedShop.computeIfAbsent(shop, k -> new ArrayList<>()).add(cartItem);
                }

                System.out.println("=================================");
                if (groupedShop.isEmpty()) {
                    System.out.println("장바구니가 비었습니다.");
                } else {
                    for (String shopName : groupedShop.keySet()) {
                        System.out.println("가게: " + shopName);
                        for (Cart k : groupedShop.get(shopName)) {
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
                        cartList.clear();
                        System.out.println("✅ 장바구니가 초기화되었습니다. \n");
                        return;
                    default:
                        System.out.println("❗ 입력값을 확인할 수 없습니다. 다시 입력해주십시오.");
                }
            }
        }
    }

    public int buy(int money) {
        Scanner bSc = new Scanner(System.in);
        if (cartList.isEmpty()) {
            System.out.println("장바구니가 비어있습니다. \n");
            return money;
        }

        System.out.println("\n[결제 전 장바구니 확인]");
        Map<String, List<Cart>> groupedShop = new HashMap<>();
        for (Cart cartItem : cartList) {
            String shop = cartItem.menu.mShopName;
            groupedShop.computeIfAbsent(shop, k -> new ArrayList<>()).add(cartItem);
        }

        int total = 0;
        System.out.println("=================================");
        for (String shopName : groupedShop.keySet()) {
            System.out.println("가게: " + shopName);
            for (Cart k : groupedShop.get(shopName)) {
                int subtotal = k.menu.price * k.amount;
                total += subtotal;
                System.out.println("* " + k.menu.number + ") " + k.menu.name + " / " + k.menu.price + "원 x " + k.amount + "개 = " + subtotal + "원");
            }
            System.out.println();
        }
        System.out.println("총 결제 금액: " + total + "원");
        System.out.println("=================================");

        System.out.print("결제를 진행하시겠습니까? (수락:Y/y, 거절:N/n) ==> ");
        String confirm = bSc.nextLine().trim();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("결제가 취소되었습니다. \n");
            return money;
        }

        if (money >= total) {
            money -= total;
            cartList.clear();
            System.out.println("✅ 결제가 완료되었습니다!");
            System.out.println("남은 소지금: " + money + "원");
        } else {
            System.out.println("❗ 소지금이 부족합니다. 결제를 진행할 수 없습니다.");
            System.out.println("필요 금액: " + total + "원 / 현재 소지금: " + money + "원");
        }

        return money;
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
                        m.showKartList(m.cartList);
                        continue;
                    case "G":
                    case "g":
                        money = m.buy(money);
                        continue;
                    case "X":
                    case "x":
                        System.out.println("키오스크 종료");
                        System.exit(0);
                    default:
                        System.out.println("❗ 입력값을 확인할 수 없습니다. 다시 입력해주십시오.");
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
                        throw new IllegalArgumentException("❗ 입력 형식이 올바르지 않습니다. (예: 1 2)");

                    int menuNum = Integer.parseInt(choiceArr[0]);
                    int amount = Integer.parseInt(choiceArr[1]);

                    boolean found = false;

                    for (Menu menuItem : nowShop.menuList) {
                        if (menuItem.number == menuNum) {
                            Cart intoCart = new Cart();
                            intoCart.mShopNumber = menuItem.mShopNumber;
                            intoCart.mNumber = menuItem.number;
                            intoCart.sName = menuItem.mShopName;
                            intoCart.menu = menuItem;
                            intoCart.amount = amount;

                            boolean isDuplicate = false;
                            for (Cart cartItem : m.cartList) {
                                if (cartItem.menu == intoCart.menu) {
                                    cartItem.amount += amount;
                                    isDuplicate = true;
                                    break;
                                }
                            }

                            if (!isDuplicate) {
                                m.cartList.add(intoCart);
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) { // 메뉴 판에 없는 메뉴 입력
                        System.out.println("❗ 해당 번호의 메뉴가 없습니다.");
                    } else {
                        System.out.println("✅ 장바구니에 물품이 담겼습니다. \n");
                    }

                } catch (NumberFormatException e) {// 잘못된 숫자 입력
                    System.out.println("❗숫자만 입력해주세요. 예: 2 1");
                } catch (IllegalArgumentException e) { //
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    System.out.println("❗ 알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
                }
            }
        }
    }
}
