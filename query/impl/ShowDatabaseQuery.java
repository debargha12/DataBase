package chakri.query.impl;


import java.io.File;
import java.util.ArrayList;
import chakri.model.DatabaseConstants;
import chakri.prompt.Literal;
import chakri.prompt.Record;
import chakri.prompt.Result;
import chakri.prompt.ResultSet;
import chakri.query.interfaces.IQuery;

/**
 * Created by Chakriramoj on Apr 21, 2019
 *
 */



public class ShowDatabaseQuery implements IQuery {
    @Override
    public Result ExecuteQuery() {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Database");
        ResultSet resultSet = ResultSet.CreateResultSet();
        resultSet.setColumns(columns);
        ArrayList<Record> records = GetDatabases();

        for(Record record : records){
            resultSet.addRecord(record);
        }

        return resultSet;
    }

    @Override
    public boolean ValidateQuery() {
        return true;
    }

    private ArrayList<Record> GetDatabases(){
        ArrayList<Record> records = new ArrayList<>();

        File baseData = new File(DatabaseConstants.DEFAULT_DATA_DIRNAME);

        for(File data : baseData.listFiles()){
            if(!data.isDirectory()) continue;
            Record record = Record.CreateRecord();
            record.put("Database", Literal.CreateLiteral(String.format("\"%s\"", data.getName())));
            records.add(record);
        }

        return records;
    }
}
