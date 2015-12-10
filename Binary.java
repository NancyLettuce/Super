public class Binary implements Comparable{

    protected int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
        _decNum = 0;
	      _binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
      if (n >= 0) {
	    _decNum = n;
	    _binNum = decToBin(n);//converts int n to binomial string
	}
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
        _binNum = s;
	      _decNum = binToDec(s);//converts string s into decimal int
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() {
	return _binNum; 
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
    	String rem = ""; //stored remainders
    	while (n != 0) {
    	    rem  = (n%2) + rem;//added on to the left
    	    n = n/2;//divided by two for next loop 
    	}//divided and % by two because base 2
    	return rem;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
    	String rem = "";//basically _binNum
    	if (n/2 == 0) {//base case
    	    rem = n%2 + rem;//adds on to the left
    	    return rem;
    	}
    	else {
    	    return decToBinR(n/2) + Integer.toString(n%2);
    	    //adds the rem on at the end
    	}
    }

    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
    	int dec = 0;
    	int power = 0; 
    	for (int ctr = 0; ctr < s.length(); ctr ++ ) {
    	    power = s.length() - ctr - 1;
    	    //ctr and power increment at opposite ends
    	    dec += (Integer.parseInt(s.substring(ctr,ctr+1))
    		    //goes through each number in the string
    		    * Math.pow(2, power));//number multiplied by 2 raised to th correct power
    	}
    	return dec;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns decimal equivalent as int
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
    	int dec = 0;
    	int power = 0;
    	if (s.length() == 0) {
    	    return -1; //in case String s is empty
    	}    
    	else if (s.length() == 1) {//base case
    	    dec += (Integer.parseInt(s.substring(0))//Steps through String
    		    * Math.pow(2, power));//multiplied by 2 to correct power
    	    return dec;
    	}
    	else {
    	    power = s.length() -1; //power is one less than length because...
    	    return binToDecR(s.substring(1))
    		//first element of s sliced off with each recursion
    		+(Integer.parseInt(s.substring(0,1))
    		  //multiplies the first int in s by
    		  *(int)(Math.pow(2, power)));//2 raised to correct power
    	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
    	boolean retVal = this == other; //checks for alias
    	if (!retVal) {
    	    retVal = other instanceof Binary
    		&& ((this).compareTo((Binary)other)) == 0;
    	}//uses compareTo
    	return retVal;
    }

    public int getValue() {
      return binToDec(_binNum);
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object o) {
      if (!(o instanceof Comparable)) {
        throw new ClassCastException("Argument to compareTo is not " + 
              "an instanceof Comparable");
      }
      if (o == null) {
        throw new NullPointerException ("Nothing in Object");
      }
    	if (o instanceof Binary) {
    		Binary please = new Binary(this._decNum);
    	    Binary other = (Binary)o; //Object other becomes Binary o
    	    //checks for equality of variables
    	    if (please.getValue() == other.getValue()) {
    		return 0; }
    	    else if (please.getValue() < other.getValue()) {
    		return -1;}
    	    return 1;
    	}
    	else if (o instanceof Hexadecimal) {
    	    Hexadecimal please = new Hexadecimal(this._decNum);
    	    Hexadecimal other = (Hexadecimal)o;
    	    if (please.getValue() == other.getValue()) {
    		return 0;
    	    }
    	    else if (please.getValue() < other.getValue()) {
    		return -1;
    	    }
    	    return 1;
    	}
      else {
        Rational other = (Rational) o;
        if (other.getNum() % other.getDem() != 0){
          return -1;
        }
        else{
          Binary otherB = new Binary (other.getNum() / other.getDem());
          if (this._decNum == otherB._decNum) {
            return 0;}
            return -1;
        }
      }
    }

    //main method for testing
    public static void main( String[] args ) {
    	System.out.println(decToBin(11)); //should be 1011
    	System.out.println(decToBin(2341)); //should be 100100100101
    	System.out.println(decToBinR(11)); //should be 1011
    	System.out.println(decToBinR(2341));// should be 100100100101
    
    	System.out.println(binToDec("1011")); //should be 11
    	System.out.println(binToDec("100100100101")); //should be 2341
    
    	System.out.println(binToDecR("1011")); //should be 11
    	System.out.println(binToDecR("100100100101")); //should be 2341
    
    
    	System.out.println();
    	System.out.println( "Testing ..." );
    
    	Binary b1 = new Binary(5);
    	Binary b2 = new Binary(5);
    	Binary b3 = b1;
    	Binary b4 = new Binary(7);
    
    	System.out.println( b1 );
    	System.out.println( b2 );
    	System.out.println( b3 );       
    	System.out.println( b4 );       
    
    	System.out.println( "\n==..." );
    	System.out.println( b1 == b2 ); //should be false
    	System.out.println( b1 == b3 ); //should be true
    
    	System.out.println( "\n.equals()..." );
    	System.out.println( b1.equals(b2) ); //should be true
    	System.out.println( b1.equals(b3) ); //should be true
    	System.out.println( b3.equals(b1) ); //should be true
    	System.out.println( b4.equals(b2) ); //should be false
    	System.out.println( b1.equals(b4) ); //should be false
    
    	System.out.println( "\n.compareTo..." );
    	System.out.println( b1.compareTo(b2) ); //should be 0
    	System.out.println( b1.compareTo(b3) ); //should be 0
    	System.out.println( b1.compareTo(b4) ); //should be neg
    	System.out.println( b4.compareTo(b1) ); //should be pos

    }//end main()

} //end class
