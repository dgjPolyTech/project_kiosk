public class Food {
    String name; // 음식 이름
    int number; // 음식 번호
    int price; // 음식 가격
    int amount; // 음식 수량

    void order(int price, int amount){
        System.out.println("주문할 음식의 번호 및 수량을dd 입력해주십시오.");
        
        this.price = price;
        this.amount = amount;
    }
}
