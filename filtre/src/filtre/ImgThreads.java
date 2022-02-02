/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filtre;


public class ImgThreads extends Thread{
    int a, b, c, d, f, g, q, w, e, r, t, dsx, dsy, dfx, dfy;
    
    ImgThreads(int a,int b,int c,int d,int f,int g,int q,int w,int e,int r,int t,int dsx,int dsy,int dfx,int dfy){
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.f=f;
        this.g=g;
        this.q=q;
        this.w=w;
        this.e=e;
        this.r=r;
        this.t=t;
        this.dsx=dsx;
        this.dsy=dsy;
        this.dfx=dfx;
        this.dfy=dfy;   
    }
    
    public void run() {
     
        Filtre.f1.fonk(a, b, c, d, f, g, q, w, e, r, t, dsx, dsy, dfx, dfy);
       
    }
}
