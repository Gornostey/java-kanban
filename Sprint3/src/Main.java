import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("Выполнен выход из программы.");
                    return;
                default:
                    System.out.println("Такой команды не существует.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        dc.addDish(dishType, dishName);


    }

    private static void generateDishCombo() {
        if (dc.typeToDishes.isEmpty()) {
            System.out.println("Список блюд пуст, список сначала нужно заполнить.");
        } else {

            System.out.println("Начинаем конструировать обед...");
            System.out.println("Введите количество наборов, которые нужно сгенерировать:");

            int numberOfCombos = scanner.nextInt();
            if (numberOfCombos < 1) {
                System.out.println("Количество наборов не может быть меньше 1. Чисто автоматически изменено на 1.");
                numberOfCombos = 1;
            }
            scanner.nextLine();
            ArrayList<String> comboStructure = new ArrayList<>();
            System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter)." +
                    " Для завершения ввода введите пустую строку");

            String nextItem = scanner.nextLine();
            while (!nextItem.isEmpty()) {
                boolean checkDish = dc.checkType(nextItem);
                if (checkDish) {
                    comboStructure.add(nextItem);
                } else {
                    System.out.println("Такого типа блюда не существует");
                }
                nextItem = scanner.nextLine();
            }
            ArrayList<ArrayList<String>> dishCombo = dc.generateCombos(numberOfCombos, comboStructure);
            System.out.println(dishCombo);

        }
    }
}
