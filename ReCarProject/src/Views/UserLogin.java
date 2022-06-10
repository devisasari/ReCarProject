package Views;

import Model.User;
import com.carRental.Helper.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLogin extends JFrame {
    private JTextField txt_kullaniciAdi;
    private JPasswordField psw_parola;
    private JButton btn_girisYap;
    private JButton btn_uyelikOlustur;
    private JButton btn_return;
    private JPanel wrapper;
    private JPanel wBottom;


    public UserLogin(){
        add(wrapper);
        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(Config.PROJECT_TİTLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ana login ekranına dönüş
        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Helper.login.setVisible(true);
            }
        });

        // giriş yapma
        btn_girisYap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(txt_kullaniciAdi) || Helper.isFieldEmpty(psw_parola)){
                    Helper.showMsg("fill");
                }else{
                    User u = User.getFetch(txt_kullaniciAdi.getText(),psw_parola.getText());
                    if(u ==null){
                        Helper.showMsg("Kullanıcı Bulunamadı !");
                    }else{
                        Helper.login.dispose();

                        // giriş doğruysa user giriş ekranı üretme
                        UserManagement userManagement = new UserManagement(u);
                        userManagement.setVisible(true);
                        dispose();
                    }
                }
            }
        });

        // kullanıcı için yeni üyelik
        btn_uyelikOlustur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Helper.isFieldEmpty(txt_kullaniciAdi) || Helper.isFieldEmpty(psw_parola)){
                    Helper.showMsg("fill");
                }else{
                    User u = User.getFetch(txt_kullaniciAdi.getText(),psw_parola.getText());
                    if(u != null){
                        Helper.showMsg("Üyelik Zaten Mevcut !");
                    }else{

                        if(User.add(txt_kullaniciAdi.getText(),psw_parola.getText()) == true){
                            Helper.showMsg("Üyelik Başarılı");
                        }

                    }
                }

            }
        });
    }
}