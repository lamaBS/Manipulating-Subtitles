

public class EndTime implements Time{
   private int HH=0;
   private int MM=0;
   private int SS=0;
   private int MS=0;
	
   public EndTime (String time){
	   HH=Integer.parseInt(time.substring(0,2));
	      MM=Integer.parseInt(time.substring(3,5));
	      SS=Integer.parseInt(time.substring(6,8)); 
	      MS=Integer.parseInt(time.substring(9));	}

   public int getHH(){
      return HH;}
   public int getMM(){ 
      return MM;}
   public int getSS(){ 
      return SS;}
   public int getMS(){ 
      return MS;}
   public void setHH(int hh){ HH=hh;}
   public void setMM(int mm){ MM=mm;}
   public void setSS(int ss){ SS=ss;}
   public void setMS(int ms){ MS=ms;}

public String toString(){
	return(HH+":"+MM+":"+SS+":"+MS);}
}
