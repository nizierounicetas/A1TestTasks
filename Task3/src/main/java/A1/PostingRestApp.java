package A1;

import A1.data.dbManagement.EntityManagerFactorySingleton;
import A1.data.migrations.CompanyCsvToSqlDbMigrator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostingRestApp {

	public static void main(String[] args) {
		/*try{
			CompanyCsvToSqlDbMigrator.migrate(EntityManagerFactorySingleton.getInstance().createEntityManager());
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}*/

		try
		{

			SpringApplication.run(PostingRestApp.class, args);
		} catch(Exception ex)
		{
			ex.printStackTrace();
			EntityManagerFactorySingleton.close();
		}
	}

}
