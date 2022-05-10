package view;

import controller.ClickController;
import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;


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
//                saveAsFileWriter(chessboard.theStore());




//                JFileChooser fileChooser = new JFileChooser();
////后缀名过滤器
//                FileNameExtensionFilter filter = new FileNameExtensionFilter("标签文件(*.txt)", "txt");
//                fileChooser.setFileFilter(filter);
//// 在容器上打开文件选择器
//                fileChooser.showSaveDialog(fileChooser);
//                File f=fileChooser.getSelectedFile();
////字节输出流
//                FileOutputStream fos = null;
//                try {
//                    String fname = f.getName(); //从文件名输入框中获取文件名
//
//
//
//                    //创建文件
//                    File file=new File(fileChooser.getCurrentDirectory()+"/"+fname+".txt");
//                    fos = new FileOutputStream(file);
//                    //写入文件操作
//                    String Datas = chessboard.theStore();
//                    fos.write(Datas.getBytes());
//                    fos.close();
//
//                } catch (IOException e1) {
//                    System.err.println("IO异常");
//                    e1.printStackTrace();
//                }

                saveFile(a);

            }
        });




        JButton buttonLoad = new JButton("Load");
        buttonLoad.setLocation(HEIGTH, HEIGTH / 10 + 240);
        buttonLoad.setSize(200, 60);
        buttonLoad.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(buttonLoad);

//        buttonLoad.addActionListener(e -> {
//            System.out.println("Click load");
//            String path = JOptionPane.showInputDialog(this,"Input Path here");
//            boolean pathForm=true;
//
//                if ((path.charAt(path.length()-3)!='t')||(path.charAt(path.length()-2)!='x')||(path.charAt(path.length()-1)!='t')){
//                    pathForm=false;
//                }
//
//
//            gameController.loadGameFromFile(path);
//            if(chessboard.isLoadTest()&&pathForm){
//
//                repaint();
//            }
//            else {
//                JOptionPane.showMessageDialog(null, "the message is not correct!", "Warnings", JOptionPane.WARNING_MESSAGE);
//            }



//        });


        buttonLoad.addActionListener(e->{//csdn
                // 按钮点击事件


                JFileChooser chooser = new JFileChooser(); // 设置选择器
                chooser.setMultiSelectionEnabled(false); // 设为多选
                int returnVal = chooser.showOpenDialog(buttonLoad); // 是否打开文件选择框
                System.out.println("returnVal=" + returnVal);

                if (returnVal == JFileChooser.APPROVE_OPTION) { // 如果符合文件类型
                    boolean pathForm=true;
                    String filepath = chooser.getSelectedFile().getAbsolutePath(); // 获取绝对路径
                    System.out.println(filepath);

                    System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
                    String path =filepath;
                    System.out.println(path);// 输出相对路径
                     if ((path.charAt(path.length()-3)!='t')||(path.charAt(path.length()-2)!='x')||(path.charAt(path.length()-1)!='t')){
                    pathForm=false;
                         JOptionPane.showMessageDialog(null, "the message is not correct!", "Warnings", JOptionPane.WARNING_MESSAGE);

                }
                     else {
                    gameController.loadGameFromFile(path);



            if(chessboard.isLoadTest()&&pathForm){

                repaint();
            }
            else {
                JOptionPane.showMessageDialog(null, "the message is not correct!", "Warnings", JOptionPane.WARNING_MESSAGE);
            }
                }}


        });
    }


//    private static   String savefile = "E:\\test.txt";
//    private static void saveAsFileWriter(String content) {
//
//        FileWriter fwriter = null;
//        try {
//            fwriter = new FileWriter(savefile);
//            fwriter.write(content);
//        } catch (IOException ex) {
//              ex.printStackTrace();
//        } finally {
//            try {
//                fwriter.flush();
//                fwriter.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public void saveFile(String a) {//csdn
        //弹出文件选择框
        JFileChooser chooser = new JFileChooser();

        //后缀名过滤器
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "(*.txt)", "txt");
        chooser.setFileFilter(filter);

        //下面的方法将阻塞，直到【用户按下保存按钮且“文件名”文本框不为空】或【用户按下取消按钮】
        int option = chooser.showSaveDialog(null);
        if(option==JFileChooser.APPROVE_OPTION){	//假如用户选择了保存
            File file = chooser.getSelectedFile();

            String fname = chooser.getName(file);	//从文件名输入框中获取文件名

            //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
            if(fname.indexOf(".txt")==-1){
                file = new File(chooser.getCurrentDirectory(),fname+".txt");
                System.out.println("renamed");
                System.out.println(file.getName());
            }

            try {
                FileOutputStream fos = new FileOutputStream(file);

                //写文件操作……
                String Datas = a;
                    fos.write(Datas.getBytes());
                    fos.close();

                fos.close();

            } catch (IOException e) {
                System.err.println("IO异常");
                e.printStackTrace();
            }
        }
    }


//    static void playMusic(){//背景音乐播放
//        try {
//            URL cb;
//            File f = new File("F:\\QQmusicDownLoad\\music.wav"); // 引号里面的是音乐文件所在的路径
//            cb = f.toURL();
//            AudioClip aau;
//            aau = Applet.newAudioClip(cb);
//
//            aau.play();
//            aau.loop();//循环播放
//            System.out.println("可以播放");
//            // 循环播放 aau.play()
//            //单曲 aau.stop()停止播放
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//        }
//    }


//    static void playMusic(){//背景音乐播放
//        try {
//            URL cb;
//            File f = new File("F:\\QQmusicDownLoad\\music.wav"); // 引号里面的是音乐文件所在的路径
//            cb = f.toURL();
//            AudioClip aau;
//            aau = Applet.newAudioClip(cb);
//
//            aau.play();
//            aau.loop();//循环播放
//            System.out.println("可以播放");
//            // 循环播放 aau.play()
//            //单曲 aau.stop()停止播放
//
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//        }
//    }








}
