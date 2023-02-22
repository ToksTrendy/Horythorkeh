package writer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppConstants {

    private AppConstants() {
    }

    public static final String FILE_NAME = "product.txt";
    public static final File FILE = new File(FILE_NAME);
    public static List<Product> PRODUCT_LIST = new ArrayList<>();


}
