package P3;

import java.util.HashSet;
import java.util.Set;

public class Person {
	private  String name;
	public boolean  vis;
	public  int distance;
	private Set<Person> friends = new HashSet<Person>();
	
	public Person(String name) {
		this.name = name;
		this.distance = 0;
		this.vis = false;
	}
	
	public String name() {
		return name;
	}
	
	public boolean vis() {
		return vis;
	}
	
	public void add_friend(Person new_friend){
		this.friends.add(new_friend);
	}
	
	public Set<Person> friends(){
		return friends;
	}
	
	
	
}
