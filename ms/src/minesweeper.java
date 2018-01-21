

import javax.swing.BorderFactory;
import javax.swing.JToggleButton;
import java.awt.*;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; 

public final class minesweeper extends javax.swing.JFrame implements ContainerListener{

    
    public minesweeper() {
        initComponents();
        setLayout(blockr,blockc,fw,fh);
       
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        NewGame = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        EasyM = new javax.swing.JMenuItem();
        MediumM = new javax.swing.JMenuItem();
        HardM = new javax.swing.JMenuItem();
        Exit = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(200, 200));
        setSize(new java.awt.Dimension(200, 200));

        panel.setPreferredSize(new java.awt.Dimension(200, 300));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
        });
        panel.setLayout(new java.awt.GridLayout(blockr, blockc));

        NewGame.setText("File");

        jMenu3.setText("New Game");

        EasyM.setText("Easy (10mines,9x9blocks)");
        EasyM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EasyMActionPerformed(evt);
            }
        });
        jMenu3.add(EasyM);

        MediumM.setText("medium (40mines,16x16blocks)");
        MediumM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MediumMActionPerformed(evt);
            }
        });
        jMenu3.add(MediumM);

        HardM.setText("hard(99mines,16x30blocks)");
        HardM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HardMActionPerformed(evt);
            }
        });
        jMenu3.add(HardM);

        NewGame.add(jMenu3);

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        NewGame.add(Exit);

        jMenuBar1.add(NewGame);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void componentAdded(ContainerEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentRemoved(ContainerEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 


        
     class MouseHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent me){
            found=false;
            int i=0,j=0;
            for(i=0;i<blockr;i++){
                for(j=0;j<blockc;j++){
                    if(me.getSource()==blocks[i][j]){
                        found=true;
                        break;
                    }
                }
                if(found)break;
            }
          
                if(me.isMetaDown()==false){
               if(flags[i][j]==true){
                    blocks[i][j].setSelected(false);
                }else if(flags[i][j]==false){
                    if(firstclick){
                    spawn(i,j);
                    firstclick=false;
                    }
                open(i,j);
                }
            }else if(me.isMetaDown()==true){
                if(opened[i][j]==false){
                    if(flags[i][j]==true){
                    flags[i][j]=false;
                    blocks[i][j].setIcon(null);
                    }else{
                         flags[i][j]=true;
                         blocks[i][j].setIcon(flag);
                    }
                        
                }
            }
            
                
            
                
            
            
           
        } 
     }
    public void open(int x,int y){
            
            if(blockValue[x][y]==-1){
                for(int i=0;i<blockr;i++)
                     for(int j=0;j<blockc;j++){
                             blocks[i][j].setEnabled(false);
                     }
                for(int i=0;i<blockr;i++)
                     for(int j=0;j<blockc;j++){ 
                         blocks[i][j].removeMouseListener(mh);
                         if(blockValue[i][j]==-1){
                             blocks[i][j].setIcon(mine);
                         }
                     }  
                 JOptionPane.showMessageDialog(null, "Game Over"); 
            }
            if(opened[x][y]==false){
                if(blockValue[x][y]==0){
                    
                    for(int i=x-1;i<=x+1;i++)
                    for(int j=y-1;j<=y+1;j++){ 
                        if(i>=0 && j>=0 && i<blockr && j<blockc && blockValue[i][j]!=-1){
                            if(x!=i||y!=j){
                                blocks[x][y].setSelected(true);
                                opened[x][y]=true;
                                open(i,j);
                            }
                        }
                    }
                }
                
            } 
            blocks[x][y].setSelected(true);
            
            if(blockValue[x][y]==0){
                blocks[x][y].setText(" ");
            }else
                blocks[x][y].setText(""+blockValue[x][y]);
            checkwin();
    }
    public void checkwin(){
        won=true;
        for(int i=0;i<blockr;i++){
            for(int j=0;j<blockc;j++){
                if(blockValue[i][j]!=-1){
                    if(blocks[i][j].isSelected()==false){
                    won=false;
                }
                }
                
            }
           if(!won) break;
        }
        if(won){
            javax.swing.JOptionPane.showMessageDialog(null, "You win!!!");
            for(int i=0;i<blockr;i++)
                     for(int j=0;j<blockc;j++){
                             blocks[i][j].setEnabled(false);
                     }
        }    
                
    }
    public void setValues(){
        for(int i=0;i<blockr;i++)
            for(int j=0;j<blockc;j++){
                mines=0;
                if(blockValue[i][j]!=-1){
                    opened[i][j]=false;
                     for(int g=i-1;g<=i+1;g++)
                        for(int h=j-1;h<=j+1;h++){
                             if(g>=0 && h>=0 && g<blockr && h<blockc && blockValue[g][h]==-1){
                                 mines++;
                             }
                                 
                        }        
                }else{
                    mines=-1;
                }
                blockValue[i][j]=mines;
            }
    }
    public void spawn(int x,int y){
        int r,c,m=0;
        for(int k=0;k<bombs;k++){
            do{
            r = ranr.nextInt(blockr);
            c = ranc.nextInt(blockc);
            }while(blockValue[r][c]==-1||(r==x&&c==y));
            blockValue[r][c]=-1;
        }
        setValues();
    }
    void setLayout(int blockRow,int blockColumm,int fw,int fh){
       
        firstclick=true;
        blocks=new JToggleButton[blockRow][blockColumm];
        blockValue=new int[blockRow][blockColumm];
        flags=new boolean[blockRow][blockColumm];
        opened=new boolean[blockRow][blockColumm];
        setSize(fw, fh);
        setResizable(false);
        mh=new MouseHandler();
        panel.removeAll();
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));
        panel.setPreferredSize(new Dimension(fw, fh));
        panel.setLayout(new java.awt.GridLayout(blockRow, blockColumm));
        for(int i = 0; i < blockRow; i++){
            for(int j = 0; j < blockColumm; j++){
                blocks[i][j] = new JToggleButton();
                blocks[i][j].setMargin(new Insets(0,0,0,0));
                blocks[i][j].setFont(new Font("Arial",Font.PLAIN,10));
                blocks[i][j].setPreferredSize(new Dimension(25,25));
                blocks[i][j].addMouseListener(mh);
                panel.add(blocks[i][j]);
            }
        }
        panel.revalidate();
        panel.repaint();
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().repaint();
        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
        
    private void EasyMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyMActionPerformed
        fw = 300;
        fh = 350;
        blockr = 9;
        blockc = 9;
        bombs = 10;
        firstclick=true;
        setLayout(blockr,blockc,fw,fh);
        
    }//GEN-LAST:event_EasyMActionPerformed

    private void MediumMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MediumMActionPerformed
       bombs=40;
       fw = 320;
       fh = 320;
       blockr = 16;
       blockc = 16;
       bombs = 40;
       firstclick=true;
       setLayout(blockr,blockc,fw,fh);
    }//GEN-LAST:event_MediumMActionPerformed

    private void HardMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HardMActionPerformed
       bombs=99;
       fw = 600;
       fh = 320;
       blockr = 16;
       blockc = 30;
       bombs= 99;
       firstclick=true;
       setLayout(blockr,blockc,fw,fh);
    }//GEN-LAST:event_HardMActionPerformed

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitActionPerformed

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked

    }//GEN-LAST:event_panelMouseClicked

   
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new minesweeper().setVisible(true);
        });
    }
    static int blockr=9, blockc=9, fw=300,fh=350,bombs=10;
   
    int mi,mj,mines,m=0;
    JToggleButton[][] blocks;
    int[][] blockValue;
    boolean[][] flags;
    boolean[][] opened;
    boolean firstclick=true,found,won=false;
    Random ranc=new Random();
    Random ranr=new Random();
    ImageIcon flag=new ImageIcon("flag.png");
    ImageIcon mine=new ImageIcon("mine.png");
    MouseHandler mh;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EasyM;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenuItem HardM;
    private javax.swing.JMenuItem MediumM;
    private javax.swing.JMenu NewGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
