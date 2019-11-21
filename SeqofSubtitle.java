
public class SeqofSubtitle implements SubtitleSeq{
 
   private LinkedList<Subtitle> subs=new LinkedList<Subtitle>();
 
   public void addSubtitle(Subtitle st){//1
	   if(subs.full()){return;}
      if(st.equals(null)){
         return;}
      int start=calculator(st.getStartTime());
      int end=calculator(st.getEndTime());
   
      if(end<=start){
         return;}
      int compstart=0;
      int compend=0;
      
      if(subs.empty()){//if list is empty
         subs.insert(st);
         return;
      }
      subs.findFirst();
      int size=findSize();
      compstart=calculator(subs.retrieve().getStartTime());
      compend=calculator(subs.retrieve().getEndTime());
      if(size==1){//if list only has one subtitle
         if(start>compstart&&start>compend){
            subs.insert(st);// insert after first
            return;}
         else if(start<compstart&&end<compstart){
            subs.insertAtBegin(st);// insert before first
            return;}
         else if(start==compstart&&end==compend){
            return;}
         else{
            return;}
         
      }
            
      Subtitle f=subs.retrieve();
      while(!subs.last()){// check overlaping
         compstart=calculator(subs.retrieve().getStartTime());
         compend=calculator(subs.retrieve().getEndTime());
         if(start==compstart&&end==compend){
            return;}
         if(((start<compstart)&&(end<compstart))||(start>compend)){
            subs.findNext();
         }
         else {
            return;}
      }
      subs.findFirst();
      int count=0;
      for(int i=0;i<size;i++){
         compstart=calculator(subs.retrieve().getStartTime());
         if(start>compstart){
            subs.findNext();
            count++;}
         else{
            break;}}
      subs.findFirst();
      for(int i=0;i<count-1;i++){
         subs.findNext();}
      if(subs.retrieve().equals(f)){
         if(start>compstart){
            subs.insert(st);// insert after first
            return;}
         else{
            subs.insertAtBegin(st);// insert before first
            return;}}
      else{ subs.insert(st);}//insert at last
      subs.findFirst(); 
   }

 
   public List<Subtitle> getSubtitles(){//2
      return subs;}
 

   public Subtitle getSubtitle(Time time){//3
      if(time.equals(null)){
         return null;}
      Subtitle temp=null;
      int start;
      int end;
      int equals=calculator(time); 
      if(subs.empty()){//case empty
         return null;  
      }
      int size=findSize();
   
      subs.findFirst();
      for(int i=0;i<size;i++){//case  more than one
         temp=subs.retrieve();
         start=calculator(temp.getStartTime());
         end=calculator(temp.getEndTime());
         if((start==equals)||(end==equals)||((start<equals&&end>equals))){
            return temp;
         }
         else{ subs.findNext();}
      }
      return null;
   }   
     
 
   public List<Subtitle> getSubtitles(Time startTime, Time endTime){//4

      if(startTime.equals(null)||endTime.equals(null)){
         return null;}
      if(subs.empty()){//case empty 
         return null;
      }
      LinkedList<Subtitle> temp=new LinkedList<Subtitle>();
      int size=findSize();
      int startequals=calculator(startTime);
      int endequals=calculator(endTime);
        
      if(startequals>endequals){
         return temp;}
         
      if(startequals==endequals){
         temp.insert(getSubtitle(startTime));
         return temp;}
         
      if(endequals==0){
         return temp;}
   
      int start=0;
      int end=0;
      subs.findFirst();
      for(int i=0;i<size;i++){//case more
         start=calculator(subs.retrieve().getStartTime());
         end=calculator(subs.retrieve().getEndTime());
         if((start==startequals)){
            temp.insert(subs.retrieve());}
         else if(start==endequals){ 
            temp.insert(subs.retrieve());}
         else if(end==endequals){ 
            temp.insert(subs.retrieve());}
         else if(end==startequals){ 
            temp.insert(subs.retrieve());}
         else if((start<startequals)&&(end>startequals)){ 
            temp.insert(subs.retrieve());}
         else if((start<endequals)&&(end>endequals)){ 
            temp.insert(subs.retrieve());}
         else if(start>startequals&&end>startequals&&end<endequals&&start<endequals){ 
            temp.insert(subs.retrieve());}
         else if(start>startequals&&end>startequals&&start<endequals&&end<endequals){ 
            temp.insert(subs.retrieve());}
            
         subs.findNext();
      }
         
            
      return temp;
   }
 
   public List<Subtitle> getSubtitles(String str){//5
      if(subs.empty()){
         return null;}
      if(str.equals(null)){
         return null;}
      LinkedList<Subtitle> temp=new LinkedList<Subtitle>();
      String s;
      int size=findSize();
      subs.findFirst();
      for(int j=0;j<size;j++){
         s=subs.retrieve().getText();
         if(s.contains(str)){
            temp.insert(subs.retrieve());  
         }
         subs.findNext();
      }
      
      return temp;
      
   }

 
 
   public void remove(String str){//6
      if(str.equals(null)){
         return;}
      if(subs.empty()){//empty
         return;}
      subs.findFirst();
      int size=findSize();
      String s;
      subs.findFirst();
      for(int j=0;j<size;j++){
         while(!subs.empty()){
            s=subs.retrieve().getText();
            if(s.contains(str)){
               subs.remove();
            }
            else{subs.findNext();}
         }}}


   public void replace(String str1, String str2){//7
      if(subs.empty()){
         return;}
      if(str1.equals(null)||str2.equals(null)){
         return;}
      String s;
      int size=findSize();String k1;
      subs.findFirst();
      for(int i=0;i<size;i++){
         s=subs.retrieve().getText();
         if(s.contains(str1)){
            k1=subs.retrieve().getText().replaceAll(str1,str2);
            subs.retrieve().setText(k1);
         }
         subs.findNext();
      }
   }
	   
   public void shift(int offset){//8
    
      if(subs.empty()){
         return;}
    
      if (offset==0){
         return;}
      subs.findFirst();
      int s=findSize();
      for(int i=0;i<s;i++){//changing the time
         editTime(subs.retrieve(),offset);
      
         subs.findNext();}
     
    
      subs.findFirst();
      while(!subs.last()){ //check if its equal zero 
         if(subs.retrieve().getStartTime().getHH()<0){//we should prevent the overlaping
         
            subs.retrieve().getStartTime().setHH(00);
            subs.retrieve().getStartTime().setMM(00);
            subs.retrieve().getStartTime().setSS(00);
         
            subs.retrieve().getStartTime().setMS(00);}
       
         if(subs.retrieve().getEndTime().getHH()<0){
            subs.retrieve().getEndTime().setHH(00);
         
            subs.retrieve().getEndTime().setMM(00);
            subs.retrieve().getEndTime().setSS(00);
            subs.retrieve().getEndTime().setMS(00);
          
            if(subs.retrieve().getEndTime().getHH()==00){//check start time
               if(subs.retrieve().getEndTime().getMM()==00)
               
                  if(subs.retrieve().getEndTime().getSS()==00)
                     if(subs.retrieve().getEndTime().getMS()==00)
                        subs.remove();               }
         } 
         else 
            subs.findNext(); }
          //////for last node
      if(subs.retrieve().getStartTime().getHH()<0){//we should prevent the overlaping
         subs.retrieve().getStartTime().setHH(00);
         subs.retrieve().getStartTime().setMM(00);
         subs.retrieve().getStartTime().setSS(00);
         subs.retrieve().getStartTime().setMS(00);}
       
      if(subs.retrieve().getEndTime().getHH()<0){
         subs.retrieve().getEndTime().setHH(00);
         subs.retrieve().getEndTime().setMM(00);
         subs.retrieve().getEndTime().setSS(00);
         subs.retrieve().getEndTime().setMS(00);
          
         if(subs.retrieve().getEndTime().getHH()==00){//check start time
            if(subs.retrieve().getEndTime().getMM()==00)
            
               if(subs.retrieve().getEndTime().getSS()==00)
                  if(subs.retrieve().getEndTime().getMS()==00)
                     subs.remove();
         }
      } 
   
   }

 
   public void cut(Time startTime, Time endTime){//9
      if(startTime.equals(null)||endTime.equals(null)){
         return;}
      if(subs.empty()){
         return;}
      int  duration;
      int size=findSize(), removed=0,skipped=0;
      int start= calculator(startTime),compstart,compend;//return received start time in seconds
      int end= calculator(endTime);//return received end time in seconds
      if(end<start){
         return;}
      if(end==0){
         return;}
      if(start<0||end<0){
         return;}
      boolean firstTime=true;
      subs.findFirst();
      for(int i=0;i<size;i++){//go through all list
      
         compstart=calculator(subs.retrieve().getStartTime());
         compend=calculator(subs.retrieve().getEndTime());//return start time of current in seconds
         
         
         if((compstart>start)&&(compstart<=end)||(compstart==start)||(compstart<start)&&((compend>=start))){
            firstTime=false;
            subs.remove();
            removed++;
         }
         else{
            if(!firstTime||(start<compstart&&end<compstart)){//all duration has been cut or no subtitles in duration is found
               break;}
            else{//duration start hasn't been found yet
               subs.findNext();
               skipped++;}  
         }
      }
     // if(removed!=0){
         List<Subtitle> ss=getSubtitles(startTime,endTime);
         if(!ss.equals(null)){
         ss.findFirst();
         if(start>calculator(ss.retrieve().getStartTime()))
            start=calculator(ss.retrieve().getStartTime());
         while(!ss.last())
            ss.findNext();
         if(end<calculator(ss.retrieve().getEndTime()))
            end=calculator(ss.retrieve().getEndTime());
      }//}
      duration=(end-start)*-1;
      //list has been cut
      if(!subs.empty()){
         for(int i=0;i<size-removed-skipped;i++){
            subs.update(editTime(subs.retrieve(),duration));
            subs.findNext();
         }
      }
   }
   
 
   
   public int calculator (Time time){//10
      int h,m,s,ms;
      h=time.getHH()*3600000;
      m=time.getMM()*60000;
      s=time.getSS()*1000;
      ms=time.getMS();
      int total=h+m+s+ms;
      return total; }

   public int findSize(){//11
      int size=1;
      subs.findFirst();
      while(!subs.last()){
         size++;
         subs.findNext();
      }
      subs.findFirst();
      return size;
   }
 
   public Subtitle editTime(Subtitle s, int t){//11
      s.getStartTime().setMS(((s.getStartTime()).getMS())+t);//How it should be, repeat for each sttribute
      s.getEndTime().setMS((s.getEndTime().getMS()+t));
     
      ////////////////////////////////edting start time
     
   
   //positive
      while(s.getStartTime().getMS()>999){ 
         s.getStartTime().setMS(((s.getStartTime()).getMS()-1000));
         s.getStartTime().setSS((s.getStartTime().getSS()+1));}
      
      while(s.getStartTime().getSS()>59){
         s.getStartTime().setSS(((s.getStartTime()).getSS()-60));
         s.getStartTime().setMM(s.getStartTime().getMM()+1);
      }
      while(s.getStartTime().getMM()>59){
         s.getStartTime().setMM(s.getStartTime().getMM()-60);
         s.getStartTime().setHH(s.getStartTime().getHH()+1); } 
   
   
   ///negative 
      while(s.getStartTime().getMS()<0){ 
         s.getStartTime().setMS(((s.getStartTime()).getMS()+1000));
         s.getStartTime().setSS((s.getStartTime().getSS()-1));}
      
      while(s.getStartTime().getSS()<0){
         s.getStartTime().setSS(((s.getStartTime()).getSS()+60));
         s.getStartTime().setMM(s.getStartTime().getMM()-1);
      }
      while(s.getStartTime().getMM()<0){
         s.getStartTime().setMM(s.getStartTime().getMM()+60);
         s.getStartTime().setHH(s.getStartTime().getHH()-1); } 
    
    
     
   ////////////////////edting end time   
    
   //psitive 
      while(s.getEndTime().getMS()>999){   
         s.getEndTime().setMS(s.getEndTime().getMS()-1000);
         s.getEndTime().setSS(s.getEndTime().getSS()+1);}
      while(s.getEndTime().getSS()>59){
         s.getEndTime().setSS(s.getEndTime().getSS()-60);
         s.getEndTime().setMM(s.getEndTime().getMM()+1);}
      while(s.getEndTime().getMM()>59){
         s.getEndTime().setMM(s.getEndTime().getMM()-60);
         s.getEndTime().setHH(s.getEndTime().getHH()+1);}
   
   
   ///negative 
      while(s.getEndTime().getMS()<0){ 
         s.getEndTime().setMS(((s.getEndTime()).getMS()+1000));
         s.getEndTime().setSS((s.getEndTime().getSS()-1));}
      
      while(s.getEndTime().getSS()<0){
         s.getEndTime().setSS(((s.getEndTime()).getSS()+60));
         s.getEndTime().setMM(s.getEndTime().getMM()-1);
      }
      while(s.getEndTime().getMM()<0){
         s.getEndTime().setMM(s.getEndTime().getMM()+60);
         s.getEndTime().setHH(s.getEndTime().getHH()-1); } 
    
   
   
   
      return s;
   }

 
}
 
