package hazi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores {
	List<Integer> list = new ArrayList<Integer>();
	
	public HighScores() {
		load();
	}
	
	public void add(int d) {
		if(list.contains(d)) return;
		list.add(d);
		Collections.sort(list);
		list.remove(0);
		save();
	}
	
	public int get(int index) {
		return list.get(index);
	}
	
	public void reset() {
		for(int i = 0; i < 5; i++)
			list.set(i, 0);
	}
	
 	private void save() {
		try {
	        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("highscores.dat"));
	        out.writeObject(list);
	        out.close();
	    }catch(IOException i) {}
	}
	
	private void load() {
		try {
	        ObjectInputStream in = new ObjectInputStream(new FileInputStream("highscores.dat"));
	        list = (ArrayList<Integer>)in.readObject();
	        in.close();
	    }catch(IOException i){
	    	list = new ArrayList<Integer>();
	    	for(int x = 0; x < 5; x++)
	    		list.add(0);
	    } catch(ClassNotFoundException c){}
	}
}
