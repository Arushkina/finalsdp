import java.util.ArrayList;
import java.util.List;

// Strategy Pattern
interface PricingStrategy {
    double calculatePrice(double basePrice);
}

class StandardPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice;
    }
}

class DiscountedPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(double basePrice) {
        return basePrice * 0.8; // 20% discount
    }
}

// Singleton Pattern
class Inventory {
    private static Inventory instance;

    private Inventory() {}

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    // Inventory management methods
    // ...
}

// Decorator Pattern
abstract class FlowerArrangement {
    protected String description;

    public abstract double cost();
}

class BasicFlowerArrangement extends FlowerArrangement {
    public BasicFlowerArrangement() {
        description = "Basic Flower Arrangement";
    }

    @Override
    public double cost() {
        return 15.0;
    }
}

class PremiumFlowerArrangement extends FlowerArrangement {
    public PremiumFlowerArrangement() {
        description = "Premium Flower Arrangement";
    }

    @Override
    public double cost() {
        return 25.0;
    }
}

abstract class FlowerArrangementDecorator extends FlowerArrangement {
    protected FlowerArrangement flowerArrangement;

    public FlowerArrangementDecorator(FlowerArrangement flowerArrangement) {
        this.flowerArrangement = flowerArrangement;
    }
}

class RibbonDecorator extends FlowerArrangementDecorator {
    public RibbonDecorator(FlowerArrangement flowerArrangement) {
        super(flowerArrangement);
        description = "Ribbon";
    }

    @Override
    public double cost() {
        return flowerArrangement.cost() + 5.0;
    }
}

// Adapter Pattern
class Flower {
    private String name;

    public Flower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class FlowerAdapter extends FlowerArrangement {
    private Flower flower;

    public FlowerAdapter(Flower flower) {
        this.flower = flower;
        description = flower.getName();
    }

    @Override
    public double cost() {
        return 10.0; // Assuming a fixed cost for each flower
    }
}

// Factory Pattern
class FlowerArrangementFactory {
    public FlowerArrangement createFlowerArrangement(String type) {
        switch (type) {
            case "Basic":
                return new BasicFlowerArrangement();
            case "Premium":
                return new PremiumFlowerArrangement();
            default:
                return null;
        }
    }
}

// Observer Pattern
interface Observer {
    void update(String message);
}

class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received a notification: " + message);
    }
}

class FlowerShop {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

public class FlowerShopProgram {
    public static void main(String[] args) {
        // Singleton Pattern
        Inventory inventory = Inventory.getInstance();

        // Strategy Pattern
        PricingStrategy standardPricing = new StandardPricingStrategy();
        PricingStrategy discountedPricing = new DiscountedPricingStrategy();

        // Decorator Pattern
        FlowerArrangement basicArrangement = new BasicFlowerArrangement();
        FlowerArrangement premiumArrangement = new PremiumFlowerArrangement();

        FlowerArrangement ribbonDecoratedBasic = new RibbonDecorator(basicArrangement);
        FlowerArrangement ribbonDecoratedPremium = new RibbonDecorator(premiumArrangement);

        // Adapter Pattern
        Flower rose = new Flower("Rose");
        FlowerAdapter roseAdapter = new FlowerAdapter(rose);

        // Factory Pattern
        FlowerArrangementFactory factory = new FlowerArrangementFactory();
        FlowerArrangement basic = factory.createFlowerArrangement("Basic");

        // Observer Pattern
        FlowerShop flowerShop = new FlowerShop();
        Customer customer1 = new Customer("Alice");
        Customer customer2 = new Customer("Bob");

        flowerShop.addObserver(customer1);
        flowerShop.addObserver(customer2);

        flowerShop.notifyObservers("New flower arrangements available!");

        // Example of how to use the patterns
        double basePrice = 30.0;
        double standardPrice = standardPricing.calculatePrice(basePrice);
        double discountedPrice = discountedPricing.calculatePrice(basePrice);

        System.out.println("Standard Pricing: $" + standardPrice);
        System.out.println("Discounted Pricing: $" + discountedPrice);

        System.out.println(ribbonDecoratedBasic.description + " Cost: $" + ribbonDecoratedBasic.cost());
        System.out.println(ribbonDecoratedPremium.description + " Cost: $" + ribbonDecoratedPremium.cost());

        System.out.println(roseAdapter.description + " Cost: $" + roseAdapter.cost());

        System.out.println(basic.description + " Cost: $" + basic.cost());
    }
}
