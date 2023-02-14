package writer;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    private boolean isDeleted;


    static int collectIntegerInput(String message){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        return scanner.nextInt();
    }
    public static final String SPLIT_PATTERN = ";\n";

    private Utils() {}

    static List<Product> getProductsFromUser(List<Product> productList) {
        do {
            System.out.println("Please enter your product details in this format name,quantity,price or -1 to stop");
            Scanner scanner = new Scanner(System.in);
            String productString = scanner.nextLine().trim();
            if (productString.equals("-1")){
                return productList;
            }
            String[] split = productString.split(",");
            Product product = new Product(productList.size() + 1,
                    split[0], Integer.parseInt(split[1]), Double.parseDouble(split[2]));
            productList.add(product);
        }while(true);
    }
    static List<Product> getProductsFromFile(File file) {
        ArrayList<Product> productList = new ArrayList<>();

        String stringFromFile = readFromFile(file);

        if (stringFromFile.isBlank()){
            return productList;
        }
        String[] split = stringFromFile.split(SPLIT_PATTERN);

        for (String productString : split) {
            String[] productStringSplits = productString.split(",");

            Product product = new Product(Integer.parseInt(productStringSplits[0]), productStringSplits[1],
                    Integer.parseInt(productStringSplits[2]), Double.parseDouble(productStringSplits[3]),
                    Boolean.parseBoolean(productStringSplits[4]));
            productList.add(product);
        }
        return productList;
    }

    public static String readFromFile(File file) {
        if (!file.exists()){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            int i;
            while((i = fileReader.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    static void saveToFile(File file, List<Product> productList) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Product product : productList) {
                fileWriter.write(product.getMessageDetails() + SPLIT_PATTERN);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
   static String readFile(){
        String fileName = "product.txt";
        try{
            FileReader fileReader = new FileReader("product.txt");
            int i;
            StringBuilder sb = new StringBuilder();
            while((i = fileReader.read()) != -1);{
                sb.append((char)i);
                System.out.println((char)i);
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
    public static int displayMessage(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("message");
        return scanner.nextInt();
    }

    public void delete(int id) { this.isDeleted = true;
    }


}
