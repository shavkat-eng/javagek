import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Define the Notebook class
class Notebook {
    private String brand;
    private int ram;        // RAM in GB
    private int storage;    // Storage capacity in GB
    private String os;      // Operating system
    private String color;

    // Constructor
    public Notebook(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Getters
    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    // Method to display notebook details
    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

// Define the NotebookStore class to manage the collection of notebooks
class NotebookStore {
    private List<Notebook> notebooks = new ArrayList<>();

    // Method to add a notebook to the store
    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    // Method to filter notebooks based on user-defined criteria
    public List<Notebook> filterNotebooks(Map<String, Object> filters) {
        List<Notebook> filteredList = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean match = true;
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case "RAM":
                        if (notebook.getRam() < (int) value) {
                            match = false;
                        }
                        break;
                    case "Storage":
                        if (notebook.getStorage() < (int) value) {
                            match = false;
                        }
                        break;
                    case "OS":
                        if (!notebook.getOs().equalsIgnoreCase((String) value)) {
                            match = false;
                        }
                        break;
                    case "Color":
                        if (!notebook.getColor().equalsIgnoreCase((String) value)) {
                            match = false;
                        }
                        break;
                    default:
                        // Handle unknown criteria if necessary
                        break;
                }
            }

            if (match) {
                filteredList.add(notebook);
            }
        }

        return filteredList;
    }
}

// Main class for testing
public class NotebookMain {
    public static void main(String[] args) {
        // Create some notebooks
        Notebook notebook1 = new Notebook("HP", 8, 512, "Windows 10", "Silver");
        Notebook notebook2 = new Notebook("Dell", 16, 1024, "Ubuntu", "Black");
        Notebook notebook3 = new Notebook("Apple", 16, 512, "MacOS", "Silver");
        Notebook notebook4 = new Notebook("Lenovo", 8, 256, "Windows 10", "Black");

        // Create a notebook store
        NotebookStore store = new NotebookStore();
        store.addNotebook(notebook1);
        store.addNotebook(notebook2);
        store.addNotebook(notebook3);
        store.addNotebook(notebook4);

        // Ask user for filtering criteria
        Scanner scanner = new Scanner(System.in);
        Map<String, Object> filters = new HashMap<>();

        System.out.println("Enter criteria to filter notebooks:");
        System.out.println("1 - RAM");
        System.out.println("2 - Storage");
        System.out.println("3 - OS");
        System.out.println("4 - Color");

        int criterion;
        do {
            System.out.print("Enter criterion number (0 to finish): ");
            criterion = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (criterion != 0) {
                String key = "";
                switch (criterion) {
                    case 1:
                        key = "RAM";
                        break;
                    case 2:
                        key = "Storage";
                        break;
                    case 3:
                        key = "OS";
                        break;
                    case 4:
                        key = "Color";
                        break;
                    default:
                        System.out.println("Invalid criterion number.");
                        continue;
                }

                System.out.print("Enter minimum value for " + key + ": ");
                Object value = scanner.nextLine();

                filters.put(key, value);
            }
        } while (criterion != 0);

        // Filter notebooks based on criteria
        List<Notebook> filteredNotebooks = store.filterNotebooks(filters);

        // Display filtered notebooks
        System.out.println("Filtered notebooks:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }

        scanner.close();
    }
}
