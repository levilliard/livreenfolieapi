/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import security.DataHash;

/**
 *
 * @author Amosse Edouard
 */
@Stateless
@Path("users")
public class UtilisateurFacadeREST extends AbstractFacade<Utilisateur> {
    
    /** TODO
     * 1/ Enlever les méthodes non nécessaires
     * 2/ Ajouter une méthode permettant d'authentifier un utilisateur par son user/password
     */

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    public UtilisateurFacadeREST() {
        super(Utilisateur.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Utilisateur entity) {
        entity.setPassword(DataHash.getHash(entity.getPassword().getBytes(), "MD5"));
        super.create(entity);
    }
    
    
    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Utilisateur login(Utilisateur entity) {
        String pass = DataHash.getHash(entity.getPassword().getBytes(), "MD5");
        System.out.println("Password: " + pass);
        TypedQuery<Utilisateur> user = 
        em.createQuery(
        "SELECT u FROM Utilisateur u WHERE u.login = :login and u.password = :password", Utilisateur.class)
        .setParameter("login", entity.getLogin()).setParameter("password", pass);
        
        return user.getSingleResult();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Utilisateur entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Utilisateur find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Utilisateur> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Utilisateur> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
