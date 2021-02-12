package kg.attractor.java;

 import kg.attractor.java.homework.RestaurantOrders;
 import kg.attractor.java.homework.domain.Item;
 import kg.attractor.java.homework.domain.Order;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Objects;

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

        var orders = RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();


//        var maxPrices = orders.stream().sorted(comparing(Order::getTotal).reversed()).limit(6).collect(toList());
//        maxPrices.forEach(System.out::println);

//        var minTotalPrice = orders.stream().sorted(comparing(Order::getTotal)).limit(6).collect(toList());
//        minTotalPrice.forEach(System.out::println);
//        var deliveryToHomeMax = orders.stream().filter(Order::isHomeDelivery).max(comparing(Order::getTotal)).get();
//        System.out.println(deliveryToHomeMax);
//
//        var deliveryToHomeMin = orders.stream().filter(Order::isHomeDelivery).min(comparing(Order::getTotal)).get();
//        System.out.println(deliveryToHomeMin);

//          var allOrdersSum = orders.stream().flatMap(e -> e.getItems().stream()).mapToDouble(e -> e.getPrice()).sum();
//            System.out.print("Общая сумма всех заказов : "+allOrdersSum);

        var emailAddressesOfCustomers = orders.stream().map(e -> e.getCustomer().getEmail()).distinct().collect(toList());
//        emailAddressesOfCustomers.forEach(System.out::println);

        var getCustomer= orders.stream().distinct().collect(groupingBy(e-> e.getCustomer().getFullName() , mapping(Order::getItems,toList())));
        getCustomer.forEach((k,v)->System.out.printf("%s - %2s",k,v+"\n"+""+"\n"));
//        System.out.println(getCustomer);
        // протестировать ваши методы вы можете как раз в  этом файле (или в любом другом, в котором вам будет удобно)
    }
}
