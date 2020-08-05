package chakri.query.impl;

import chakri.prompt.DatabaseHelper;
import chakri.prompt.QueryHandler;
import chakri.prompt.Result;
import chakri.prompt.Utils;
import chakri.query.interfaces.IQuery;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */



public class UseDatabaseQuery implements IQuery {
    public String databaseName;

    public UseDatabaseQuery(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public Result ExecuteQuery() {
        QueryHandler.ActiveDatabaseName = this.databaseName;
        Utils.printMessage("Database changed");
        return null;
    }

    @Override
    public boolean ValidateQuery() {
        boolean databaseExists = DatabaseHelper.getDatabaseHelper().databaseExists(this.databaseName);
        if(!databaseExists){
            Utils.printMissingDatabaseError(this.databaseName);
        }

        return databaseExists;
    }
}
