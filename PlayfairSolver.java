
import java.util.Scanner;

public class PlayfairSolver {

    public static boolean checkAvailable(String originalText, String encodedText) {
        boolean check = true;
        if (encodedText.length() == originalText.length()) {
            for (int i = 0; i < (encodedText.length()); i++) {
                if ((originalText.charAt(i) >= 'a') && (originalText.charAt(i) <= 'z') && (encodedText.charAt(i) >= 'a') && (encodedText.charAt(i) <= 'z')) {
                    check = true;
                } else {
                    System.out.println("The original or encoded text has a strange character");
                    check = false;
                    break;
                }
            }
        } else {
            System.out.println("The original and the encoded text not the same lenght");
            check = false;
        }
        return check;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the original text then the encoded (no spaces) :");
        String originalText = input.next();
        String encodedText = input.next();
        boolean check = true;
        originalText = originalText.toLowerCase();
        encodedText = encodedText.toLowerCase();
        check = checkAvailable(originalText, encodedText);

        if (check) {
            char matrix[][] = new char[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    matrix[i][j] = '0';
                }
            }

            int size = originalText.length();

            //make the original and encoded text in pairs
            String original[] = new String[size / 2];
            String encoded[] = new String[size / 2];
            int counter = 0;
            for (int i = 0; i < size / 2; i++) {
                original[i] = originalText.substring(counter, counter + 2);
                encoded[i] = encodedText.substring(counter, counter + 2);
                counter = counter + 2;
            }

            //Get the words with three characters only
            String triple[] = new String[size / 2];
            int tripleCounter = 0;
            for (int i = 0; i < size / 2; i++) {
                triple[i] = "";
                if (original[i].charAt(0) == encoded[i].charAt(0)) {
                    triple[tripleCounter] += (char) original[i].charAt(1) + encoded[i];
                    tripleCounter++;
                } else if (original[i].charAt(1) == encoded[i].charAt(0)) {
                    triple[tripleCounter] += (char) original[i].charAt(0) + encoded[i];
                    tripleCounter++;
                } else if (original[i].charAt(0) == encoded[i].charAt(1)) {
                    triple[tripleCounter] += ((char) original[i].charAt(1));
                    triple[tripleCounter] += ((char) original[i].charAt(0));
                    triple[tripleCounter] += ((char) encoded[i].charAt(0));
                    tripleCounter++;
                } else if (original[i].charAt(1) == encoded[i].charAt(1)) {
                    triple[tripleCounter] += original[i] + (char) encoded[i].charAt(0);
                    tripleCounter++;
                }
            }

            //Print the pairs
            System.out.println("original	encoded");
            for (int i = 0; i < size / 2; i++) {
                System.out.println(original[i] + "		" + encoded[i]);
            }

            //Print the words with three characters only
            System.out.println("There are " + tripleCounter + " word/s with triple characters only");
            for (int i = 0; i < tripleCounter; i++) {
                System.out.println(triple[i]);
            }

            boolean availability[][] = new boolean[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    availability[i][j] = true;
                }
            }

            boolean availableChars[] = new boolean[26];
            for (int i = 97; i < 123; i++) {
                if (i == 'j') {
                    availableChars[i - 97] = false;
                } else {
                    availableChars[i - 97] = true;
                }
            }

            int x[] = new int[25];
            int y[] = new int[25];
            char wanted[] = new char[25];
            boolean checkWanted[] = new boolean[25];

            //Matching the words with triple character
            for (int z = 0; z < 50; z++) {
                for (int i = 0; i < tripleCounter; i++) {
                    
                    
                    for (int j=0 ;j<5 ;j++)
                            for(int k =0 ; k<5 ; k++)
                                if (matrix[j][k] != '0')
                                    availability[j][k] = false ;
                    
                    for (int p = 97; p < 123; p++) {
                        for (int j=0 ;j<5 ;j++)
                            for(int k =0 ; k<5 ; k++)
                                if (matrix[j][k] == k)
                                    availableChars[k - 97] = false;
                    }
                    for (int l = 0; l < 25; l++) {
                        x[l] = 10;
                        y[l] = 10;
                        checkWanted[l] = false;
                    }
                    wanted[0] = triple[i].charAt(0);
                    wanted[1] = triple[i].charAt(1);
                    wanted[2] = triple[i].charAt(2);
                    wanted[3] = (char) (triple[i].charAt(2) + 1);
                    if (wanted[3] == 'j') {
                        wanted[3] = 'i';
                    }

                    for (int m = 0; m < 5; m++) {
                        for (int n = 0; n < 5; n++) {
                            if (matrix[m][n] == wanted[0]) {
                                x[0] = m;
                                y[0] = n;
                                checkWanted[0] = true;
                            } else if (matrix[m][n] == wanted[1]) {
                                x[1] = m;
                                y[1] = n;
                                checkWanted[1] = true;
                            } else if (matrix[m][n] == wanted[2]) {
                                x[2] = m;
                                y[2] = n;
                                checkWanted[2] = true;
                            } else if (matrix[m][n] == wanted[3]) {
                                x[3] = m;
                                y[3] = n;
                                checkWanted[3] = true;
                            }
                            if (matrix[m][n] != '0')
                                checkWanted[4] =true;
                        }
                    }
                    //if it find the last litter only of the triple on the matrix
                    if (!checkWanted[0] && !checkWanted[1] && checkWanted[2]) {
                        if (((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4 )) {
                            matrix[(x[2] - 2)][y[2]] = triple[i].charAt(0);
                            matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0 && (triple[i].charAt(2) - triple[i].charAt(1)) < 5) {
                            matrix[(x[2])][(y[2] - 2)] = triple[i].charAt(0);
                            matrix[(x[2])][(y[2] - 1)] = triple[i].charAt(1);
                        }
                        else{
                            if (y[2] == 0 && x[2]>1){
                                if (availability[(x[2] - 1)][y[2]] && availability[(x[2] - 2)][y[2]]
                                    && (!availability[(x[2])][4] || !availability[(x[2])][3])){
                                    matrix[(x[2] - 2)][y[2]] = triple[i].charAt(0);
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] || !availability[(x[2] - 2)][y[2]])
                                    && (availability[(x[2])][4] && availability[(x[2])][3])){
                                    matrix[(x[2])][3] = triple[i].charAt(0);
                                    matrix[(x[2])][4] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] == 1 && x[2]>1){
                                if (availability[(x[2] - 1)][y[2]] && availability[(x[2] - 2)][y[2]]
                                    && (!availability[(x[2])][0] || !availability[(x[2])][4])){
                                    matrix[(x[2] - 2)][y[2]] = triple[i].charAt(0);
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] || !availability[(x[2] - 2)][y[2]])
                                    && (availability[(x[2])][4] && availability[(x[2])][3])){
                                    matrix[(x[2])][4] = triple[i].charAt(0);
                                    matrix[(x[2])][0] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] > 1 && x[2] == 1){
                                if (availability[(x[2] - 1)][y[2]] && availability[4][y[2]]
                                    && (!availability[(x[2])][y[2]-1] || !availability[(x[2])][y[2]-2])){
                                    matrix[(4)][y[2]] = triple[i].charAt(0);
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] || !availability[(x[2] - 2)][y[2]])
                                    && (availability[(x[2])][(y[2] - 2)] && availability[(x[2])][(y[2] - 1)])){
                                    matrix[(x[2])][(y[2] - 2)] = triple[i].charAt(0);
                                    matrix[(x[2])][(y[2] - 1)] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] > 1 && x[2] == 0){
                                if (availability[(4)][y[2]] && availability[3][y[2]]
                                    && (!availability[(x[2])][y[2]-1] || !availability[(x[2])][y[2]-2])){
                                    matrix[3][y[2]] = triple[i].charAt(0);
                                    matrix[4][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] || !availability[(x[2] - 2)][y[2]])
                                    && (availability[(x[2])][(y[2] - 2)] && availability[(x[2])][(y[2] - 1)])){
                                    matrix[(x[2])][(y[2] - 2)] = triple[i].charAt(0);
                                    matrix[(x[2])][(y[2] - 1)] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] > 1 && x[2] > 1){
                                if (availability[(x[2] - 1)][y[2]] && availability[x[2]-2][y[2]]
                                    && (!availability[(x[2])][y[2]-1] || !availability[(x[2])][y[2]-2])){
                                    matrix[(x[2] - 2)][y[2]] = triple[i].charAt(0);
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] || !availability[(x[2] - 2)][y[2]])
                                    && (availability[(x[2])][(y[2] - 2)] && availability[(x[2])][(y[2] - 1)])){
                                    matrix[(x[2])][(y[2] - 2)] = triple[i].charAt(0);
                                    matrix[(x[2])][(y[2] - 1)] = triple[i].charAt(1);
                                }
                            }
                        }

                    } //if it find the mid. litter only of the triple on the matrix
                    else if (!checkWanted[0] && checkWanted[1] && !checkWanted[2]) {
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4) {
                            matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                            matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0) {
                            matrix[(x[1])][(y[1] - 1)] = triple[i].charAt(0);
                            matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(2);
                        }
                        else{
                            if (y[1] == 0 && x[1]<4 && x[1]>0){
                                if (availability[(x[1] - 1)][y[1]] && availability[(x[1] + 1)][y[1]]
                                    && (!availability[(x[1])][4] || !availability[(x[1])][1])){
                                    matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] || !availability[(x[1] + 1)][y[1]])
                                    && (availability[(x[1])][4] && availability[(x[1])][1])){
                                    matrix[(x[1])][4] = triple[i].charAt(0);
                                    matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] == 4 && x[1]<4 && x[1]>0){
                                if (availability[(x[1] - 1)][y[1]] && availability[(x[1] + 1)][y[1]]
                                    && (!availability[(x[1])][3] || !availability[(x[1])][0])){
                                    matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] || !availability[(x[1] + 1)][y[1]])
                                    && (availability[(x[1])][0] && availability[(x[1])][3])){
                                    matrix[(x[1])][3] = triple[i].charAt(0);
                                    matrix[(x[1])][0] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] > 0 && y[1] < 4 && x[1] == 0){
                                if (availability[(4)][y[1]] && availability[1][y[1]]
                                    && (!availability[(x[1])][y[1]-1] || !availability[(x[1])][y[1]+1])){
                                    matrix[4][y[1]] = triple[i].charAt(0);
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[4][y[1]] || !availability[(x[1] +1)][y[1]])
                                    && (availability[(x[1])][(y[1] - 1)] && availability[(x[1])][(y[1] + 1)])){
                                    matrix[(x[1])][(y[1] - 1)] = triple[i].charAt(0);
                                    matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] > 0 && y[1] < 4 && x[1] == 4){
                                if (availability[0][y[1]] && availability[3][y[1]]
                                    && (!availability[(x[1])][y[1]-1] || !availability[(x[2])][y[1]+1])){
                                    matrix[3][y[1]] = triple[i].charAt(0);
                                    matrix[0][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] || !availability[0][y[1]])
                                    && (availability[(x[1])][(y[1] - 1)] && availability[(x[1])][(y[1] + 1)])){
                                    matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(0);
                                    matrix[(x[1])][(y[1] - 1)] = triple[i].charAt(1);
                                }
                            }
                            else if (y[1] < 4 && x[1] < 4 && y[1] > 1 && x[1] > 1){
                                if (availability[(x[1] - 1)][y[1]] && availability[x[1]-2][y[1]]
                                    && (!availability[(x[1])][y[1]-1] || !availability[(x[1])][y[1]-2])){
                                    matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] || !availability[(x[1] - 2)][y[1]])
                                    && (availability[(x[1])][(y[1] - 2)] && availability[(x[1])][(y[1] - 1)])){
                                    matrix[(x[1])][(y[1] - 1)] = triple[i].charAt(0);
                                    matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(2);
                                }
                            }
                        }
                    } //if it find the First litter only of the triple on the matrix
                    else if (checkWanted[0] && !checkWanted[1] && !checkWanted[2]) {
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4) {
                            matrix[(x[0] + 1)][y[0]] = triple[i].charAt(1);
                            matrix[(x[0] + 2)][y[0]] = triple[i].charAt(2);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0) {
                            matrix[(x[0])][(y[0] + 1)] = triple[i].charAt(1);
                            matrix[(x[0])][(y[0] + 2)] = triple[i].charAt(2);
                        }
                        else{
                            if (y[0] == 4 && x[0]<3){
                                if (availability[(x[0] + 1)][y[0]] && availability[(x[0] + 2)][y[0]]
                                    && (!availability[(x[0])][0] || !availability[(x[0])][1])){
                                    matrix[(x[0] + 1)][y[0]] = triple[i].charAt(1);
                                    matrix[(x[0] + 2)][y[0]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[0] + 1)][y[0]] || !availability[(x[0] + 2)][y[0]])
                                    && (availability[(x[0])][0] && availability[(x[0])][1])){
                                    matrix[(x[0])][1] = triple[i].charAt(2);
                                    matrix[(x[0])][0] = triple[i].charAt(1);
                                }
                            }
                            else if (y[0] == 3 && x[0] <3){
                                if (availability[(x[0] + 1)][y[0]] && availability[(x[0] + 2)][y[0]]
                                    && (!availability[(x[0])][4] || !availability[(x[0])][0])){
                                    matrix[(x[0] + 2)][y[0]] = triple[i].charAt(2);
                                    matrix[(x[0] + 1)][y[0]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[0] + 1)][y[0]] || !availability[(x[0] + 2)][y[0]])
                                    && (availability[(x[0])][4] && availability[(x[0])][0])){
                                    matrix[(x[0])][4] = triple[i].charAt(2);
                                    matrix[(x[0])][0] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] < 3 && x[2] == 4){
                                if (availability[0][y[2]] && availability[1][y[2]]
                                    && (!availability[(x[2])][y[2]+1] || !availability[(x[2])][y[2]+2])){
                                    matrix[0][y[2]] = triple[i].charAt(1);
                                    matrix[1][y[2]] = triple[i].charAt(2);
                                }
                                else if ((!availability[0][y[2]] || !availability[1][y[2]])
                                    && (availability[(x[2])][(y[2] + 2)] && availability[(x[2])][(y[2] + 1)])){
                                    matrix[(x[2])][(y[2] + 1)] = triple[i].charAt(1);
                                    matrix[(x[2])][(y[2] + 2)] = triple[i].charAt(2);
                                }
                            }
                            else if (y[2] < 3 && x[2] == 3){
                                if (availability[4][y[2]] && availability[0][y[2]]
                                    && (!availability[(x[2])][y[2]+1] || !availability[(x[2])][y[2]+2])){
                                    matrix[0][y[2]] = triple[i].charAt(2);
                                    matrix[4][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] + 1)][y[2]] || !availability[0][y[2]])
                                    && (availability[(x[2])][(y[2] + 2)] && availability[(x[2])][(y[2] + 1)])){
                                    matrix[(x[2])][(y[2] + 2)] = triple[i].charAt(2);
                                    matrix[(x[2])][(y[2] + 1)] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] < 3 && x[2] < 3){
                                if (availability[(x[2] + 1)][y[2]] && availability[x[2]+2][y[2]]
                                    && (!availability[(x[2])][y[2]+1] || !availability[(x[2])][y[2]+2])){
                                    matrix[(x[2] + 2)][y[2]] = triple[i].charAt(2);
                                    matrix[(x[2] + 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] + 1)][y[2]] || !availability[(x[2] + 2)][y[2]])
                                    && (availability[(x[2])][(y[2] + 2)] && availability[(x[2])][(y[2] + 1)])){
                                    matrix[(x[2])][(y[2] + 2)] = triple[i].charAt(2);
                                    matrix[(x[2])][(y[2] + 1)] = triple[i].charAt(1);
                                }
                            }
                        }

                    } //if it find the last & mid. litter of the triple on the matrix
                    else if (!checkWanted[0] && checkWanted[1] && checkWanted[2]) {
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4) {
                            matrix[(x[2] - 2)][y[2]] = triple[i].charAt(0);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0) {
                            matrix[(x[2])][(y[2] - 2)] = triple[i].charAt(0);
                        }
                        else{
                            if (y[1] > 0 && x[1]>0){
                                if (availability[(x[1] - 1)][y[1]]&& (!availability[(x[1])][y[1]-1 ] )){
                                    matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] && availability[(x[1])][y[1]-1])){
                                    matrix[(x[1])][y[1]-1] = triple[i].charAt(0);
                                }
                            }
                            else if (y[1] == 0 && x[1] > 0){
                                if (availability[(x[1] - 1)][y[1]] && !availability[(x[1])][4] ){
                                    matrix[(x[1] - 1)][y[1]] = triple[i].charAt(0);
                                }
                                else if ((!availability[(x[1] - 1)][y[1]] && availability[(x[1])][4])){
                                    matrix[(x[1])][4] = triple[i].charAt(0);
                                }
                            }
                            else if (y[1] > 0 && x[1] == 0){
                                if (availability[4][y[1]] && !availability[(x[1])][y[1]-1] ){
                                    matrix[4][y[1]] = triple[i].charAt(0);
                                }
                                else if ((!availability[4][y[1]] && availability[(x[1])][(y[1]-1)])){
                                    matrix[(x[1])][(y[1] - 1)] = triple[i].charAt(0);
                                }
                            }
                            else if (y[1] == 0 && x[1] == 0){
                                if (availability[4][y[1]] && !availability[(x[1])][4] ){
                                    matrix[4][y[1]] = triple[i].charAt(0);
                                }
                                else if ((!availability[4][y[1]] && availability[(x[1])][4])){
                                    matrix[(x[1])][4] = triple[i].charAt(0);
                                }
                            }
                        }
                    } //if it find the last & first litter of the triple on the matrix
                    else if (checkWanted[0] && !checkWanted[1] && checkWanted[2]) {
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4) {
                            matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0) {
                            matrix[(x[2])][(y[0] + 1)] = triple[i].charAt(1);
                        }
                        else{
                            if (y[2] > 0 && x[2]>0){
                                if (availability[(x[2] - 1)][y[2]]&& (!availability[(x[2])][y[2]-1 ] )){
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] && availability[(x[2])][y[2]-1])){
                                    matrix[(x[2])][y[2]-1] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] == 0 && x[2] > 0){
                                if (availability[(x[2] - 1)][y[2]] && !availability[(x[2])][4] ){
                                    matrix[(x[2] - 1)][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[(x[2] - 1)][y[2]] && availability[(x[2])][4])){
                                    matrix[(x[2])][4] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] > 0 && x[2] == 0){
                                if (availability[4][y[2]] && !availability[(x[2])][y[2]-1] ){
                                    matrix[4][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[4][y[2]] && availability[(x[2])][(y[2]-1)])){
                                    matrix[(x[2])][(y[2] - 1)] = triple[i].charAt(1);
                                }
                            }
                            else if (y[2] == 0 && x[2] == 0){
                                if (availability[4][y[2]] && !availability[(x[2])][4] ){
                                    matrix[4][y[2]] = triple[i].charAt(1);
                                }
                                else if ((!availability[4][y[2]] && availability[(x[2])][4])){
                                    matrix[(x[2])][4] = triple[i].charAt(1);
                                }
                            }
                        }
                    } //if it find the first & mid. litter of the triple on the matrix
                    else if (checkWanted[0] && checkWanted[1] && !checkWanted[2]) {
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4) {
                            matrix[(x[1] + 1)][y[1]] = triple[i].charAt(1);
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0) {
                            matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(1);
                        }
                        else{
                            if (y[1] < 4  && x[1] < 4){
                                if (availability[(x[1] + 1)][y[1]]&& (!availability[(x[1])][y[1] + 1 ] )){
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] + 1)][y[1]] && availability[(x[1])][y[1]+1])){
                                    matrix[(x[1])][y[1] + 1] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] == 4 && x[1] < 4){
                                if (availability[(x[1] + 1)][y[1]] && !availability[(x[1])][0] ){
                                    matrix[(x[1] + 1)][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[(x[1] + 1)][y[1]] && availability[(x[1])][0])){
                                    matrix[(x[1])][0] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] < 4 && x[1] == 4){
                                if (availability[0][y[1]] && !availability[(x[1])][y[1] + 1] ){
                                    matrix[0][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[0][y[1]] && availability[(x[1])][(y[1] + 1)])){
                                    matrix[(x[1])][(y[1] + 1)] = triple[i].charAt(2);
                                }
                            }
                            else if (y[1] == 4 && x[1] == 4){
                                if (availability[0][y[1]] && !availability[(x[1])][0] ){
                                    matrix[0][y[1]] = triple[i].charAt(2);
                                }
                                else if ((!availability[0][y[1]] && availability[(x[1])][0])){
                                    matrix[(x[1])][0] = triple[i].charAt(2);
                                }
                            }
                        }
                    } //if it dosen't fond any litter at the matrix
                    else if (!checkWanted[0] && !checkWanted[1] && !checkWanted[2]) {
                        //if the next letter is in the matrix
                        if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0
                                && checkWanted[3]) {
                            switch (y[3]) {
                                case 0:
                                    matrix[(x[3] - 1)][1] = triple[i].charAt(0);
                                    matrix[(x[3] - 1)][3] = triple[i].charAt(1);
                                    matrix[(x[3] - 1)][4] = triple[i].charAt(2);
                                    break;
                                case 1:
                                    matrix[(x[3])][3] = triple[i].charAt(0);
                                    matrix[(x[3])][4] = triple[i].charAt(1);
                                    matrix[(x[3])][0] = triple[i].charAt(2);
                                    break;
                                case 2:
                                    matrix[(x[3])][4] = triple[i].charAt(0);
                                    matrix[(x[3])][0] = triple[i].charAt(1);
                                    matrix[(x[3])][1] = triple[i].charAt(2);
                                    break;
                                default:
                                    matrix[(x[3])][(y[3] - 3)] = triple[i].charAt(0);
                                    matrix[(x[3])][(y[3] - 2)] = triple[i].charAt(1);
                                    matrix[(x[3])][(y[3] - 1)] = triple[i].charAt(2);
                                    break;
                            }
                        } //if it fond a triple at the same colomn with z at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4
                                && (triple[i].charAt(2) == 'z')) {
                            matrix[2][4] = triple[i].charAt(0);
                            matrix[3][4] = triple[i].charAt(1);
                            matrix[4][4] = triple[i].charAt(2);
                        } //if it fond a triple at the same colomn with y at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4
                                && (triple[i].charAt(2) == 'y')) {
                            matrix[2][3] = triple[i].charAt(0);
                            matrix[3][3] = triple[i].charAt(1);
                            matrix[4][3] = triple[i].charAt(2);
                            //if it fond a triple at the same colomn with x at the end
                        } else if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4
                                && (triple[i].charAt(2) == 'x')) {
                            matrix[2][2] = triple[i].charAt(0);
                            matrix[3][2] = triple[i].charAt(1);
                            matrix[4][2] = triple[i].charAt(2);
                        } //if it fond a triple at the same colomn with w at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4
                                && (triple[i].charAt(2) == 'w')) {
                            matrix[2][1] = triple[i].charAt(0);
                            matrix[3][1] = triple[i].charAt(1);
                            matrix[4][1] = triple[i].charAt(2);
                        } //if it fond a triple at the same colomn with v at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) > 4 && (triple[i].charAt(2) - triple[i].charAt(1)) > 4
                                && (triple[i].charAt(2) == 'v')) {
                            matrix[2][0] = triple[i].charAt(0);
                            matrix[3][0] = triple[i].charAt(1);
                            matrix[4][0] = triple[i].charAt(2);
                        } //if it fond a triple aw the same row with z at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0
                                && (triple[i].charAt(2) == 'z')) {
                            matrix[4][2] = (char) triple[i].charAt(0);
                            matrix[4][3] = (char) triple[i].charAt(1);
                            matrix[4][4] = (char) triple[i].charAt(2);
                        } //if it fond a triple aw the same row with y at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0
                                && (triple[i].charAt(2) == 'y')) {
                            matrix[4][1] = (char) triple[i].charAt(0);
                            matrix[4][2] = (char) triple[i].charAt(1);
                            matrix[4][3] = (char) triple[i].charAt(2);
                        } //if it fond a triple aw the same row with x at the end
                        else if ((triple[i].charAt(1) - triple[i].charAt(0)) < 5 && (triple[i].charAt(1) - triple[i].charAt(0)) >= 0
                                && (triple[i].charAt(2) - triple[i].charAt(1)) < 5 && (triple[i].charAt(2) - triple[i].charAt(1)) >= 0
                                && (triple[i].charAt(2) == 'x')) {
                            matrix[4][0] = (char) triple[i].charAt(0);
                            matrix[4][1] = (char) triple[i].charAt(1);
                            matrix[4][2] = (char) triple[i].charAt(2);
                        }
                        else if (!checkWanted[4]){
                            matrix[0][0] = (char) triple[i].charAt(0);
                            matrix[0][1] = (char) triple[i].charAt(1);
                            matrix[0][2] = (char) triple[i].charAt(2);
                            
                        }
                    }

                    //print the matrix
                    /*System.out.println(matrix[0][0] + "|" + matrix[0][1] + "|" + matrix[0][2] + "|" + matrix[0][3] + "|" + matrix[0][4]);
                    System.out.println(matrix[1][0] + "|" + matrix[1][1] + "|" + matrix[1][2] + "|" + matrix[1][3] + "|" + matrix[1][4]);
                    System.out.println(matrix[2][0] + "|" + matrix[2][1] + "|" + matrix[2][2] + "|" + matrix[2][3] + "|" + matrix[2][4]);
                    System.out.println(matrix[3][0] + "|" + matrix[3][1] + "|" + matrix[3][2] + "|" + matrix[3][3] + "|" + matrix[3][4]);
                    System.out.println(matrix[4][0] + "|" + matrix[4][1] + "|" + matrix[4][2] + "|" + matrix[4][3] + "|" + matrix[4][4]);
                    System.out.println("_____________________________________");
                  */   
                }
                for (int c = 0; (c < size / 2); ++c) {
                    //restart the variables
                    for (int l = 0; l < 25; l++) {
                        x[l] = 10;
                        y[l] = 10;
                        checkWanted[l] = false;
                    }
                    //get the information of every char at the matrix
                    int checkCounter = 0;
                    for (int k = 4; k >= 0; k--) {
                        for (int j = 4; j >= 0; j--) {
                            if (matrix[k][j] != '0') {
                                x[checkCounter] = k;
                                y[checkCounter] = j;
                                wanted[checkCounter] = matrix[k][j];
                                checkWanted[checkCounter] = true;
                                checkCounter++;
                            }
                        }
                    }
                    //auto complete the matrix
                    int checkNum, checkNum2;
                    for (int k = 0; k < 24; k++) {
                        checkNum = wanted[k] - wanted[k + 1];
                        checkNum2 = (x[k] * 5 + y[k]) - (x[k + 1] * 5 + y[k + 1]);
                        if (checkNum == checkNum2 && checkNum < 5) {
                            for (int j = 1; j < (checkNum); j++) {
                                if (y[k] == 0) {
                                    matrix[(x[k] - 1)][4] = (char) (wanted[k] - j);
                                } else {
                                    matrix[(x[k])][(y[k] - j)] = (char) (wanted[k] - j);
                                }
                            }
                        }
                    }
                }
                for (int m = 0; m < 100; m++) {
                    for (int c = 0; (c < size / 2); ++c) {
                        //restart the variables
                        for (int l = 0; l < 25; l++) {
                            x[l] = 10;
                            y[l] = 10;
                            checkWanted[l] = false;
                        }
                        //get the variable it need to check
                        wanted[0] = original[c].charAt(0);
                        wanted[1] = original[c].charAt(1);
                        wanted[2] = encoded[c].charAt(0);
                        wanted[3] = encoded[c].charAt(1);
                        //check if there are any variables we can use in the matrix
                        for (int k = 0; k < 5; k++) {
                            for (int j = 0; j < 5; j++) {
                                if (matrix[k][j] == wanted[0]) {
                                    x[0] = k;
                                    y[0] = j;
                                    checkWanted[0] = true;
                                } else if (matrix[k][j] == wanted[1]) {
                                    x[1] = k;
                                    y[1] = j;
                                    checkWanted[1] = true;
                                } else if (matrix[k][j] == wanted[2]) {
                                    x[2] = k;
                                    y[2] = j;
                                    checkWanted[2] = true;
                                } else if (matrix[k][j] == wanted[3]) {
                                    x[3] = k;
                                    y[3] = j;
                                    checkWanted[3] = true;
                                }
                            }
                        }
                        //if the all original and encoded characters are in the matrix 
                        if (checkWanted[0] && checkWanted[1] && checkWanted[2] && checkWanted[3]); //if the both original characters are in the matrix
                        else if (checkWanted[0] && checkWanted[1]) {
                            //if it's in the same row
                            if (x[0] == x[1]) {
                                if (y[0] == 4) {
                                    matrix[x[1]][0] = encoded[c].charAt(0);
                                } else {
                                    matrix[x[1]][(y[0] + 1)] = encoded[c].charAt(0);
                                }
                                if (y[1] == 4) {
                                    matrix[x[1]][0] = encoded[c].charAt(1);
                                } else {
                                    matrix[x[1]][(y[1] + 1)] = encoded[c].charAt(1);
                                }
                            } //if it's in the same colomn
                            else if (y[0] == y[1]) {
                                if (x[0] == 4) {
                                    matrix[0][y[0]] = encoded[c].charAt(0);
                                } else {
                                    matrix[(x[0] + 1)][y[0]] = encoded[c].charAt(0);
                                }
                                if (x[1] == 4) {
                                    matrix[0][y[1]] = encoded[c].charAt(1);
                                } else {
                                    matrix[(x[1] + 1)][y[1]] = encoded[c].charAt(1);
                                }
                            } //if it's not at the same row or colomn
                            else {
                                matrix[x[1]][y[0]] = encoded[c].charAt(0);
                                matrix[x[0]][y[1]] = encoded[c].charAt(1);
                            }
                        } //if the both encoded characters are in the matrix
                        else if (checkWanted[2] && checkWanted[3]) {
                            //if it's in the same row
                            if (x[2] == x[3]) {
                                if (y[2] == 0) {
                                    matrix[x[2]][4] = original[c].charAt(0);
                                } else {
                                    matrix[x[2]][(y[2] - 1)] = original[c].charAt(0);
                                }
                                if (y[3] == 0) {
                                    matrix[x[2]][4] = original[c].charAt(1);
                                } else {
                                    matrix[x[2]][(y[3] - 1)] = original[c].charAt(1);
                                }
                            } //if it's in the same colomn
                            else if (y[2] == y[3]) {
                                if (x[2] == 0) {
                                    matrix[4][y[2]] = original[c].charAt(0);
                                } else {
                                    matrix[(x[2] - 1)][y[2]] = original[c].charAt(0);
                                }
                                if (x[3] == 0) {
                                    matrix[4][y[3]] = original[c].charAt(1);
                                } else {
                                    matrix[(x[3] - 1)][y[3]] = original[c].charAt(1);
                                }
                            } //if it's not in the same row or colomn
                            else {
                                matrix[x[3]][y[2]] = original[c].charAt(0);
                                matrix[x[2]][y[3]] = original[c].charAt(1);
                            }
                        } else if (checkWanted[0] && checkWanted[2]) {
                            //if it's in the same row
                            if (x[0] == x[2]) {
                                for (int p = 4; p < 9; p++) {
                                    if (matrix[x[0]][p - 4] == '0') {
                                        y[p] = p - 4;
                                    }
                                }
                                for (int p = 4; p < 9; p++) {
                                    if (y[p] + 1 == y[p + 1]) {
                                        matrix[x[2]][y[p]] = original[c].charAt(1);
                                        matrix[x[2]][y[p + 1]] = encoded[c].charAt(1);
                                    }

                                }
                            } //if it's in the same colomn
                            else if (y[0] == y[2]) {
                                for (int p = 4; p < 9; p++) {
                                    if (matrix[p - 4][y[0]] == '0') {
                                        x[p] = p - 4;
                                    }
                                }
                                for (int p = 4; p < 9; p++) {
                                    if (x[p] + 1 == x[p + 1]) {
                                        matrix[x[p]][y[2]] = original[c].charAt(1);
                                        matrix[x[p + 1]][y[2]] = encoded[c].charAt(1);
                                    }

                                }
                            }
                        }

                    }
                }

            }
            int checkCounter = 0;
                    for (int k = 4; k >= 0; k--) {
                        for (int j = 4; j >= 0; j--) {
                            if (matrix[k][j] != '0') {
                                x[checkCounter] = k;
                                y[checkCounter] = j;
                                wanted[checkCounter] = matrix[k][j];
                                checkWanted[checkCounter] = true;
                                checkCounter++;
                            }
                        }
                    }
                    //auto complete the matrix
                    int checkNum, checkNum2;
                    for (int k = 0; k < 24; k++) {
                        for (int p = 97; p < 123; p++) {
                            for (int j = 0; j < 5; j++) {
                                for (int i = 0; i < 5; i++) {
                                    if (matrix[j][i] == p) {
                                        availableChars[p - 97] = false;
                                    }
                                }
                            }
                        }
                        checkNum = wanted[k+1] - wanted[k];
                        checkNum2 = (x[k+1] * 5 + y[k+1]) - (x[k] * 5 + y[k]);
                        if (checkNum == checkNum2 && checkNum < 5) {
                            for (int j = 1; j < (checkNum); j++) {
                                if (y[k] == 0) {
                                    matrix[(x[k] - 1)][4] = (char) (wanted[k] - j);
                                } else {
                                    matrix[(x[k])][(y[k] - j)] = (char) (wanted[k] - j);
                                }
                            }
                        }
                        else if (checkNum == checkNum2 +1 && checkNum2 < 5) {
                            for (int j = 0; j < (checkNum2); j++) {
                                if(availableChars[k+j]){
                                if (y[k] == 4) {
                                    matrix[(x[k+1] + 1)][0] = (char) (wanted[k+1] + j);
                                } else {
                                    matrix[(x[k+1])][(y[k+1] + j)] = (char) (wanted[k+1] + j);
                                }
                            }
                            }
                        
                        }
                    }

            //print the matrix
            System.out.println(matrix[0][0] + "|" + matrix[0][1] + "|" + matrix[0][2] + "|" + matrix[0][3] + "|" + matrix[0][4]);
            System.out.println(matrix[1][0] + "|" + matrix[1][1] + "|" + matrix[1][2] + "|" + matrix[1][3] + "|" + matrix[1][4]);
            System.out.println(matrix[2][0] + "|" + matrix[2][1] + "|" + matrix[2][2] + "|" + matrix[2][3] + "|" + matrix[2][4]);
            System.out.println(matrix[3][0] + "|" + matrix[3][1] + "|" + matrix[3][2] + "|" + matrix[3][3] + "|" + matrix[3][4]);
            System.out.println(matrix[4][0] + "|" + matrix[4][1] + "|" + matrix[4][2] + "|" + matrix[4][3] + "|" + matrix[4][4]);
            System.out.println("_____________________________________");
        }

    }
}
