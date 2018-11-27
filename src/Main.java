import java.io.*;
import java.util.ArrayList;

public class Main {

    private static final int MAX_N = 105;
    static ArrayList<Integer> from = new ArrayList<>();
    static ArrayList<Integer> to = new ArrayList<>();
    static ArrayList<Integer> hamiltonianPath = new ArrayList<>();
    static ArrayList<Integer> vertex = new ArrayList<>();
    static int[] color = new int[MAX_N * MAX_N + 5];
    static int n, m, tmp1, tmp2, edge1, edge2;

    static boolean dfs(int v, int k) {
        color[v] = k;
        for (int i = 0; i < from.size(); i++) {
            edge1 = v;
            edge2 = i;
            if (vertex.get(from.get(edge1)) > vertex.get(from.get(edge2))) {
                int tmp = edge1;
                edge1 = edge2;
                edge2 = tmp2;
            }
            if ((vertex.get(from.get(edge1)) < vertex.get(from.get(edge2))) &&
                    (vertex.get(from.get(edge2)) < vertex.get(to.get(edge1))) &&
                    (vertex.get(to.get(edge1)) < vertex.get(to.get(edge2)))) {
                if (color[i] != 0) {
                    if (k == 1) {
                        dfs(i, 2);
                    } else {
                        dfs(i, 1);
                    }
                } else {
                    if (color[edge1] == color[edge2]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n, m;
            String[] argus;
            argus = in.readLine().split(" ");
            n = Integer.parseInt(argus[0]);
            m = Integer.parseInt(argus[1]);
            for (int i = 0; i < n; i++) {
                vertex.add(i);
            }
            for (int i = 0; i < m; i++) {
                argus = in.readLine().split(" ");
                tmp1 = Integer.parseInt(argus[0]) - 1;
                tmp2 = Integer.parseInt(argus[1]) - 1;
                from.add(tmp1);
                to.add(tmp2);
            }
            argus = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                hamiltonianPath.add(Integer.valueOf(argus[i]) - 1);
                vertex.set(Integer.valueOf(argus[i]) - 1, i);
            }
            for (int i = 0; i < m; i++) {
                if (vertex.get(from.get(i)) > vertex.get(to.get(i))) {
                    int t = from.get(i);
                    from.set(i, to.get(i));
                    to.set(i, t);
                }
            }
            boolean noAnswer = false;
            for (int i = 0; i < m; ++i) {
                if (color[i] == 0 && !dfs(i, 1)) {
                    noAnswer = true;
                    out.write("NO");
                }
            }
            if (!noAnswer) {
                out.write("YES\n");
                for (int i = 0; i < n; i++) {
                    out.write(String.valueOf(vertex.get(i)) + " 0 ");
                }
                out.write('\n');
                for (int i = 0; i < m; i++) {
                    out.write(String.valueOf((vertex.get(from.get(i)) + vertex.get(to.get(i))) / 2.0) + ' ');
                    if (color[i] == 1)
                        out.write(String.valueOf(Math.abs(vertex.get(from.get(i)) - vertex.get(to.get(i))) / 2.0));
                    else out.write(String.valueOf(Math.abs(vertex.get(from.get(i)) - vertex.get(to.get(i))) / -2.0));
                    out.write('\n');
                }
            }
        }
    }

}
