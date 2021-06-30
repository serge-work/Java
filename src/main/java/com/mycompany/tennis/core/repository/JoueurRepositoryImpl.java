package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur){

        Connection conn = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("INSERT INTO JOUEUR ( NOM, PRENOM, SEXE)  VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedstatement.setString(1, joueur.getNom());
            preparedstatement.setString(2, joueur.getPrenom());
            preparedstatement.setString(3,joueur.getSexe().toString());

            preparedstatement.executeUpdate();

            //auto incremente de l'ID
            ResultSet rs = preparedstatement.getGeneratedKeys();
            //boucle
            if (rs.next()) {
               joueur.setId( rs.getLong(1));
            }

            System.out.println("Joueur créé");

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


    public void update(Joueur joueur){

        Connection conn = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //Etablir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            preparedstatement.setString(1, joueur.getNom());
            preparedstatement.setString(2, joueur.getPrenom());
            preparedstatement.setString(3,joueur.getSexe().toString());
            preparedstatement.setLong(4,joueur.getId());

            preparedstatement.executeUpdate();

            System.out.println("Joueur modifié");

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


    public void delete(Long id){

        Connection conn = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("delete FROM  JOUEUR WHERE ID=?");

            preparedstatement.setLong(1,id);
            preparedstatement.executeUpdate();

            System.out.println("Joueur supprimé");

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

    //affiche un joueur
    public Joueur getById(Long id){

        Connection conn = null;
        Joueur joueur = null;
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM  JOUEUR WHERE ID=?");

            preparedstatement.setLong(1,id);

            ResultSet rs = preparedstatement.executeQuery();

            if (rs.next()) {
                joueur=new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }

            System.out.println("Joueur lu");

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
        return joueur;
    }

    //affiche une liste de joueur
    public List<Joueur> list(){

        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM  JOUEUR");

            ResultSet rs = preparedstatement.executeQuery();

            while (rs.next()) {
                Joueur joueur=new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }

            System.out.println("Joueurs lus");

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
        return joueurs;
    }

}
