import java.util.Scanner;

public class Main {
    public static Object scanner;

    public static void main(String[] args) {
        NoteBook notebookList = new NoteBook();
        addNotebooksToList(notebookList);
        new Menu().choiceInMenu(notebookList);
    }

    private static void addNotebooksToList(NoteBook notebookList) {
        notebookList.add("ASUS", 8, 1000, "HDD", "Windows 10", "black");
        notebookList.add("HP", 16, 500, "SSD", "Windows 10", "red");
        notebookList.add("ASUS", 8, 500, "HDD", "Windows 10", "black");
        notebookList.add("HP", 32, 500, "SSD", "Linux", "gray");
        notebookList.add("ASUS", 8, 1000, "HDD", "Windows 10", "black");
        notebookList.add("ASUS", 16, 1000, "HDD", "Windows 10", "gray");
        notebookList.add("HP", 8, 500, "SSD", "Linux", "black");
        notebookList.add("ASUS", 16, 1000, "HDD", "Windows 10", "white");
    }

    private static class Menu {

        private void choiceInMenu(NoteBook notebookList) {
            int choice;
            String key;
            Object value;
            Scanner scanner = new Scanner(System.in);
            do {
                printMenu();
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println(notebookList.toString());
                        break;
                    case 2:
                        notebookList.printFilter();
                        break;
                    case 3:
                        key = getKey(scanner);
                        value = getValue(scanner, key);
                        notebookList.addFilter(key, value);
                        break;
                    case 4:
                        key = getKey(scanner);
                        notebookList.removeFilter(key);
                        break;
                    case 5:
                        notebookList.removeFilter();
                        break;
                    case 0:
                        scanner.close();
                        return;
                }
            } while (true);
        }

        private static void printMenu() {
            System.out.println("Выберите действие:");
            System.out.println("1 - вывести список");
            System.out.println("2 - вывести установленный фильтр");
            System.out.println("3 - добавить фильтр");
            System.out.println("4 - удалить поле из фильтра");
            System.out.println("5 - очистить фильтр");
            System.out.println("0 - выход");
        }

        private static void printFilterMenu() {
            System.out.println("Введите номер редактируемого параметра:");
            System.out.println("1 - Brand");
            System.out.println("2 - RAM (Gb)");
            System.out.println("3 - diskSize (Gb)");
            System.out.println("4 - diskType (HDD/SDD");
            System.out.println("5 - OS");
            System.out.println("6 - Color");
        }

        private static Object getValue(Scanner scanner, String key) {

            Object result = null;
            do {
                printFilterValueMenu(key);
                if (isIntKey(key)) {
                    if (scanner.hasNextInt()) {
                        result = scanner.nextInt();
                    } else {
                        scanner.next();
                    }
                } else
                    result = scanner.next();
            } while (result == null);
            return result;
        }

        private static boolean isIntKey(String key) {
            return key == "ram" || key == "diskSize";
        }

        private static void printFilterValueMenu(String key) {
            switch (key) {
                case "brand":
                    System.out.println("Введите производителя: (ASUS/HP)");
                    break;
                case "ram":
                    System.out.println("Введите объем оперативной памяти: (8/16/32 Gb)");
                    break;
                case "diskSize":
                    System.out.println("Введите размер жесткого диска: (500/1000 Gb)");
                    break;
                case "diskType":
                    System.out.println("Введите тип жесткого диска: (HDD/SSD)");
                    break;
                case "os":
                    System.out.println("Введите операционную систему: (Windows 10/Linux) ");
                    break;
                case "color":
                    System.out.println("Введите цвет: (black/red/gray/white)");
                    break;
            }
        }

        private static String getKey(Scanner scanner) {

            printFilterMenu();
            int i = 0;
            do {
                if (scanner.hasNextInt()) {
                    i = scanner.nextInt();
                    if (i > 0 && i < 7)
                        break;
                    i = 0;
                }
                printFilterMenu();
            } while (i == 0);

            switch (i) {
                case 1:
                    return "brand";
                case 2:
                    return "ram";
                case 3:
                    return "diskSize";
                case 4:
                    return "diskType";
                case 5:
                    return "os";
                case 6:
                    return "color";
            }
            return "";
        }
    }

    public static void printNotebook(NoteBook notebook) {
    }
}