import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class Pendu implements Iterable<Character> {
    private String mot;
    private String reponse;
    private InterfaceUtilisateur ui;
    private int nbEssais;
    private ArrayList<Character> lettreUtilise;

    public Pendu(InterfaceUtilisateur ui) {
        try {
            this.ui = ui;
            this.nbEssais = 5;
            this.lettreUtilise = new ArrayList<>();

            this.mot = choixMots();
            this.mot = correctionMots(mot);

            this.reponse = creationRep(mot);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String choixMots() throws IOException, URISyntaxException {
        String mot = null;

        File file = new File(Objects.requireNonNull(getClass().getResource("liste_mots.txt")).toURI());

        int nbLine = 0;
        Scanner numberLine = new Scanner(file);
        while (numberLine.hasNextLine()){
            numberLine.nextLine();
            nbLine++;
        }
        numberLine.close();


        Random r = new Random();
        int line = r.nextInt(nbLine);

        Scanner search = new Scanner(file);
        int i = 0;
        while (i != line){
            mot = search.nextLine();
            i++;
        }
        search.close();

        return mot;
    }

    private String correctionMots(String mot){
        mot = mot.replace('è', 'e');
        mot = mot.replace('ê', 'e');
        mot = mot.replace('é', 'e');
        mot = mot.replace('à', 'a');
        mot = mot.replace('ù', 'u');
        mot = mot.replace('î', 'i');
        mot = mot.replace('ï', 'i');

        return mot;
    }

    private String creationRep(String mot){
        return "." + ".".repeat(mot.length() - 1);
    }

    public void reagir(char rep){
        this.ajouterChar(rep);
        boolean trouve = true;
        for (int i = 0; i < this.mot.length(); i++) {
            if(this.mot.charAt(i) == rep){
                StringBuilder sb = new StringBuilder(this.reponse);
                sb.setCharAt(i, this.mot.charAt(i));
                this.reponse = sb.toString();
                trouve = false;
            }
        }
        if(trouve){
            this.setEssai();
        }

        System.out.println(this.reponse);

        if(verifFin(this.reponse)){
            System.out.println("Fin du jeu");
        } else if (nbEssais == 0) {
            System.out.println("Perdu");
            System.out.println("Le mot était : " + this.mot);
        } else{
            this.ui.demandeLettre();
        }
    }

    private boolean verifFin(String rep){
        boolean v = true;

        for (int i = 0; i < rep.length(); i++) {
            if (rep.charAt(i) == '.') {
                v = false;
                break;
            }
        }

        return v;
    }

    public int getNbEssais() {
        return nbEssais;
    }

    public void setEssai(){
        this.nbEssais--;
    }

    public void ajouterChar(char c){
        this.lettreUtilise.add(c);
    }

    public int nbLettre(){
        return this.lettreUtilise.size();
    }

    @Override
    public String toString() {
        return "Pendu{" +
                "mot='" + mot + '\'' +
                '}';
    }

    @Override
    public Iterator<Character> iterator() {
        return this.lettreUtilise.iterator();
    }
}
