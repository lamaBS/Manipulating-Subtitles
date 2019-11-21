import java.io.*;
import java.util.*;
public class SubtitleSeqFactory {
   static int seq=0, preseq=0;
   static double start=0,end=0,pre=0;
   public static String unknown;
   private static Scanner input;
  public SubtitleSeq SL;
   static Scanner read=new Scanner(System.in);
	// Return an empty subtitles sequence 
   public static SubtitleSeq getSubtitleSeq() {
	    SubtitleSeq SL=new SeqofSubtitle();
      return SL;}

	// Load a subtitle sequence from an SRT file. If the file does not exist or
	// is corrupted (incorrect format), null is returned.

   public static SubtitleSeq loadSubtitleSeq(String fileName) throws IOException, FileNotFoundException {
	    SubtitleSeq SS=getSubtitleSeq();
      String txt, Tm;
      Subtitle sb;
      File F=new File(fileName);
   
      if(!(F.exists())){
         return null;}
   	
      input = new Scanner(F);
      while (input.hasNext()){
         seq=Integer.parseInt(input.nextLine());
         Tm=input.nextLine();
         txt=input.nextLine();
         if(input.hasNext()){
            unknown=input.nextLine();
            while(!unknown.equals("")){
               txt=  txt.concat("\n"+unknown);
               if(input.hasNext())
                  unknown=input.nextLine();
               else{  
                  break;}
            }}
         sb=new CofSubtitle(Tm,txt);
         if(seq!=preseq+1){
            return null;}
         else{preseq=seq;}
         start=calculate(sb.getStartTime());
         end=calculate(sb.getEndTime());
        if(!(0<=start&&start<end&&start>=pre)){
            return null;}
         else{pre=end;
         }
         SS.addSubtitle(sb);}
      input.close();
      return SS;
   }
   public static int calculate (Time t){
      int h,m,s,ms;
      h=t.getHH()*3600000;
      m=t.getMM()*60000;
      s=t.getSS()*1000;
      ms=t.getMS();
      int total=h+m+s+ms;
      return total;}
		
}
