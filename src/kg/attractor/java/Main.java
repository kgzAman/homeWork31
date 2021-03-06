package kg.attractor.java;

 import kg.attractor.java.homework.RestaurantOrders;
 import kg.attractor.java.homework.domain.Item;
 import kg.attractor.java.homework.domain.Order;

 import java.util.List;


 import static java.util.stream.Collectors.*;
 import static java.util.Comparator.*;

// используя статические imports
// мы импортируем *всё* из Collectors и Comparator.
// теперь нам доступны такие операции как
// toList(), toSet() и прочие, без указания уточняющего слова Collectors. или Comparator.
// вот так было до импорта Collectors.toList(), теперь стало просто toList()


public class Main {

    public static void main(String[] args) {

        // это для домашки
        // выберите любое количество заказов, какое вам нравится.

        List<Order> orders = RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();


        var maxPrices = orders.stream().sorted(comparing(Order::getTotal).reversed()).limit(6).collect(toList());
//        maxPrices.forEach(System.out::println);

        var minTotalPrice = orders.stream().sorted(comparing(Order::getTotal)).limit(6).collect(toList());
//        minTotalPrice.forEach(System.out::println);
        var deliveryToHomeMax = orders.stream().filter(Order::isHomeDelivery).max(comparing(Order::getTotal)).get();
//        System.out.println(deliveryToHomeMax);
//
        var deliveryToHomeMin = orders.stream().filter(Order::isHomeDelivery).min(comparing(Order::getTotal)).get();
//        System.out.println(deliveryToHomeMin);
        var maxOrderTotal= orders.stream().sorted(comparing(Order::getTotal).reversed()).skip(1).collect(toList());
//        maxOrderTotal.forEach(System.out::print);

        var minOrderTotal = orders.stream().sorted(comparing(Order::getTotal)).skip(1).collect(toList());
        minOrderTotal.forEach(System.out::print);

        var allOrdersSum = orders.stream().flatMap(e -> e.getItems().stream()).mapToDouble(e -> e.getPrice()).sum();
//            System.out.print("Общая сумма всех заказов : "+allOrdersSum);

        var emailAddressesOfCustomers = orders.stream().map(e -> e.getCustomer().getEmail()).distinct().collect(toList());
//        emailAddressesOfCustomers.forEach(System.out::println);

        var getCustomer= orders.stream().distinct().collect(groupingBy(e-> e.getCustomer().getFullName() , mapping(Order::getItems,toList())));
//        getCustomer.forEach((k,v)->System.out.printf("%s - %2s",k,v+"\n"+""+"\n"));
//        System.out.println(getCustomer);

        var getCustomerTotalOrdersPrices = orders.stream().collect(groupingBy(e->e.getCustomer().getFullName(),summingDouble(Order::getTotal)));
//        getCustomerTotalOrdersPrices.forEach((k,v)->System.out.printf("%s - %2s",k,v+"\n"+""+"\n"));

        var getCustomerWithMaxTotalPrice = orders.stream().max(comparing(Order::getTotal)).get();
//        System.out.println(getCustomerWithMaxTotalPrice);

        var getCustomerWithMinTotalPrice = orders.stream().min(comparing(Order::getTotal)).get();
//        System.out.println(getCustomerWithMinTotalPrice);

        var getAllOrderedItems = orders.stream().map(e->e.getItems().stream().collect(groupingBy(Item::getName,counting())));
//        getAllOrderedItems.forEach(System.out::println);
        // протестировать ваши методы вы можете как раз в  этом файле (или в любом другом, в котором вам будет удобно)
    }
}
