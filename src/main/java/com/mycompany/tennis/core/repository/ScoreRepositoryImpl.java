package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryImpl {

    public void create(Score score){

        Connection conn = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR ( ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5)  VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedstatement.setLong(1, score.getMatch().getId());
            preparedstatement.setByte(2, score.getSet_1());
            preparedstatement.setByte(3, score.getSet_2());
            preparedstatement.setByte(4, score.getSet_3());
            preparedstatement.setByte(5, score.getSet_4());
            preparedstatement.setByte(6, score.getSet_5());


            preparedstatement.executeUpdate();

            //auto incremente de l'ID
            ResultSet rs = preparedstatement.getGeneratedKeys();
            //boucle
            if (rs.next()) {
               score.setId( rs.getLong(1));
            }

            System.out.println("Score créé");

            //si une erreur d'accès à la base de données se produit
        } catch (SQLException e) {
            e.printStackTrace();

            //retour en arriere de la validation
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
