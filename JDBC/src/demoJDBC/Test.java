package demoJDBC;

import maprojetsbeans.Client;
import maprojetservice.ClientService;

public class Test { public static void main (String[] args) {
    ClientService cs = new ClientService(); //Création des clients
    cs.create(new Client("SAFI", "ali"));
    cs.create(new Client("ALAOUI", "widane"));
    cs.create(new Client("RAMI", "amine"));
    cs.create(new Client("ALAMI", "kamal"));
    cs.create(new Client("SELAMI", "mohamed"));
//Afficher le client dont id = 3 client
    Client c = cs.findById(3);
    if (c != null) {
        System.out.println(c.getNom() + " " + c.getPrenom());
    } else {
        System.out.println("Client with ID 3 not found.");
    }

//Supprimer le client dont id = 3
    cs.delete(cs.findById(3));
//Modifier le client dont id = 2
    Client cc = cs.findById(2);
    cc.setNom("nouveau nom");
    cc.setPrenom("nouveau Prénom");
    cs.update(c);
//Afficher la liste des clients
    for(Client cl : cs.findAll()) System.out.println(cl.getNom());
}}