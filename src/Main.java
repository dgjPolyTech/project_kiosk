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
                // Map이라는 자바의 기본 인터페이스의 HashMap 구현체 사용.
                // 인터페이스는 어떤 기능을 가져야 할 지 일종의 설명서 같은 개념, 구현체는 그 설명서를 토대로 실제 동작을 구현한 것이라 보면 됨.
                // Map 인터페이스는 <키, 값>의 형태로 특정 값을 저장하고, 찾을 수 있도록 만든 인터페이스이며, hashMap은 map 인터페이스에서 가장 보편적으로 사용되는 구현체.
                Map<String, List<Cart>> groupedShop = new HashMap<>();
                for (Cart cartItem : cartList) {
                    String shop = cartItem.menu.mShopName;
                    // 카트에 담긴 요소(cartItem)의 메뉴.가게명을 뽑아, 키 값으로 만듬. 해당 키 값을 토대로 그룹화(즉, 가게명을 토대로 그룹화)
                    // coputeIfAbsent(shop, k -> new ArrayList<>())
                    // shop(키 값)을 확인하여, 있으면 뒤의 문장(add.(cartItem)) 그대로 실행, 없으면 새로운 리스트 생성.
                    // 여기서 k -> new ArrayList<>() 식으로 쓰는 것을 "람다 표현식"이라 함.
                    groupedShop.computeIfAbsent(shop, k -> new ArrayList<>()).add(cartItem);
                }

                System.out.println("=================================");
                if (groupedShop.isEmpty()) {
                    System.out.println("장바구니가 비었습니다.");
                } else {
                    for (String shopName : groupedShop.keySet()) {
                        System.out.println("가게: " + shopName); // 만들어진 groupedShop hashmap에서, 키값(=ShopName)을 받아오고 출력
                        for (Cart k : groupedShop.get(shopName)) { // 그 키 값을 토대로 groupedShope에서 검색
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
                        cartList.clear(); // cartList 전체 초기화한다.
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
                int subtotal = k.menu.price * k.amount; // 해당 메뉴의 값*수량
                total += subtotal; // 을 계산하여 총 결제 금액에 추가함.
                System.out.println("* " + k.menu.number + ") " + k.menu.name + " / " + k.menu.price + "원 x " + k.amount + "개 = " + subtotal + "원");
            }
            System.out.println();
        }
        System.out.println("총 결제 금액: " + total + "원");
        System.out.println("=================================");

        System.out.print("결제를 진행하시겠습니까? (수락:Y/y, 거절:N/n) ==> ");
        String confirm = bSc.nextLine();

        if(confirm.equalsIgnoreCase("Y")) {
            if (money >= total) {
                money -= total;
                cartList.clear();
                System.out.println("✅ 결제가 완료되었습니다!");
                System.out.println("남은 소지금: " + money + "원");
            } else {
                System.out.println("❗ 소지금이 부족합니다. 결제를 진행할 수 없습니다.");
                System.out.println("필요 금액: " + total + "원 / 현재 소지금: " + money + "원");
            }
        } else if(confirm.equalsIgnoreCase("N")) {
            System.out.println("결제가 취소되었습니다. \n");
            return money;
        } else {
            System.out.println("❗ 올바른 값을 입력해주십시오.\n");
            return money;
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
            ShopKiosk nowShop; // 추상 클래스는 객체로 사용할 수 없고, 다음과 같이 선언만 해두는 식으로 쓸 수 있다.

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


                // choice 입력값으로 B 입력 시 해당 while문을 빠져나가며 다시 위의 메뉴 선택 화면으로 돌아간다.
                // 대소문자 구분은 필요 없음.
                if (choice.equalsIgnoreCase("B")) break;

                try {
                    // 공백을 기준으로 입력값을 구분짓는다.
                    String[] choiceArr = choice.trim().split(" ");

                    // 입력값이 2개가 아니면, 아래의 오류를 실행한다.
                    if (choiceArr.length != 2)
                        throw new IllegalArgumentException("❗ 입력 형식이 올바르지 않습니다. (예: 1 2)");

                    int menuNum = Integer.parseInt(choiceArr[0]); // 메뉴 번호
                    int amount = Integer.parseInt(choiceArr[1]); // 메뉴 수량

                    boolean found = false;

                    for (Menu menuItem : nowShop.menuList) { // 현재 해당하는 가게의 menuList를 흝는다.
                        if (menuItem.number == menuNum) { // 동일한 메뉴 발견 시 cart 객체를 만들어 cartList에 추가한다.
                            Cart intoCart = new Cart();
                            intoCart.mShopNumber = menuItem.mShopNumber;
                            intoCart.mNumber = menuItem.number;
                            intoCart.sName = menuItem.mShopName;
                            intoCart.menu = menuItem;
                            intoCart.amount = amount;

                            boolean isDuplicate = false; // 이미 카트에 중복값이 있는 지 확인하는 변수

                            for (Cart cartItem : m.cartList) { // 현재 카트(cartList)를 확인한다.
                                if (cartItem.menu == intoCart.menu) { // 카트에 이미 같은 메뉴가 담겨있다면, 따로 추가하지 않고 수량만 증가시킨다.
                                    cartItem.amount += amount;
                                    isDuplicate = true; // 중복값 체크를 true로 변환.
                                    break; // 이후 반복은 필요 없으므로 break;
                                }
                            }

                            if (!isDuplicate) { // 중복값이 없다면, 신규 요소로 카트 안에 추가한다.
                                m.cartList.add(intoCart);
                            }

                            found = true; // 위 과정을 마쳤으면, found를 true로 변환한 뒤 해당 for문을 종료한다.
                            break;
                        }
                    }

                    if (!found) { // 메뉴를 찾지 못했다는 의미이므로, 아래의 메시지를 출력한다.
                        System.out.println("❗ 해당 번호의 메뉴가 없습니다.");
                    } else {
                        System.out.println("✅ 장바구니에 물품이 담겼습니다. \n");
                    }

                } catch (NumberFormatException e) {// 잘못된 숫자 입력 시
                    System.out.println("❗숫자만 입력해주세요. 예: 2 1");
                } catch (IllegalArgumentException e) { // 입력 형식에 맞지 않을 시
                    System.out.println(e.getMessage());
                } catch (Exception e) {// 그 외 오류 발생 시
                    System.out.println("❗ 알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
                }
            }
        }
    }
}
