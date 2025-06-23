import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("도마트 푸드코트에 오신 걸 환영 합니다!\n");

        while(true){
            System.out.println("원하는 음식점의 번호를 입력해주십시오.");
            System.out.println("=================================");
            System.out.println("(1)우리집 밥상(한식)");
            System.out.println("(2)카츠 앤 스시(일식)");
            System.out.println("(3)백일각(중식)");
            System.out.println("(4)뉴욕 레스토랑(양식)");
            System.out.println("=================================");
            System.out.println("결재하기:G 입력 / 키오스크 종료:Q 입력");

            String next = sc.nextLine();

            switch(next){
                case "1":
                    System.out.println("[우리집 밥상(한식)]");
                    System.out.println("=================================");
                    System.out.println("메뉴와 수량을 입력해주십시오.");
                    System.out.println("(1)비빔밥(8000원)");
                    System.out.println("(2)부대찌개(9000원)");
                    System.out.println("(3)불고기(10000원)");
                    System.out.println("=================================");
                    System.out.println("음식점 선택으로 돌아가기:G 입력 / 키오스크 종료:Q 입력");
                    String choice = sc.nextLine();
                    break;
                case "q":
                    System.out.println("키오스크 종료");
                    break;

            }
        }
    }
}