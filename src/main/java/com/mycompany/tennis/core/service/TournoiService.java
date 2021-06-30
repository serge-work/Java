package com.mycompany.tennis.core.service;


import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository=new TournoiRepositoryImpl();
    }

    public void createTournoi(Tournoi tournoi){
        tournoiRepository.create(tournoi);

    }
    public void getJoueur(long id){
        this.tournoiRepository.getById(id);
    }
}
