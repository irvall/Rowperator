import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

class Rowperator {

    float[][] matrix;

    public Rowperator(String input) {
        matrix = parse(input);
        show();
    }

    public Rowperator() {
        Random r = new Random();
        int x = r.nextInt(10) + 1;
        int y = r.nextInt(10) + 1;
        matrix = new float[x][y];
        System.out.printf("Pseudo-randomly generated a %d x %d matrix\n", x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = r.nextInt(30);
            }
        }
        show();
    }

    private String wolframAlphaFormat() {
        String out = "{";

        for (int i = 0; i < matrix.length; i++) {
            out += "{";
            for (int j = 0; j < matrix[0].length; j++) {
                if (j != 0) {
                    out += ", ";
                }
                out += matrix[i][j];
            }

            out += "}";
            if (i < matrix.length - 1)
                out += ", ";

        }

        out += "}";
        return out;
    }

    private float[][] parse(String input) {
        String[] sa = input.split(" ");
        int row = Integer.parseInt(sa[0]);
        int col = Integer.parseInt(sa[1]);
        float[][] out = new float[row][col];
        int k = 2;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                out[i][j] = Float.parseFloat(sa[k++]);
            }
        }
        return out;
    }

    private void addTwoRows(int r1, int r2) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[r1][i] += matrix[r2][i];
        }
    }

    private void subtractTwoRows(int r1, int r2) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[r1][i] -= matrix[r2][i];
        }
    }

    private void multiplyRow(int row, int times) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= times;
        }
    }

    private void divideRow(int row, int times) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] /= times;
        }
    }

    private void swapRow(int r1, int r2) {
        for (int i = 0; i < matrix[0].length; i++) {
            float t = matrix[r1][i];
            matrix[r1][i] = matrix[r2][i];
            matrix[r2][i] = t;
        }
    }

    private void rowOperations(String op) {

        String[] a = op.split(" ");
        int aLen = a.length;

        char[] operators = new char[aLen / 2];
        String[] instructions = new String[(aLen / 2) + 1];

        int oi = 0, ii = 0;
        for (int i = 0; i < aLen; i++) {
            a[i] = a[i].trim().toLowerCase();
            if (a[i].length() == 1) {
                if (!Character.isDigit(a[i].charAt(0)))
                    operators[oi++] = a[i].charAt(0);
                else
                    instructions[ii++] = a[i];
            } else
                instructions[ii++] = a[i];
        }

        for (int i = 0; i < instructions.length - 1; i++) {
            if (operators[i] == '/' || operators[i] == '*') {

                String r = instructions[i];
                int times = Integer.parseInt(instructions[i + 1]);
                String[] rcomp = r.split("r");
                int row = Integer.parseInt(rcomp[1]) - 1;

                if (operators[i] == '/') {
                    divideRow(row, times);
                } else {
                    multiplyRow(row, times);
                }

                continue;
            }
            if (instructions.length > 1) {
                String r1 = instructions[i];
                String r2 = instructions[i + 1];
                String[] r1comp = r1.split("r");
                String[] r2comp = r2.split("r");
                int row1 = Integer.parseInt(r1comp[1]) - 1;
                int row2 = Integer.parseInt(r2comp[1]) - 1;
                if (r1comp[0].length() > 0) {
                    multiplyRow(row1, Integer.parseInt(r1comp[0]));
                }
                if (r2comp[0].length() > 0) {
                    multiplyRow(row2, Integer.parseInt(r2comp[0]));
                }

                switch (operators[i]) {
                case '+':
                    addTwoRows(row1, row2);
                    break;
                case '-':
                    subtractTwoRows(row1, row2);
                    break;
                case '/':
                    divideRow(row1, 4);
                    break;
                case 's':
                    swapRow(row1, row2);
                    break;
                }
            }
        }
    }

    private void show() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Rowperator rp;
        Scanner in = new Scanner(System.in);

        if (args.length > 0) {
            String i = "";
            for (String s : args) {
                i += s.trim() + " ";
            }
            rp = new Rowperator(i);
        } else {
            rp = new Rowperator();
        }

        while (true) {
            String s = in.nextLine();
            while (s.length() > 0) {
                rp.rowOperations(s);
                rp.show();
                s = "";
            }
        }
    }
}
