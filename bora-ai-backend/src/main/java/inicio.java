
import java.math.BigInteger;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import modulos.Usuario;

public class inicio {

	public static void main(String[] args) {
		Conexao app = new Conexao();
        //app.connect();
        
        SessionFactory sessionFactory;
        Configuration  configuracao = new  Configuration().
        setProperty("hibernate.dialect"                , "org.hibernate.dialect.PostgreSQLDialect").
        //setProperty("hibernate.cache.provider_class"   , "org.hibernate.cache.NoCacheProvider").
        setProperty("hibernate.connection.driver_class", "org.postgresql.Driver").
		setProperty("hibernate.connection.url"         , "jdbc:postgresql://localhost:5432/boraai").
		setProperty("hibernate.connection.username"    , "postgres").
		setProperty("hibernate.connection.password"    , "pgsql").
		setProperty("hibernate.hbm2ddl.auto"           , "update").
		setProperty("hibernate.show_sql"               , "true").
        setProperty("hibernate.format_sql"             , "true").
        //setProperty("hibernate.current_session_context_class", "thread").
        setProperty("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider").
        setProperty("hibernate.c3p0.min_size"          , "5").
        setProperty("hibernate.c3p0.max_size"          , "20").
        setProperty("hibernate.c3p0.timeout"           , "300").
        
        addAnnotatedClass(Usuario.class);
        
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.applySettings(configuracao.getProperties());
        
        StandardServiceRegistry standardServiceRegistry = standardServiceRegistryBuilder.build();
        sessionFactory = configuracao.buildSessionFactory(standardServiceRegistry); 
        
        testeRapido(sessionFactory);
	}
	
	private static void testeRapido(SessionFactory sessionFactory) {
		Usuario usuario = new Usuario(346519920);
        usuario.setNome("Deno");
        usuario.setDataNascimento(null);
        usuario.setEmail("valdenir@alunos.utfpr.edu.br");
        usuario.setTelefone("46 3536 7939");
        
        Session 	session = null;
        Transaction tx 	    = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.merge(usuario);
            tx.commit();
            
            System.out.println(usuario.getCpfCnpj() + " - " + usuario.getNome());
        } catch (Exception exception) {
            if (tx != null) {
              tx.rollback();
            }
            System.out.println("Erro ao gravar: " + exception.getMessage());
            exception.printStackTrace();
        } finally {
            session.close();
        }
	}
}
