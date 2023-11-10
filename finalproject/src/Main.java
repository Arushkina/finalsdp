
// strategy
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using credit card ending in " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal account " + email);
    }
}

// singelton
class FlowerStore {
    private static FlowerStore instance;

    private FlowerStore() {}

    public static FlowerStore getInstance() {
        if (instance == null) {
            instance = new FlowerStore();
        }
        return instance;
    }

    public void displayAvailableFlowers() {
        System.out.println("Available Flowers: Rose, Lily, Tulip");
    }
}

// decorator
interface FlowerBouquet {
    String getDescription();

    double getCost();
}

class RoseBouquet implements FlowerBouquet {
    @Override
    public String getDescription() {
        return "Rose bouquet";
    }

    @Override
    public double getCost() {
        return 15.0;
    }
}

class LilyBouquet implements FlowerBouquet {
    @Override
    public String getDescription() {
        return "Lily bouquet";
    }

    @Override
    public double getCost() {
        return 12.0;
    }
}

class TulipBouquet implements FlowerBouquet {
    @Override
    public String getDescription() {
        return "Tulip bouquet";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}

abstract class FlowerBouquetDecorator implements FlowerBouquet {
    protected FlowerBouquet flowerBouquet;

    public FlowerBouquetDecorator(FlowerBouquet flowerBouquet) {
        this.flowerBouquet = flowerBouquet;
    }
}

class RibbonDecorator extends FlowerBouquetDecorator {
    public RibbonDecorator(FlowerBouquet flowerBouquet) {
        super(flowerBouquet);
    }

    @Override
    public String getDescription() {
        return flowerBouquet.getDescription() + " with ribbon";
    }

    @Override
    public double getCost() {
        return flowerBouquet.getCost() + 2.0;
    }
}

// adapter
class FlowerStoreAdapter {
    private FlowerStore flowerStore;

    public FlowerStoreAdapter() {
        this.flowerStore = FlowerStore.getInstance();
    }

    public void displayAvailableFlowers() {
        flowerStore.displayAvailableFlowers();
    }
}

// factory pattern
class FlowerFactory {
    public static FlowerBouquet createFlowerBouquet(String type) {
        switch (type) {
            case "rose":
                return new RoseBouquet();
            case "lily":
                return new LilyBouquet();
            case "tulip":
                return new TulipBouquet();
            default:
                throw new IllegalArgumentException("Invalid flower type: " + type);
        }
    }
}

// observer patt
interface OrderObserver {
    void update(double totalAmount);
}

class EmailNotification implements OrderObserver {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(double totalAmount) {
        System.out.println("Sent email to " + email + " with order details. Total amount: " + totalAmount);
    }
}



