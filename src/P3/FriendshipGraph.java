package P3;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendshipGraph {
	public Set<Person> persons = new HashSet<Person>();
	
	public void addVertex(Person a) {
		for(Person i : persons) {//判断是否重复
			if(a.name().equals(i.name())) {
				System.out.println("name "+i.name()+" has exist");
			}
		}
		persons.add(a);
	}
	
	public void addEdge(Person a,  Person b) {
		if(a.equals(b)) {//判断a、b是否相等
			return;
		}
		
		for(Person i : a.friends()) {
			if(b.name().equals(i.name())) {
				System.out.println("edge "+a.name()+"-"+b.name()+"has exist" );
				return;
			}
		}
		a.add_friend(b);
	}
	
	public int getDistance(Person a, Person b) {
		if(a.name().equals(b.name())) {
			return 0;
		}
		if(a.friends().isEmpty()) {
			return -1;
		}

		for(Person i: persons) {//初始化
			i.vis = false;
			i.distance = 0;
		}
		Queue<Person> q = new LinkedList<Person>();
		a.vis = true;
		q.offer(a);
		
		while(!q.isEmpty()){
			Person tmp = q.poll();
			//System.out.println(tmp.name());
			for(Person i : tmp.friends()){
				if(i.name().equals(b.name())){
					return tmp.distance + 1;
				}
				
				if(i.vis == true) {
					continue;
				}
				i.vis = true;
				i.distance = tmp.distance + 1;
				q.offer(i);
			}
		}
        return -1;
	}
	
	
	public static void main(String[] args) {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);
		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);
		 
		System.out.println(graph.getDistance(rachel, ross));  //should print 1 
		System.out.println(graph.getDistance(rachel, ben));  //should print 2 
		System.out.println(graph.getDistance(rachel, rachel));  //should print 0  
		System.out.println(graph.getDistance(rachel, kramer));  //should print -1 
		
	}
}
