import java.lang.*;
import java.sql.*;
import java.io.*;
import java.util.*;

/*
create table student
(
no integer(5) primary key,
name varchar(100),
bdate DATE,
jdate DATE
);
*/
class Assignment
{

public static void main (String args[])throws SQLException
{
        Connection cn;
        ResultSet rs;
		PreparedStatement prstm;
		Statement stm;
		String sql;
		 
  int no,cnt=0;
  String name;
  Date bdate,jdate;
  
  Scanner sc = new Scanner(System.in);
  
  try
  {
  cn = DriverManager.getConnection(jdbc:mysql:///MyDB","root","");
  stm=cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			while(true)
			{
			System.out.println("\n\t 1.INSRET \n\t 2.UPDATE \n\t 3.DELETE \n\t 4.SEARCH \n\t 5.VIEW ALL \n\t 6.Exit");
			System.out.println("\n\t Enter ur Choice");
			int ch=sc.nextInt();
			
			if(ch==6)
			{
				System.exit(0);
			}
			
			if(ch==1)
			{
		    System.out.println("Enter Number");
            no = sc.nextInt();
			
			System.out.println("Enter Name");
            name = sc.next();
			
			System.out.println("Enter BirthDate");
            bdate = sc.nextDate();
  
            System.out.println("Enter Joining Date");
            jdate = sc.nextDate();
  
           sql="insert into student values("+no+",'"+name+"',"+bdate+","+jdate+")";
				prstm=cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				System.out.println("\n\t Record sucessfully Inserted");
			}
			
			if(ch==2)
			{
		    System.out.println("To Update: Enter Number");
            no = sc.nextInt();
			
			System.out.println("To Update: Enter Name");
            name = sc.next();
			sql="update student set name='"+name+"' where number="+no;
				prstm=cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				System.out.println("\n\t Record sucessfully Updated");		
			
			System.out.println("To Update: Enter BirthDate");
            bdate = sc.nextDate();
			sql="update student set birthdate='"+bdate+"' where number="+no;
				prstm=cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				System.out.println("\n\t Record sucessfully Updated");		
			
  
            System.out.println("To Update: Enter Joining Date");
            jdate = sc.nextDate();
            sql="update student set joindate='"+jdate+"' where number="+no;
				prstm=cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				System.out.println("\n\t Record sucessfully Updated");	
			}

		      if(ch==3)
			  {
			  System.out.println("Enter student Name To Delete Record");
              no = sc.nextInt();
			  
			  sql="delete from student where name='"+name+"'";
				prstm=cn.prepareStatement(sql);
				prstm.execute();
				prstm.close();
				System.out.println("\n\t Record sucessfully deleted");			
			}
			
			if(ch==4)
			{
				System.out.println("\n\t enter the student no For information:");
				no=sc.nextInt();
				rs=stm.executeQuery("select * from student where no =" +no+"");

				while(rs.next())
					System.out.print("Student No.:"+rs.getString(1)+"\n Name :"+rs.getString(2)+"\n BirthDate :"+rs.getString(3)+"\n Joining Date :"+rs.getString(4));
				
			}
			
			if(ch==5)
			{
			   rs=stm.executeQuery("select * from student");

				while(rs.next())
				{
					System.out.print("\n\t"+rs.getString(1)+"\t");
					System.out.print(rs.getString(2)+"\t");
					System.out.print(rs.getString(3));
					System.out.print(rs.getString(4));
					cnt++;
				}
				rs.close();
				System.out.println("\n\t total no of records :"+cnt);
			}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}