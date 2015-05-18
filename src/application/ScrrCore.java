package application;

import java.util.ArrayList;
import java.util.Random;
/**
 * Core if StarCraft Race randomizer 
 * This will randomize and return the value of race
 * @author Bardia
 *
 */
public class ScrrCore {
	private Random random ;
	private boolean skip[] = {false,false,false,true};
	
	/**
	 * Initializes the Random element
	 */
	public ScrrCore() {
			random = new Random();		
	}
	
	/**
	 * Recursively rolls till get an element that is not skipped 
	 * @return -1 if all skip[] is true else a value 0-4
	 */
	public int roll(){
		if (!skip[0] | !skip[0] | !skip[1] | !skip[2] | !skip[3]) {
			int rand = random.nextInt(4);
			rand = ignor(rand);
			return rand;
		}
		return -1;
	}
	/**
	 * will check if skip[i] is ignore or not if true it will recall it self till it find one with a false value
	 * @param in
	 * @return a the first index of skip that is true
	 */
	private int ignor(int in){
		if (skip[in]){
			//System.out.print("ignoring_["+in +"]_");
			in = ignor(random.nextInt(4));
		}
		return in;
	}
	
	/**
	 * setting skip on the index
	 * @param ix
	 * @param value
	 */
	public void setSkip(int ix , boolean value){
		this.skip[ix] = value;
	}
	/**
	 * will fill the hole array
	 * @param inArray
	 */
	public void setSkip(ArrayList<Boolean> inArray){
		for(int i =0 ; i < 4 ;i++)
			skip[i] = inArray.get(i);
	}
	
	/**
	 * 
	 * @return ArrayList of skip
	 */
	public ArrayList<Boolean> getSkip(){
		ArrayList<Boolean> ret = new ArrayList<Boolean>();
		for (boolean b : skip) {
			ret.add(b);
		}
		return ret;
	}
	
}
