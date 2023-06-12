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
            System.out.println("Tu as " + p.getNbEssais() + " essais");
            System.out.println("Quel lettre choisissez-vous ?");
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
        }
        this.p.reagir(rep);
    }
}
