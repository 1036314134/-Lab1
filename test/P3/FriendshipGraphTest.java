package P3;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class FriendshipGraphTest {
   
	
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@Test
	public void addVerteTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross"); 
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");
		
		assertEquals(0, graph.persons.size());
		graph.addVertex(rachel);
		assertEquals(1, graph.persons.size());
		graph.addVertex(ross);
		assertEquals(2, graph.persons.size());
		graph.addVertex(ben);
		assertEquals(3, graph.persons.size());
		graph.addVertex(kramer);
		assertEquals(4, graph.persons.size());
		graph.addVertex(kramer);
		assertEquals(4, graph.persons.size());
	}
	
	@Test
	public void addEdgeTest() {
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
		graph.addEdge(rachel, ross);
		
	}
	
	@Test
    public void getDistanceTest(){
		
		FriendshipGraph graph = new FriendshipGraph();
		Person p1 = new Person("P1");
		Person p2 = new Person("P2"); 
		Person p3 = new Person("P3");
		Person p4 = new Person("P4");
		Person p5 = new Person("P5");
		 
		graph.addVertex(p1);
		graph.addVertex(p2);
		graph.addVertex(p3);
		graph.addVertex(p4);
		graph.addVertex(p5);
		
		graph.addEdge(p1, p2);
		graph.addEdge(p1, p4);
		graph.addEdge(p1, p5);
		graph.addEdge(p2, p4);
		graph.addEdge(p3, p1);
		graph.addEdge(p3, p2);
		
		assertEquals(1, graph.getDistance(p1, p5));
		assertEquals(1, graph.getDistance(p2, p4));
		assertEquals(2, graph.getDistance(p3, p5));
		assertEquals(-1, graph.getDistance(p5, p3));
		assertEquals(-1, graph.getDistance(p4, p5));
    }
}


	

