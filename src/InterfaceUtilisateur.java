import java.util.Scanner;

public class InterfaceUtilisateur {
    private Pendu p;
    private String nom;

    private Scanner sc;

    public InterfaceUtilisateur() {
        System.out.println("Salut, bienvenue dans le pendu");

        System.out.println("Quel est ton nom : ");
        this.sc = new Scanner(System.in);
        this.nom = sc.nextLine();
        System.out.println("Tu t'appelles " + this.nom);

        this.p = new Pendu(this);
    }

    public void demandeLettre(){
        char rep = 0;
        boolean verif = false;

        while (!verif){
            System.out.println();
            System.out.println("Il te reste " + p.getNbEssais() + " erreurs");

            System.out.print("Lettres utilisées : ");
            int i = 0;
            for(char c : this.p){
                if(i == p.nbLettre()-1){
                    System.out.print(c);
                }else{
                    System.out.print(c + ", ");
                }
                i++;
            }
            System.out.println();

            System.out.println("Quelle lettre choisissez-vous ?");
            String choix = sc.nextLine();

            if(choix.length() != 1){
                System.out.println("Ce n'est pas une lettre");
            }else {
                rep = choix.charAt(0);
                if(rep >= 65 && rep <= 92){
                    rep = (char) (rep + 32);
                    verif = true;
                }else if(rep >= 97 && rep <= 122){
                    verif = true;
                }else{
                    System.out.println("Ce n'est pas une lettre");
                }
            }

            for(char c : this.p){
                if(rep == c){
                    System.out.println("Lettre déjà utilisée");
                    verif = false;
                }
            }
        }
        this.p.reagir(rep);
    }
}
