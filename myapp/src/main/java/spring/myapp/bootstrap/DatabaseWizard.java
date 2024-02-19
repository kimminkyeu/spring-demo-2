package spring.myapp.bootstrap;

import java.util.EnumSet;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import spring.myapp.domain.Author;

import java.util.HashMap;
import java.util.Map;

public class DatabaseWizard {
  /**
   * When we run this code, our database creation commands are exported into the create.sql file in our main project folder.
   * The SchemaExport is part of the Hibernate Bootstrapping API.
   */
  public static void exportDDL_V1() {
    Map<String, Object> settings = createRegistrySettings();
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
    MetadataSources metadataSources = new MetadataSources(serviceRegistry);
    metadataSources.addAnnotatedClass(Author.class);

    SchemaExport schemaExport = new SchemaExport();
    schemaExport.setFormat(true);
    schemaExport.setOutputFile("./ddl/create.sql");
    schemaExport.createOnly(EnumSet.of(TargetType.SCRIPT), metadataSources.buildMetadata());
  }

  private static Map<String, Object> createRegistrySettings() {
    Map<String, Object> settings = new HashMap<>();
    settings.put("connection.driver_class", "com.mysql.jdbc.Driver");
    settings.put("dialect", "org.hibernate.dialect.MySQLDialect");
    settings.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate_examples");
    settings.put("hibernate.connection.username", "root");
    settings.put("hibernate.connection.password", "password");
    settings.put("hibernate.show_sql", "true");
    settings.put("hibernate.format_sql", "true");
    return settings;
  }
}
