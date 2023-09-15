import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NoteBook {

    private List<Notebook> list = new LinkedList<>();
    private Map<String, Object> filter = new HashMap<>();

    public void add(String brand, int ram, int diskSize, String diskType, String os, String color) {
        list.add(new Notebook(brand, ram, diskSize, diskType, os, color));
    }

    public void addFilter(String key, Object value) {
        filter.put(key, value);
    }

    public void removeFilter(String key) {
        filter.remove(key);
    }

    public void removeFilter() {
        filter.clear();
    }

    public String toString() {
        return filterToString() + listToString();
    }

    private String listToString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (list.size() == 0)
            stringBuilder.append("Список ноутбуков пуст");
        else {
            stringBuilder.append("Список ноутбуков:\n");
            for (Notebook notebook : list) {
                if (meetsFilter(notebook)) {
                    stringBuilder.append(notebook);
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    private String filterToString() {
        if (filter.isEmpty()) {
            return "Фильтр не установлен\n";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Установленный фильтр:\n");
        for (String key : filter.keySet()) {
            stringBuilder.append(key);
            if (filter.get(key) instanceof Integer)
                stringBuilder.append(" >= ");
            else
                stringBuilder.append(" = ");
            stringBuilder.append(filter.get(key));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void printFilter() {
        System.out.println(filterToString());
    }

    private boolean meetsFilter(Notebook notebook) {
        for (String key : filter.keySet()) {
            Object value = filter.get(key);
            switch (key) {
                case "brand":
                    if (!notebook.brand.equals(value))
                        return false;
                    break;
                case "ram":
                    if (notebook.ram < (int) value)
                        return false;
                    break;
                case "diskSize":
                    if (notebook.diskSize < (int) value)
                        return false;
                    break;
                case "diskType":
                    if (!notebook.diskType.equals(value))
                        return false;
                    break;
                case "os":
                    if (!notebook.os.equals(value))
                        return false;
                    break;
                case "color":
                    if (!notebook.color.equals(value))
                        return false;
                    break;
            }
        }
        return true;
    }

    class Notebook {
        private String brand;
        private int ram;
        private int diskSize;
        private String diskType;
        private String os;
        private String color;

        public Notebook(String brand, int ram, int diskSize, String diskType, String os, String color) {
            this.brand = brand;
            this.ram = ram;
            this.diskSize = diskSize;
            this.diskType = diskType;
            this.os = os;
            this.color = color;
        }

        public String toString() {
            return String.format("%s/%d Gb/%s/%d Gb/%s/%s", brand, ram, diskType, diskSize, os, color);
        }
    }
}
