import java.util.*;
public class FlowerStoreApplication {
    public static void main(String[] args) {
        FlowerStoreAdapter store = new FlowerStoreAdapter();
        store.displayAvailableFlowers();

        FlowerBouquet roseBouquet = FlowerFactory.createFlowerBouquet("rose");
        roseBouquet = new RibbonDecorator(roseBouquet);

        double totalAmount = roseBouquet.getCost();
        System.out.println("Selected bouquet: " + roseBouquet.getDescription());
        System.out.println("Total cost: " + totalAmount);

        PaymentStrategy paymentStrategy = new CreditCardPayment("1234", "Aruzhan");
        paymentStrategy.pay(totalAmount);

        OrderObserver emailNotification = new EmailNotification("aruzhan@gmail.com");
        emailNotification.update(totalAmount);
    }
}
