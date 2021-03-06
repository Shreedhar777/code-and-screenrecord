
import java.io.*;
import java.util.*;
class Item {
  String name;
  int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
    FileInputStream fis=new FileInputStream("input.txt");      /* reading data from file  */
    Scanner sc=new Scanner(fis);
    int num_of_emp = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> g_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      g_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(g_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; 
      } 
    });

    int min_diff = g_items.get(g_items.size()-1).price;
    int min_index = 0;
    for(int i=0;i<g_items.size()-num_of_emp+1;i++) {
      int diff = g_items.get(num_of_emp+i-1).price-g_items.get(i).price;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    

    FileWriter fw = new FileWriter("output.txt");       /*write the data for file */
    fw.write("Number of employees are: "+num_of_emp);
    fw.write("\n Here the goodies that are selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + num_of_emp; i++) {
      fw.write(g_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is" + min_diff);
	  fw.close();
  }
}