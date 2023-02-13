package writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileWriterAndReaderRunner {
    public static final String FILE_NAME = "product.txt";

    public static void main(String[] args) {
        int ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product \n4.Update Product \n5.Sell Product \n6.Exit");
        while(ops != 6){
            if(ops == 1){
                File file = new File(FILE_NAME);
                List<Product> productList= new ArrayList<>();
                if (file.exists()) {
                    productList = Utils.getProductsFromFile(file);
                }
                Utils.getProductsFromUser(productList);
                Utils.saveToFile(file, productList);
            }else if(ops==2){
                Object file;
                String productList = Utils.readFromFile(new File("product.txt"));
                if(productList != null){
                    System.out.println(productList);
                }
            } else if (ops==3){
                int inputID = Utils.displayMessage("please enter the Id of product you want to delete");

            }
            ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product \n4.Update Product \n5.Sell Product");
        }
    }
}