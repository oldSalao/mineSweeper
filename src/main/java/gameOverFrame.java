import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gameOverFrame extends Frame {
    Button goBtn, reBtn;

    public gameOverFrame(String str) {
        super(str);
        setBounds(300, 300, 340, 125);
        //레이아웃 매니저의 설정을 해제한다.
        setLayout(null);
        setVisible(true);
        goBtn = new Button();
        reBtn = new Button();
        goBtn.setBounds(180, 50, 100, 50);
        reBtn.setBounds(60, 50, 100, 50);
        reBtn.setLabel("restart");
        goBtn.setLabel("close");
        //restart 버튼에 대한 이벤트 처리
        reBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        //모튼 버튼을 제거한다.
                        main.f1.remove(main.f1.g[i][j].btn);
                        main.f1.setVisible(false);
                    }
                }
                //버튼을 다시 배치.
                main.f1.setBtn();
                main.f1.setMine();
                main.f1.setNum();
                main.f1.setVisible(true);
                //해당 프레임을 제거한다.
                dispose();
            }
        });
        //close 버튼에 대한 이벤트 처리
        goBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //프로그램 종료
                System.exit(0);
            }
        });
        add(goBtn);
        add(reBtn);
    }

}
