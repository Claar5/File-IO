import java.util.*;
import java.io.*;
class Employee implements Serializable{
	int no;
	String name;
	int salary;
	
	public Employee( int no, String name, int salary){
		this.name = name; 
		this.no = no;
		this.salary = salary;
	}
	
	public String toString(){
		return no + " " + name + " " + salary;
	}

}

class EmployeeMain { // implement in the class that throws the exception
		public static void main(String[] args) throws Exception {// need to implement for file
			int choice = -1;
			Scanner input = new Scanner(System.in);
			Scanner input2 = new Scanner(System.in);
			
			ArrayList<Employee> al = new ArrayList<Employee>();
			File file = new File("Employee.txt"); 	 // creating the file
			ObjectOutputStream oos = null;	//only create the outputstream when needed
			ObjectInputStream ois = null;
			
			ListIterator li = null;
			
			if(file.isFile()){
				ois = new ObjectInputStream(new FileInputStream(file));// if file exist we load the data
				// if doesnt exist we write into file
				al = (ArrayList<Employee>)ois.readObject();
				ois.close(); // success
			}
			
			
			do{
				System.out.println("0. Exit");
				System.out.println("1. Insert");
				System.out.println("2. Display");
				System.out.println("3. Search");
				System.out.println("Enter your choice:/n");
				choice = input.nextInt();
				
				switch(choice){
					case 1:
						System.out.println("How many employees:");
						int n = input.nextInt();
						
						for (int i = 0; i < n; i ++){
							System.out.println("Enter employee number:");
							int no = input.nextInt();
						
							System.out.println("Enter employee name:");
							String name = input2.nextLine();
						
							System.out.println("Enter employee salary:");
							int salary = input.nextInt();
						
							al.add(new Employee(no, name, salary));//store in list
							
						}
						// place it here so it does not overwrite each time for runs
						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al); // write array list to file
						oos.close(); // save data to file so we can append
						//success

					break;
					
					
					case 2:
					
						if(file.isFile()){
							ois = new ObjectInputStream(new FileInputStream(file));// if file exist we load the data
							// if doesnt exist we write into file
							al = (ArrayList<Employee>)ois.readObject();
							ois.close(); // success
							
							li = al.listIterator();
							while(li.hasNext()){
								System.out.println(li.next());
							}
						
						}
						else
							System.out.println("File does not exist");
						
					break;
					
					case 3:
						if(file.isFile()){
							boolean found = false;
							System.out.println("Enter number to search:");
							int no = input.nextInt();
					
							li = al.listIterator();
							while(li.hasNext()){
								Employee e = (Employee)li.next();
							
								if(e.no == no){
									System.out.println(e);
									found = true;
								}
							
							if (!found)
								System.out.println("Record not found");
							}
						}
						else
							System.out.println("File does not exist");
					break;
				}
			}while(choice != 0);
				
		}
}