import java.awt.*;
import java.awt.event.*;

public class myFrame extends Frame implements WindowListener {
    //지뢰의 위치를 무작위로 선정하기 위한 난수를 받을 배열
    int[] xRand = new int[10];
    int[] yRand = new int[10];
    //버튼 인스턴스 생성, 2차원 배열로써 생성하여 10*10 게임판을 구성한다
    public static grnd[][] g = new grnd[10][10];

    //프레임의 생성자
    public myFrame(String str) {
        super(str);
        setBounds(0, 0, 1000, 1000);
        //레이아웃 매니저의 설정을 해제한다.
        setLayout(null);
        //윈도우리스너 추가.
        addWindowListener(this);
    }

    //버튼을 프레임에 추가한다.
    public void setBtn() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //객체를 배열로써 사용하려면 요소 인스턴스를 전부 초기화해주어야 한다.
                g[i][j] = new grnd();
                //버튼의 위치값 설정
                g[i][j].setBounds(j * 50, i * 50);
                //버튼의 위치 지정
                g[i][j].setLoc(j, i);
                //버튼을 프레임에 추가
                add(g[i][j].btn);
            }
        }
    }

    //지뢰를 심는다.
    public void setMine() {
        for (int i = 0; i < 10; i++) {
            xRand[i] = (int) (Math.random() * 10);
            yRand[i] = (int) (Math.random() * 10);
            //중복체크
            for (int j = 0; j < i; j++) {
                if ((xRand[i] == xRand[j]) && (yRand[i] == yRand[j])) {
                    i--;
                    break;
                }
            }
            //지뢰를 심음
            g[xRand[i]][yRand[i]].mine = 1;
        }
    }

    //주변의 지뢰를 감지하여 숫자를 가짐.
    public void setNum() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = -1; k < 2; k++) {
                    for (int l = -1; l < 2; l++) {
                        if ((((l + j) >= 0) && ((l + j) < 10)) && (((k + i) >= 0) && ((k + i) < 10))) {
                            if (g[i + k][j + l].mine == 1){
                                g[i][j].sNum++;
                            }
                        }

                    }
                }
            }
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //해당 라인으로 인해 close 버튼을 누르는 이벤트 발생시 프로그램 종료.
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}