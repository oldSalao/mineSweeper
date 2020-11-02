import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class grnd implements ActionListener {
    // stat : 상태를 저장하기 위한 변수. 1이면 눌린상태, 0이면 안눌린상태.
    // sNum : 버튼 주변의 지뢰 갯수에 따라 버튼에 나타낼 숫자를 담는 변수
    // mine : 지뢰를 가지고 있는지 여부를 담는 변수 1이면 지뢰가 있고 0이면 없다.
    // x,y : 버튼의 위치를 담는 변수.
    // winC : 승리조건에 활용될 변수.

    public int stat = 0, sNum = 0, mine = 0, x = 0, y = 0, winC = 0;
    //버튼 선언
    public Button btn;
    // 마우스 우클릭시 X표시를 하기위한 조건에 사용될 불리언.
    boolean plag = false;

    //생성자 정의
    public grnd() {
        btn = new Button(" ");
        btn.setFont(new Font("serif", Font.BOLD, 40));
        btn.addActionListener(this);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            //마우스 오른쪽 클릭 이벤트 처리
            public void mouseClicked(MouseEvent e) {
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
                    // 우클릭시 X 표시.
                    if (plag == false) {
                        btn.setLabel("X");
                        plag = true;
                    }
                    //다시 우클릭시 X 제거
                    else {
                        btn.setLabel(" ");
                        plag = false;
                    }
                }
            }
        });
    }


    //버튼의 setBounds에 연산을 위한 매개변수를 전달하는 함수.
    public void setBounds(int x, int y) {
        btn.setBounds(150 + x, 125 + y, 50, 50);
    }
    //버튼의 위치를 설정하기 위한 함수.
    public void setLoc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //자동으로 버튼이 눌리도록 하는 함수.
    public void chkGrnd(int x, int y) {
        try {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((((x + j) >= 0) && ((x + j) < 10)) && (((y + i) >= 0) && ((y + i) < 10))) {
                        if ((myFrame.g[y + i][x + j].stat == 0) && (myFrame.g[y + i][x + j].mine != 1)) {
                            myFrame.g[y + i][x + j].stat = 1;
                            myFrame.g[y + i][x + j].btn.setEnabled(false);
                            myFrame.g[y + i][x + j].btn.setLabel(" ");
                            myFrame.g[y + i][x + j].btn.setBackground(new Color(193, 193, 193));
                            if (myFrame.g[y + i][x + j].sNum != 0) {
                                myFrame.g[y + i][x + j].btn.setFont(new Font("serif", Font.BOLD, 40));
                                myFrame.g[y + i][x + j].btn.setLabel(String.valueOf(myFrame.g[y + i][x + j].sNum));
                                continue;
                            }
                            chkGrnd(x + j, y + i);
                        }
                    }

                }

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    //버튼 액션이벤트 처리
    @Override

    public void actionPerformed(ActionEvent e) {
        btn.setEnabled(false);
        btn.setBackground(new Color(193, 193, 193));
        btn.setLabel(" ");
        // 상태변수 눌렀을때 1임.
        stat = 1;
        //지뢰,숫자표시
        if (mine == 1) {
            //지뢰를 표시
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    //지뢰를 모두 나타나게 함.
                    if (myFrame.g[i][j].mine == 1) {
                        myFrame.g[i][j].btn.setEnabled(false);
                        myFrame.g[i][j].btn.setLabel(" * ");
                    }
                }
            }
//            모든 버튼 비활성화
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    myFrame.g[i][j].btn.setEnabled(false);
                }
            }
            //게임종료 프레임 생성.
            new gameOverFrame("You Lose.");
        } else {
            if (sNum != 0) {
                // 주변 지뢰 갯수에 따른 숫자를 버튼에 표시.
                btn.setLabel(String.valueOf(sNum));
            }
        }

        if ((sNum == 0) && (mine == 0)) {
            //자동으로 비어있는 버튼 누르는 함수 chkGrnd 호출.
            chkGrnd(this.x, this.y);
        }
        //게임을 이겼는지 체크
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((myFrame.g[i][j].mine == 0) && (myFrame.g[i][j].stat == 0)) {
                    winC++;
                }
            }
        }
        if (winC == 0) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    //지뢰를 모두 나타나게 함.
                    if (myFrame.g[i][j].mine == 1) {
                        myFrame.g[i][j].btn.setEnabled(false);
                        myFrame.g[i][j].btn.setLabel(" * ");
                    }
                }
            }
            new gameOverFrame("You Win.");
        }

        winC = 0;
    }
}
