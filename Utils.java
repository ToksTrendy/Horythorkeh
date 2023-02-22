package writer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static writer.AppConstants.FILE;
import static writer.AppConstants.PRODUCT_LIST;

public class Utils {

    private Utils() {
    }

    public static final String SPLIT_PATTERN = ";\n";

    static int collectIntegerInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }

    static void getProductsFromUser() {
        do {
            System.out.println("Please enter your product details in this format name,quantity,price or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String productString = scanner.nextLine().trim();
            if (productString.equals("-1")) {
                return;
            }
            String[] split = productString.split(",");
            Product product = new Product(PRODUCT_LIST.size() + 1,
                    split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            PRODUCT_LIST.add(product);
        } while (true);
    }

    static void getProductsFromFile() {

        String stringFromFile = readFromFile();

        if (!stringFromFile.isBlank()) {

            String[] split = stringFromFile.split(SPLIT_PATTERN);

            for (String productString : split) {
                String[] productStringSplits = productString.split(",");

                Product product = new Product(Integer.parseInt(productStringSplits[0]), productStringSplits[1],
                        Integer.parseInt(productStringSplits[2]), Double.parseDouble(productStringSplits[3]),
                        Boolean.parseBoolean(productStringSplits[4]));
                PRODUCT_LIST.add(product);
            }
        }
    }

    static void saveToFile() {
        try (FileWriter fileWriter = new FileWriter(FILE)) {
            for (Product product : PRODUCT_LIST) {
                fileWriter.write(product.getMessageDetails() + SPLIT_PATTERN);
                fileWriter.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String readFile() {
        String fileName = "product.txt";
        try {
            FileReader fileReader = new FileReader("product.txt");
            int i;
            StringBuilder sb = new StringBuilder();
            while ((i = fileReader.read()) != -1) ;
            {
                sb.append((char) i);
                System.out.println((char) i);
            }
            String productList = sb.toString();
            System.out.println(productList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void viewProducts() {
        if (!PRODUCT_LIST.isEmpty()) {
            String format = String.format("%-4s %-30s %-12s %-8s %n",
                    "ID", "NAME", "QUANTITY", "PRICE");

            StringBuilder sb = new StringBuilder(format);

            for (Product product : PRODUCT_LIST) {
                if (!product.isDeleted()) {
                    String productLine = String.format("%-4d %-30s %-12d %-8s %n",
                            product.getId(), product.getName(), product.getQuantity(), product.getPrice());
                    sb.append(productLine);
                }
            }
            System.out.println(sb);
        } else {
            System.out.println("No product found!");
        }
    }

    private static String readFromFile() {
        if (!FILE.exists()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(FILE)) {
            int i;
            while ((i = fileReader.read()) != -1) {
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void delete() {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        product.delete();
        saveToFile();
        System.out.println("Product deleted");
    }

    private static Product getProduct(int productId) {
        if (productId < 1 || productId > PRODUCT_LIST.size()) {
            try {
                throw new InvalidProductIdException(String.format("Product with id %d is invalid", productId));
            } catch (InvalidProductIdException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
        }
        return PRODUCT_LIST.get(productId - 1);
    }

    public static void sell() {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        if (!product.isDeleted()) {
            System.out.println("How many quantity");
            Scanner scanner = new Scanner(System.in);
            int quantity = scanner.nextInt();
            //product.sell();
            saveToFile();
        } else {
            System.out.println("Product has been sold out");
        }
    }
    public static void update() throws FileNotFoundException {
        viewProducts();
        int productId = collectIntegerInput("Please select an Id from the list above");
        Product product = getProduct(productId);
        int ops = collectIntegerInput("chose what to update \n.1. Quantity \n2.Price");
        if(ops == 1){
            System.out.println("Enter the quantity");
            Scanner scanner = new Scanner(System.in);
           int newQuantity = scanner.nextInt();
           Scanner scanner1= new Scanner(new File("product.txt"));

           }
        }
    }
}