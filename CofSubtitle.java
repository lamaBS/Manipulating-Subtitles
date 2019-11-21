public class CofSubtitle implements Subtitle{
   private Time startTime;
   private Time endTime;
   private String Text;
   
   public CofSubtitle(String time,String text){
	   String st=time.substring(0,12);
	String et=time.substring(17);
      startTime= new StartTime(st);
      endTime= new EndTime(et);
      Text=text;}
  
   public Time getStartTime(){
      return startTime;}
  
   public Time getEndTime(){
      return endTime;}
      
   public String getText(){
      return Text;}
      
   public void setStartTime(Time StartTime){
     startTime=StartTime;}
      
   public void setEndTime(Time EndTime){
      endTime=EndTime;}
      
   public void setText(String text){
      Text=text;}

public String toString(){
	return(Text+"\n"+(startTime.toString())+"\n"+(endTime.toString()));
	}
}