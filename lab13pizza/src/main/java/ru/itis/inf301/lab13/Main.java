package ru.itis.inf301.lab13;

public class Main {
    public static void main(String[] args) {

        NextNumImpl numFactory = new NextNumImpl();

        Client client1 = new Client("Игорь", Gender.Male, Discount.Student);
        Client client2 = new Client("Федор", Gender.Male);
        Client client3 = new Client("Лилиана", Gender.Female);

        Cook cook1 = new Cook("Федор");
        Cook cook2 = new Cook("Арсений");

        Order order1 = Order.getBuilder().cook(cook1).client(client1).pizza(Pizza.Маргарита).date("16.12.2023").build();

        Order order2 = Order.getBuilder().cook(cook1).client(client2).pizza(Pizza.Пипперони).build();
    }
}
