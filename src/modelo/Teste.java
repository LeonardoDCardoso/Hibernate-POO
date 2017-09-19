/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Cardoso
 */
public class Teste {
    
    
    public static void salvar(Session sessao){
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        try {
            String nome =JOptionPane.showInputDialog(null, "Nome:");
           int idade =Integer.parseInt(JOptionPane.showInputDialog(null, "Idade:"));
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setIdade(idade);
            sessao.save(pessoa);
            sessao.getTransaction().commit();
            sessao.close();
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Operacao mal sucedida "+e);
        }
        
    }
    
    public static void atualizar(Session sessao){
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        try {
            int id =Integer.parseInt(JOptionPane.showInputDialog(null, "Id:"));
            Pessoa pessoa = (Pessoa)sessao.get(Pessoa.class, id);
            String nome =JOptionPane.showInputDialog(null, "Nome:");
            int idade =Integer.parseInt(JOptionPane.showInputDialog(null, "Idade:"));
            pessoa.setNome(nome);
            pessoa.setIdade(idade);
            sessao.saveOrUpdate(pessoa);
            sessao.getTransaction().commit();
            sessao.close();
            JOptionPane.showMessageDialog(null, "Actualizado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Operacao mal sucedida "+e);
        }
        
    }
    
    public static void remover(Session sessao){
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        try {
            int id =Integer.parseInt(JOptionPane.showInputDialog(null, "Id: "));
            Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            sessao.delete(pessoa);
            sessao.getTransaction().commit();
            sessao.close();    
            JOptionPane.showMessageDialog(null, "removido com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Operacao mal sucedida "+e);
        }
    }
    
    public static ArrayList<Pessoa> listar(Session sessao){
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        sessao = sessionFactory.openSession();
        sessao.beginTransaction();
        ArrayList<Pessoa> arrayList = new ArrayList();
        Criteria pessoa = sessao.createCriteria(Pessoa.class);
        arrayList.addAll((ArrayList) pessoa.list());
        sessao.close();
        return arrayList;
    }
    
    public static void main(String[] args) {
        SessionFactory sessionFactory= NewHibernateUtil.getSessionFactory();
        //SessionFactory sessionFactory= new AnnotationConfiguration().configure().buildSessionFactory();
        Session sessao = sessionFactory.openSession();
       // salvar(sessao);
        //sessao.beginTransaction();
//        Pessoa pessoa = new Pessoa();
//        pessoa.setIdade(18);
//        pessoa.setNome("Marco");
//        Pessoa pessoa1 = new Pessoa();
//        pessoa1.setIdade(18);
//        pessoa1.setNome("Cabeca");
//        sessao.save(pessoa);
//        sessao.save(pessoa1);
//        sessao.getTransaction().commit();
         //remover(sessao);
         //atualizar(sessao);
        // ArrayList<Pessoa> al = new ArrayList();
//         al = listar(sessao);
//         
        //Pessoa pessoa2 = (Pessoa)sessao.get(Pessoa.class, 5);
        //System.out.println(" ID: "+pessoa2.getId()+"\n Nome: "+pessoa2.getNome()+"\n Idade: "+pessoa2.getIdade());
        //sessao.close();
        int numero = 0;
        while((numero!=5)){
            numero = Integer.parseInt(JOptionPane.showInputDialog(null,"1: Salvar"+"\n"+"2: Remover"+"\n"+"3: Actualizar"+"\n"+"4: Listar"+"\n"+"5: Sair"));
            switch (numero){
                case 1 :{
                    salvar(sessao);
                };break;
                case 2 :{
                    remover(sessao);
                };break;
                case 3: {
                    atualizar(sessao);
                };break;
                case 4: {
                    for (Pessoa pessoa : listar(sessao)) {
              System.out.println(" ID: "+pessoa.getId()+"\n Nome: "+pessoa.getNome()+"\n Idade: "+pessoa.getIdade());
                };break;
                }
            }
        }
        
        sessionFactory.close();
    }
}
