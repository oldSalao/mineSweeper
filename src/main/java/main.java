import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public static myFrame f1;
    public static void main(String[] args) {
        //프레임 생성
        f1 = new myFrame("테스트");
        f1.setBtn();
        f1.setMine();
        f1.setNum();
        f1.setVisible(true);
    }
}
