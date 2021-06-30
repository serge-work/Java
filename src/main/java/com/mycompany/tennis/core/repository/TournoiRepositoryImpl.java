package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public void create(Tournoi tournoi) {
        Connection conn = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            PreparedStatement preparedstatement = conn.prepareStatement("INSERT INTO TOURNOI ( NOM,CODE)  VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedstatement.setString(1, tournoi.getNom());
            preparedstatement.setString(2, tournoi.getCode());

            preparedstatement.executeUpdate();

            ResultSet rs = preparedstatement.getGeneratedKeys();
//boucle
            if (rs.next()) {
                tournoi.setId(rs.getLong(1));
            }

            System.out.println("Tournoi créé");

            //si une erreur d'accès à la base de données se produit
        } catch (SQLException e) {
            e.printStackTrace();

            //retour en arriere de la validation
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Tournoi tournoi) {
        Connection conn = null;

        DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

        try {
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("update tournoi set nom=?, code=? where id=?");

            preparedStatement.setString(1, tournoi.getNom());
            preparedStatement.setString(2, tournoi.getCode());
            preparedStatement.setLong(3, tournoi.getId());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi modifié");


        } catch (SQLException e) {
            e.printStackTrace();

            //retour en arriere de la validation
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void delete(Long id) {

        Connection conn = null;

        DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

        try {
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("delete from tournoi where id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();


            System.out.println("Tournoi supprimé");
        } catch (SQLException e) {
            e.printStackTrace();

            //retour en arriere de la validation
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Tournoi getById(Long id) {
        Connection conn = null;
        Tournoi tournoi = null;

        DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

        try {
            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("select nom, code, id from tournoi where id=?");

            preparedStatement.setLong(1, tournoi.getId());

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                tournoi = new Tournoi();
                tournoi.setId(id);
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));

            }

            System.out.println("Joueur lu");
        } catch (SQLException e) {
            e.printStackTrace();

            //retour en arriere de la validation
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tournoi;
    }


    //affiche une liste de joueur
    public List<Tournoi> list(){

        Connection conn = null;
        List<Tournoi> tournois = new ArrayList<>();
        try {

            //connexion a la base de donnée configuré dans la classe DataSourceProvider
            DataSource dataSource=DataSourceProvider.getSingleDataSourceInstance();

            //établir la connexion avec la source de données que cet DataSourceObjet représente
            conn = dataSource.getConnection();

            //lire les données
            PreparedStatement preparedstatement=conn.prepareStatement("SELECT ID, NOM, CODE FROM  tournoi");

            ResultSet rs = preparedstatement.executeQuery();

            while (rs.next()) {
                Tournoi tournoi =new Tournoi();
                tournoi.setId(rs.getLong("ID"));
                tournoi.setNom(rs.getString("NOM"));
                tournoi.setCode(rs.getString("CODE"));

                tournois.add(tournoi);
            }

            System.out.println("Tournoi lus");

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
        return tournois;
    }

}
