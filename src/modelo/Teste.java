/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Cardoso
 */
public class Teste {
    public static void main(String[] args) {
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        //SessionFactory sessionFactory= new AnnotationConfiguration().configure().buildSessionFactory();
        Session sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        
        Pessoa pessoa = new Pessoa();
        pessoa.setIdade(18);
        pessoa.setNome("Marco");
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setIdade(18);
        pessoa1.setNome("Cabeca");
        sessao.save(pessoa);
        sessao.save(pessoa1);
        sessao.getTransaction().commit();
        sessao.close();
        sessionFactory.close();
    }
}
