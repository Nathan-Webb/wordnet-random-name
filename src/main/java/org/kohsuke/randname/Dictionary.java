package org.kohsuke.randname;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Dictionary of adjectives and nouns.
 *
 * @author Kohsuke Kawaguchi
 */
public class Dictionary {
    private List<String> nouns = new ArrayList<String>();
    private List<String> adjectives = new ArrayList<String>();
    private List<String> monsters = new ArrayList<String>();
    private List<String> verbs = new ArrayList<String>();

    private final int prime;

    public Dictionary() {
        try {
            load("a.txt", adjectives);
            load("n.txt", nouns);
            load("m.txt", monsters);
            load("v.txt", verbs);
        } catch (IOException e) {
            throw new Error(e);
        }

        int combo = size();

        int primeCombo = 2;
        while (primeCombo<=combo) {
            int nextPrime = primeCombo+1;
            primeCombo *= nextPrime;
        }
        prime = primeCombo+1;
    }

    /**
     * Total size of the combined words.
     */
    public int size() {
        return nouns.size()*adjectives.size();
    }

    /**
     * Sufficiently big prime that's bigger than {@link #size()}
     */
    public int getPrime() {
        return prime;
    }

    public String word() {

        Random random = new Random();

        int a = random.nextInt(adjectives.size());
        int n = random.nextInt(nouns.size());
        int m = random.nextInt(monsters.size());
        int v = random.nextInt(verbs.size());
        return verbs.get(v) + "_" + adjectives.get(a) + "_" + monsters.get(m);
    }

    private void load(String name, List<String> col) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(name),"US-ASCII"));
        try {
            String line;
            while ((line=r.readLine())!=null)
                col.add(line);
        } finally {
            r.close();
        }
    }

    static final Dictionary INSTANCE = new Dictionary();
}
