package chakri.query.impl;


import java.util.ArrayList;
import chakri.model.DatabaseConstants;
import chakri.prompt.Condition;
import chakri.prompt.DatabaseHelper;
import chakri.prompt.Result;
import chakri.prompt.Utils;
import chakri.query.interfaces.IQuery;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */


public class ShowTableQuery implements IQuery {

    public String databaseName;

    public ShowTableQuery(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public Result ExecuteQuery() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("table_name");

        Condition condition = Condition.CreateCondition(String.format("database_name = '%s'", this.databaseName));
        ArrayList<Condition> conditionList = new ArrayList<>();
        conditionList.add(condition);

        IQuery query = new SelectQuery(DatabaseConstants.DEFAULT_CATALOG_DATABASENAME, DatabaseConstants.SYSTEM_TABLES_TABLENAME, columns, conditionList, false);
        if (query.ValidateQuery()) {
            return query.ExecuteQuery();
        }

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
