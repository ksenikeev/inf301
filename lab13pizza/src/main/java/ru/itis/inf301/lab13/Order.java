package ru.itis.inf301.lab13;

public class Order {

    private Client client;
    private Cook cook;
    private Pizza pizza;
    private OrderStatus orderStatus;
    private Integer orderNumber;
    private String date;

    /* Вместо конструктора с большим количеством аргументов удобнее использовать шаблон проектирования БИЛДЕР */
/*
    public Order(Client client, Cook cook, Pizza pizza, OrderStatus orderStatus, Integer orderNumber, String date) {
        this.client = client;
        this.cook = cook;
        this.pizza = pizza;
        this.orderStatus = orderStatus;
        this.orderNumber = orderNumber;
        this.date = date;
    }
*/

    /**
     * Приватный конструктор для создания объекта Order с использованием данных технического класса OrderBuilder
     * создание объекта происходит при вызове метода build()
     * @param builder
     */
    private Order(OrderBuilder builder) {
        this.client = builder.client;
        this.cook = builder.cook;
        this.pizza = builder.pizza;
        this.orderStatus = builder.orderStatus;
        this.orderNumber = builder.orderNumber;
        this.date = builder.date;
    }

    /**
     * Статический метод getBuilder() класса Order позволяет создать экземпляр вспомогательного класса OrderBuilder
     * для последовательного конструирования класса Order
     *
     * Модификатор static позволяет вызвать метод до создания объекта Order
     *
     * @return
     */
    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    /**
     * Вспомогательный класс OrderBuilder предназначен для последовательного конструирования класса Order
     * структура класса совпадает со структурой класса Order
     *
     * Модификатор static дает возможность работать с классом в статическом методе getBuilder()
     */
    public static class OrderBuilder {
        private Client client;
        private Cook cook;
        private Pizza pizza;
        private OrderStatus orderStatus;
        private Integer orderNumber;
        private String date;

        /**
         * Метод возвращает экземпляр OrderBuilder для выстраивания последовательных вызовов методов инициализации членов
         * @param client
         * @return
         */
        public OrderBuilder client(Client client) {
            this.client = client;
            return this;
        }
        public OrderBuilder cook(Cook cook) {
            this.cook = cook;
            return this;
        }
        public OrderBuilder pizza(Pizza pizza) {
            this.pizza = pizza;
            return this;
        }
        public OrderBuilder orderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }
        public OrderBuilder orderNumber(Integer orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }
        public OrderBuilder date(String date) {
            this.date = date;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
