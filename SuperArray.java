
public class SuperArray{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

       
    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray()
    {
    _data = new Comparable[10];
    _lastPos = -1; //flag to indicate no lastpos yet
    _size = 0;   
    }

       
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString()
    {
    String foo = "[";
    for( int i = 0; i < _size; i++ ) {
        foo += _data[i] + ",";
    }
    //shave off trailing comma
    if ( foo.length() > 1 )
        foo = foo.substring( 0, foo.length()-1 );
    foo += "]";
    return foo;
    }

       
    //double capacity of this SuperArray
    private void expand()
    {
    Comparable[] temp = new Comparable[ _data.length * 2 ];
    for( int i = 0; i < _data.length; i++ )
        temp[i] = _data[i];
    _data = temp;
    }

       
    //accessor -- return value at specified index
    public Comparable get( int index ) { 
        return _data[index]; 
    }

       
    //mutator -- set value at index to newVal,
    //           return old value at index
    public Comparable set( int index, Comparable newVal )
    {
     Comparable temp = _data[index]; //preserves old value
    _data[index] = newVal;
    return temp; //returns old value
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
    if (_size >= _data.length) { //if array is full
        expand();
    }
        _data[++_lastPos] = newVal;
        ++_size; //size increases by one 
    }
   
   
    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
    if( index <= _lastPos ){ //does not add if index of new value is past _lastPos

        if( _lastPos >= _data.length - 1 ) { //if array is almost filled up
            expand();
        }

       Comparable temp = set( index, newVal ); //holds last val

       for(int i = index + 1; i <= _lastPos + 1; i++){//also increments _lastPos
          temp = set(i, temp); //temp is redefined after the right side is done
          //System.out.println(temp + " " + this);         
       }
       _lastPos++;
       ++_size;
    }
}
        
  
    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
       
    Comparable temp = new Rational(0, 1); //holds last val

    for (int i = --_lastPos; i >= index; i--){ //also decrements _lastPos
        temp = set(i, temp); //temp is redefined after the right side is done
    }
    --_size;
    }
   
   
   
    //return number of meaningful items in _data
    public int size() {
        _size = _lastPos + 1; //index of last meaningful item + 1
        return _size;
    }
    
    ////////////******************/////////////
    
    //linSearch
    public int linSearch (Comparable o) {
        for (int ctr = 0; ctr < _data.length; ctr ++) {
            if (o.compareTo(_data[ctr]) == 0) {
                return ctr;
            }
        }
        return -1;
    }
    
    //isSorted
    public boolean isSorted() {
        for (int ctr = 0; ctr < _data.length; ctr ++) {
            if (!(_data[ctr - 1].compareTo(_data[ctr]) < 0)) {
                return false;
            }
        }
        return true;
    }

 
    //main method for testing
    public static void main( String[] args )
    {
        
        SuperArray mayfield = new SuperArray();
	//===============================================//
	
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	//Test Add Methods
	mayfield.add(new Rational (5, 1));
	mayfield.add(new Binary (8));
	mayfield.add(new Hexadecimal (16));
	mayfield.add(new Binary (56));
	mayfield.add(new Rational (6, 2));

	
	
	System.out.println("Printing populated SuperArray mayfield...");
	System.out.println(mayfield);
	
	
	//Test Remove Method
	mayfield.remove(3);
	System.out.println("Printing ListInt mayfield post-remove...");
	System.out.println(mayfield);
	mayfield.remove(3);
	System.out.println("Printing ListInt mayfield post-remove...");
	System.out.println(mayfield); 

	
	//Test Add-at-Index
	mayfield.add(2,new Binary (56));
	System.out.println("Printing ListInt mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(2,new Binary (58));
	System.out.println("Printing ListInt mayfield post-insert...");
	System.out.println(mayfield);
	mayfield.add(1,new Binary (57));
	System.out.println("Printing ListInt mayfield post-insert...");
	System.out.println(mayfield);
	
	
	//Test Size 
	System.out.println("Printing ListInt mayfield size");
	System.out.println (mayfield.size());
	
	//isSorted Test


	//linSearch Test
	System.out.println (mayfield.linSearch (new Binary(8)));


        /*
    SuperArray curtis = new SuperArray();
    System.out.println("Printing empty SuperArray curtis...");
    System.out.println(curtis);

    for( int i = 0; i < curtis._data.length; i++ ) {
        curtis.set(i,i*2);
        curtis._size++; //necessary bc no add() method yet
    }

    System.out.println("Printing populated SuperArray curtis...");
    System.out.println(curtis);

    System.out.println("testing get()...");
    for( int i = 0; i < curtis._size; i++ ) {
        System.out.print( "item at index" + i + ":\t" );
        System.out.println( curtis.get(i) );
    }

    System.out.println("Expanded SuperArray curtis:");
    curtis.expand();
    System.out.println(curtis);

    SuperArray mayfield = new SuperArray();
    System.out.println("Printing empty SuperArray mayfield...");
    System.out.println(mayfield);

      mayfield.add(5);
      mayfield.add(4);
      mayfield.add(3);
      mayfield.add(2);
      mayfield.add(1);
     
      System.out.println("Printing populated SuperArray mayfield...");
      System.out.println(mayfield);

      mayfield.remove(3);
      System.out.println("Printing SuperArray mayfield post-remove...");
      System.out.println(mayfield);
      mayfield.remove(3);
      System.out.println("Printing SuperArray mayfield post-remove...");
      System.out.println(mayfield);

      mayfield.add(3,99);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
      mayfield.add(2,88);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
      mayfield.add(1,77);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield);
    //*****INSERT ANY ADDITIONAL TEST CALLS HERE*****
      mayfield.add(10,77);
      System.out.println("Printing SuperArray mayfield post-insert...");
      System.out.println(mayfield); //should not change
*/

    }//end main
}//end class
