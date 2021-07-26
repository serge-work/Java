package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {

    public void create(Match match){

        Connection conn = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("INSERT INTO MATCH_TENNIS ( ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE)  VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedstatement.setLong(1, match.getEpreuve().getId());
            preparedstatement.setLong(2, match.getVainqueur().getId());
            preparedstatement.setLong(3, match.getFinaliste().getId());

            preparedstatement.executeUpdate();

            //auto incremente de l'ID
            ResultSet rs = preparedstatement.getGeneratedKeys();
            //boucle
            if (rs.next()) {
               match.setId( rs.getLong(1));
            }

            System.out.println("Match créé");

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
