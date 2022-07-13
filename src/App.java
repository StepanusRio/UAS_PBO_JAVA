import javax.swing.JFrame;

import com.conf.*;
import com.view.*;

public class App {
    public static void main(String[] args) throws Exception {
        KoneksiDBMS.connection();
        JFrame login = new vlogin();
        login.setVisible(true);
    }
}
