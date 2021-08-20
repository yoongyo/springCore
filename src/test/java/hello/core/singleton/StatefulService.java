package hello.core.singleton;

public class StatefulService {
    // 공유필드를 사용해서는 안된다.
//    private int price;

    public int order(String name, int price) {
        System.out.println("name = "+ name+ " price = "+ price);
//        this.price = price; // 여기가 문제
        return price; // 이런식으로 반환해야한다.
    }

//    public int getPrice() {
//        return price;
//    }
}
