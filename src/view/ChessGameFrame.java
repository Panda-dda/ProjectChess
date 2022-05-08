package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    public int cc=0;
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;

    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);


        addChessboard();



    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);

    /**
     * 在游戏面板中添加标签
     */
        JLabel statusLabel = new JLabel("Turn For BLACK");
        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
        statusLabel.setSize(200, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));

        add(statusLabel);
        chessboard.setLable(statusLabel);


          JButton button = new JButton("Restart");
            button.setLocation(HEIGTH, HEIGTH / 10 + 360);
            button.setSize(200, 60);
            button.setFont(new Font("Rockwell", Font.BOLD, 20));
            add(button);

            button.addActionListener(e -> {
                System.out.println("Click Restart");
                int n=JOptionPane.showConfirmDialog(null,"Are you sure to restart","Confirm",JOptionPane.YES_NO_OPTION);
                if (n==JOptionPane.YES_OPTION){
                    chessboard.setCurrentColor(ChessColor.BLACK);
                    chessboard.Restarted();
                    System.out.printf("%d",chessboard.getA());
                    repaint();

                }
            });





    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */


        JButton button1 = new JButton("Store");
        button1.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button1.setSize(200, 60);
        button1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button1);
        button1.addActionListener(e -> {
            System.out.println("Click store");
            int n=JOptionPane.showConfirmDialog(null,"Are you sure to store","Confirm",JOptionPane.YES_NO_OPTION);
            if (n==JOptionPane.YES_OPTION){
                System.out.println(chessboard.theStore());
                String a=chessboard.theStore();
                saveAsFileWriter(chessboard.theStore());
            }
        });




        JButton buttonLoad = new JButton("Load");
        buttonLoad.setLocation(HEIGTH, HEIGTH / 10 + 240);
        buttonLoad.setSize(200, 60);
        buttonLoad.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(buttonLoad);

        buttonLoad.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.loadGameFromFile(path);
            if(chessboard.isLoadTest()){
                repaint();
            }
            else {
                JOptionPane.showMessageDialog(null, "the message is not correct？", "Warnings", JOptionPane.WARNING_MESSAGE);
            }



        });
    }


    private static   String savefile = "E:\\test.txt";
    private static void saveAsFileWriter(String content) {

        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(savefile);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


}
