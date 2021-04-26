
import java.util.Scanner;
import java.io.IOException;
import java.util.Date;
import java.util.InputMismatchException;
import java.text.DateFormat;

class LinkedQueue{
	public int size;
	private Node head=new Node(null);

	private class Node{
		Object obj1;
		Object obj2;
		Object obj3;
		Node next=this;
		Node prev=this;

		Node(Object obj1){
			this.obj1=obj1;
			}

		Node(Object obj1 , Object obj2 , Object obj3, Node prev , Node next){
			this.obj1=obj1;
			this.obj2=obj2;
			this.obj3=obj3;
			this.prev=prev;
			this.next=next;
		}
	}

	public void add(Object obj1,Object obj2, Object obj3) {
		++size;
		head.prev=head.prev.next=new Node(obj1,obj2,obj3,head.prev,head);
	}

    public int size(){return size;}

	public Object remove() {
		if(size==0) {
			throw new IllegalStateException("Record is void");
		}
		Object temp1=head.next.obj1;
		Object temp2=head.next.obj2;
		Object temp3=head.next.obj3;
		String temp=(temp1+"\t\t"+temp2+"\t\t"+temp3);
		head.next=head.next.next;
		head.next.prev=head;
		--size;
		return temp;
	}

	public void viewList(){
		Node p=head.next; int i=0;
		for(;p!=null&&i<size;p=p.next,i++)
		{System.out.println(p.obj1+"\t\t"+p.obj2+"\t\t"+p.obj3);}
		}
}

public class CheckIn extends LinkedQueue{
LinkedQueue lq= new LinkedQueue();

public static void hold(){
	try{System.in.read();}
		catch(IOException e){
	System.out.println(e.getMessage());}}

public static void clear(){
	try{
new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
catch(Exception e){
	System.out.println(e.getMessage());}
}

public void mainmenu() {
	try{
	Scanner input=new Scanner(System.in);
	int i;
	System.out.println("******welcome to Hostel check-in station******\n");
	System.out.println("1. new entry");
	System.out.println("2. delete entry");
	System.out.println("3. view record");
	System.out.println("4. exit");
	System.out.println("\n\nselect any option");
	i=input.nextInt();

	if(i==1){
	clear();
	System.out.println("enter an entry: \n");
	System.out.println("enter your name: ");
	String s1=input.next();
	System.out.println("\nenter your roll number: ");
	String s2=input.next();
	Date currentDate = new Date();
	String date=DateFormat.getInstance().format(currentDate);
	lq.add(s1,s2,date);
	System.out.println("\n****entry added successfully****\n\npress enter to go back to main menu");
	hold();
	clear();
	mainmenu();
	}

	else if(i==2){
	clear();
	if((lq.size())>0)
	System.out.println("****first entry deleted successfully****\n\n");

	try{
	lq.remove();}
    catch(Exception e){
	System.out.println(e.getMessage());}

	System.out.println("press enter to go back to main menu");
	hold();
	clear();
	mainmenu();}

	else if(i==3){
	clear();
	System.out.println("****ENTRY BOOK****\n");
	if((lq.size())==0)
	System.out.println("no record found\n");
	lq.viewList();
	System.out.println("\n\npress enter to go back to main menu");
	hold();
	clear();
	mainmenu();}

	else if(i==4){
	clear();
	System.out.println("****good bye****\n");
	System.exit(0);}

	else{
	clear();
	System.out.println("ivalid key pressed do you want to go back? if yes then press 'y'");
	char c=input.next().charAt(0);
	if(c=='y'){
	clear();
	mainmenu();}
	else{
	clear();
	System.out.println("****good bye****\n");
	System.exit(0);}}}

	catch(InputMismatchException e){
	Scanner in=new Scanner(System.in);
	System.out.println("\n"+e.getMessage());
	clear();
	System.out.println("you can only enter numeric input for selection. do you want to go back?press y to go back");
	String s3=in.next();
	if(s3.equalsIgnoreCase("y")){
	clear();
	mainmenu();}
	else
	System.exit(0);}
}

	public static void main (String[] args) throws Exception{
		CheckIn c=new CheckIn();
		c.mainmenu();
	}
}
