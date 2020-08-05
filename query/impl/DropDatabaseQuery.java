package chakri.query.impl;


import java.io.File;
import java.util.ArrayList;
import chakri.model.DatabaseConstants;
import chakri.prompt.Condition;
import chakri.prompt.DatabaseHelper;
import chakri.prompt.QueryHandler;
import chakri.prompt.Result;
import chakri.prompt.Utils;
import chakri.query.interfaces.IQuery;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */


public class DropDatabaseQuery implements IQuery {
    public String databaseName;

    public DropDatabaseQuery(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public Result ExecuteQuery() {
        File database = new File(Utils.getDatabasePath(databaseName));

        Condition condition = Condition.CreateCondition(String.format("database_name = '%s'", this.databaseName));
        ArrayList<Condition> conditions = new ArrayList<>();
        conditions.add(condition);

        IQuery deleteEntryQuery = new DeleteQuery(DatabaseConstants.DEFAULT_CATALOG_DATABASENAME, DatabaseConstants.SYSTEM_TABLES_TABLENAME, conditions, true);
        deleteEntryQuery.ExecuteQuery();

        deleteEntryQuery = new DeleteQuery(DatabaseConstants.DEFAULT_CATALOG_DATABASENAME, DatabaseConstants.SYSTEM_COLUMNS_TABLENAME, conditions, true);
        deleteEntryQuery.ExecuteQuery();

        boolean isDeleted = Utils.RecursivelyDelete(database);

        if(!isDeleted){
            Utils.printMessage(String.format("ERROR(200): Unable to delete database '%s'", this.databaseName));
            return null;
        }

        if(QueryHandler.ActiveDatabaseName == this.databaseName){
            QueryHandler.ActiveDatabaseName = "";
        }

        Result result = new Result(1);
        return result;
    }

    @Override
    public boolean ValidateQuery() {
        boolean databaseExists = DatabaseHelper.getDatabaseHelper().databaseExists(this.databaseName);

        if(!databaseExists){
            Utils.printMissingDatabaseError(this.databaseName);
            return false;
        }

        return true;
    }
}
