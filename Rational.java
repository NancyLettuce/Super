public class Rational implements Comparable{
    private int numerator;
    private int denominator;
    
    public Rational() { //default constructor
	numerator = 0;
	denominator = 1;
    }
    
    public Rational( int num, int dem ) { //custom
	if (dem == 0) {
	    numerator = 0;
	    denominator = 1;
	    System.out.println("Cannot divide by 0");
	}
	else {
	    numerator = num;
	    denominator = dem;
	}
    }

    public String toString() { //gives fraction and decimal forms
	String s1 = "Fraction : "+numerator +"/"+denominator;
	String s2 = "Decimal : "+ (numerator * 1.0 / denominator);
	return s1 +"\n"+ s2;
    }

    public boolean equals(Object obj) {
	if (this == obj) { //if same alias
	    return true;
	}
	if (obj instanceof Rational) {
	    Rational rightSide = (Rational)obj; //if it is a Rational
	    this.reduce();//both reduce
	    rightSide.reduce();
	    return numerator == rightSide.numerator && //returns if both numerator and denominator are the same
		denominator == rightSide.denominator;
	}
	return false;
    }

    public double floatValue() { //returns "decimal" value
	return numerator * 1.0 / denominator;
    }

    //methods for multiplying, dividing, adding, and subtracting
    public void multiply (Rational r) { //multiplying fractions
	numerator *= r.numerator;
	denominator *= r.denominator;
    }

    public void divide (Rational r) { //dividing fractions
	if( r.numerator != 0 ) {
	    numerator *= r.denominator;
	    denominator *= r.numerator;
	}
    }

    public void add (Rational r) { //adding fractions
	numerator = numerator * r.denominator + r.numerator * denominator;
	denominator = denominator * r.denominator;
    }

    public void subtract (Rational r) { //subtracting fractions
	numerator = numerator * r.denominator - r.numerator * denominator;
	denominator = denominator * r.denominator;
    }

    public int gcd() {
	int r; //remainder
	int mx = Math.max( Math.abs(numerator), Math.abs(denominator) ); //max value
	int mn = Math.min( Math.abs(numerator), Math.abs(denominator) ); //min value
	//uses abs since the input is negatice, but negative numbers still have the same gcd as their positive counterparts
		
	while(mn!=0)
	    {
		r = mx % mn; //gets remainder ex: 30%12 = 6
		mx = mn;//max becomes min ex: 12
		mn = r; //min becomes remainder ex: 6
	    }
	return mx; //returns gcd
    }

    //~~~~~~STATIC VERSION OF GCD~~~~~~~~~
    public static int gcd( int n, int d ){
	int r; //remainder
	int mx = Math.max( Math.abs(n), Math.abs(d) ); //max value
	int mn = Math.min( Math.abs(n), Math.abs(d) ); //min value
	//uses abs since the input is negatice, but negative numbers still have the same gcd as their positive counterparts
		
	while(mn!=0)
	    {
		r = mx % mn; //gets remainder ex: 30%12 = 6
		mx = mn;//max becomes min ex: 12
		mn = r; //min becomes remainder ex: 6
	    }
	return mx; //returns gcd
    }
    //~~~~~~END GCD (STATIC)~~~~~~~~

    public void reduce() {
	int gcd = gcd(); //get greatest common factor
	denominator /= gcd; //simplifies fraction
	numerator /= gcd;
	if (denominator < 0) {
	    numerator *= -1;
	    denominator *= -1;
	}
    }
    
    
    ////////////*******************//////////////////
    
    public int getNum() {
    	return numerator;
    }
    public int getDem() {
    	return denominator;
    }
	
    public int compareTo( Object o ) { //implementing interface
	if (o instanceof Rational ) {
	    Rational other = (Rational) o;
	    int diff = this.numerator*other.denominator -
		other.numerator*this.denominator;
	    if (diff == 0){
                return 0;
	    }
            return -1;
	}
	else if (o instanceof Binary) {
		Binary please = new Binary(numerator/denominator);
	    Binary other = (Binary)o; //Object other becomes Binary o
	    //checks for equality of variables
	    if (please.getValue() == other.getValue()) {
		return 0; }
	    else if (please.getValue() < other.getValue()) {
		return -1;}
	    return 1;
	}
	else {
	    Hexadecimal please = new Hexadecimal(numerator/denominator);
	    Hexadecimal other = (Hexadecimal)o;
	    if (please.getValue() == other.getValue()) {
		return 0;
	    }
	    else if (please.getValue() < other.getValue()) {
		return -1;
	    }
	    return 1;
	}
    }
    
    public static void main(String[] args){
	Rational r1 = new Rational(1,5); //rational number 1/5
	Rational r2 = new Rational(1,2); //rational number 1/2
	
	r1.multiply(r2); //Multiplies r1 by r2, changes r1 to 1/10 (0.1)
	System.out.println( r1 );
	System.out.println( r1.denominator ); //10
	System.out.println( r1.numerator ); //1
	
	System.out.println();
	r2.divide(r1); //Divides r2 by r1, changes r2 to 5/1 (5.0)
	System.out.println( r2 );
	System.out.println( r2.denominator );//2
	System.out.println(r2.numerator);//10

	System.out.println();
	System.out.println(r2.gcd()); //2

	System.out.println();
	r2.reduce();
	System.out.println( r2.denominator ); //1
	System.out.println(r2.numerator);//5

	System.out.println();
	System.out.println( r1.compareTo( r2 ) );//-1
	System.out.println( r2.compareTo( r1 ) );//1

	System.out.println();
	System.out.println( gcd( 32, 12 ));//4

	System.out.println();
	r1.add( r2 );
	System.out.println( r1 ); //5.1

	System.out.println();
	r2.subtract( r1 );
	System.out.println( r2 ); //-0.1

	System.out.println();
	System.out.println( r1.compareTo( r2 ) );//1
	System.out.println( r2.compareTo( r1 ) );//-1

	Rational r3 = new Rational( 3, 4 );
	Rational r4 = new Rational( 4, 5 );

	System.out.println();
	System.out.println( r3.compareTo( r4 ) );//-1
	System.out.println( r4.compareTo( r3 ) );//1

	Rational r5 = new Rational( 33333333, 100000000 );
	Rational r6 = new Rational( 1, 3 );

	System.out.println();
	System.out.println( r5 );//0.33333333
	System.out.println( r6 );// 1/3
	System.out.println( r5.compareTo( r6 ) );//1
	System.out.println( r6.compareTo( r5 ) );//-1

	System.out.println("-------------");
	System.out.println("-- .equals --");
	System.out.println(r3.equals(r4));//false
	Rational re0 = new Rational(0, 10);
	Rational re1 = new Rational(0, 3);
	System.out.println(re0.equals(re1));//true
	System.out.println(re1.equals(re0));//true
	Rational re2 = new Rational(2, 4);
	Rational re3 = new Rational(12, 24);
	Rational re4 = new Rational(-12, -24);
	System.out.println(re2.equals(re3));//true
        System.out.println(re4.equals(re2));//true
	Rational re5 = new Rational(3, 7);
	Rational re6 = new Rational(3, -7);
	Rational re7 = new Rational(-3, 7);
	System.out.println(re5.equals(re6));//false
	System.out.println(re7.equals(re5));//false
	System.out.println(re6.equals(re7));//true
    }
}
