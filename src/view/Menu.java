package view;


import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

    public class Menu extends JFrame implements ActionListener {
        private JButton Game_Start;

        private JButton Game_Over;

        public Menu(){

//定义按钮的排列方式

            setLayout(new FlowLayout());

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            this.setSize(300,200);

            this.setLocation(300,400);

            Game_Start = new JButton("Start");

            Game_Over = new JButton("Out");

            this.add(Game_Start);

            this.add(Game_Over);

            Game_Start.addActionListener(this);

            Game_Over.addActionListener(this);

            this.setVisible(true);

        }







        /**

         * Invoked when an action occurs.

         *

         * @param e

         */

        @Override

        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == Game_Start){

                this.dispose();
//                String file = "F:\\music.mp3";
//                    Music play = new Music(file);
//                    // 开启
//                    play.start();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    System.out.println("stop!");
                    ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
                    mainFrame.setVisible(true);

//                SwingUtilities.invokeLater(() -> {
//                    String file = "F:\\music.mp3";
//                    Music play = new Music(file);
//                    // 开启
//                    play.start();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                    System.out.println("stop!");
//                    ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
//                    mainFrame.setVisible(true);
//
//                });}

            }

            if(e.getSource() == Game_Over){

                this.dispose();

                System.exit(0);

            }

        }

    }



