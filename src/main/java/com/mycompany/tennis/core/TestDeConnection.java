package com.mycompany.tennis.core;


import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.List;


public class TestDeConnection {
    public static void main(String [] args) {

        TournoiService tournoiService=new TournoiService();

        Tournoi binho = new Tournoi();
        binho.setNom("Binho open");
        binho.setCode("BO");
        tournoiService.createTournoi(binho);






        System.out.println("l'identifiant du joueur créé est "+ binho.getId() );
    }

}

