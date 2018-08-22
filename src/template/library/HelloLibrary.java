package template.library;


import processing.core.*;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class HelloLibrary {
	
	// myParent is a reference to the parent sketch
	PApplet myParent;

	int myVariable = 0;
	
	public final static String VERSION = "##library.prettyVersion##";
	

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @example Hello
	 * @param theParent
	 */
	public HelloLibrary(PApplet theParent) {
		myParent = theParent;
		welcome();
		
		myParent.registerMethod("pre", this);
		myParent.registerMethod("draw", this);
	    myParent.registerMethod("post", this);
	}
	
	
	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}
	
	
	public String sayHello() {
		return "hello library.";
	}
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}

	/**
	 * 
	 * @param theA
	 *          the width of test
	 * @param theB
	 *          the height of test
	 */
	public void setVariable(int theA, int theB) {
		myVariable = theA + theB;
	}

	/**
	 * 
	 * @return int
	 */
	public int getVariable() {
		return myVariable;
	}
	
	public void pre() {
		System.out.println("PRE "+System.currentTimeMillis());
	}
	
	public void draw() {
		System.out.println("DRAW "+System.currentTimeMillis());
	}
	
	public void post() {
		System.out.println("POST "+System.currentTimeMillis());
		
		//print memory stats. ISO : https://physics.nist.gov/cuu/Units/binary.html
		int mb = 1024*1024;
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
	    System.out.println("##### Heap utilization statistics [MB] #####");    
	    //Print used memory
	    System.out.println("Used Memory:"  + (runtime.totalMemory() - runtime.freeMemory()) / mb);
	    //Print free memory
	    System.out.println("Free Memory:"  + runtime.freeMemory() / mb);    
	    //Print total available memory
	    System.out.println("Total Memory:" + runtime.totalMemory() / mb);
	    //Print Maximum available memory
	    System.out.println("Max Memory:" + runtime.maxMemory() / mb);
	}
}

