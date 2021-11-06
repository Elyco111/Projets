import java.io.File;
import java.io.*;

public class Partie{
    private Joueur joueurN;
    private Joueur joueurB;
    private Plateau plat;
    private Verif verif;
    private Affichage affichage;
    private boolean changement;
    private boolean pat;
    private boolean mat;
    private boolean echec;
    private Piece piontransformable;
    private boolean saveInfo = false;


	public Partie(){}

    public Partie(int a){
        this.changement = false;
        this.affichage = new Affichage();
        this.verif = new Verif();
        this.plat = new Plateau();
        this.joueurB = new Joueur(new Couleur(true));
        this.joueurN = new Joueur(new Couleur(false));
    }

    public void lancerPartie(){
        int affdeppart = this.affichage.debutPartie();
        if (affdeppart == 0){
            this.plat.initialiser();

        }
        else{
            this.plat = charger();
            //this.plat.inistialMat();
        }
        Joueur winJ = tourettour();
        if(this.mat == true && this.echec){
            this.affichage.winner(winJ);
        }
        else if(this.pat == true){
            this.affichage.affPat();
        }
        else if(this.saveInfo == true){
            System.out.println("Vous avez quittez la partie, rassurez vous elle a bien été sauvegardée");
        }
    }

    public Joueur tourettour(){
        int j = 0;
        Joueur winoregal;
        for(boolean i = false; i == false; j++){
            if(j%2 == 0){
                if(this.echec == true){
                    //System.out.println(affichage.echequier(this.plat, this.joueurB));
                    this.joueurB = tourSousEchec(this.joueurB, this.plat);
                    //System.out.println(affichage.echequier(this.plat, this.joueurB));
}
                else{
                    //System.out.println(affichage.echequier(this.plat, this.joueurB));
                    winoregal = deroulTour(this.joueurB);
                    //System.out.println(affichage.echequier(this.plat, this.joueurB));
                }
                this.piontransformable = verif.verifPionTrans(this.plat, joueurB);
                if(this.piontransformable != null){
                    String lacase = "";
                    for(int r = 0; r<this.plat.getCases().size(); r++){
                        if(this.plat.getCases().get(r).getPiece() != null){
                            if(this.plat.getCases().get(r).getPiece().equals(this.piontransformable)){
                                lacase = this.plat.getCases().get(r).getNomcase();
                            }
                        }
                    }
                    this.plat = suprePiece(this.plat, this.piontransformable);
                    this.plat = addPiece(lacase, this.plat, this.plat.creaPiece(affichage.choixTransPion(this.piontransformable), piontransformable.getNumpiece()+2));
                    
                }
                if(this.mat==true && this.echec == true){
                    return joueurB;
                }        
            }
            else{
                if(this.echec == true){
                    //System.out.println(affichage.echequier(this.plat, this.joueurN));
                    this.joueurN = tourSousEchec(this.joueurN, this.plat);
                    //System.out.println(affichage.echequier(this.plat, this.joueurN));
                }
                else{
                    winoregal = deroulTour(this.joueurN);
                }
                this.piontransformable = verif.verifPionTrans(this.plat, joueurN);
                if(this.piontransformable != null){
                    String lacase = "";
                    for(int r = 0; r<this.plat.getCases().size(); r++){
                        if(this.plat.getCases().get(r).getPiece() != null){
                            if(this.plat.getCases().get(r).getPiece().equals(this.piontransformable)){
                                lacase = this.plat.getCases().get(r).getNomcase();
                            }
                        }
                    }
                    this.plat = suprePiece(this.plat, this.piontransformable);
                    this.plat = addPiece(lacase, this.plat, this.plat.creaPiece(affichage.choixTransPion(this.piontransformable), piontransformable.getNumpiece()+2));
                    
                }
                if(this.mat==true && this.echec == true){
                    return joueurN;
                }
            }
            if(this.pat == true){
                return null;
            }
            if(this.saveInfo == true){
                return null;
            } 
        }
        return null;
    }

    public Joueur tourSousEchec(Joueur joueurEnEchec, Plateau ancienPlat){
        boolean vEchec = true;
        while(vEchec == true){
            //System.out.println(affichage.echequier(ancienPlat, joueurEnEchec));
            this.plat = new Plateau(ancienPlat.getCases());
            //System.out.println(affichage.echequier(this.plat, joueurEnEchec));
            deroulTour(joueurEnEchec);
            //System.out.println(affichage.echequier(this.plat, joueurEnEchec));
            vEchec = verif.verifEchecPourAdversaire(this.plat, joueurEnEchec);
        }
        return joueurEnEchec;
    }
            
                /*System.out.println(this.affichage.echequier(this.plat, this.joueurN));
                this.affichage.letourdeJ(this.joueurN);


                while(this.changement == false){
                    choixcase = this.affichage.demandercaseo(this.plat, this.verif);
                    coup = this.affichage.demandercased(this.plat, this.verif);
                    this.plat = deplacer(choixcase, coup, this.plat, this.joueurN);
                }
                this.changement = false;


                if(verif.verifEchecPourAdversaire(this.plat, this.joueurN) == true){
                    this.mat = verif.verifMat(this.plat, this.joueurN);
                    this.echec = true;
                    if(this.mat == true){
                        return this.joueurN;
                    }
                }
                else{
                    this.echec = false;
                    this.pat = verif.verifPat(this.plat, this.joueurN);
                    if(this.pat == true){
                        return this.joueurN;
                    }
                }*/

    public Joueur deroulTour(Joueur joueurT){
            String choixcase;
            String coup;
            System.out.println(this.affichage.echequier(this.plat, joueurT)); 
            if(this.echec == true){
                System.out.println("Vous etes en ECHEC !");
            }               

            while(this.changement == false){
                choixcase = this.affichage.demandercaseo(this.plat, this.verif);
                if(choixcase.equals("save")){
                    sauvegarde(this.plat, joueurT);
                    this.saveInfo = true;
                    return null;
                }
                else{
                    //System.out.println(verif.verifTout(this.plat, choixcase));
                    coup = this.affichage.demandercased(this.plat, this.verif);
                    //System.out.println(affichage.echequier(this.plat, joueurT));
                    this.plat = new Plateau(deplacer(choixcase, coup, this.plat, joueurT).getCases());
                    //System.out.println(affichage.echequier(this.plat, joueurT));
                }
            }
            this.changement = false;
            Plateau savePlat = new Plateau(this.plat.getCases());
            if(verif.verifEchecPourAdversaire(savePlat, joueurT) == true){
                this.echec = true;
                if(verif.verifCounterEchec(savePlat, joueurT) == false){
                    //System.out.println("le counter echec renvoi false je pu le seum =============================================");
                    this.mat = verif.verifMat(savePlat, joueurT);
                    //System.out.println(this.mat);
                    if(this.mat == true){
                        return joueurT;
                    }
                }
            }
            else{
                this.echec = false;
                //System.out.println(verif.verifMat(savePlat, joueurT) + " juste pour etre sur que c'est le mat");
                this.mat = verif.verifMat(savePlat, joueurT);
                this.pat = verif.verifPat(savePlat, joueurT);
                //System.out.println(this.pat + "c'est pas important celui là");
                if(this.pat == true && this.mat == true){
                    this.mat = false;
                    return null;
                }
            }
        return null; //on return null ici donc c'est problème va falloir résoudre ça
    }

    public Plateau deplacer(String choixcase, String coup, Plateau plat, Joueur joueur){// cette fonction est le début de détéction de échec============
        //Affichage a = new Affichage();
        Verif v = new Verif();
        if(v.verifcoup(choixcase, coup, plat, joueur) == true){
            this.plat = new Plateau(plat.getCases());
            Piece piece = recupPiece( plat, choixcase);
            plat = suprePiece(plat, piece);
            plat = addPiece( coup,  plat,  piece);
            //System.out.println(a.echequier(plat, joueur));
            //System.out.println(a.echequier(this.plat, joueur));
            if(v.verifEchecPourJoueur(plat, joueur) == true){
                //System.out.println("Votre coup à pour conséquence de vous mettre en ECHEC, veuillez à donner un autre coup.");
                this.changement = false;
                return this.plat;
            }
            else{
                this.changement = true;
                return plat;
            }
            
            
        }
        else{
            this.changement = false;
            System.out.println("Votre coup n'est pas valide, veuillez donner un autre coup.");
            return plat;
        }
        
    }

    public Piece recupPiece(Plateau plat, String choixcase){
        for(int i = 0 ; i<plat.getCases().size() ; i+=1){
            if(choixcase.equals(plat.getCases().get(i).getNomcase())){ 
                return plat.getCases().get(i).getPiece();                              
            }        
        }
        return null;
    }

    public Plateau suprePiece(Plateau plat, Piece piece){
        for(int i = 0 ; i<plat.getCases().size() ; i+=1){
            if(piece == plat.getCases().get(i).getPiece()){
                plat.ajoutPiece(plat.getCases().get(i) , null);
            }
        }
        return plat;
    }

    public Plateau addPiece(String coup, Plateau plat, Piece piece){
        for(int i = 0 ; i<plat.getCases().size() ; i+=1){
            if(coup.equals(plat.getCases().get(i).getNomcase())){
                plat.ajoutPiece(plat.getCases().get(i) , piece);
            }
        }
        return plat;
    }

    
    public void sauvegarde(Plateau plat, Joueur joueur){
        String nomSave = affichage.save();
        File file = new File("saves/"+nomSave+".txt");//on crée le fichier.txt

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch(IOException e){
            System.out.println("y a une erreur je crois 2");
            }
        }

        try{
            FileWriter writer = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(writer);
            String numP;
            for(int i = 0; i<plat.getCases().size(); i++){
                if(plat.getCases().get(i).getPiece() != null){
                    numP = "";
                    numP = numP + plat.getCases().get(i).getPiece().getNumpiece();
                    bw.write(plat.getCases().get(i).getPiece().getClass().getName());
                    bw.newLine();
                    bw.write(plat.getCases().get(i).getPiece().getCouleur().getColor());
                    bw.newLine();
                    bw.write(numP);
                    bw.newLine();
                    bw.write(plat.getCases().get(i).getNomcase());
                    bw.newLine();
                }
            }
            /*bw.write("Ceci est un essaie");//permet d'écrire dans le fichier
            bw.newLine();//revient à la ligne
            bw.write("ese");
            bw.write("niie");*/
            bw.close();
            writer.close();
        }catch(IOException e){
            System.out.println("y a une erreur je crois 3");
        }

    }

    public Plateau charger(){
        Plateau charg = new Plateau();
        String nomCharg = affichage.demanderCharg();
        File file = new File("saves/"+nomCharg+".txt");

        while(!file.exists()){
            nomCharg = affichage.demanderCharg();
        }

        try{
            //BufferedReader reader = new FileReader(file);
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            //String line = "";
            String nomClass = "";
            String numP;
            String couleurP;
            String nomCase;
            Piece placer;
            while(nomClass != null){
                nomClass  = br.readLine();//si je ne me trompe pas, je lis la premier ligne
                couleurP  = br.readLine();
                numP  = br.readLine();
                nomCase  = br.readLine();  
                if(nomClass != null){
                    placer = charg.creaPiecev2(nomClass, numP, couleurP);
                    charg = addPiece(nomCase, charg, placer);
                }
            }



            br.close();
            reader.close();
        }catch(IOException e){
            System.out.println("y a une erreur dans charger");
            }
        
        return charg;
    } 
}