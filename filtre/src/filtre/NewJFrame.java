/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package filtre;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.concurrent.TimeUnit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class NewJFrame extends javax.swing.JFrame {
    
    
    BufferedImage img;
    int[][] Red;
    int[][] Blue;
    int[][] Green;
    
    int[][] Red1;
    int[][] Green1;
    int[][] Blue1;
   
    
    public NewJFrame() {
        try{
        initComponents();
        Ayır(jLabel1);
        RGBbirlestir(Red,Green,Blue);
        Birleştir(jLabel2);
        
        Red1 = new int[img.getWidth()][img.getHeight()];
        Green1= new int[img.getWidth()][img.getHeight()];
        Blue1= new int[img.getWidth()][img.getHeight()];
        }
        catch(Exception e){System.out.println("Error in Constructor.");}
        
    }
    
    public void fonk(int a,int b,int c,int d,int f,int g,int q,int w,int e,int r,int t,int dsx,int dsy,int dfx,int dfy){
        
        try{
        kirmiziFitre(d,dsx,dsy,dfx,dfy);
        yesilFitre(f,dsx,dsy,dfx,dfy);
        maviFitre(g,dsx,dsy,dfx,dfy);
        
        for(int k =0;k<r;k++){
           pixelation(dsx,dsy,dfx,dfy);  
        }
        for(int k =0;k<t;k++){
           blurring(dsx,dsy,dfx,dfy); 
        }
        
        
        for(int k =0;k<q;k++){
           detection(a,a,a,dsx,dsy,dfx,dfy);  
        }
        for(int k =0;k<w;k++){
           detectionDelete(b,b,b,dsx,dsy,dfx,dfy); 
        }
        for(int k =0;k<e;k++){
           detectionDark(c,c,c,dsx,dsy,dfx,dfy);
        }
        
        }
        
        catch(Exception ex){System.out.println("Error in fonk.");}
        
    }
    
    public void ThreadRun(int tSayısı,int height,int width, int a,int b,int c,int d,int f,int g,int q,int w,int e,int r,int t){
    
        try {
        Ayır(jLabel1);
        
        int pix = 300/tSayısı;
        int tempSpix=0;                   //başlangıcı
        int tempFpix=pix;                 //piksel sayisi 300/thread sayisi /bitisi
        
        int dsy = 0;
        int dfy=300;
       
        ImgThreads[] Tarray = new ImgThreads[tSayısı];
        
        for(int i=0;i<tSayısı;i++){
            
            //ImgThreads t1 = new ImgThreads(a,b,c,d,f,g,q,w,e,r,t,tempSpix,dsy,tempFpix,dfy);
            //t1.start();
            
            Tarray[i] =  new ImgThreads(a,b,c,d,f,g,q,w,e,r,t,tempSpix,dsy,tempFpix,dfy);
            Tarray[i].start();
            tempSpix+=pix;                 
            tempFpix+=pix;
            
        }
        
        //Bütün threadların işi bitene kadar programı while döngüsünde sıkıştırıyoruz.
        for(int i=0;i<tSayısı;i++){
            while(Tarray[i].isAlive()){
                //System.out.println(i+1+".Thread hala yaşıyor!");
            }
        }
        
        RGBbirlestir(Red,Green,Blue);
        Birleştir(jLabel2);
        
        int artpix =width%tSayısı;
        
        }
        
        catch(ArithmeticException ex){System.out.println("Arithmetic Exception in ThreadRun Function.");}
        catch(ArrayIndexOutOfBoundsException ex){System.out.println("Out Of Bounds Exception in ThreadRun Function.");}
        
    }
    
    public void detection(int Rtolerance,int Gtolerance,int Btolerance,int xST,int yST,int xFT,int yFT){  
        
        try{
        int R[][] =new int[3][3];
        int G[][] =new int[3][3];
        int B[][] =new int[3][3];  
        
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
               
                if(x-1>=0 && y-1>=0){                    
                    R[0][0]=Red[x-1][y-1];
                    G[0][0]=Green[x-1][y-1];
                    B[0][0]=Blue[x-1][y-1]; 
                }
                if(x>=0 && y-1>=0){                    
                    R[0][1]=Red[x][y-1];
                    G[0][1]=Green[x][y-1];
                    B[0][1]=Blue[x][y-1]; 
                }
                if(x+1<img.getWidth() && y-1>=0){                    
                    R[0][2]=Red[x+1][y-1];
                    G[0][2]=Green[x+1][y-1];
                    B[0][2]=Blue[x+1][y-1]; 
                }
                if(x-1>=0 && y>=0){                    
                    R[1][0]=Red[x-1][y];
                    G[1][0]=Green[x-1][y];
                    B[1][0]=Blue[x-1][y]; 
                }
                if(x>=0 && y>=0){                    
                    R[1][1]=Red[x][y];
                    G[1][1]=Green[x][y];
                    B[1][1]=Blue[x][y]; 
                }               
                if(x+1<img.getWidth() && y>=0){                    
                    R[1][2]=Red[x+1][y];
                    G[1][2]=Green[x+1][y];
                    B[1][2]=Blue[x+1][y];  
                }
                if(x-1>=0 && y+1<img.getHeight()){                    
                    R[2][0]=Red[x-1][y+1];
                    G[2][0]=Green[x-1][y+1];
                    B[2][0]=Blue[x-1][y+1];  
                }
                if(x>=0 && y+1<img.getHeight()){                    
                    R[2][1]=Red[x][y+1];
                    G[2][1]=Green[x][y+1];
                    B[2][1]=Blue[x][y+1];  
                }
                if(x+1<img.getWidth() && y+1<img.getHeight()){  
                    R[2][2]=Red[x+1][y+1];
                    G[2][2]=Green[x+1][y+1];
                    B[2][2]=Blue[x+1][y+1];                 
                }
                
               
                //-----------------------------------------------------
                
                if(Math.abs(R[1][1]-R[0][0])<=Rtolerance){                           
                    R[0][0]=(R[1][1]+R[0][0])/2;
                    R[1][1]=R[0][0];      
                }
                if(Math.abs(G[1][1]-G[0][0])<=Gtolerance){
                    G[0][0]=(G[1][1]+G[0][0])/2;
                    G[1][1]=G[0][0];
                }
                if(Math.abs(B[1][1]-B[0][0])<=Btolerance){
                    B[0][0]=(B[1][1]+B[0][0])/2;
                    B[1][1]=B[0][0];
                }
                
                if(Math.abs(R[1][1]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[1][1]+R[0][1])/2;
                    R[1][1]=R[0][1];
                }
                if(Math.abs(G[1][1]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[1][1]+G[0][1])/2;
                    G[1][1]=G[0][1];
                }
                if(Math.abs(B[1][1]-B[0][1])<=Btolerance){
                    B[0][1]=(B[1][1]+B[0][1])/2;
                    B[1][1]=B[0][1];
                }

                if(Math.abs(R[1][1]-R[0][2])<=Rtolerance){
                    R[0][2]=(R[1][1]+R[0][2])/2;
                    R[1][1]=R[0][2];
                }
                if(Math.abs(G[1][1]-G[0][2])<=Gtolerance){
                    G[0][2]=(G[1][1]+G[0][2])/2;
                    G[1][1]=G[0][2];
                }                
                if(Math.abs(B[1][1]-B[0][2])<=Btolerance){
                    B[0][2]=(B[1][1]+B[0][2])/2;
                    B[1][1]=B[0][2];
                }
                
                if(Math.abs(R[1][1]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[1][1]+R[1][0])/2;
                    R[1][1]=R[1][0];
                }
                if(Math.abs(G[1][1]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[1][1]+G[1][0])/2;
                    G[1][1]=G[1][0];
                }
                if(Math.abs(B[1][1]-B[1][0])<=Btolerance){
                    B[1][0]=(B[1][1]+B[1][0])/2;
                    B[1][1]=B[1][0];
                }
                
                if(Math.abs(R[1][1]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[1][1]+R[1][2])/2;
                    R[1][1]=R[1][2];
                }
                if(Math.abs(G[1][1]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[1][1]+G[1][2])/2;
                    G[1][1]=G[1][2];
                }
                if(Math.abs(B[1][1]-B[1][2])<=Btolerance){
                    B[1][2]=(B[1][1]+B[1][2])/2;
                    B[1][1]=B[1][2];
                }

                if(Math.abs(R[1][1]-R[2][0])<=Rtolerance){
                    R[2][0]=(R[1][1]+R[2][0])/2;
                    R[1][1]=R[2][0]; 
                }
                if(Math.abs(G[1][1]-G[2][0])<=Gtolerance){
                    G[2][0]=(G[1][1]+G[2][0])/2;
                    G[1][1]=G[2][0];
                }
                if(Math.abs(B[1][1]-B[2][0])<=Btolerance){
                    B[2][0]=(B[1][1]+B[2][0])/2;
                    B[1][1]=B[2][0];
                }

                if(Math.abs(R[1][1]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[1][1]+R[2][1])/2;
                    R[1][1]=R[2][1];
                }
                if(Math.abs(G[1][1]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[1][1]+G[2][1])/2;
                    G[1][1]=G[2][1];
                }
                if(Math.abs(B[1][1]-B[2][1])<=Btolerance){
                    B[2][1]=(B[1][1]+B[2][1])/2;
                    B[1][1]=B[2][1];
                }

                if(Math.abs(R[1][1]-R[2][2])<=Rtolerance){
                    R[2][2]=(R[1][1]+R[2][2])/2;
                    R[1][1]=R[2][2];
                }
                if(Math.abs(G[1][1]-G[2][2])<=Gtolerance){
                    G[2][2]=(G[1][1]+G[2][2])/2;
                    G[1][1]=G[2][2];
                }
                if(Math.abs(B[1][1]-B[2][2])<=Btolerance){
                    B[2][2]=(B[1][1]+B[2][2])/2;
                    B[1][1]=B[2][2];
                }
                //-----------------------
                
                if(Math.abs(R[0][0]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[0][0]+R[0][1])/2;              
                    R[0][0]=R[0][1];      
                }             
                if(Math.abs(G[0][0]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[0][0]+G[0][1])/2;
                    G[0][0]=G[0][1];

                }
                if(Math.abs(B[0][0]-B[0][1])<=Btolerance){
                    B[0][1]=(B[0][0]+B[0][1])/2;
                    B[0][0]=B[0][1];
                }
                if(Math.abs(R[0][0]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[0][0]+R[1][0])/2; 
                    R[0][0]=R[1][0];
                }
                if(Math.abs(G[0][0]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[0][0]+G[1][0])/2;
                    G[0][0]=G[1][0];
                }
                if(Math.abs(B[0][0]-B[1][0])<=Btolerance){
                    B[1][0]=(B[0][0]+B[1][0])/2;
                    B[0][0]=B[1][0];
                }              
 //-----------------------
                if(Math.abs(R[0][2]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[0][2]+R[0][1])/2;       
                    R[0][2]=R[0][1];  
                }
                if(Math.abs(G[0][2]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[0][2]+G[0][1])/2;
                    G[0][2]=G[0][1];
                }
                if(Math.abs(B[0][2]-B[0][1])<=Btolerance){
                    B[0][1]=(B[0][2]+B[0][1])/2;
                    B[0][2]=B[0][1];
                }
                if(Math.abs(R[0][2]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[0][2]+R[1][2])/2;                  
                    R[0][2]=R[1][2];  
                }                
                if(Math.abs(G[0][2]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[0][2]+G[1][2])/2;
                    G[0][2]=G[1][2];
                }
                if(Math.abs(B[0][2]-B[1][2])<=Btolerance){
                    B[1][2]=(B[0][2]+B[1][2])/2;
                    B[0][2]=B[1][2];
                }
                //---------------------------------
                if(Math.abs(R[2][0]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[2][0]+R[1][0])/2;                
                    R[2][0]=R[1][0];                                 
                }
                if(Math.abs(G[2][0]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[2][0]+G[1][0])/2;
                    G[2][0]=G[1][0];
                }
                if( Math.abs(B[2][0]-B[1][0])<=Btolerance){
                    B[1][0]=(B[2][0]+B[1][0])/2;
                    B[2][0]=B[1][0];
                }
                if(Math.abs(R[2][0]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[2][0]+R[2][1])/2;
                    R[2][0]=R[2][1];                                     
                }
                if(Math.abs(G[2][0]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[2][0]+G[2][1])/2;
                    G[2][0]=G[2][1];
                }
                if(Math.abs(B[2][0]-B[2][1])<=Btolerance){
                    B[2][1]=(B[2][0]+B[2][1])/2;
                    B[2][0]=B[2][1];
                }
                
                //-------------------------------
                if(Math.abs(R[2][2]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[2][2]+R[1][2])/2;       
                    R[2][2]=R[1][2];   
                }
                if( Math.abs(G[2][2]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[2][2]+G[1][2])/2;
                    G[2][2]=G[1][2];
                }
                if(Math.abs(B[2][2]-B[1][2])<=Btolerance){
                    B[1][2]=(B[2][2]+B[1][2])/2;
                    B[2][2]=B[1][2];
                }

                if(Math.abs(R[2][2]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[2][2]+R[2][1])/2;              
                    R[2][2]=R[2][1];
                }
                if(Math.abs(G[2][2]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[2][2]+G[2][1])/2;
                    G[2][2]=G[2][1];
                }
                if(Math.abs(B[2][2]-B[2][1])<=Btolerance){
                    B[2][1]=(B[2][2]+B[2][1])/2;
                    B[2][2]=B[2][1];
                }
                    
                /*for(int yy=-1 ;yy<2; yy++){
                    for(int xx=-1 ;xx<2; xx++){
                        if(x+xx>=0 && x+xx<img.getWidth() && y+yy>=0 && y+yy<img.getHeight()){
                            Red[x+xx][y+yy] = R[1+xx][1+yy];
                            Green[x+xx][y+yy] = G[1+xx][1+yy];
                            Blue[x+xx][y+yy] = B[1+xx][1+yy];
                        }
                        
                    }
                }*/  
                
                Red[x][y] = R[1][1];
                Green[x][y] = G[1][1];
                Blue[x][y] = B[1][1];
            }
        }    
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in Detection Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception Detection Function.");}
        
    }
    
    public void detectionDelete(int Rtolerance,int Gtolerance,int Btolerance,int xST,int yST,int xFT,int yFT){      //
        try{
        int R[][] =new int[3][3];
        int G[][] =new int[3][3];
        int B[][] =new int[3][3];  
        
        int val=3;
        
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
               
                if(x-1>=0 && y-1>=0){                    
                    R[0][0]=Red[x-1][y-1];
                    G[0][0]=Green[x-1][y-1];
                    B[0][0]=Blue[x-1][y-1]; 
                }
                if(x>=0 && y-1>=0){                    
                    R[0][1]=Red[x][y-1];
                    G[0][1]=Green[x][y-1];
                    B[0][1]=Blue[x][y-1]; 
                }
                if(x+1<img.getWidth() && y-1>=0){                    
                    R[0][2]=Red[x+1][y-1];
                    G[0][2]=Green[x+1][y-1];
                    B[0][2]=Blue[x+1][y-1]; 
                }
                if(x-1>=0 && y>=0){                    
                    R[1][0]=Red[x-1][y];
                    G[1][0]=Green[x-1][y];
                    B[1][0]=Blue[x-1][y]; 
                }
                if(x>=0 && y>=0){                    
                    R[1][1]=Red[x][y];
                    G[1][1]=Green[x][y];
                    B[1][1]=Blue[x][y]; 
                }               
                if(x+1<img.getWidth() && y>=0){                    
                    R[1][2]=Red[x+1][y];
                    G[1][2]=Green[x+1][y];
                    B[1][2]=Blue[x+1][y];  
                }
                if(x-1>=0 && y+1<img.getHeight()){                    
                    R[2][0]=Red[x-1][y+1];
                    G[2][0]=Green[x-1][y+1];
                    B[2][0]=Blue[x-1][y+1];  
                }
                if(x>=0 && y+1<img.getHeight()){                    
                    R[2][1]=Red[x][y+1];
                    G[2][1]=Green[x][y+1];
                    B[2][1]=Blue[x][y+1];  
                }
                if(x+1<img.getWidth() && y+1<img.getHeight()){  
                    R[2][2]=Red[x+1][y+1];
                    G[2][2]=Green[x+1][y+1];
                    B[2][2]=Blue[x+1][y+1];                 
                }
                
               
                //-----------------------------------------------------
                
               if(Math.abs(R[1][1]-R[0][0])<=Rtolerance){                           
                    R[0][0]=(R[1][1]+R[0][0])/val;
                    R[1][1]=R[0][0];      
                }
                if(Math.abs(G[1][1]-G[0][0])<=Gtolerance){
                    G[0][0]=(G[1][1]+G[0][0])/val;
                    G[1][1]=G[0][0];
                }
                if(Math.abs(B[1][1]-B[0][0])<=Btolerance){
                    B[0][0]=(B[1][1]+B[0][0])/val;
                    B[1][1]=B[0][0];
                }
                
                if(Math.abs(R[1][1]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[1][1]+R[0][1])/val;
                    R[1][1]=R[0][1];
                }
                if(Math.abs(G[1][1]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[1][1]+G[0][1])/val;
                    G[1][1]=G[0][1];
                }
                if(Math.abs(B[1][1]-B[0][1])<=Btolerance){
                    B[0][1]=(B[1][1]+B[0][1])/val;
                    B[1][1]=B[0][1];
                }

                if(Math.abs(R[1][1]-R[0][2])<=Rtolerance){
                    R[0][2]=(R[1][1]+R[0][2])/val;
                    R[1][1]=R[0][2];
                }
                if(Math.abs(G[1][1]-G[0][2])<=Gtolerance){
                    G[0][2]=(G[1][1]+G[0][2])/val;
                    G[1][1]=G[0][2];
                }                
                if(Math.abs(B[1][1]-B[0][2])<=Btolerance){
                    B[0][2]=(B[1][1]+B[0][2])/val;
                    B[1][1]=B[0][2];
                }
                
                if(Math.abs(R[1][1]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[1][1]+R[1][0])/val;
                    R[1][1]=R[1][0];
                }
                if(Math.abs(G[1][1]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[1][1]+G[1][0])/val;
                    G[1][1]=G[1][0];
                }
                if(Math.abs(B[1][1]-B[1][0])<=Btolerance){
                    B[1][0]=(B[1][1]+B[1][0])/val;
                    B[1][1]=B[1][0];
                }
                
                if(Math.abs(R[1][1]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[1][1]+R[1][2])/val;
                    R[1][1]=R[1][2];
                }
                if(Math.abs(G[1][1]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[1][1]+G[1][2])/val;
                    G[1][1]=G[1][2];
                }
                if(Math.abs(B[1][1]-B[1][2])<=Btolerance){
                    B[1][2]=(B[1][1]+B[1][2])/val;
                    B[1][1]=B[1][2];
                }

                if(Math.abs(R[1][1]-R[2][0])<=Rtolerance){
                    R[2][0]=(R[1][1]+R[2][0])/val;
                    R[1][1]=R[2][0]; 
                }
                if(Math.abs(G[1][1]-G[2][0])<=Gtolerance){
                    G[2][0]=(G[1][1]+G[2][0])/val;
                    G[1][1]=G[2][0];
                }
                if(Math.abs(B[1][1]-B[2][0])<=Btolerance){
                    B[2][0]=(B[1][1]+B[2][0])/val;
                    B[1][1]=B[2][0];
                }

                if(Math.abs(R[1][1]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[1][1]+R[2][1])/val;
                    R[1][1]=R[2][1];
                }
                if(Math.abs(G[1][1]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[1][1]+G[2][1])/val;
                    G[1][1]=G[2][1];
                }
                if(Math.abs(B[1][1]-B[2][1])<=Btolerance){
                    B[2][1]=(B[1][1]+B[2][1])/val;
                    B[1][1]=B[2][1];
                }

                if(Math.abs(R[1][1]-R[2][2])<=Rtolerance){
                    R[2][2]=(R[1][1]+R[2][2])/val;
                    R[1][1]=R[2][2];
                }
                if(Math.abs(G[1][1]-G[2][2])<=Gtolerance){
                    G[2][2]=(G[1][1]+G[2][2])/val;
                    G[1][1]=G[2][2];
                }
                if(Math.abs(B[1][1]-B[2][2])<=Btolerance){
                    B[2][2]=(B[1][1]+B[2][2])/val;
                    B[1][1]=B[2][2];
                }
                //-----------------------
                
                if(Math.abs(R[0][0]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[0][0]+R[0][1])/val;              
                    R[0][0]=R[0][1];      
                }             
                if(Math.abs(G[0][0]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[0][0]+G[0][1])/val;
                    G[0][0]=G[0][1];

                }
                if(Math.abs(B[0][0]-B[0][1])<=Btolerance){
                    B[0][1]=(B[0][0]+B[0][1])/val;
                    B[0][0]=B[0][1];
                }
                if(Math.abs(R[0][0]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[0][0]+R[1][0])/val; 
                    R[0][0]=R[1][0];
                }
                if(Math.abs(G[0][0]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[0][0]+G[1][0])/val;
                    G[0][0]=G[1][0];
                }
                if(Math.abs(B[0][0]-B[1][0])<=Btolerance){
                    B[1][0]=(B[0][0]+B[1][0])/val;
                    B[0][0]=B[1][0];
                }              
 //-----------------------
                if(Math.abs(R[0][2]-R[0][1])<=Rtolerance){
                    R[0][1]=(R[0][2]+R[0][1])/val;       
                    R[0][2]=R[0][1];  
                }
                if(Math.abs(G[0][2]-G[0][1])<=Gtolerance){
                    G[0][1]=(G[0][2]+G[0][1])/val;
                    G[0][2]=G[0][1];
                }
                if(Math.abs(B[0][2]-B[0][1])<=Btolerance){
                    B[0][1]=(B[0][2]+B[0][1])/val;
                    B[0][2]=B[0][1];
                }
                if(Math.abs(R[0][2]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[0][2]+R[1][2])/val;                  
                    R[0][2]=R[1][2];  
                }                
                if(Math.abs(G[0][2]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[0][2]+G[1][2])/val;
                    G[0][2]=G[1][2];
                }
                if(Math.abs(B[0][2]-B[1][2])<=Btolerance){
                    B[1][2]=(B[0][2]+B[1][2])/val;
                    B[0][2]=B[1][2];
                }
                //---------------------------------
                if(Math.abs(R[2][0]-R[1][0])<=Rtolerance){
                    R[1][0]=(R[2][0]+R[1][0])/val;                
                    R[2][0]=R[1][0];                                 
                }
                if(Math.abs(G[2][0]-G[1][0])<=Gtolerance){
                    G[1][0]=(G[2][0]+G[1][0])/val;
                    G[2][0]=G[1][0];
                }
                if( Math.abs(B[2][0]-B[1][0])<=Btolerance){
                    B[1][0]=(B[2][0]+B[1][0])/val;
                    B[2][0]=B[1][0];
                }
                if(Math.abs(R[2][0]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[2][0]+R[2][1])/val;
                    R[2][0]=R[2][1];                                     
                }
                if(Math.abs(G[2][0]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[2][0]+G[2][1])/val;
                    G[2][0]=G[2][1];
                }
                if(Math.abs(B[2][0]-B[2][1])<=Btolerance){
                    B[2][1]=(B[2][0]+B[2][1])/val;
                    B[2][0]=B[2][1];
                }
                
                //-------------------------------
                if(Math.abs(R[2][2]-R[1][2])<=Rtolerance){
                    R[1][2]=(R[2][2]+R[1][2])/val;       
                    R[2][2]=R[1][2];   
                }
                if( Math.abs(G[2][2]-G[1][2])<=Gtolerance){
                    G[1][2]=(G[2][2]+G[1][2])/val;
                    G[2][2]=G[1][2];
                }
                if(Math.abs(B[2][2]-B[1][2])<=Btolerance){
                    B[1][2]=(B[2][2]+B[1][2])/val;
                    B[2][2]=B[1][2];
                }

                if(Math.abs(R[2][2]-R[2][1])<=Rtolerance){
                    R[2][1]=(R[2][2]+R[2][1])/val;              
                    R[2][2]=R[2][1];
                }
                if(Math.abs(G[2][2]-G[2][1])<=Gtolerance){
                    G[2][1]=(G[2][2]+G[2][1])/val;
                    G[2][2]=G[2][1];
                }
                if(Math.abs(B[2][2]-B[2][1])<=Btolerance){
                    B[2][1]=(B[2][2]+B[2][1])/val;
                    B[2][2]=B[2][1];
                }
                    
                /*for(int yy=-1 ;yy<2; yy++){
                    for(int xx=-1 ;xx<2; xx++){
                        if(x+xx>=0 && x+xx<img.getWidth() && y+yy>=0 && y+yy<img.getHeight()){
                            Red[x+xx][y+yy] = R[1+xx][1+yy];
                            Green[x+xx][y+yy] = G[1+xx][1+yy];
                            Blue[x+xx][y+yy] = B[1+xx][1+yy];
                        }
                        
                    }
                }*/  
                
                Red[x][y] = R[1][1];
                Green[x][y] = G[1][1];
                Blue[x][y] = B[1][1];
            }
        }
        RGBbirlestir(Red,Green,Blue);
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in DetectionDelete Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception DetectionDelete Function.");}
    }
    
    public void detectionDark(int Rtolerance,int Gtolerance,int Btolerance,int xST,int yST,int xFT,int yFT){  
         
        try {
        
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
                Red1[x][y]=Red[x][y];
                Green1[x][y]=Green[x][y];
                Blue1[x][y]=Blue[x][y];
            }
        }
       
        
        int[][] R1 = new int[3][3];
        int[][] G2 = new int[3][3];
        int[][] B1 = new int[3][3];  
        
        int val=3;
        
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
               
                if(x-1>=0 && y-1>=0){                    
                    R1[0][0]=Red1[x-1][y-1];
                    G2[0][0]=Green1[x-1][y-1];
                    B1[0][0]=Blue1[x-1][y-1]; 
                }
                if(x>=0 && y-1>=0){                    
                    R1[0][1]=Red1[x][y-1];
                    G2[0][1]=Green1[x][y-1];
                    B1[0][1]=Blue1[x][y-1]; 
                }
                if(x+1<img.getWidth() && y-1>=0){                    
                    R1[0][2]=Red1[x+1][y-1];
                    G2[0][2]=Green1[x+1][y-1];
                    B1[0][2]=Blue1[x+1][y-1]; 
                }
                if(x-1>=0 && y>=0){                    
                    R1[1][0]=Red1[x-1][y];
                    G2[1][0]=Green1[x-1][y];
                    B1[1][0]=Blue1[x-1][y]; 
                }
                if(x>=0 && y>=0){                    
                    R1[1][1]=Red1[x][y];
                    G2[1][1]=Green1[x][y];
                    B1[1][1]=Blue1[x][y]; 
                }               
                if(x+1<img.getWidth() && y>=0){                    
                    R1[1][2]=Red1[x+1][y];
                    G2[1][2]=Green1[x+1][y];
                    B1[1][2]=Blue1[x+1][y];  
                }
                if(x-1>=0 && y+1<img.getHeight()){                    
                    R1[2][0]=Red1[x-1][y+1];
                    G2[2][0]=Green1[x-1][y+1];
                    B1[2][0]=Blue1[x-1][y+1];  
                }
                if(x>=0 && y+1<img.getHeight()){                    
                    R1[2][1]=Red1[x][y+1];
                    G2[2][1]=Green1[x][y+1];
                    B1[2][1]=Blue1[x][y+1];  
                }
                if(x+1<img.getWidth() && y+1<img.getHeight()){  
                    R1[2][2]=Red1[x+1][y+1];
                    G2[2][2]=Green1[x+1][y+1];
                    B1[2][2]=Blue1[x+1][y+1];                 
                }
                
               
                //-----------------------------------------------------
                
               if(Math.abs(R1[1][1]-R1[0][0])<=Rtolerance){                           
                    R1[0][0]=0;
                    R1[1][1]=R1[0][0];      
                }
                if(Math.abs(G2[1][1]-G2[0][0])<=Gtolerance){
                    G2[0][0]=0;
                    G2[1][1]=G2[0][0];
                }
                if(Math.abs(B1[1][1]-B1[0][0])<=Btolerance){
                    B1[0][0]=0;
                    B1[1][1]=B1[0][0];
                }
                
                if(Math.abs(R1[1][1]-R1[0][1])<=Rtolerance){
                    R1[0][1]=0;
                    R1[1][1]=R1[0][1];
                }
                if(Math.abs(G2[1][1]-G2[0][1])<=Gtolerance){
                    G2[0][1]=0;
                    G2[1][1]=G2[0][1];
                }
                if(Math.abs(B1[1][1]-B1[0][1])<=Btolerance){
                    B1[0][1]=0;
                    B1[1][1]=B1[0][1];
                }

                if(Math.abs(R1[1][1]-R1[0][2])<=Rtolerance){
                    R1[0][2]=0;
                    R1[1][1]=R1[0][2];
                }
                if(Math.abs(G2[1][1]-G2[0][2])<=Gtolerance){
                    G2[0][2]=0;
                    G2[1][1]=G2[0][2];
                }                
                if(Math.abs(B1[1][1]-B1[0][2])<=Btolerance){
                    B1[0][2]=0;
                    B1[1][1]=B1[0][2];
                }
                
                if(Math.abs(R1[1][1]-R1[1][0])<=Rtolerance){
                    R1[1][0]=0;
                    R1[1][1]=R1[1][0];
                }
                if(Math.abs(G2[1][1]-G2[1][0])<=Gtolerance){
                    G2[1][0]=0;
                    G2[1][1]=G2[1][0];
                }
                if(Math.abs(B1[1][1]-B1[1][0])<=Btolerance){
                    B1[1][0]=0;
                    B1[1][1]=B1[1][0];
                }
                
                if(Math.abs(R1[1][1]-R1[1][2])<=Rtolerance){
                    R1[1][2]=0;
                    R1[1][1]=R1[1][2];
                }
                if(Math.abs(G2[1][1]-G2[1][2])<=Gtolerance){
                    G2[1][2]=0;
                    G2[1][1]=G2[1][2];
                }
                if(Math.abs(B1[1][1]-B1[1][2])<=Btolerance){
                    B1[1][2]=0;
                    B1[1][1]=B1[1][2];
                }

                if(Math.abs(R1[1][1]-R1[2][0])<=Rtolerance){
                    R1[2][0]=0;
                    R1[1][1]=R1[2][0]; 
                }
                if(Math.abs(G2[1][1]-G2[2][0])<=Gtolerance){
                    G2[2][0]=0;
                    G2[1][1]=G2[2][0];
                }
                if(Math.abs(B1[1][1]-B1[2][0])<=Btolerance){
                    B1[2][0]=0;
                    B1[1][1]=B1[2][0];
                }

                if(Math.abs(R1[1][1]-R1[2][1])<=Rtolerance){
                    R1[2][1]=0;
                    R1[1][1]=R1[2][1];
                }
                if(Math.abs(G2[1][1]-G2[2][1])<=Gtolerance){
                    G2[2][1]=0;
                    G2[1][1]=G2[2][1];
                }
                if(Math.abs(B1[1][1]-B1[2][1])<=Btolerance){
                    B1[2][1]=0;
                    B1[1][1]=B1[2][1];
                }

                if(Math.abs(R1[1][1]-R1[2][2])<=Rtolerance){
                    R1[2][2]=0;
                    R1[1][1]=R1[2][2];
                }
                if(Math.abs(G2[1][1]-G2[2][2])<=Gtolerance){
                    G2[2][2]=0;
                    G2[1][1]=G2[2][2];
                }
                if(Math.abs(B1[1][1]-B1[2][2])<=Btolerance){
                    B1[2][2]=0;
                    B1[1][1]=B1[2][2];
                }
                //-----------------------
                
                if(Math.abs(R1[0][0]-R1[0][1])<=Rtolerance){
                    R1[0][1]=0;              
                    R1[0][0]=R1[0][1];      
                }             
                if(Math.abs(G2[0][0]-G2[0][1])<=Gtolerance){
                    G2[0][1]=0;
                    G2[0][0]=G2[0][1];

                }
                if(Math.abs(B1[0][0]-B1[0][1])<=Btolerance){
                    B1[0][1]=0;
                    B1[0][0]=B1[0][1];
                }
                if(Math.abs(R1[0][0]-R1[1][0])<=Rtolerance){
                    R1[1][0]=0; 
                    R1[0][0]=R1[1][0];
                }
                if(Math.abs(G2[0][0]-G2[1][0])<=Gtolerance){
                    G2[1][0]=0;
                    G2[0][0]=G2[1][0];
                }
                if(Math.abs(B1[0][0]-B1[1][0])<=Btolerance){
                    B1[1][0]=0;
                    B1[0][0]=B1[1][0];
                }              
 //-----------------------
                if(Math.abs(R1[0][2]-R1[0][1])<=Rtolerance){
                    R1[0][1]=0;       
                    R1[0][2]=R1[0][1];  
                }
                if(Math.abs(G2[0][2]-G2[0][1])<=Gtolerance){
                    G2[0][1]=0;
                    G2[0][2]=G2[0][1];
                }
                if(Math.abs(B1[0][2]-B1[0][1])<=Btolerance){
                    B1[0][1]=0;
                    B1[0][2]=B1[0][1];
                }
                if(Math.abs(R1[0][2]-R1[1][2])<=Rtolerance){
                    R1[1][2]=0;                  
                    R1[0][2]=R1[1][2];  
                }                
                if(Math.abs(G2[0][2]-G2[1][2])<=Gtolerance){
                    G2[1][2]=0;
                    G2[0][2]=G2[1][2];
                }
                if(Math.abs(B1[0][2]-B1[1][2])<=Btolerance){
                    B1[1][2]=0;
                    B1[0][2]=B1[1][2];
                }
                //---------------------------------
                if(Math.abs(R1[2][0]-R1[1][0])<=Rtolerance){
                    R1[1][0]=0;                
                    R1[2][0]=R1[1][0];                                 
                }
                if(Math.abs(G2[2][0]-G2[1][0])<=Gtolerance){
                    G2[1][0]=0;
                    G2[2][0]=G2[1][0];
                }
                if( Math.abs(B1[2][0]-B1[1][0])<=Btolerance){
                    B1[1][0]=0;
                    B1[2][0]=B1[1][0];
                }
                if(Math.abs(R1[2][0]-R1[2][1])<=Rtolerance){
                    R1[2][1]=0;
                    R1[2][0]=R1[2][1];                                     
                }
                if(Math.abs(G2[2][0]-G2[2][1])<=Gtolerance){
                    G2[2][1]=0;
                    G2[2][0]=G2[2][1];
                }
                if(Math.abs(B1[2][0]-B1[2][1])<=Btolerance){
                    B1[2][1]=0;
                    B1[2][0]=B1[2][1];
                }
                
                //-------------------------------
                if(Math.abs(R1[2][2]-R1[1][2])<=Rtolerance){
                    R1[1][2]=0;       
                    R1[2][2]=R1[1][2];   
                }
                if( Math.abs(G2[2][2]-G2[1][2])<=Gtolerance){
                    G2[1][2]=0;
                    G2[2][2]=G2[1][2];
                }
                if(Math.abs(B1[2][2]-B1[1][2])<=Btolerance){
                    B1[1][2]=0;
                    B1[2][2]=B1[1][2];
                }

                if(Math.abs(R1[2][2]-R1[2][1])<=Rtolerance){
                    R1[2][1]=0;              
                    R1[2][2]=R1[2][1];
                }
                if(Math.abs(G2[2][2]-G2[2][1])<=Gtolerance){
                    G2[2][1]=0;
                    G2[2][2]=G2[2][1];
                }
                if(Math.abs(B1[2][2]-B1[2][1])<=Btolerance){
                    B1[2][1]=0;
                    B1[2][2]=B1[2][1];
                }
                    
                /*for(int yy=-1 ;yy<2; yy++){
                    for(int xx=-1 ;xx<2; xx++){
                        if(x+xx>=0 && x+xx<img.getWidth() && y+yy>=0 && y+yy<img.getHeight()){
                            Red[x+xx][y+yy] = R[1+xx][1+yy];
                            Green[x+xx][y+yy] = G[1+xx][1+yy];
                            Blue[x+xx][y+yy] = B[1+xx][1+yy];
                        }
                        
                    }
                }*/  
                
                Red1[x][y] = R1[1][1];
                Green1[x][y] = G2[1][1];
                Blue1[x][y] = B1[1][1];
            }
        }
         
        BufferedImage img10 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                Color myWhite = new Color(Red1[x][y], Green1[x][y], Blue1[x][y]);
                int rgb = myWhite.getRGB();
                img10.setRGB(x,y,rgb);  
            }
        }
        Image a= new ImageIcon(img10).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel6.setIcon(new ImageIcon(a));
        
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in DetectionDark Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception DetectionDark Function.");}
        catch(IllegalArgumentException e){System.out.println("Neither width nor height can be zero (DetectionDark Function).");}
        
    }
    
    public void pixelation(int xST,int yST,int xFT,int yFT){
        
        try{
        int filtre[][]=new int[3][3];
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        
        for (int y = yST+1; y < yFT; y+=3) {            
            for (int x = xST+1; x <xFT; x+=3) {
               
                if(x-1>=0 && y-1>=0){                    
                    Color myWhite = new Color(Red[x-1][y-1],Green[x-1][y-1],  Blue[x-1][y-1]);
                    filtre[0][0] = myWhite.getRGB();
                }
                if(x>=0 && y-1>=0){                    
                    Color myWhite = new Color(Red[x][y-1],Green[x][y-1],  Blue[x][y-1]);
                    filtre[0][1] = myWhite.getRGB();
                }
                if(x+1<img.getWidth() && y-1>=0){                    
                    Color myWhite = new Color(Red[x+1][y-1],Green[x+1][y-1],  Blue[x+1][y-1]);
                    filtre[0][2] = myWhite.getRGB();
                }
                if(x-1>=0 && y>=0){                    
                    Color myWhite = new Color(Red[x-1][y],Green[x-1][y],  Blue[x-1][y]);
                    filtre[1][0] = myWhite.getRGB();
                }
                if(x>=0 && y>=0){                    
                    Color myWhite = new Color(Red[x][y],Green[x][y],  Blue[x][y]);
                    filtre[1][1] = myWhite.getRGB();
                }               
                if(x+1<img.getWidth() && y>=0){                    
                    Color myWhite = new Color(Red[x+1][y],Green[x+1][y],  Blue[x+1][y]);
                    filtre[1][2] = myWhite.getRGB();
                }
                if(x-1>=0 && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x-1][y+1],Green[x-1][y+1],  Blue[x-1][y+1]);
                    filtre[2][0] = myWhite.getRGB();
                }
                if(x>=0 && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x][y+1],Green[x][y+1],  Blue[x][y+1]);
                    filtre[2][1] = myWhite.getRGB();
                }
                if(x+1<img.getWidth() && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x+1][y+1],Green[x+1][y+1],  Blue[x+1][y+1]);
                    filtre[2][2] = myWhite.getRGB();
                }
                
                
                
                sumR=0;
                sumG=0;
                sumB=0;
                
                for (int[] arr : filtre){
                   for(int i: arr){
                       
                       int r=(i >> 16) & 0xFF;
                       int g=(i >> 8) & 0xFF;
                       int b = i & 0xFF;
                       
                       sumR+=r;
                       sumG+=g;
                       sumB+=b;
                   }
                }
                
                sumR=sumR/9;
                sumG=sumG/9;
                sumB=sumB/9;
                
                //int rgb = sum1/9;
                
                for(int yy=-1 ;yy<2; yy++){
                    for(int xx=-1 ;xx<2; xx++){
                        
                        Red[x+xx][y+yy] = sumR;
                        Green[x+xx][y+yy] = sumG;
                        Blue[x+xx][y+yy] = sumB;
                    }
                }
                
                //Red[x][y] = (rgb >> 16) & 0xFF;
                //Green[x][y] = (rgb >> 8) & 0xFF;
                //Blue[x][y] = rgb & 0xFF;
                               
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in pixelation Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception pixelation Function.");}
    }
    
    public void blurring(int xST,int yST,int xFT,int yFT){
        
        try{
        int filtre[][]=new int[3][3];
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
               
                if(x-1>=0 && y-1>=0){                    
                    Color myWhite = new Color(Red[x-1][y-1],Green[x-1][y-1],  Blue[x-1][y-1]);
                    filtre[0][0] = myWhite.getRGB();
                }
                if(x>=0 && y-1>=0){                    
                    Color myWhite = new Color(Red[x][y-1],Green[x][y-1],  Blue[x][y-1]);
                    filtre[0][1] = myWhite.getRGB();
                }
                if(x+1<img.getWidth() && y-1>=0){                    
                    Color myWhite = new Color(Red[x+1][y-1],Green[x+1][y-1],  Blue[x+1][y-1]);
                    filtre[0][2] = myWhite.getRGB();
                }
                if(x-1>=0 && y>=0){                    
                    Color myWhite = new Color(Red[x-1][y],Green[x-1][y],  Blue[x-1][y]);
                    filtre[1][0] = myWhite.getRGB();
                }
                 if(x+1<img.getWidth() && y>=0){                    
                    Color myWhite = new Color(Red[x+1][y],Green[x+1][y],  Blue[x+1][y]);
                    filtre[1][2] = myWhite.getRGB();
                }
                if(x-1>=0 && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x-1][y+1],Green[x-1][y+1],  Blue[x-1][y+1]);
                    filtre[2][0] = myWhite.getRGB();
                }
                if(x>=0 && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x][y+1],Green[x][y+1],  Blue[x][y+1]);
                    filtre[2][1] = myWhite.getRGB();
                }
                if(x+1<img.getWidth() && y+1<img.getHeight()){                    
                    Color myWhite = new Color(Red[x+1][y+1],Green[x+1][y+1],  Blue[x+1][y+1]);
                    filtre[2][2] = myWhite.getRGB();
                }
                
                 
                sumR=0;
                sumG=0;
                sumB=0;
                
                for (int[] arr : filtre){
                   for(int i: arr){
                       
                       int r=(i >> 16) & 0xFF;
                       int g=(i >> 8) & 0xFF;
                       int b = i & 0xFF;
                       
                       sumR+=r;
                       sumG+=g;
                       sumB+=b;
                   }
                }
                
                sumR=sumR/8;
                sumG=sumG/8;
                sumB=sumB/8;
                
                Red[x][y] = sumR;
                Green[x][y] = sumG;
                Blue[x][y] = sumB;
                               
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in blurring Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in blurring Function.");}
        
    }
    
    public void yesilFitre(int a,int xST,int yST,int xFT,int yFT){
        try{
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
                //System.out.print(" ,"+Red[x][y]);
                Green[x][y]=Green[x][y]+a;
                if(Green[x][y]<0){
                    Green[x][y]=0;
                }
                if(Green[x][y]>255){
                    Green[x][y]=255;
                }
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in yesilFitre Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in yesilFitre Function.");}
        
    }
    
    public void kirmiziFitre(int a,int xST,int yST,int xFT,int yFT){
        try{
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
                //System.out.print(" ,"+Red[x][y]);
                Red[x][y]=Red[x][y]+a;
                
                if(Red[x][y]<0){
                    Red[x][y]=0;
                }
                if(Red[x][y]>255){
                    Red[x][y]=255;
                }
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in kirmiziFitre Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in kirmiziFitre Function.");}
    }
    
    public void maviFitre(int a,int xST,int yST,int xFT,int yFT){
        try{
        for (int y = yST; y < yFT; y++) {            
            for (int x = xST; x <xFT; x++) {
                //System.out.print(" ,"+Red[x][y]);
                Blue[x][y]=Blue[x][y]+a;
                if(Blue[x][y]<0){
                    Blue[x][y]=0;
                }
                if(Blue[x][y]>255){
                    Blue[x][y]=255;
                }
            }
        }
        }
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in maviFitre Function.");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in maviFitre Function.");}
    }
    
    public void Ayır(javax.swing.JLabel jLabel1){
        
        try{
        Icon ico = jLabel1.getIcon();
        img = new BufferedImage(ico.getIconWidth(), ico.getIconHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics g = img.createGraphics();
        ico.paintIcon(null, g, 0, 0);
        g.dispose();
        
        
        Image a= new ImageIcon(img).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel1.setIcon(new ImageIcon(a));
        
        
        
        Red = new int[img.getWidth()][img.getHeight()];
        Blue = new int[img.getWidth()][img.getHeight()];
        Green = new int[img.getWidth()][img.getHeight()];
        
        
        //Ayır
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                int pixel = img.getRGB(x,y);

                Color color = new Color(pixel, true);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                
                
                Red[x][y]=red;
                Green[x][y]=green;
                Blue[x][y]=blue;
            }
        }
        
        }
        catch(NullPointerException e){System.out.println("There isn't any image in NewJFrame Form(Icon Object Fault, Ayır Function).");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in Ayır Function.");}
        catch(IllegalArgumentException  e){System.out.println("Pixel Fault due to BufferedImage Object in Ayır Function");}
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in Ayır Function.");}
    }
    
    public void RGBbirlestir(int Red[][],int Green[][],int Blue[][]){
        try{
        BufferedImage img1 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        BufferedImage img2 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        BufferedImage img3 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < Red[0].length; y++) {
            for (int x = 0; x < Red.length; x++) {

                Color red = new Color(Red[x][y], 0,0);
                int r = red.getRGB();
                   
                Color green = new Color(0, Green[x][y],0);
                int g = green.getRGB();
                
                Color blue = new Color(0, 0,Blue[x][y]);
                int b = blue.getRGB();
                
                img1.setRGB(x,y,r);  
                img2.setRGB(x,y,g);  
                img3.setRGB(x,y,b);  
            }
        }
        Image a= new ImageIcon(img1).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel3.setIcon(new ImageIcon(a));
        
        Image b= new ImageIcon(img2).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel4.setIcon(new ImageIcon(b));
        
        Image c= new ImageIcon(img3).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel5.setIcon(new ImageIcon(c));
        }
        catch(NullPointerException e){System.out.println("There isn't any image in NewJFrame Form(Icon Object Fault, RGBbirlestir Function).");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in RGBbirlestir Function.");}
        catch(IllegalArgumentException  e){System.out.println("Pixel Fault due to BufferedImage Object in RGBbirlestir Function / Neither width nor height can be zero (RGBbirlestir Function)");}
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in RGBbirlestir Function.");}
        
    }
     
    public void Birleştir(javax.swing.JLabel jLabel1){
        try{
        BufferedImage img1 = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        for (int y = 0; y < Red[0].length; y++) {
            for (int x = 0; x < Red.length; x++) {

                Color myWhite = new Color(Red[x][y], Green[x][y], Blue[x][y]);
                int rgb = myWhite.getRGB();

                
                img1.setRGB(x,y,rgb);  
            }
        }
        Image a= new ImageIcon(img1).getImage().getScaledInstance(300, 300, Image.SCALE_FAST);
        jLabel1.setIcon(new ImageIcon(a));
        }
        
        catch(NullPointerException e){System.out.println("There isn't any image in NewJFrame Form(Icon Object Fault, Birlestir Function).");}
        catch(ArithmeticException e){System.out.println("Arithmetic Exception in RGBbirlestir Function.");}
        catch(IllegalArgumentException  e){System.out.println("Pixel Fault due to BufferedImage Object in Birlestir Function / Neither width nor height can be zero (Birlestir Function)");}
        catch(ArrayIndexOutOfBoundsException e){System.out.println("Out Of Bounds Exception in Birlestir Function.");}
    }
    

      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jSlider2 = new javax.swing.JSlider();
        jSlider3 = new javax.swing.JSlider();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        jSlider4 = new javax.swing.JSlider();
        jSlider5 = new javax.swing.JSlider();
        jSlider6 = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1300, 700));
        setMinimumSize(new java.awt.Dimension(1300, 700));
        setPreferredSize(new java.awt.Dimension(1300, 700));
        setResizable(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/filtre/2.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(112, 112));

        jSlider1.setMaximum(255);
        jSlider1.setValue(0);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jSlider2.setMaximum(255);
        jSlider2.setValue(0);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jSlider3.setMajorTickSpacing(2);
        jSlider3.setMaximum(255);
        jSlider3.setMinorTickSpacing(10);
        jSlider3.setToolTipText("");
        jSlider3.setValue(0);
        jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider3StateChanged(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner5StateChanged(evt);
            }
        });

        jSlider4.setMajorTickSpacing(2);
        jSlider4.setMaximum(255);
        jSlider4.setMinimum(-255);
        jSlider4.setMinorTickSpacing(10);
        jSlider4.setToolTipText("");
        jSlider4.setValue(0);
        jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider4StateChanged(evt);
            }
        });

        jSlider5.setMajorTickSpacing(2);
        jSlider5.setMaximum(255);
        jSlider5.setMinimum(-255);
        jSlider5.setMinorTickSpacing(10);
        jSlider5.setToolTipText("");
        jSlider5.setValue(0);
        jSlider5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider5StateChanged(evt);
            }
        });

        jSlider6.setMajorTickSpacing(2);
        jSlider6.setMaximum(255);
        jSlider6.setMinimum(-255);
        jSlider6.setMinorTickSpacing(10);
        jSlider6.setToolTipText("");
        jSlider6.setValue(0);
        jSlider6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider6StateChanged(evt);
            }
        });

        jLabel7.setText("Red");

        jLabel8.setText("Green");

        jLabel9.setText("Blue");

        jLabel10.setText("Details Finder");

        jLabel11.setText("Softness");

        jLabel12.setText("Darkness");

        jLabel13.setText("Pixelation");

        jLabel14.setText("Blurring");

        jButton1.setText("Upload Image");
        jButton1.setPreferredSize(new java.awt.Dimension(120, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jLabel15.setText("Thread Number:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSpinner3, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(14, 14, 14))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel11))
                                                .addGap(38, 38, 38)))
                                        .addGap(54, 54, 54)
                                        .addComponent(jLabel15)
                                        .addGap(49, 49, 49))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(8, 8, 8)))
                                        .addGap(51, 51, 51))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8)))))
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSlider3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSlider4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jSlider5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jSlider6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider2StateChanged

    private void jSlider3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider3StateChanged
      ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider3StateChanged

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
       ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
      ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
      ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSpinner3StateChanged

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
       ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSpinner4StateChanged

    private void jSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner5StateChanged
        ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSpinner5StateChanged

    private void jSlider4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider4StateChanged
       ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider4StateChanged

    private void jSlider5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider5StateChanged
       ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider5StateChanged

    private void jSlider6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider6StateChanged
      ThreadRun((Integer)jSpinner6.getValue(),img.getHeight(),img.getWidth(),jSlider1.getValue(),jSlider2.getValue(),jSlider3.getValue(),jSlider4.getValue(),jSlider5.getValue(),jSlider6.getValue(),
                (Integer)jSpinner1.getValue(),(Integer)jSpinner2.getValue(),(Integer)jSpinner3.getValue(),
                (Integer)jSpinner4.getValue(),(Integer)jSpinner5.getValue());
    }//GEN-LAST:event_jSlider6StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();                 //Dosya çekme
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, jpeg, gif, png","jpg","jpeg","gif","png");
        fileChooser.setFileFilter(filter);
        int selected = fileChooser.showOpenDialog(null);
        
        if(selected == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            String getSelectedImage = file.getAbsolutePath();
            JOptionPane.showMessageDialog(null, getSelectedImage);
            ImageIcon imIco = new ImageIcon(getSelectedImage);
            
            //image to fit in the jlabel
            Image imFit = imIco.getImage();
            Image imgFit = imFit.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
            jLabel1.setIcon(new ImageIcon(imFit));
            
            Ayır(jLabel1);
            RGBbirlestir(Red,Green,Blue);
            Birleştir(jLabel2);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JSlider jSlider3;
    private javax.swing.JSlider jSlider4;
    private javax.swing.JSlider jSlider5;
    private javax.swing.JSlider jSlider6;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    // End of variables declaration//GEN-END:variables
}
