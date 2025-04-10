import java.util.*;
class Arthematic
{

 void Area(int a,int b,int c)
{
 float s=(float)1/2*(a+b+c);
 float area=sqrt(s*(s-a)*(s-b)*(s-c));
 System.out.println(area);
}
 public static void main(String args[])
 {
  Scanner sc=new Scanner(System.in);
  Arthematic s=new Arthematic();
  int a=5,b=6,c=7;
  s.Area(a,b,c);
 }
  
}
