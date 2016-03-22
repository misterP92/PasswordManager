public class CryptoExe extends Exception {
	 
    public CryptoExe() {
    	System.out.println(super.toString());
    }
 
    public CryptoExe(String message, Throwable throwable) {
        super(message, throwable);
    }
}