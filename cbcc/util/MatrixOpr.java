package cbcc.util;

public class MatrixOpr {
    public double[][] mult (double[][] m1, double[][] m2){
        if(m1[0].length != m2.length) return null;
        double[][] mr = new double[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m2[0].length; j++)
                mr[i][j] = 0;
        for(int i = 0; i < m1.length; i++)
            for(int j = 0; j < m2[0].length; j++)
                for(int k = 0; k < m2.length; k++)
                    mr[i][j] += m1[i][k] * m2[k][j];
        return mr;
    }

}
