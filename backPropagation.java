/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package abppn;
 
import java.math.BigDecimal;
import java.text.DecimalFormat;
 
/**
 *
 * @author x86
 */
public class backPropagation {
    // kondisi
    double x[][];
    // target
    double t[];
    
    int unit_input;
    int unit_hidden;
    int unit_output;
    
    // learning rate
    double alfa;
    // maximum loop
    int maxloop;
    // target error
    double ERR;
    // last error
    double ERX;
    
    // bobot input - hidden
    double v[][];
    // bobot bias - hidden 
    double v0[];
    // bobot hidden - ouput
    double w[][];
    // bobot bias - output
    double w0[];
    
    double MSE;
    
    int loop;
    double hasil_mentah;
    double hasil_akhir;
    
    void init_static(){
        //double init_x[][] = {{0.5,1},{1,0.5},{1,1},{0.5,0.5}};
        //double init_t[] = {1,1,0.5,0.5};
        //double init_x[][] = {{-1,0,0,0},{-1,0,0,1},{-1,0,1,0},{-1,0,1,1},{-1,1,0,0},{-1,1,0,1},{-1,1,1,0},{-1,1,1,1},{ 0,0,0,0},{ 0,0,0,1},{ 0,0,1,0},{ 0,0,1,1},{ 0,1,0,0},{ 0,1,0,1},{ 0,1,1,0},{ 0,1,1,1},{ 1,0,0,0},{ 1,0,0,1},{ 1,0,1,0},{ 1,0,1,1},{ 1,1,0,0},{ 1,1,0,1},{ 1,1,1,0},{ 1,1,1,1}};
        //double init_t[] = {1, 0, 1,-1, 1, 0, 1,-1, 1, 0, 1,-1, 1, 0, 1, 1, 1, 1, 1,-1, 1, 0, 1, 1};
        double init_x[][] = {{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},{0.5,0,0,0},{0.5,0,0,1},{0.5,0,1,0},{0.5,0,1,1},{0.5,1,0,0},{0.5,1,0,1},{0.5,1,1,0},{0.5,1,1,1},{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
        double init_t[] = {1,0.5,1,0,1,0.5,1,0,1,0.5,1,0,1,0.5,1,1,1,1,1,0,1,0.5,1,1};
        int init_uinput = init_x[0].length;
        int init_uhidden = 4;
        int init_uoutput = 1;
        double init_alfa = 1;
        int init_maxloop = 10000;
        double init_ERR = 0.01;
        //double init_v[][] = {{0.9562,0.7762,0.1623,0.2886},{0.1962,0.6133,0.0311,0.9711}};
        double init_v[][] = new double[init_uinput][init_uhidden];
        for(int a=0; a<init_v.length; a++){
            for(int b=0; b<init_v[0].length; b++){
                init_v[a][b] = Math.random();
            }
        }
        //double init_v0[] = {0.7496,0.3796,0.7256,0.1628};
        double init_v0[] = new double[init_uhidden];
        for(int a=0; a<init_v0.length; a++){
            init_v0[a] = Math.random();
        }
        //double init_w[][] = {{0.2280,0.9585,0.6799,0.0550}};
        double init_w[][] = new double[init_uoutput][init_uhidden];
        for(int a=0; a<init_w.length; a++){
            for(int b=0; b<init_w[0].length; b++){
                init_w[a][b] = Math.random();
            }
        }
        //double init_w0[] = {0.9505};
        double init_w0[] = new double[init_uoutput];
        for(int a=0; a<init_w0.length; a++){
            init_w0[a] = Math.random();
        }
        
        this.x = init_x;
        this.t = init_t;
        this.unit_input = init_uinput;
        this.unit_hidden = init_uhidden;
        this.unit_output = init_uoutput;
        this.alfa = init_alfa;
        this.maxloop = init_maxloop;
        this.ERR = init_ERR;
        this.v = init_v;
        this.v0 = init_v0;
        this.w = init_w;
        this.w0 = init_w0;
    }
    
    void init_dinamic(){
        double init_x[][] = {{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},{0.5,0,0,0},{0.5,0,0,1},{0.5,0,1,0},{0.5,0,1,1},{0.5,1,0,0},{0.5,1,0,1},{0.5,1,1,0},{0.5,1,1,1},{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
        double init_t[] = {1,0.5,1,0,1,0.5,1,0,1,0.5,1,0,1,0.5,1,1,1,1,1,0,1,0.5,1,1};
        int init_uinput = init_x[0].length;
        //int init_uhidden = 4;
        int init_uoutput = 1;
        //double init_alfa = 1;
        //int init_maxloop = 10000;
        //double init_ERR = 0.01;
        //double init_v[][] = {{0.9562,0.7762,0.1623,0.2886},{0.1962,0.6133,0.0311,0.9711}};
        
        this.x = init_x;
        this.t = init_t;
        this.unit_input = init_uinput;
        //this.unit_hidden = init_uhidden;
        this.unit_output = init_uoutput;
        //this.alfa = init_alfa;
        //this.maxloop = init_maxloop;
        //this.ERR = init_ERR;
        
        
        double init_v[][] = new double[this.unit_input][this.unit_hidden];
        for(int a=0; a<init_v.length; a++){
            for(int b=0; b<init_v[0].length; b++){
                init_v[a][b] = Math.random();
            }
        }
        //double init_v0[] = {0.7496,0.3796,0.7256,0.1628};
        double init_v0[] = new double[this.unit_hidden];
        for(int a=0; a<init_v0.length; a++){
            init_v0[a] = Math.random();
        }
        //double init_w[][] = {{0.2280,0.9585,0.6799,0.0550}};
        double init_w[][] = new double[this.unit_output][this.unit_hidden];
        for(int a=0; a<init_w.length; a++){
            for(int b=0; b<init_w[0].length; b++){
                init_w[a][b] = Math.random();
            }
        }
        //double init_w0[] = {0.9505};
        double init_w0[] = new double[this.unit_output];
        for(int a=0; a<init_w0.length; a++){
            init_w0[a] = Math.random();
        }
        
        this.v = init_v;
        this.v0 = init_v0;
        this.w = init_w;
        this.w0 = init_w0;
    }
    
    void learn_static(){
        double data[][] = this.x;
        double target[] = this.t;
        int jumlah_data = data.length;
        int jumlah_input = this.unit_input;
        int jumlah_hidden = this.unit_hidden;
        int jumlah_output = this.unit_output;
        // do it for learn
        int loop = 0;
        //this.maxloop = 1000;
        //System.out.println(jumlah_hidden);
        //System.out.println(this.v0.length);
        //System.exit(0);
        
        do{
            //System.out.println("Loop : "+loop);
            //System.out.println("-----------");
            // for all data
            for(int h=0; h<jumlah_data; h++){
                // hitung z_in dan z
                double z[] = new double[jumlah_hidden];
                for(int j=0; j<jumlah_hidden; j++){
                    //itung sigma xi vij
                    double z_in[] = new double[jumlah_hidden];
                    double jum_xv=0;
                    for(int i=0; i<jumlah_input; i++){
                        double tmp=x[h][i]*v[i][j];
                        jum_xv=jum_xv+tmp;
                    }
                    z_in[j] = v0[j]+jum_xv;
                    z[j] = aktivasi(z_in[j]);
                    //z[j] = 1/(1+(double)Math.exp(-z_in[j]));
                    //z[j] = (1-(double)Math.exp(-z_in[j]))/(1+(double)Math.exp(-z_in[j]));
                  //  System.out.println("z["+j+"] = "+z[j]);
                }
                
                //~ itung y_in dan y     (output)
                double y[] = new double[jumlah_output];
                for(int k=0; k<jumlah_output; k++){
                    double y_in[] = new double[y.length];
                    double jum_zw=0;
                    for(int j=0; j<jumlah_hidden; j++){
                        double tmp=z[j]*w[k][j];
                        jum_zw=jum_zw+tmp;
                    }
                    y_in[k]=w0[k]+jum_zw;
                    y[k] = aktivasi(y_in[k]);
                    //y[k]=1/(1+(double)Math.exp(-y_in[k]));
                    //y[k]=(1-(double)Math.exp(-y_in[k]))/(1+(double)Math.exp(-y_in[k]));
                    //System.out.println("Math.exp("+-y_in[k]+") = "+Math.exp(-y_in[k]));
                    //System.out.println("y["+k+"] = "+y[k]);
                }
                
                // hitung MSE
                double sum_e = 0;
                double Err_y[] = new double[jumlah_output];
                for(int k=0; k<jumlah_output; k++){
                    //error otput
                    //Err_y[k]=(t[h]-y[k])*y[k]*(1-y[k]);
                    Err_y[k] = t[h]-y[k];
                    sum_e += Math.pow(Err_y[k],2);
                }
                
                this.MSE = 0.5*sum_e;
                
                //ngitung delta bias dan delta bobot
                double Aw[][] = new double[this.w.length][this.w[0].length];
                double Aw0[] = new double[this.w0.length];
                for(int k=0; k<jumlah_output; k++){
                    for(int j=0; j<jumlah_hidden; j++){
                        //delta bobot hO
                        Aw[k][j] = this.alfa * Err_y[k] * y[k] * z[j];
                        //delta bias hO
                        Aw0[k] = this.alfa * Err_y[k] * y[k];
                    }
                }
                
                //ngitung delta bias dan delta bobot
                double Err_in[] = new double[jumlah_hidden]; 
                double Err_z[] = new double[jumlah_hidden];
                double Av[][] = new double[this.v.length][this.v[0].length];
                double Av0[] = new double[this.v0.length];
                for(int j=0; j<jumlah_hidden; j++){
                    double tmp=0;
                    for(int k=0; k<jumlah_output; k++){
                        tmp = tmp + (Err_y[k]*this.w[k][j]);
                    }
                    // eror sebelum output / setelah hidden
                    Err_in[j]=tmp;
                    // eror hidden (t[h]-y[k])*y[k]*(1-y[k]);
                    Err_z[j]=Err_in[j]*(z[j])*(1-z[j]);
 
                    for(int i=0; i<jumlah_input; i++){
                        //delta bobot iH
                        Av[i][j]=this.alfa*Err_z[j]*this.x[h][i];
                    }
                    //delta bias  hidden
                    Av0[j]=this.alfa*Err_z[j];
                }
                
                //update bobot dan bias
                //update bobot bias outpuut
                for(int j=0; j<jumlah_hidden; j++){
                    for(int k=0; k<jumlah_output; k++){
                        this.w[k][j]=this.w[k][j]+Aw[k][j];
                        //this.w[k][j]=this.w[k][j]-Aw[k][j];
                    }
                }
                for(int k=0; k<jumlah_output; k++){
                    this.w0[k]=this.w0[k]+Aw0[k];
                    //this.w0[k]=this.w0[k]-Aw0[k];
                }
 
                //update bobot bias hidden
                for(int i=0; i<jumlah_input; i++){
                    for(int j=0; j<jumlah_hidden; j++){
                        this.v[i][j]=this.v[i][j]+Av[i][j];
                        //this.v[i][j]=this.v[i][j]-Av[i][j];
                    }
                }
                for(int j=0; j<jumlah_hidden; j++){
                    this.v0[j]=this.v0[j]+Av0[j];
                    //this.v0[j]=this.v0[j]-Av0[j];
                }
            }
            loop++;
            //System.out.println("err : "+ERX);
        }while(is_stop()>this.ERR && loop<this.maxloop);
        this.loop = loop;
        //System.out.println("err : "+this.MSE);
        //System.out.println("loop : "+loop);
    }
    
    double aktivasi(double inp){
        return 1/(1+(double)Math.exp(-inp)); 
    }
    
    //penentuan berhenti atau lanjut
    double is_stop(){
        int jumlah_input = this.unit_input;
        int jumlah_hidden = this.unit_hidden;
        int jumlah_output = this.unit_output;
        int jumlah_data = this.x.length;
        double akumY=0;
        
        //~ itung z_in dan z
        for(int h=0; h<jumlah_data; h++){
            double z[] = new double[jumlah_hidden];
            for(int j=0; j<jumlah_hidden; j++){
                //itung sigma xi vij
                double z_in[] = new double[z.length];
                double jum_xv=0;
                for(int i=0; i<jumlah_input; i++){
                    double tmp=this.x[h][i]*this.v[i][j];
                    jum_xv=jum_xv+tmp;
                }
                z_in[j]=this.v0[j]+jum_xv;
                z[j] = aktivasi(z_in[j]);
                //z[j]=1/(1+(double)Math.exp(-z_in[j]));
                //z[j]=(1-(double)Math.exp(-z_in[j]))/(1+(double)Math.exp(-z_in[j]));
                //System.out.println(-z_in[j]);
            }
 
            //~ itung y_in dan y (output)
            double y[] = new double[jumlah_output];
            for(int k=0; k<jumlah_output; k++){
                double y_in[] = new double[y.length];
                double jum_zw=0;
                for(int j=0; j<jumlah_hidden; j++){
                    double tmp=z[j]*this.w[k][j];
                    jum_zw=jum_zw+tmp;
                }
                y_in[k]=this.w0[k]+jum_zw;
                y[k] = aktivasi(y_in[k]);
                //y[k]=1/(1+(double)Math.exp(-y_in[k]));
                //y[k]=(1-(double)Math.exp(-y_in[k]))/(1+(double)Math.exp(-y_in[k]));
                //System.out.println("t[]-y = "+t);
                akumY += Math.pow((t[h]-y[k]),2);
            }
        }
        this.MSE = akumY/this.x[0].length;
        return this.MSE;
    }
    
    void test(double data[]){
        int jumlah_input = this.unit_input;
        int jumlah_hidden = this.unit_hidden;
        int jumlah_output = this.unit_output;
        double outt[] = this.t;
        
        //pada hidden
        double z[] = new double[jumlah_hidden];
        for(int j=0; j<jumlah_hidden; j++){
            double z_in[] = new double[z.length];
            double tmp = 0;
            for(int i=0; i<data.length; i++){
                tmp = tmp + (data[i] * this.v[i][j]);
            }
            z_in[j] = this.v0[j] + tmp;
            z[j] = aktivasi(z_in[j]);
            //z[j] = 1/(1+(double)Math.exp(-z_in[j]));
            //z[j] = (1-(double)Math.exp(-z_in[j]))/(1+(double)Math.exp(-z_in[j]));
        }
 
        //pada ouotpr
        double y[] = new double[jumlah_output];
        for(int k=0; k<jumlah_output; k++){
            double y_in[] = new double[y.length];
            double tmp = 0;
            for(int j=0; j<jumlah_hidden; j++){
                tmp = tmp + z[j] * this.w[k][j];
            }
            y_in[k] = this.w0[k] + tmp;
 
            y[k] = aktivasi(y_in[k]);
            //y[k] = 1/(1+(double)Math.exp(-y_in[k]));
            //y[k] = (1-(double)Math.exp(-y_in[k]))/(1+(double)Math.exp(-y_in[k]));
/*
            if(y[k]<0.1){
                y[k] = -1;
            }else if(y[k]>0.1){
                y[k] = 1;
            }else{
                y[k] = 0;
            }
            /*
            if(y[k]>0)
             y[k]=1;
            else
             y[k]=0;*/
            double err_pad = this.MSE*this.x[0].length;
            double hazil = 0;
            if(y[k] < err_pad){
                hazil = 0;
            }else if(y[k]>(1-err_pad)){
                hazil = 1;
            }else{
                hazil = 0.5;
            }
            
            this.hasil_mentah = y[k];
            this.hasil_akhir = hazil;
            //System.out.println("Output "+y[k]);
        }
    }
    
    double double_format(double num, int len){
        String format = "#.";
        for(int a=0; a<len; a++){
            format += "#";
        }
        DecimalFormat change = new DecimalFormat(format);
        return Double.valueOf(change.format(num));
    }
    
    String kesimpulan(double h){
        String x = "";
        switch(String.valueOf(h)){
            case "0.0":
                x = "Hard Contact Lenses";
                break;
            case "0.5":
                x = "Soft Contact Lenses";
                break;
            case "1.0":
                x = "No Contact Lenses";
                break;
        }
        return x;
    }
    
    public static void main(String haripinter[]){
       seleksiMahasiswa BP = new seleksiMahasiswa();
       BP.init_static();
       BP.learn_static();
       /*double ax[] = {0.5,0.5};
       double bx[] = {0.5,1};
       double cx[] = {1,0.5};
       double dx[] = {1,1};
       BP.test(ax);
       BP.test(bx);
       BP.test(cx);
       BP.test(dx);*/
       
       //double xys[][] = {{-1,0,0,0},{-1,0,0,1},{-1,0,1,0},{-1,0,1,1},{-1,1,0,0},{-1,1,0,1},{-1,1,1,0},{-1,1,1,1},{ 0,0,0,0},{ 0,0,0,1},{ 0,0,1,0},{ 0,0,1,1},{ 0,1,0,0},{ 0,1,0,1},{ 0,1,1,0},{ 0,1,1,1},{ 1,0,0,0},{ 1,0,0,1},{ 1,0,1,0},{ 1,0,1,1},{ 1,1,0,0},{ 1,1,0,1},{ 1,1,1,0},{ 1,1,1,1}};//
    
       double xys[][] = {{0,0,0,0},{0,0,0,1},{0,0,1,0},{0,0,1,1},{0,1,0,0},{0,1,0,1},{0,1,1,0},{0,1,1,1},{0.5,0,0,0},{0.5,0,0,1},{0.5,0,1,0},{0.5,0,1,1},{0.5,1,0,0},{0.5,1,0,1},{0.5,1,1,0},{0.5,1,1,1},{1,0,0,0},{1,0,0,1},{1,0,1,0},{1,0,1,1},{1,1,0,0},{1,1,0,1},{1,1,1,0},{1,1,1,1}};
       for(int a=0; a<xys.length; a++){
           double inp[] = xys[a];
           System.out.println(BP.t[a]);
           BP.test(inp);
       }
       //BigDecimal k = new BigDecimal(Math.exp(10));
       
       //System.err.println(-1-(-1));
    }
}