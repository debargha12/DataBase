package chakri.query.impl;


import java.io.File;
import chakri.prompt.DatabaseHelper;
import chakri.prompt.Result;
import chakri.prompt.Utils;
import chakri.query.interfaces.IQuery;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */



public class CreateDatabaseQuery implements IQuery {
    public String databaseName;

    public CreateDatabaseQuery(String databaseName){
        this.databaseName = databaseName;
    }

    @Override
    public Result ExecuteQuery() {
        File database = new File(Utils.getDatabasePath(this.databaseName));
        boolean isCreated = database.mkdir();

        if(!isCreated){
            System.out.println(String.format("ERROR(200): Unable to create database '%s'", this.databaseName));
            return null;
        }

        Result result = new Result(1);
        return result;
    }

    @Override
    public boolean ValidateQuery() {
        boolean databaseExists = DatabaseHelper.getDatabaseHelper().databaseExists(this.databaseName);

        if(databaseExists){
            System.out.println(String.format("ERROR(104D): Database '%s' already exists", this.databaseName));
            return false;
        }

        return true;
    }
}
