package writer;

import java.io.FileNotFoundException;

import static writer.AppConstants.FILE;

public class FileWriterAndReaderRunner {

    public static void main(String[] args) throws FileNotFoundException {
        if (FILE.exists()) {
            Utils.getProductsFromFile();
        }

        int ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product \n4.Sell Product \n5.Update Product \n6.Exit");
        while(ops != 6){
            if(ops == 1){
                Utils.getProductsFromUser();
                Utils.saveToFile();
            }else if(ops == 2){
                Utils.viewProducts();
            } else if (ops == 3){
                Utils.delete();
            }else if (ops == 4){
                Utils.sell();
            }else {
                Utils.update();
            }
            ops = Utils.collectIntegerInput("Select operation \n1.Add Product \n2.View Product \n3.Delete Product \n4.Sell Product \n5.Update Product \n6.Exit");
        }
    }
}