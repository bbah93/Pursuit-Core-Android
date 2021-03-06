package nyc.c4q;

import java.util.Scanner;


public class Hangman {
    private final SecretWord mSecretWord;
    private char mLastGuess;
    private int mMisses = 0;

    public Hangman(){
        mSecretWord = new SecretWord();
    }


    public int getMisses(){
        return mMisses;
    }
    private void readLetter() {
        Scanner scanner = new Scanner(System.in);
        char input = scanner.next().charAt(0);
        mLastGuess = Character.toUpperCase(input);
    }

    private void promptPlayer(String string) {
        System.out.println(string);
    }

    private boolean checkLetter() {
        if (mSecretWord.isLetter(mLastGuess)) {
            // If letter is in word do something
            mSecretWord.set(mLastGuess);
            return true;
        } else {
            mMisses++;
            return false;
        }
    }

    private void printCurrentWord(){
        System.out.println(mSecretWord);
    }


    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        while (hangman.getMisses() < 5){
            hangman.printCurrentWord();
            hangman.promptPlayer("Enter a letter: ");
            hangman.readLetter();
            hangman.checkLetter();
            if(hangman.guessedSuccessfully()){
                break;
            }
            System.out.println(Drawing.get(hangman.getMisses()));
            System.out.println("Misses -> " +hangman.getMisses());
        }
        if(hangman.guessedSuccessfully()){
            System.out.println("Success");
        } else {
            System.out.println("The answer was " + hangman.getSecretWord());
        }
    }

    private String getSecretWord() {
        return mSecretWord.reveal();
    }

    private boolean guessedSuccessfully() {
        return mSecretWord.isGuessed();
    }

}
